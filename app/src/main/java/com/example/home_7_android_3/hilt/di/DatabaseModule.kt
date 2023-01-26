package com.example.home_7_android_3.hilt.di

import android.content.Context
import androidx.room.Room
import com.example.home_7_android_3.hilt.data.local.room.HomeDatabase
import com.example.home_7_android_3.hilt.data.local.room.dao.HomeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideItemDatabase(@ApplicationContext context: Context): HomeDatabase {
        return Room.databaseBuilder(
            context, HomeDatabase::class.java, "photo-database"
        ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
    }

    @Provides
    @Singleton
    fun provideItemDao(database: HomeDatabase): HomeDao {
        return database.dao()
    }
}