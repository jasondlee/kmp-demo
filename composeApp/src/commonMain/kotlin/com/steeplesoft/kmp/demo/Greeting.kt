package com.steeplesoft.kmp.demo

class Greeting {
    private val platform = com.steeplesoft.kmp.demo.getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}
