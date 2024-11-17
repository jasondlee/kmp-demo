package com.steeplesoft.kmp.demo.userlist

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.decompose.value.update
import com.steeplesoft.kmp.demo.Status
import com.steeplesoft.kmp.demo.room.User
import com.steeplesoft.kmp.demo.room.db
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

interface UserListComponent {
    var results: List<User>
    var requestStatus : MutableValue<Status>
}

class UserListComponentImpl(componentContext: ComponentContext) : UserListComponent,
    ComponentContext by componentContext {
    override var requestStatus  = MutableValue(Status.LOADING)
    override var results: List<User> = listOf()

    init {
        CoroutineScope(Dispatchers.IO).launch {
            val userDao = db.userDao()
            if (userDao.getAll().isEmpty()) {
                userDao.insertAll(User(1, "Fenton", "Hardy"))
                userDao.insertAll(User(2, "Frank", "Hardy"))
                userDao.insertAll(User(3, "Joe", "Hardy"))
            }

            results = userDao.getAll()
            requestStatus.update { Status.SUCCESS }
        }
    }
}
