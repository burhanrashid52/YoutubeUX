package com.burhanrashid52.player.di.modules

import android.app.Application
import android.arch.persistence.room.Room
import com.burhanrashid52.player.data.local.AppDatabase
import com.burhanrashid52.player.data.remote.WebService
import com.burhanrashid52.player.di.scopes.AppScope
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit


@Module
class AppComponent {

    @Provides
    @AppScope
    fun provideRetrofitClient(retrofit: Retrofit) = retrofit.create(WebService::class.java)!!

    @Provides
    @AppScope
    fun provideRoomDB(application: Application): AppDatabase {
        return Room.databaseBuilder(application.applicationContext, AppDatabase::class.java, "movies-db")
                .fallbackToDestructiveMigration()
                .build()
    }

    @Provides
    @AppScope
    fun provideMoviesDao(appDatabase: AppDatabase) = appDatabase.moviesDao()

}