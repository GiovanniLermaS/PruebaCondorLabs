package com.example.pruebacondorlabs.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pruebacondorlabs.db.model.Team

@Dao
interface TeamDao {

    @Query("SELECT * FROM Team WHERE idTeam = :idTeam")
    suspend fun getTeamById(idTeam: Int): Team

    @Query("SELECT * FROM Team")
    suspend fun getTeams(): List<Team>

    @Query("DELETE FROM Team WHERE idTeam=:idTeam")
    suspend fun deleteTeamById(idTeam: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setTeam(team: Team?): Long
}