package com.knc.core.model

import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@ExperimentalTime
data class Task(
    val id: Int? = null,
    val title: String,
    val description: String,
    val status: TaskStatus,
    val timestamp: Instant
)
