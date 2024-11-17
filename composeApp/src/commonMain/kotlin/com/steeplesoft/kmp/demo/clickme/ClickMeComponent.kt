package com.steeplesoft.kmp.demo.clickme

import com.arkivanov.decompose.ComponentContext
import com.steeplesoft.kmp.demo.room.User
import com.steeplesoft.kmp.demo.room.db
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

interface ClickMeComponent {
}


class ClickMeComponentImpl(componentContext: ComponentContext) : ClickMeComponent,
    ComponentContext by componentContext {

    init {
        CoroutineScope(Dispatchers.IO).launch {
            val userDao = db.userDao()
            userDao.insertAll(User(2, "Jason", "Lee"))
            val all = userDao.getAll()
            println("users = " + all)
        }
    }
}
