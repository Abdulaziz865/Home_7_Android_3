package com.example.home_7_android_3.hilt.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "home_model")
data class HomeModel(
    @SerializedName("albumId")
    val albumId: Int,
    @SerializedName("id")
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    @SerializedName("title")
    val title: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String?
)