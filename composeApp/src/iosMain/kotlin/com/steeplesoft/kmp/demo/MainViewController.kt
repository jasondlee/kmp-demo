package com.steeplesoft.kmp.demo

import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.ApplicationLifecycle
import com.steeplesoft.kmp.demo.root.RootComponentImpl

fun MainViewController() = ComposeUIViewController {
    val rootComponent = remember {
        RootComponentImpl(DefaultComponentContext(ApplicationLifecycle()))
    }
    App(rootComponent)
}
