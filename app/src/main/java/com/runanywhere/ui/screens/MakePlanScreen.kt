package com.runanywhere.startup_hackathon20.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.runanywhere.startup_hackathon20.ChatViewModel
import com.runanywhere.startup_hackathon20.data.model.PlanForm

@Composable
fun MakePlanScreen(onPlanCreated: (String) -> Unit, vm: ChatViewModel = viewModel()) {
    var from by remember { mutableStateOf("") }
    var to by remember { mutableStateOf("") }
    var startDate by remember { mutableStateOf("") }
    var nights by remember { mutableStateOf("3") }
    var budget by remember { mutableStateOf("50000") }
    var people by remember { mutableStateOf("2") }

    val isLoading by vm.isLoading.collectAsState()
    val modelLoaded by vm.currentModelId.collectAsState()

    Column(Modifier.fillMaxSize().padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Text("Make a Travel Plan", style = MaterialTheme.typography.titleLarge)
        OutlinedTextField(from, { from = it }, label = { Text("From") })
        OutlinedTextField(to, { to = it }, label = { Text("To") })
        OutlinedTextField(startDate, { startDate = it }, label = { Text("Start Date (YYYY-MM-DD)") })
        OutlinedTextField(nights, { nights = it }, label = { Text("Nights") })
        OutlinedTextField(budget, { budget = it }, label = { Text("Budget") })
        OutlinedTextField(people, { people = it }, label = { Text("People") })
        Button(onClick = {
            val form = PlanForm(from, to, startDate, nights.toInt(), budget.toInt(), people.toInt())
            vm.generatePlanFromForm(form) { id -> onPlanCreated(id) }
        }, enabled = !isLoading && modelLoaded != null, modifier = Modifier.fillMaxWidth()) {
            Text(if (isLoading) "Generating..." else "Generate Plan")
        }
    }
}
