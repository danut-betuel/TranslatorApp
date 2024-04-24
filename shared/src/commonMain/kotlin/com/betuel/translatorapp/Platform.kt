package com.betuel.translatorapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform