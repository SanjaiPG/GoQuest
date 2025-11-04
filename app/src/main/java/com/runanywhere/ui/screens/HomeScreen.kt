package com.runanywhere.startup_hackathon20.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.runanywhere.startup_hackathon20.data.DI

@Composable
fun HomeScreen(onOpenDestination: (String) -> Unit) {
    val repo = remember { DI.repo }
    var query by remember { mutableStateOf(TextFieldValue("")) }
    val all = remember { repo.getPopularDestinations() }
    val filtered = all.filter { it.name.contains(query.text, true) || it.country.contains(query.text, true) }

    Column(Modifier.fillMaxSize()) {
        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            placeholder = { Text("Search destinations") }
        )

        // Placeholder map area (removed Google Maps dependency)
        Box(Modifier.fillMaxWidth().height(220.dp).padding(horizontal = 16.dp).background(Color.LightGray), contentAlignment = androidx.compose.ui.Alignment.Center) {
            Text("Map preview")
        }

        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.weight(1f)
        ) {
            items(filtered) { d ->
                Card(onClick = { onOpenDestination(d.id) }) {
                    Row(Modifier.padding(12.dp)) {
                        // Image placeholder (removed Coil)
                        Box(modifier = Modifier.size(72.dp).background(Color.Gray))
                        Spacer(Modifier.width(12.dp))
                        Column {
                            Text(d.name, style = MaterialTheme.typography.titleMedium)
                            Text(d.country, style = MaterialTheme.typography.bodySmall)
                        }
                    }
                }
            }
        }
    }
}
