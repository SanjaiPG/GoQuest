package com.runanywhere.startup_hackathon20.data

import com.runanywhere.startup_hackathon20.data.model.*

class TravelRepository {
    private val destinations = listOf(
        Destination("paris","Paris","France",48.8566,2.3522,"https://picsum.photos/600/400?1","EUR"),
        Destination("tokyo","Tokyo","Japan",35.6762,139.6503,"https://picsum.photos/600/400?2","JPY"),
        Destination("bali","Bali","Indonesia",-8.3405,115.0920,"https://picsum.photos/600/400?3","IDR"),
    )

    private val likedDestinations = mutableSetOf<String>()
    private val plans = mutableMapOf<String, Plan>()
    private val likedPlans = mutableSetOf<String>()

    fun getPopularDestinations(): List<Destination> = destinations
    fun getDestination(id: String): Destination? = destinations.find { it.id == id }

    fun likeDestination(id: String) { likedDestinations += id }
    fun getLikedDestinations(): List<Destination> = likedDestinations.mapNotNull { getDestination(it) }

    fun savePlan(plan: Plan) { plans[plan.id] = plan }
    fun getPlan(planId: String): Plan? = plans[planId]
    fun likePlan(planId: String) { likedPlans += planId }
    fun getLikedPlans(): List<Plan> = likedPlans.mapNotNull { plans[it] }
}
