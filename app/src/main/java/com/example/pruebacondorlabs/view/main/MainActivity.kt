package com.example.pruebacondorlabs.view.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.pruebacondorlabs.R
import com.example.pruebacondorlabs.application.MyApplication
import com.example.pruebacondorlabs.db.AppDatabase
import com.example.pruebacondorlabs.util.SPANISH_LA_LIGA
import com.example.pruebacondorlabs.util.ViewModelFactory
import com.example.pruebacondorlabs.util.showProgress
import com.example.pruebacondorlabs.view.main.adapter.TeamAdapter
import com.example.pruebacondorlabs.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    var appDatabase: AppDatabase? = null
        @Inject set
    var viewModelFactory: ViewModelFactory? = null
        @Inject set
    var mainActivityViewModel: MainActivityViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showProgress(this, isAlertInit = true)
        setContentView(R.layout.activity_main)
        (applicationContext as MyApplication).getComponent()?.inject(this)

        mainActivityViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(MainActivityViewModel::class.java)
        consumeServiceTeamsByLeague()
    }

    private fun consumeServiceTeamsByLeague() {
        mainActivityViewModel?.getTeamsByLeague(SPANISH_LA_LIGA)
        mainActivityViewModel?.getSuccessMain()?.observe(this) { listTeamsByLeague ->
            rvListTeams.adapter = TeamAdapter(this, listTeamsByLeague.teams)
            showProgress(this, isAlertInit = false)
        }
        mainActivityViewModel?.getErrorMain()?.observe(this) { message ->
            Log.e("Error consume service", message!!)
            showProgress(this, isAlertInit = false)
        }
    }
}