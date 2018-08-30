package com.burhanrashid52.player.network

import android.arch.lifecycle.LiveData
import com.burhanrashid52.player.data.local.MoviesSource
import com.burhanrashid52.player.data.remote.WebService
import ja.burhanrashid52.base.api.ApiResponse

/**
 *
 * @author <a href="https://github.com/burhanrashid52">Burhanuddin Rashid</a>
 * @since 6/22/2018
 */
class TestWebService : WebService {
    override fun getMovies(): LiveData<ApiResponse<MoviesSource>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}