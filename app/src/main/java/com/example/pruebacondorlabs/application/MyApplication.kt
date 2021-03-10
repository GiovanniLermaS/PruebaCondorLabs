package com.example.pruebacondorlabs.application

import android.app.Application
import com.example.pruebacondorlabs.di.component.ApplicationComponent
import com.example.pruebacondorlabs.di.component.DaggerApplicationComponent
import com.example.pruebacondorlabs.di.module.ApplicationModule
import com.example.pruebacondorlabs.di.module.DatabaseModule
import com.example.pruebacondorlabs.di.module.RetrofitModule

class MyApplication : Application() {

    var mApplicationComponent: ApplicationComponent? = null

    override fun onCreate() {
        super.onCreate()
        mApplicationComponent = DaggerApplicationComponent
            .builder()
            .applicationModule(ApplicationModule(this))
            .retrofitModule(RetrofitModule(this))
            .databaseModule(DatabaseModule(this))
            .build()
        mApplicationComponent?.inject(this)
    }

    fun getComponent(): ApplicationComponent? {
        return mApplicationComponent
    }
}