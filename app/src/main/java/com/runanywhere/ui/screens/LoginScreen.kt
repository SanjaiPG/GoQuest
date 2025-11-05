package com.runanywhere.startup_hackathon20.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.runanywhere.startup_hackathon20.data.DI

// Common country codes
val countryCodes = listOf(
    "+1" to "🇺🇸 USA/Canada",
    "+44" to "🇬🇧 UK",
    "+91" to "🇮🇳 India",
    "+86" to "🇨🇳 China",
    "+81" to "🇯🇵 Japan",
    "+49" to "🇩🇪 Germany",
    "+33" to "🇫🇷 France",
    "+39" to "🇮🇹 Italy",
    "+34" to "🇪🇸 Spain",
    "+61" to "🇦🇺 Australia",
    "+971" to "🇦🇪 UAE",
    "+65" to "🇸🇬 Singapore",
    "+60" to "🇲🇾 Malaysia",
    "+62" to "🇮🇩 Indonesia",
    "+66" to "🇹🇭 Thailand",
    "+82" to "🇰🇷 South Korea",
    "+7" to "🇷🇺 Russia",
    "+55" to "🇧🇷 Brazil",
    "+52" to "🇲🇽 Mexico",
    "+27" to "🇿🇦 South Africa"
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(onLoginSuccess: () -> Unit) {
    val repo = remember { DI.repo }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var isRegisterMode by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    val scrollState = rememberScrollState()

    // Country code selection
    var selectedCountryCode by remember { mutableStateOf("+91") }
    var expandedCountryCode by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        MaterialTheme.colorScheme.primary,
                        MaterialTheme.colorScheme.primaryContainer
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // App Logo/Title
            Text(
                "✈️",
                style = MaterialTheme.typography.displayLarge
            )
            Spacer(Modifier.height(16.dp))
            Text(
                "Travel Planner",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Text(
                "Discover Your Next Adventure",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f)
            )

            Spacer(Modifier.height(48.dp))

            // Login Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        if (isRegisterMode) "Create Account" else "Welcome Back",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(24.dp))

                    // Registration fields (only in register mode)
                    if (isRegisterMode) {
                        OutlinedTextField(
                            value = name,
                            onValueChange = { name = it },
                            label = { Text("Full Name") },
                            placeholder = { Text("John Doe") },
                            leadingIcon = {
                                Icon(Icons.Filled.Person, contentDescription = null)
                            },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp),
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = MaterialTheme.colorScheme.primary,
                                unfocusedBorderColor = MaterialTheme.colorScheme.outline
                            )
                        )
                        Spacer(Modifier.height(16.dp))
                    }

                    // Username Field (for both login and register)
                    OutlinedTextField(
                        value = username,
                        onValueChange = { username = it.lowercase().trim() },
                        label = { Text("Username") },
                        placeholder = { Text("johndoe") },
                        leadingIcon = {
                            Icon(Icons.Filled.Person, contentDescription = null)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outline
                        )
                    )

                    Spacer(Modifier.height(16.dp))

                    // Email Field (only for registration)
                    if (isRegisterMode) {
                        OutlinedTextField(
                            value = email,
                            onValueChange = { email = it.trim() },
                            label = { Text("Email") },
                            placeholder = { Text("john@example.com") },
                            leadingIcon = {
                                Icon(Icons.Filled.Person, contentDescription = null)
                            },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp),
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = MaterialTheme.colorScheme.primary,
                                unfocusedBorderColor = MaterialTheme.colorScheme.outline
                            )
                        )
                        Spacer(Modifier.height(16.dp))
                    }

                    // Password Field
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Password") },
                        placeholder = { Text("Enter password") },
                        leadingIcon = {
                            Icon(Icons.Filled.Lock, contentDescription = null)
                        },
                        trailingIcon = {
                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                Text(
                                    if (passwordVisible) "👁" else "🔒",
                                    style = MaterialTheme.typography.titleMedium
                                )
                            }
                        },
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outline
                        )
                    )

                    // Phone number with country code (registration only)
                    if (isRegisterMode) {
                        Spacer(Modifier.height(16.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            // Country Code Dropdown
                            ExposedDropdownMenuBox(
                                expanded = expandedCountryCode,
                                onExpandedChange = { expandedCountryCode = !expandedCountryCode },
                                modifier = Modifier.weight(0.35f)
                            ) {
                                OutlinedTextField(
                                    value = selectedCountryCode,
                                    onValueChange = {},
                                    readOnly = true,
                                    trailingIcon = {
                                        Icon(
                                            Icons.Filled.ArrowDropDown,
                                            contentDescription = null
                                        )
                                    },
                                    modifier = Modifier.menuAnchor(),
                                    shape = RoundedCornerShape(16.dp),
                                    singleLine = true,
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                                        unfocusedBorderColor = MaterialTheme.colorScheme.outline
                                    )
                                )

                                ExposedDropdownMenu(
                                    expanded = expandedCountryCode,
                                    onDismissRequest = { expandedCountryCode = false }
                                ) {
                                    countryCodes.forEach { (code, label) ->
                                        DropdownMenuItem(
                                            text = { Text("$code $label") },
                                            onClick = {
                                                selectedCountryCode = code
                                                expandedCountryCode = false
                                            }
                                        )
                                    }
                                }
                            }

                            // Phone Number Field
                            OutlinedTextField(
                                value = phone,
                                onValueChange = { phone = it.filter { char -> char.isDigit() } },
                                label = { Text("Phone Number") },
                                placeholder = { Text("1234567890") },
                                leadingIcon = {
                                    Icon(Icons.Filled.Phone, contentDescription = null)
                                },
                                modifier = Modifier.weight(0.65f),
                                shape = RoundedCornerShape(16.dp),
                                singleLine = true,
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = MaterialTheme.colorScheme.primary,
                                    unfocusedBorderColor = MaterialTheme.colorScheme.outline
                                )
                            )
                        }
                    }

                    // Error message
                    if (errorMessage.isNotEmpty()) {
                        Spacer(Modifier.height(16.dp))
                        Text(
                            errorMessage,
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }

                    Spacer(Modifier.height(24.dp))

                    // Login/Register Button
                    Button(
                        onClick = {
                            errorMessage = ""
                            if (isRegisterMode) {
                                // Validation for registration
                                when {
                                    username.isBlank() || password.isBlank() || name.isBlank() || email.isBlank() -> {
                                        errorMessage = "Please fill all required fields"
                                    }
                                    username.length < 3 -> {
                                        errorMessage = "Username must be at least 3 characters"
                                    }

                                    username.contains(" ") -> {
                                        errorMessage = "Username cannot contain spaces"
                                    }

                                    !email.contains("@") || !email.contains(".") -> {
                                        errorMessage = "Please enter a valid email address"
                                    }

                                    password.length < 6 -> {
                                        errorMessage = "Password must be at least 6 characters"
                                    }

                                    phone.isNotBlank() && phone.length < 10 -> {
                                        errorMessage = "Please enter a valid phone number"
                                    }

                                    else -> {
                                        val success = repo.registerUser(
                                            username = username,
                                            password = password,
                                            name = name,
                                            email = email,
                                            countryCode = selectedCountryCode,
                                            phone = phone
                                        )
                                        if (success) {
                                            // Registration successful, now switch to login mode
                                            isRegisterMode = false
                                            password = "" // Clear password for security
                                            errorMessage = ""
                                            // Show success message
                                            errorMessage =
                                                "✅ Registration successful! Please login."
                                        } else {
                                            errorMessage =
                                                "Username '$username' is already taken. Please choose another."
                                        }
                                    }
                                }
                            } else {
                                // Validation for login
                                if (username.isBlank() || password.isBlank()) {
                                    errorMessage = "Please enter username and password"
                                } else {
                                    val success = repo.loginUser(username, password)
                                    if (success) {
                                        onLoginSuccess()
                                    } else {
                                        errorMessage = "Invalid username or password"
                                    }
                                }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Text(
                            if (isRegisterMode) "Register" else "Login",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(Modifier.height(16.dp))

                    // Toggle between Login/Register
                    TextButton(
                        onClick = {
                            isRegisterMode = !isRegisterMode
                            errorMessage = ""
                        }
                    ) {
                        Text(
                            if (isRegisterMode) "Already have an account? Login" else "Don't have an account? Register",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }

            Spacer(Modifier.height(24.dp))
        }
    }
}
