package com.example.pruebacondorlabs.view.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pruebacondorlabs.R
import com.example.pruebacondorlabs.db.AppDatabase
import com.example.pruebacondorlabs.util.ViewModelFactory
import com.example.pruebacondorlabs.viewmodel.MainActivityViewModel
import javax.inject.Inject

class DetailActivity : AppCompatActivity() {

    var appDatabase: AppDatabase? = null
        @Inject set
    var viewModelFactory: ViewModelFactory? = null
        @Inject set
    var mainActivityViewModel: MainActivityViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
    }
}