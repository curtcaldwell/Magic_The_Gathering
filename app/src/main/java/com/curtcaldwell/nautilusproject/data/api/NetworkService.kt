package com.curtcaldwell.nautilusproject.data.api

import com.curtcaldwell.nautilusproject.data.model.Result
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkService {

    @GET("cards?contains=imageUrl")
    fun getResult( @Query("page") page: Int, @Query("Page-Size") pageSize: Int)  : Observable<Result>

    companion object {
        fun getService(): NetworkService {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.magicthegathering.io/v1/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(NetworkService::class.java)
        }
    }


}