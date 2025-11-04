package com.runanywhere.startup_hackathon20.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.unit.dp
import com.runanywhere.startup_hackathon20.data.DI

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun PlanResultScreen(planId: String) {
    val plan = remember { DI.repo.getPlan(planId) }

    if (plan == null) {
        Box(Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
            Text("Plan not found")
        }
        return
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text(plan.title) }) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Box(modifier = Modifier.padding(16.dp).weight(1f)) {
                AndroidView(factory = { context ->
                    android.widget.TextView(context).apply {
                        // show the raw markdown as plain text (avoids needing an extra dependency)
                        text = plan.markdownItinerary
                    }
                }, modifier = Modifier.fillMaxSize())
            }
        }
    }
}
