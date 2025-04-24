package com.knc.feature.create.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.knc.feature.create.databinding.FragmentCreateTaskBinding
import kotlinx.coroutines.FlowPreview
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.time.ExperimentalTime

@FlowPreview
@ExperimentalTime
class CreateTaskFragment : Fragment() {
    private var _binding: FragmentCreateTaskBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CreateTaskViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCreateTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTextInputObserver()
        onAddTask()
    }

    private fun setupTextInputObserver() {
        binding.apply {
            tilTitle.editText?.addTextChangedListener(InputTextWatcher { text ->
                viewModel.updateTitleTextInput(text)
            })
            tilDesc.editText?.addTextChangedListener(InputTextWatcher { text ->
                viewModel.updateDescTextInput(text)
            })
        }
    }

    class InputTextWatcher(private val onTextChanged: (String) -> Unit) : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            onTextChanged(s?.toString() ?: "")
        }

        override fun afterTextChanged(s: Editable?) {}
    }

    private fun onAddTask() {
        binding.btnCreateTask.setOnClickListener {
            viewModel.createTask()
            findNavController().navigateUp()
        }
    }
}