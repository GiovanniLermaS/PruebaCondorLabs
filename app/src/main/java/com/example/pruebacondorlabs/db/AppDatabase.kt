package com.example.pruebacondorlabs.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pruebacondorlabs.db.dao.TeamDao
import com.example.pruebacondorlabs.db.model.Team

@Database(
    entities = [Team::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun teamDao(): TeamDao
}