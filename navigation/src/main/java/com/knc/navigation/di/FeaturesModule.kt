package com.knc.navigation.di
import com.knc.feature.create.createTaskModule
import com.knc.feature.home.homeModule
import org.koin.dsl.module
import kotlin.time.ExperimentalTime

@ExperimentalTime
val featuresModule = module {
    includes(homeModule)
    includes(createTaskModule)
}