package com.steeplesoft.kmp.demo

import android.os.Build
import com.steeplesoft.kmp.demo.Platform

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()
