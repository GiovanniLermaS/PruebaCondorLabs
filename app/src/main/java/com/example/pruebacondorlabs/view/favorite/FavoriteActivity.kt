package com.example.pruebacondorlabs.view.favorite

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.pruebacondorlabs.R
import com.example.pruebacondorlabs.application.MyApplication
import com.example.pruebacondorlabs.db.AppDatabase
import com.example.pruebacondorlabs.db.model.Team
import com.example.pruebacondorlabs.view.main.adapter.TeamAdapter
import kotlinx.android.synthetic.main.activity_favorite.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteActivity : AppCompatActivity() {

    var appDatabase: AppDatabase? = null
        @Inject set

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        (applicationContext as MyApplication).getComponent()?.inject(this)
        addOrDeleteDatabase()
    }

    override fun onResume() {
        super.onResume()
        addOrDeleteDatabase()
    }

    private fun addOrDeleteDatabase() {
        lifecycleScope.launch {
            val teams = appDatabase?.teamDao()?.getTeams()
            if (teams.isNullOrEmpty()) {
                tvNoItemsFavorite.visibility = View.VISIBLE
            } else {
                teams as ArrayList<Team>
                rvListTeamsFavorite.adapter = TeamAdapter(this@FavoriteActivity, teams)
            }
        }
    }
}