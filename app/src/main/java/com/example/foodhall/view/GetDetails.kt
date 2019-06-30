package com.example.foodhall.view

import com.example.foodhall.Constants
import com.example.foodhall.datamodel.BottomModel
import com.example.foodhall.datamodel.CategoryModel
import com.example.foodhall.datamodel.MainContentDetails
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Headers


interface GetDetails {

    @GET(Constants.URL_1 )
    fun getAllCategories(@HeaderMap headers: Map<String, String>): Call<MainContentDetails>

    @GET(Constants.URL_2 )
    fun getBannerList(@HeaderMap headers: Map<String, String>): Call<BottomModel>
}