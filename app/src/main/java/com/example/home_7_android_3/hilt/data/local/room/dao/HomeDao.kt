package com.example.home_7_android_3.hilt.data.local.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.home_7_android_3.hilt.data.models.HomeModel

@Dao
interface HomeDao {

    @Query("SELECT * FROM home_model")
    fun getAllItem(): LiveData<List<HomeModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItems(homeModel: List<HomeModel>)
}