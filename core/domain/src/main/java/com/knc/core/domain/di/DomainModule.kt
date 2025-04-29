package com.knc.core.domain.di

import com.knc.core.domain.CreateTaskUseCase
import com.knc.core.domain.DeleteTaskUseCase
import com.knc.core.domain.GetAllTaskUseCase
import com.knc.core.domain.UpdateTaskStatusUseCase
import org.koin.dsl.module
import kotlin.time.ExperimentalTime

@ExperimentalTime
val domainModule = module{
    single<GetAllTaskUseCase> { GetAllTaskUseCase(get()) }
    single<CreateTaskUseCase> { CreateTaskUseCase(get()) }
    single<DeleteTaskUseCase> { DeleteTaskUseCase(get()) }
    single<UpdateTaskStatusUseCase> { UpdateTaskStatusUseCase(get()) }
}