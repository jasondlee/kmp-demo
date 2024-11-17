package com.steeplesoft.kmp.demo

import androidx.room.RoomDatabase
import com.steeplesoft.kmp.demo.room.AppDatabase

expect fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase>
