package ja.burhanrashid52.base.di.modules

import android.app.Application
import android.preference.PreferenceManager

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor

import java.util.concurrent.TimeUnit


import dagger.Module
import dagger.Provides
import ja.burhanrashid52.base.BuildConfig
import ja.burhanrashid52.base.api.ApiConstants
import ja.burhanrashid52.base.api.ApiServices
import ja.burhanrashid52.base.di.scopes.BaseScope
import ja.burhanrashid52.base.liveUtils.LiveDataCallAdapterFactory
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class BaseNetworkModule {

    private val mBaseUrl = "https://my-json-server.typicode.com/burhanrashid52/AspectRatioExample/"

    @Provides
    @BaseScope
    fun providesSharedPreferences(application: Application) = PreferenceManager.getDefaultSharedPreferences(application)

    @Provides
    @BaseScope
    fun provideHttpCache(application: Application): Cache {
        val cacheSize = 10 * 1024 * 1024
        return Cache(application.cacheDir, cacheSize.toLong())
    }

    @Provides
    @BaseScope
    fun provideGson(): Gson = with(GsonBuilder()) {
        setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        create()
    }

    @Provides
    @BaseScope
    fun provideBeautyLoggingInterceptor(): LoggingInterceptor = with(LoggingInterceptor.Builder()) {
        loggable(BuildConfig.DEBUG)
        setLevel(Level.BASIC)
        log(Platform.INFO)
        request("Request")
        response("Response")
        addHeader("Version", BuildConfig.VERSION_NAME)
        build()
    }

    @Provides
    @BaseScope
    fun provideOkhttpClient(
            cache: Cache,
            interceptor: HttpLoggingInterceptor,
            beautyloggingInterceptor: LoggingInterceptor): OkHttpClient = with(OkHttpClient.Builder()) {

        connectTimeout(ApiConstants.HTTP_CONNECT_TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
        readTimeout(ApiConstants.HTTP_READ_TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
        addInterceptor(interceptor)
        build()
    }

    @Provides
    @BaseScope
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .baseUrl(mBaseUrl)
                .client(okHttpClient)
                .build()
    }


    @Provides
    @BaseScope
    fun provideHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @BaseScope
    fun provideApiService(retrofit: Retrofit) = retrofit.create(ApiServices::class.java)!!
}
