package com.knc.navigation.di

import androidx.navigation.NavController
import com.knc.feature.home.navigation.HomeNavigation
import com.knc.navigation.HomeNavigationImpl
import org.koin.dsl.module

val navigationModule = module {
    factory { (navController: NavController) ->
        HomeNavigationImpl(navController) as HomeNavigation
    }
}
