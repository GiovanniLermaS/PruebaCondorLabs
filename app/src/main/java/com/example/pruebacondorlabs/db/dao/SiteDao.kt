package com.example.pruebacondorlabs.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pruebacondorlabs.db.model.Site

@Dao
interface SiteDao {

    @Query("SELECT * FROM Site WHERE default_currency_id = :default_currency_id")
    suspend fun getSiteById(default_currency_id: Int): Site

    @Query("SELECT * FROM Site")
    suspend fun getSites(): List<Site>

    @Query("DELETE FROM Site WHERE default_currency_id=:default_currency_id")
    suspend fun deleteSiteById(default_currency_id: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setSite(movie: Site?): Long
}