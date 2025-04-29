package com.knc.feature.home.ui

import android.os.Bundle
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.knc.feature.home.R
import com.knc.feature.home.adapter.TaskAdapter
import com.knc.feature.home.databinding.FragmentHomeBinding
import com.knc.feature.home.navigation.HomeNavigation
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import timber.log.Timber
import kotlin.time.ExperimentalTime


@ExperimentalTime
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModel()
    private val navigation: HomeNavigation by inject { parametersOf(findNavController()) }
    private val taskAdapter = TaskAdapter { taskId ->
        navigation.navigateToTaskDetail(taskId)
    }
    private var contextMenuPosition = RecyclerView.NO_POSITION
    private var selectedTaskPosition: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupFAB()
        setupRecyclerView()
        observeTaskList()
    }

    private fun setupFAB() {
        binding.fabHome.setOnClickListener {
            navigation.navigateToCreateTask()
        }
    }

    private fun setupRecyclerView() {
        binding.rvHome.apply {
            adapter = taskAdapter
            layoutManager = LinearLayoutManager(requireContext())
            registerForContextMenu(this)
            taskAdapter.setOnItemLongClickListener { position ->
                selectedTaskPosition = position
                false // Return false to allow the context menu to show
            }
        }
    }

    private fun observeTaskList() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    when (state) {
                        is HomeUiState.Loading -> {
                            Timber.d("helloooooo loading}")
                            binding.cpiHome.show()
                        }

                        is HomeUiState.Success -> {
                            binding.cpiHome.hide()
                            taskAdapter.submitList(state.data)
                        }

                        is HomeUiState.Error -> {
                            Timber.d("helloooooo error}")
                        }
                    }
                }
            }
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        requireActivity().menuInflater.inflate(R.menu.menu_item_option, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        if (selectedTaskPosition != -1) {
            val task = taskAdapter.currentList[selectedTaskPosition]
            when (item.itemId) {
                R.id.action_delete -> {
                    viewModel.deleteTask(task)
                    return true
                }

                R.id.action_mark_complete -> {
                    viewModel.markComplete(task)
                    return true
                }
            }
        }
        return super.onContextItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}