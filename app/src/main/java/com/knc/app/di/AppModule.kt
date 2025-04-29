package com.knc.app.di

import com.knc.core.common.dispatchers.di.dispatchersModule
import com.knc.core.data.di.dataModule
import com.knc.core.database.di.databaseModule
import com.knc.core.datastore.di.datastoreModule
import com.knc.core.domain.di.domainModule
import com.knc.navigation.di.featuresModule
import com.knc.navigation.di.navigationModule
import kotlin.time.ExperimentalTime

@ExperimentalTime
val appModule = listOf(
    dispatchersModule,
    databaseModule,
    datastoreModule,
    dataModule,
    domainModule,
    featuresModule,
    navigationModule
)