package com.example.home_7_android_3.hilt.data.remote.api

import com.example.home_7_android_3.hilt.data.models.HomeModel
import retrofit2.Call
import retrofit2.http.GET

interface PostsApiService {

    @GET("albums/1/photos")
    fun getItem(): Call<List<HomeModel>>
}