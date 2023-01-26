package com.example.home_7_android_3.hilt.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.home_7_android_3.hilt.data.local.room.dao.HomeDao
import com.example.home_7_android_3.hilt.data.models.HomeModel

@Database(entities = [HomeModel::class], version = 2, exportSchema = false)
abstract class HomeDatabase : RoomDatabase() {

    abstract fun dao(): HomeDao
}