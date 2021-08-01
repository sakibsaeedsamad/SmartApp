package com.sssakib.smartapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sssakib.smartapp.model.AccountModel
import com.sssakib.smartapp.model.InsertModel
import com.sssakib.smartapp.retrofit.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class AccountViewModel: ViewModel() {

    private val apiService = RetrofitClient()
    private val disposable = CompositeDisposable()

    var accountInsert_response = MutableLiveData<AccountModel>();
    var accountInsert_response_error = MutableLiveData<Boolean>();

    fun doInsert(model: InsertModel) {

        disposable.add(
            apiService.doInsert(model)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<AccountModel>() {
                    override fun onSuccess(model: AccountModel) {
                        model?.let {
                            accountInsert_response.value=model
                        }

                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        accountInsert_response_error.value = true
                        Log.e("Login-->", e.toString())

                    }

                })
        )
    }

}