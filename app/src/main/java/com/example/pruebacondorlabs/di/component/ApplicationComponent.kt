package com.example.pruebacondorlabs.di.component

import android.app.Application
import android.content.Context
import com.example.pruebacondorlabs.application.MyApplication
import com.example.pruebacondorlabs.di.ApplicationContext
import com.example.pruebacondorlabs.di.DatabaseInfo
import com.example.pruebacondorlabs.di.module.ApplicationModule
import com.example.pruebacondorlabs.di.module.DatabaseModule
import com.example.pruebacondorlabs.di.module.RetrofitModule
import com.example.pruebacondorlabs.view.detail.DetailActivity
import com.example.pruebacondorlabs.view.favorite.FavoriteActivity
import com.example.pruebacondorlabs.view.main.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, DatabaseModule::class, RetrofitModule::class])
interface ApplicationComponent {
    fun inject(myApplication: MyApplication?)
    fun inject(mainActivity: MainActivity?)
    fun inject(detailActivity: DetailActivity?)
    fun inject(favoriteActivity: FavoriteActivity?)

    @get:ApplicationContext
    val context: Context?
    val application: Application?

    @get:DatabaseInfo
    val databaseName: String?
}