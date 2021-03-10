package com.example.pruebacondorlabs.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pruebacondorlabs.db.model.Teams
import com.example.pruebacondorlabs.repository.MainActivityRepository
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(private val mainActivityRepository: MainActivityRepository) :
    ViewModel() {

    private var successMain: MutableLiveData<Teams>? = MutableLiveData()
    private var errorMain: MutableLiveData<String>? = MutableLiveData<String>()

    fun getSuccessMain(): LiveData<Teams>? {
        return successMain
    }

    fun setSuccessMain(team: Teams?) {
        this.successMain?.value = team
    }

    fun getErrorMain(): LiveData<String>? {
        return errorMain
    }

    fun setErrorMain(message: String?) {
        this.errorMain?.value = message
    }

    fun getTeamsByLeague(league: String) {
        mainActivityRepository.getTeamsByLeague(league).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<Response<Teams>> {
                override fun onSubscribe(d: Disposable) {}

                override fun onError(e: Throwable) {
                    errorMain?.value = e.message
                }

                override fun onSuccess(teams: Response<Teams>) {
                    try {
                        successMain?.value = teams.body()
                    } catch (e: Exception) {
                        errorMain?.value =
                            "El servicio falló, vuelve a intentar"
                    }
                }
            })
    }
}
