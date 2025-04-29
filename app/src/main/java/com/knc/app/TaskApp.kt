package com.knc.app

import android.app.Application
import com.knc.app.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import timber.log.Timber
import kotlin.time.ExperimentalTime

@ExperimentalTime
class TaskApp : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        startKoin {
            androidLogger()
            androidContext(this@TaskApp)
            modules(appModule)
        }
    }
}
