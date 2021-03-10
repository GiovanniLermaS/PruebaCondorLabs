package com.example.pruebacondorlabs.util

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.example.pruebacondorlabs.R

const val BASE_URL_RETROFIT = "https://www.thesportsdb.com/api/v1/json/1/"
const val ALL_TEAMS_BY_LEAGUE = "search_all_teams.php"
const val LEAGUE = "l"
const val SPANISH_LA_LIGA = "Spanish La Liga"
const val TEAM = "team"

var dialog: Dialog? = null

fun hasNetwork(context: Context): Boolean? {
    var isConnected: Boolean? = false
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
    if (activeNetwork != null && activeNetwork.isConnected)
        isConnected = true
    return isConnected
}

fun showProgress(context: Context, isAlertInit: Boolean) {
    if (isAlertInit) {
        val builder = AlertDialog.Builder(context)
        builder.setView(R.layout.progress)
        builder.setCancelable(false)
        dialog = builder.create()
    }
    if (isAlertInit) {
        try {
            dialog?.show()
        } catch (e: Exception) {
            showProgress(context, isAlertInit = true)
        }
    } else dialog?.dismiss()
}

