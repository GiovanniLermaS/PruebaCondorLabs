package com.example.pruebacondorlabs.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pruebacondorlabs.db.dao.SiteDao
import com.example.pruebacondorlabs.db.model.Site

@Database(
    entities = [Site::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun siteDao(): SiteDao
}