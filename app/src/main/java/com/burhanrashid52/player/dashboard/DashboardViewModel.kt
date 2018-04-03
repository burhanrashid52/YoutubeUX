package com.burhanrashid52.player.dashboard

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.burhanrashid52.player.PlayerApp
import com.burhanrashid52.player.data.MoviesRepository
import com.burhanrashid52.player.data.local.Movies
import com.burhanrashid52.player.di.components.DaggerActivityComponent
import ja.burhanrashid52.base.liveUtils.SingleLiveEvent
import ja.burhanrashid52.base.repo.Resource
import javax.inject.Inject

/**
 * Created by Burhanuddin Rashid on 3/6/2018.
 */
class DashboardViewModel(application: Application) : AndroidViewModel(application) {

    @Inject
    lateinit var moviesRepository: MoviesRepository
    val movies: LiveData<Resource<List<Movies>>>
    val moviesSelectionListener = SingleLiveEvent<Movies>()
    val controllersListener = SingleLiveEvent<ViewsEvents>()

    private val viewClicked = ViewsEvents.CLICKED()
    private val viewShow = ViewsEvents.SHOW()
    private val viewHide = ViewsEvents.HIDE()
    private val viewLongPress = ViewsEvents.LONGPRESS()

    init {
        DaggerActivityComponent.builder()
                .baseNetworkComponent(PlayerApp.baseNetworkComponent)
                .build()
                .inject(this)

        movies = moviesRepository.getMoviesTrailers()
    }

    fun loadVideo(movies: Movies) {
        moviesSelectionListener.value = movies
    }

    fun showControllers(isShown: Boolean) {
        controllersListener.value = if (isShown) viewShow else viewHide
    }

    fun onClicked(isLongPress: Boolean = false) {
        controllersListener.value = if (isLongPress) viewLongPress else viewClicked
    }


    fun getMoviesDetails(movieId: Int) = moviesRepository.getMoviesDetails(movieId)
}