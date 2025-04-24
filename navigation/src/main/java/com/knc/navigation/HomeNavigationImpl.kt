package com.knc.navigation

import androidx.navigation.NavController
import com.knc.feature.home.navigation.HomeNavigation

class HomeNavigationImpl(private val navController: NavController) : HomeNavigation {
    override fun navigateToCreateTask() {
        navController.navigate(R.id.action_homeFragment_to_createTaskFragment)
    }

    override fun navigateToTaskDetail(taskId: Int) {
//        navController.navigate(
//            R.id.action_homeFragment_to_createTaskFragment,
//            bundleOf("taskId" to taskId)
//        )
    }
}