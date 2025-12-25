package com.example.note

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform