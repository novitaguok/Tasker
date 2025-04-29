@file:OptIn(ExperimentalTime::class)

package com.knc.feature.home

import com.knc.core.common.dispatchers.DispatchersType
import com.knc.feature.home.ui.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import kotlin.time.ExperimentalTime

val homeModule = module {
    viewModel {
        HomeViewModel(
            get(),
            get(),
            get(),
            get(named(DispatchersType.IO.name))
        )
    }
}
