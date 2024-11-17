package com.steeplesoft.kmp.demo

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.steeplesoft.kmp.demo.root.RootComponent
import com.steeplesoft.kmp.demo.root.RootContent
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(rootComponent: RootComponent) {
    MaterialTheme {
        RootContent(rootComponent)
    }
}
