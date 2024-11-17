package com.steeplesoft.kmp.demo

import androidx.room.Room
import androidx.room.RoomDatabase
import com.steeplesoft.kmp.demo.room.AppDatabase
import com.steeplesoft.kmp.demo.room.dbFileName

actual fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
    val context = AppContext.get()
    return Room.databaseBuilder<AppDatabase>(context,
        context.getDatabasePath(dbFileName).absolutePath)
}
