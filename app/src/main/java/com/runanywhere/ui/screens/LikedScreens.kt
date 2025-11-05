package com.runanywhere.startup_hackathon20.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.runanywhere.startup_hackathon20.data.DI

@Composable
fun LikedPlansScreen(onOpenPlan: (String) -> Unit) {
    val plans = remember { DI.repo.getLikedPlans() }
    val unlikedPlans = remember { mutableStateSetOf<String>() }

    Column(
        Modifier
            .fillMaxSize()
            .background(Color(0xFFF0F9FF))
    ) {
        // Header with gradient
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.surface
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.linearGradient(
                            colors = listOf(
                                Color(0xFF0EA5E9),
                                Color(0xFF3B82F6)
                            )
                        )
                    )
            ) {
                Column(Modifier.padding(24.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(56.dp)
                                .background(Color.White.copy(alpha = 0.9f), CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                Icons.Filled.Favorite,
                                contentDescription = null,
                                tint = Color(0xFF3B82F6),
                                modifier = Modifier.size(32.dp)
                            )
                        }
                        Column {
                            Text(
                                "My Cart ",
                                style = MaterialTheme.typography.headlineLarge,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Text(
                                "${plans.filter { !unlikedPlans.contains(it.id) }.size} itineraries saved",
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color.White.copy(alpha = 0.95f)
                            )
                        }
                    }
                }
            }
        }

        if (plans.isEmpty() || plans.all { unlikedPlans.contains(it.id) }) {
            // Empty state
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(120.dp)
                            .background(
                                Color(0xFF3B82F6).copy(alpha = 0.1f),
                                CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Filled.Favorite,
                            contentDescription = null,
                            modifier = Modifier.size(64.dp),
                            tint = Color(0xFF3B82F6).copy(alpha = 0.5f)
                        )
                    }
                    Text(
                        "No saved plans yet",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1F2937)
                    )
                    Text(
                        "Start planning your adventures!",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color(0xFF6B7280)
                    )
                }
            }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(plans.filter { !unlikedPlans.contains(it.id) }) { p ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onOpenPlan(p.id) },
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        ),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 4.dp,
                            pressedElevation = 8.dp
                        )
                    ) {
                        Box(modifier = Modifier.fillMaxWidth()) {
                            Row(
                                modifier = Modifier.padding(16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                // Icon with gradient background
                                Box(
                                    modifier = Modifier
                                        .size(80.dp)
                                        .clip(RoundedCornerShape(16.dp))
                                        .background(
                                            Brush.linearGradient(
                                                colors = listOf(
                                                    Color(0xFF0EA5E9),
                                                    Color(0xFF3B82F6)
                                                )
                                            )
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        "",
                                        style = MaterialTheme.typography.displayMedium
                                    )
                                }

                                Spacer(Modifier.width(16.dp))

                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        p.title,
                                        style = MaterialTheme.typography.titleLarge,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFF1F2937)
                                    )
                                    Spacer(Modifier.height(6.dp))
                                    Text(
                                        "Tap to view full itinerary",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = Color(0xFF6B7280)
                                    )
                                    Spacer(Modifier.height(8.dp))
                                    Surface(
                                        color = Color(0xFFDBEAFE),
                                        shape = RoundedCornerShape(10.dp)
                                    ) {
                                        Text(
                                            "Saved Plan",
                                            modifier = Modifier.padding(
                                                horizontal = 12.dp,
                                                vertical = 6.dp
                                            ),
                                            style = MaterialTheme.typography.labelMedium,
                                            color = Color(0xFF1E40AF),
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                            }

                            // Wishlist Button in Top Right Corner
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp),
                                contentAlignment = Alignment.TopEnd
                            ) {
                                IconButton(
                                    onClick = {
                                        unlikedPlans.add(p.id)
                                    },
                                    modifier = Modifier
                                        .size(48.dp)
                                        .background(
                                            Color.White.copy(alpha = 0.95f),
                                            CircleShape
                                        )
                                ) {
                                    Icon(
                                        Icons.Filled.Favorite,
                                        contentDescription = "Remove from Cart",
                                        tint = Color(0xFF3B82F6),
                                        modifier = Modifier.size(24.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LikedDestinationsScreen(onOpenDestination: (String) -> Unit) {
    val places = remember { DI.repo.getLikedDestinations() }
    val unlikedPlaces = remember { mutableStateSetOf<String>() }

    Column(Modifier
        .fillMaxSize()
        .background(Color(0xFFF0F9FF))
    ) {
        // Header with gradient
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.surface
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.linearGradient(
                            colors = listOf(
                                Color(0xFF0EA5E9),
                                Color(0xFF3B82F6)
                            )
                        )
                    )
            ) {
                Column(Modifier.padding(24.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(56.dp)
                                .background(Color.White.copy(alpha = 0.9f), CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                Icons.Filled.LocationOn,
                                contentDescription = null,
                                tint = Color(0xFF0EA5E9),
                                modifier = Modifier.size(32.dp)
                            )
                        }
                        Column {
                            Text(
                                "Wishlist ",
                                style = MaterialTheme.typography.headlineLarge,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Text(
                                "${places.filter { !unlikedPlaces.contains(it.id) }.size} destinations saved",
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color.White.copy(alpha = 0.95f)
                            )
                        }
                    }
                }
            }
        }

        if (places.isEmpty() || places.all { unlikedPlaces.contains(it.id) }) {
            // Empty state
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(120.dp)
                            .background(
                                Color(0xFF0EA5E9).copy(alpha = 0.1f),
                                CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Filled.LocationOn,
                            contentDescription = null,
                            modifier = Modifier.size(64.dp),
                            tint = Color(0xFF0EA5E9).copy(alpha = 0.5f)
                        )
                    }
                    Text(
                        "No saved places yet",
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1F2937)
                    )
                    Text(
                        "Discover amazing destinations!",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color(0xFF6B7280)
                    )
                }
            }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(places.filter { !unlikedPlaces.contains(it.id) }) { d ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onOpenDestination(d.id) },
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        ),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 4.dp,
                            pressedElevation = 8.dp
                        )
                    ) {
                        Box(modifier = Modifier.fillMaxWidth()) {
                            Row(
                                modifier = Modifier.padding(16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                // Location icon with gradient background
                                Box(
                                    modifier = Modifier
                                        .size(80.dp)
                                        .clip(RoundedCornerShape(16.dp))
                                        .background(
                                            Brush.linearGradient(
                                                colors = listOf(
                                                    Color(0xFF0EA5E9),
                                                    Color(0xFF3B82F6),
                                                    Color(0xFF2563EB)
                                                )
                                            )
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        d.name.take(2).uppercase(),
                                        style = MaterialTheme.typography.headlineLarge,
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold
                                    )
                                }

                                Spacer(Modifier.width(16.dp))

                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        d.name,
                                        style = MaterialTheme.typography.titleLarge,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFF1F2937)
                                    )
                                    Spacer(Modifier.height(6.dp))
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                                    ) {
                                        Icon(
                                            Icons.Filled.LocationOn,
                                            contentDescription = null,
                                            modifier = Modifier.size(18.dp),
                                            tint = Color(0xFF6B7280)
                                        )
                                        Text(
                                            d.country,
                                            style = MaterialTheme.typography.bodyLarge,
                                            color = Color(0xFF6B7280)
                                        )
                                    }
                                    Spacer(Modifier.height(8.dp))
                                    Surface(
                                        color = Color(0xFFDBEAFE),
                                        shape = RoundedCornerShape(10.dp)
                                    ) {
                                        Row(
                                            modifier = Modifier.padding(
                                                horizontal = 12.dp,
                                                vertical = 6.dp
                                            ),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                                        ) {
                                            Text(
                                                "",
                                                style = MaterialTheme.typography.bodyMedium
                                            )
                                            Text(
                                                d.currencyCode,
                                                style = MaterialTheme.typography.labelLarge,
                                                color = Color(0xFF1E40AF),
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                    }
                                }
                            }

                            // Wishlist Button in Top Right Corner
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp),
                                contentAlignment = Alignment.TopEnd
                            ) {
                                IconButton(
                                    onClick = {
                                        unlikedPlaces.add(d.id)
                                    },
                                    modifier = Modifier
                                        .size(48.dp)
                                        .background(
                                            Color.White.copy(alpha = 0.95f),
                                            CircleShape
                                        )
                                ) {
                                    Icon(
                                        Icons.Filled.Favorite,
                                        contentDescription = "Remove from Wishlist",
                                        tint = Color(0xFF3B82F6),
                                        modifier = Modifier.size(24.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
