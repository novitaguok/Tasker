package com.knc.core.database.di

import android.content.Context
import androidx.room.Room
import com.knc.core.database.TaskDb
import com.knc.core.database.dao.TaskDao
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single<TaskDb> { providesDb(androidContext()) }
    single<TaskDao> { providesTaskDao(get()) }
}

fun providesDb(context: Context): TaskDb {
    return Room.databaseBuilder(
        context = context,
        klass = TaskDb::class.java,
        name = "task.db"
    ).build()
}

fun providesTaskDao(taskDb: TaskDb): TaskDao {
    return taskDb.getTaskDao()
}