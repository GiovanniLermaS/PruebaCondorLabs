package com.example.pruebacondorlabs.di.module

import android.content.Context
import androidx.room.Room
import com.example.pruebacondorlabs.db.AppDatabase
import com.example.pruebacondorlabs.di.ApplicationContext
import com.example.pruebacondorlabs.di.DatabaseInfo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule(@ApplicationContext val context: Context) {
    @DatabaseInfo
    private val dbName = "sites"

    @Singleton
    @Provides
    fun provideDatabase(): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            dbName
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @DatabaseInfo
    fun provideDatabaseName(): String {
        return dbName
    }
}