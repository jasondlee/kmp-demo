package com.steeplesoft.kmp.demo.clickme

import com.arkivanov.decompose.ComponentContext

interface ClickMeComponent {
}


class ClickMeComponentImpl(componentContext: ComponentContext) : ClickMeComponent,
    ComponentContext by componentContext {

}
