package com.runanywhere.startup_hackathon20.ui.screens

import android.webkit.WebView
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.unit.dp
import com.runanywhere.startup_hackathon20.data.DI

@Composable
fun DestinationScreen(destinationId: String, onMakePlan: () -> Unit) {
    val repo = remember { DI.repo }
    val dest = remember { repo.getDestination(destinationId) } ?: return

    Column(Modifier.fillMaxSize()) {
        // Placeholder for image (removed Coil usage to avoid adding a dependency)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = dest.name, style = MaterialTheme.typography.titleLarge)
        }

        Column(Modifier.padding(16.dp)) {
            Text("${dest.name}, ${dest.country}", style = MaterialTheme.typography.titleLarge)
            Spacer(Modifier.height(8.dp))
            Text("Currency: ${dest.currencyCode}")
            Spacer(Modifier.height(16.dp))
            Text("YouTube Vlog", style = MaterialTheme.typography.titleSmall)
            YouTubeEmbed(videoId = "dQw4w9WgXcQ")
            Spacer(Modifier.height(16.dp))
            Button(onClick = onMakePlan, modifier = Modifier.fillMaxWidth()) {
                Text("Make Plan")
            }
        }
    }
}

@Composable
fun YouTubeEmbed(videoId: String) {
    AndroidView(factory = { ctx ->
        WebView(ctx).apply {
            // Load the embed URL directly; avoid enabling JavaScript to remove lint warnings
            loadUrl("https://www.youtube.com/embed/$videoId")
        }
    }, modifier = Modifier.fillMaxWidth().height(200.dp))
}
