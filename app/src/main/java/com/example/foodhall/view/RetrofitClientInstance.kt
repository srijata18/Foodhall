package com.example.foodhall.view

import com.example.foodhall.Constants
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit



class RetrofitClientInstance {
    private var retrofit: Retrofit? = null

    fun getRetrofitInstance(): Retrofit? {
        if (retrofit == null) {
            retrofit = retrofit2.Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }
}