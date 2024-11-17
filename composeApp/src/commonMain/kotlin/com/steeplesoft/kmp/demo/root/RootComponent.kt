package com.steeplesoft.kmp.demo.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.steeplesoft.kmp.demo.AppContext
import com.steeplesoft.kmp.demo.clickme.ClickMeComponent
import com.steeplesoft.kmp.demo.clickme.ClickMeComponentImpl
import com.steeplesoft.kmp.demo.room.db
import com.steeplesoft.kmp.demo.room.getRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

interface RootComponent {
    val stack: Value<ChildStack<*, Child>>

    sealed interface Child {
        class ClickMe(val component: ClickMeComponent) : Child
    }
}

class RootComponentImpl(componentContext: ComponentContext) :
    RootComponent, ComponentContext by componentContext {
    private val nav = StackNavigation<NavigationConfig>()

    override val stack: Value<ChildStack<*, RootComponent.Child>> = childStack(
        source = nav,
        serializer = NavigationConfig.serializer(),
        initialConfiguration = NavigationConfig.ClickMe,
        handleBackButton = true,
        childFactory = ::child,
    )

    private fun child(
        config: NavigationConfig,
        componentContext: ComponentContext
    ): RootComponent.Child {
        return when (config) {
            is NavigationConfig.ClickMe ->
                RootComponent.Child.ClickMe(ClickMeComponentImpl(componentContext))
        }
    }
}

@Serializable
sealed interface NavigationConfig {
    @Serializable
    data object ClickMe : NavigationConfig
}
