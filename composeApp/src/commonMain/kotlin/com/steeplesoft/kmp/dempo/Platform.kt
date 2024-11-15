package com.steeplesoft.kmp.dempo

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform