package com.runanywhere.startup_hackathon20.ui

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import com.runanywhere.startup_hackathon20.ChatViewModel
import com.runanywhere.startup_hackathon20.ui.navigation.*
import com.runanywhere.startup_hackathon20.ui.screens.*

@Suppress("unused")
@Composable
fun AppRoot() {
    // Simple state-based navigator (avoids androidx.navigation dependency in static checks)
    var currentRoute by remember { mutableStateOf(AppRoute.Home.route) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                val current = currentRoute
                bottomItems.forEach { item ->
                    NavigationBarItem(
                        selected = current == item.route,
                        onClick = { currentRoute = item.route },
                        icon = { Icon(item.icon, contentDescription = null) },
                        label = { Text(item.label) }
                    )
                }
            }
        }
    ) { padding ->
        // Apply Scaffold padding so the content isn't obscured by bars
        Box(modifier = Modifier.padding(padding).fillMaxSize()) {
            when {
                currentRoute == AppRoute.Home.route -> {
                    HomeScreen(onOpenDestination = { id ->
                        currentRoute = "${AppRoute.Destination.base}/$id"
                    })
                }

                currentRoute == AppRoute.LikedPlans.route -> {
                    LikedPlansScreen(onOpenPlan = { planId ->
                        currentRoute = "${AppRoute.PlanResult.base}/$planId"
                    })
                }

                currentRoute == AppRoute.LikedDestinations.route -> {
                    LikedDestinationsScreen(onOpenDestination = { id ->
                        currentRoute = "${AppRoute.Destination.base}/$id"
                    })
                }

                currentRoute == AppRoute.Chat.route -> {
                    ChatTab()
                }

                currentRoute.startsWith("${AppRoute.Destination.base}/") -> {
                    val id = currentRoute.substringAfter("${AppRoute.Destination.base}/")
                    DestinationScreen(destinationId = id, onMakePlan = {
                        currentRoute = AppRoute.MakePlan.route
                    })
                }

                currentRoute == AppRoute.MakePlan.route -> {
                    MakePlanScreen(onPlanCreated = { planId ->
                        currentRoute = "${AppRoute.PlanResult.base}/$planId"
                    })
                }

                currentRoute.startsWith("${AppRoute.PlanResult.base}/") -> {
                    val planId = currentRoute.substringAfter("${AppRoute.PlanResult.base}/")
                    PlanResultScreen(planId = planId)
                }
            }
        }
    }
}

@Composable
private fun ChatTab(viewModel: ChatViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    com.runanywhere.startup_hackathon20.ChatScreen(viewModel)
}
