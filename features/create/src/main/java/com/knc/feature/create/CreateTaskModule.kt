@file:OptIn(ExperimentalTime::class)

package com.knc.feature.create

import com.knc.core.common.dispatchers.DispatchersType
import com.knc.feature.create.ui.CreateTaskViewModel
import kotlinx.coroutines.FlowPreview
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import kotlin.time.ExperimentalTime

@FlowPreview
val createTaskModule = module {
    viewModel {
        CreateTaskViewModel(
            get(),
            get(
                named(DispatchersType.IO.name)
            )
        )
    }
}
