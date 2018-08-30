package com.burhanrashid52.player.di

import com.burhanrashid52.player.data.remote.WebService
import com.burhanrashid52.player.network.TestWebService
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import ja.burhanrashid52.base.di.modules.BaseNetworkModule
import ja.burhanrashid52.base.di.scopes.BaseScope
import ja.burhanrashid52.base.liveUtils.LiveDataCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 *
 * @author <a href="https://github.com/burhanrashid52">Burhanuddin Rashid</a>
 * @since 6/22/2018
 */

@Module
class TestBaseNetworkModule : BaseNetworkModule() {

    @BaseScope
    @Provides
    override fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .baseUrl("")
                .client(okHttpClient)
                .build()
    }

    @BaseScope
    @Provides
    fun provideApiServices(retrofit: Retrofit): WebService {
        return TestWebService()
    }
}