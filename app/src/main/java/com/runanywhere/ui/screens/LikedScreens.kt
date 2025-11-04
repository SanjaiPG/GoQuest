package com.runanywhere.startup_hackathon20.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.runanywhere.startup_hackathon20.data.DI

@Composable
fun LikedPlansScreen(onOpenPlan: (String) -> Unit) {
    val plans = remember { DI.repo.getLikedPlans() }
    LazyColumn(contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        items(plans) { p ->
            Card(Modifier.fillMaxWidth().clickable { onOpenPlan(p.id) }) {
                Column(Modifier.padding(12.dp)) {
                    Text(p.title, style = MaterialTheme.typography.titleMedium)
                }
            }
        }
    }
}

@Composable
fun LikedDestinationsScreen(onOpenDestination: (String) -> Unit) {
    val places = remember { DI.repo.getLikedDestinations() }
    LazyColumn(contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        items(places) { d ->
            Card(Modifier.fillMaxWidth().clickable { onOpenDestination(d.id) }) {
                Column(Modifier.padding(12.dp)) {
                    Text(d.name, style = MaterialTheme.typography.titleMedium)
                    Text(d.country, style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}
