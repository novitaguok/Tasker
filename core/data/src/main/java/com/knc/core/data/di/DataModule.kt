package com.knc.core.data.di

import com.knc.core.data.repository.TaskRepository
import com.knc.core.data.repository.TaskRepositoryImpl
import org.koin.dsl.module
import kotlin.time.ExperimentalTime

@ExperimentalTime
val dataModule = module {
    single<TaskRepository> { TaskRepositoryImpl(get()) }
}