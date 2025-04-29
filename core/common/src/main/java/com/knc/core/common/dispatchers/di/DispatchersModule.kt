package com.knc.core.common.dispatchers.di

import com.knc.core.common.dispatchers.DispatchersType
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dispatchersModule = module {
    single (named(DispatchersType.IO.name)) {providesDispatchersIO()}
    single (named(DispatchersType.MAIN.name)) {providesDispatchersMain()}
}

fun providesDispatchersIO() : CoroutineDispatcher {
    return Dispatchers.IO
}

fun providesDispatchersMain() : CoroutineDispatcher {
    return Dispatchers.Main
}