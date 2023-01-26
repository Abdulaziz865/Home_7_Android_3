package com.example.home_7_android_3.hilt.data.repositories

import com.example.home_7_android_3.hilt.data.local.room.dao.HomeDao
import com.example.home_7_android_3.hilt.data.models.HomeModel
import com.example.home_7_android_3.hilt.data.remote.api.PostsApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val apiService: PostsApiService,
    private val postDao: HomeDao
) {
    fun getPhoto(
        onSuccess: (homeModelList: List<HomeModel>) -> Unit,
        onFailure: (massage: String) -> Unit
    ) {

        apiService.getItem().enqueue(object : Callback<List<HomeModel>> {
            override fun onResponse(call: Call<List<HomeModel>>, response: Response<List<HomeModel>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        postDao.insertItems(it)
                        onSuccess(it)
                    }
                }
            }

            override fun onFailure(call: Call<List<HomeModel>>, t: Throwable) {
                t.localizedMessage?.let {
                    onFailure(it)
                }
            }
        })
    }

    fun getLocalItems() = postDao.getAllItem()
}