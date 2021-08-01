package com.sssakib.smartapp.retrofit


import android.service.autofill.UserData
import com.sssakib.smartapp.model.AccountModel
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.*


interface API {


    @FormUrlEncoded
    @POST("AccountApi")
    fun doInsert(
        @Field("requestCode") requestCode: String?,
        @Field("id") id: String?,
        @Field("amount") amount: String?,
        @Field("remarks") remarks: String?,
        @Field("flag") flag: String?

    ): Single<AccountModel>





}
