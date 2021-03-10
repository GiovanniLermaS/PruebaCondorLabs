package com.example.pruebacondorlabs.util

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.example.pruebacondorlabs.R
import java.text.NumberFormat

const val BASE_URL_RETROFIT = "https://api.mercadolibre.com"
const val SITES = "/sites"
const val ITEMS = "/items"
const val SEARCH_BY_SITE = "/search?q="
const val COP = "COP"
const val WORD_SEARCH = "wordSearch"
const val ID_SITE = "idSite"
const val IDS = "ids"
const val IDS_INTENT = "ids"

var dialog: Dialog? = null

var formatter: NumberFormat = NumberFormat.getCurrencyInstance()

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

