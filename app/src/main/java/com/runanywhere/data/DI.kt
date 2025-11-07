package com.runanywhere.startup_hackathon20.data

object DI {
    val repo by lazy { Repository() }   // ✅ use Firebase-enabled Repository
}
