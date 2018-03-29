package com.burhanrashid52.player.data.remote

import android.arch.lifecycle.LiveData
import com.burhanrashid52.player.data.local.MoviesSource
import ja.burhanrashid52.base.api.ApiResponse
import retrofit2.http.GET

/**
 * Created by Burhanuddin Rashid on 3/6/2018.
 */
interface WebService {
    @GET("movieslist")
    fun getMovies(): LiveData<ApiResponse<MoviesSource>>
}