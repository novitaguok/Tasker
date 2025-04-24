@file:OptIn(ExperimentalTime::class)

package com.knc.feature.home.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.knc.core.model.Task
import com.knc.core.model.TaskStatus
import com.knc.feature.home.databinding.ItemTaskBinding
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import kotlin.time.ExperimentalTime
import com.knc.core.common.R as CommonResource

class TaskAdapter(
    private val onItemClick: (Int) -> Unit
) : ListAdapter<Task, TaskAdapter.TaskViewHolder>(TaskDiffCallback()) {

    private var onItemLongClickListener: ((Int) -> Boolean)? = null

    fun setOnItemLongClickListener(listener: (Int) -> Boolean) {
        onItemLongClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class TaskViewHolder(
        private val binding: ItemTaskBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    getItem(position)?.let { task ->
                        onItemClick(task.id ?: 0)
                    }
                }
            }
            itemView.setOnLongClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemLongClickListener?.invoke(position) ?: false
                } else {
                    false
                }
            }
        }

        fun bind(task: Task) {
            binding.apply {
                val statusText = when (task.status) {
                    TaskStatus.PENDING -> Pair(CommonResource.color.color_pending, null)
                    TaskStatus.COMPLETE -> Pair(
                        CommonResource.color.color_complete,
                        Paint.STRIKE_THRU_TEXT_FLAG
                    )
                }
                tvTaskTitle.apply {
                    text = task.title
                    paintFlags = statusText.second ?: 0
                }
                tvTaskDesc.apply {
                    text = task.description
                    paintFlags = statusText.second ?: 0
                }
                tvTaskStatus.apply {
                    text = task.status.name
                    setTextColor(ContextCompat.getColor(context, statusText.first))
                }

//                val formattedTime = changeDateFormat(task.timestamp.toString())
//                tvTaskTime.text = formattedTime
            }
        }
    }
}

private class TaskDiffCallback : DiffUtil.ItemCallback<Task>() {
    override fun areItemsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Task, newItem: Task): Boolean {
        return oldItem == newItem
    }
}

fun changeDateFormat(strDate: String): String {
    val offsetDateTime = OffsetDateTime.parse(strDate)
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    val formattedDate = offsetDateTime.format(formatter)
    return formattedDate
}
