package com.example.pruebacondorlabs.view.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.pruebacondorlabs.R
import com.example.pruebacondorlabs.application.MyApplication
import com.example.pruebacondorlabs.util.ViewModelFactory
import com.example.pruebacondorlabs.util.showProgress
import com.example.pruebacondorlabs.view.favorite.FavoriteActivity
import com.example.pruebacondorlabs.view.main.adapter.TeamAdapter
import com.example.pruebacondorlabs.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener, View.OnClickListener {

    var viewModelFactory: ViewModelFactory? = null
        @Inject set
    var mainActivityViewModel: MainActivityViewModel? = null
    private var leagueSelected: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        (applicationContext as MyApplication).getComponent()?.inject(this)

        mainActivityViewModel =
            ViewModelProviders.of(this, viewModelFactory).get(MainActivityViewModel::class.java)
        spinnerLeague.onItemSelectedListener = this
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        showProgress(this, isAlertInit = true)
        val item = parent!!.getItemAtPosition(position).toString()
        tvNameLeague.text = item
        consumeServiceTeamsByLeague(item)
        leagueSelected = item
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

    override fun onClick(v: View?) {
        if (v?.id == R.id.clMyFavorite)
            startActivity(Intent(this, FavoriteActivity::class.java))
    }

    private fun consumeServiceTeamsByLeague(league: String) {
        if (leagueSelected != league) {
            mainActivityViewModel?.getTeamsByLeague(league)
            mainActivityViewModel?.getSuccessMain()?.observe(this) { listTeamsByLeague ->
                if (listTeamsByLeague != null) {
                    rvListTeams.adapter = TeamAdapter(this, listTeamsByLeague.teams)
                    mainActivityViewModel?.setSuccessMain(null)
                    showProgress(this, isAlertInit = false)
                }
            }
            mainActivityViewModel?.getErrorMain()?.observe(this) { message ->
                if (message != null) {
                    Log.e("Error consume service", message)
                    mainActivityViewModel?.setErrorMain(null)
                    showProgress(this, isAlertInit = false)
                }
            }
        }
    }
}