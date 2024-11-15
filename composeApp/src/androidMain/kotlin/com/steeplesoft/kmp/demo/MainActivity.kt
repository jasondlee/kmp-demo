package com.steeplesoft.kmp.demo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.arkivanov.decompose.defaultComponentContext
import com.steeplesoft.kmp.demo.App
import com.steeplesoft.kmp.demo.root.RootComponentImpl

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Always create the root component outside Compose on the main thread
        val rootComponent = RootComponentImpl(defaultComponentContext())

        setContent {
            App(rootComponent = rootComponent)
        }
    }
}

