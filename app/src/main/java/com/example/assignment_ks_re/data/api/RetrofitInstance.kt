package com.example.assignment_ks_re.data.api

import com.example.assignment_ks_re.data.util.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitInstance {

    private val retrofit : Retrofit
        get() = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    val api : BookApiService by lazy {
        retrofit.create(BookApiService::class.java)
    }

}