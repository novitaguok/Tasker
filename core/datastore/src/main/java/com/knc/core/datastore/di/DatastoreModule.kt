package com.knc.core.datastore.di

import com.knc.core.datastore.TaskLocalDataSource
import com.knc.core.datastore.TaskLocalDataSourceImpl
import org.koin.dsl.module

val datastoreModule = module {
    single<TaskLocalDataSource> { TaskLocalDataSourceImpl(get()) }
}
