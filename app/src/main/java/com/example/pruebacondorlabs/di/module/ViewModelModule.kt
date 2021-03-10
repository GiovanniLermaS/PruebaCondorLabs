package com.example.pruebacondorlabs.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pruebacondorlabs.di.util.ViewModelKey
import com.example.pruebacondorlabs.util.ViewModelFactory
import com.example.pruebacondorlabs.viewmodel.DetailActivityViewModel
import com.example.pruebacondorlabs.viewmodel.MainActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun bindMainActivityViewModel(mainActivityViewModel: MainActivityViewModel?): ViewModel?

    @Binds
    @IntoMap
    @ViewModelKey(DetailActivityViewModel::class)
    abstract fun bindDetailActivityViewModel(detailActivityViewModel: DetailActivityViewModel?): ViewModel?

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory?): ViewModelProvider.Factory?
}