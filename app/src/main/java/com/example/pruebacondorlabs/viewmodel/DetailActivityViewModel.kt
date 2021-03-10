package com.example.pruebacondorlabs.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pruebacondorlabs.db.model.Results
import com.example.pruebacondorlabs.repository.DetailActivityRepository
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

class DetailActivityViewModel @Inject constructor(private val detailActivityRepository: DetailActivityRepository) :
    ViewModel() {

    private val successDetail: MutableLiveData<Results> = MutableLiveData()
    private val errorDetail = MutableLiveData<String>()

    fun getSuccessDetail(): LiveData<Results> {
        return successDetail
    }

    fun getErrorDetail(): LiveData<String?> {
        return errorDetail
    }

    fun getEventsByTeamId(id: String) {
        detailActivityRepository.getEventsByTeamId(id).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<Response<Results>> {
                override fun onSubscribe(d: Disposable) {}

                override fun onError(e: Throwable) {
                    errorDetail.value = e.message
                }

                override fun onSuccess(results: Response<Results>) {
                    try {
                        successDetail.value = results.body()
                    } catch (e: Exception) {
                        errorDetail.value =
                            "El servicio falló, vuelve a intentar"
                    }
                }
            })
    }
}
