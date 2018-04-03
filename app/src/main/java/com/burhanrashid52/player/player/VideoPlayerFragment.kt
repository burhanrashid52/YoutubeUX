package com.burhanrashid52.player.player

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.support.transition.TransitionInflater
import androidx.net.toUri
import androidx.os.bundleOf
import androidx.view.isGone
import androidx.view.isVisible
import com.burhanrashid52.player.R
import com.burhanrashid52.player.dashboard.DashboardViewModel
import com.burhanrashid52.player.dashboard.ViewsEvents
import ja.burhanrashid52.base.BaseFragment
import ja.burhanrashid52.base.getActivityViewModel
import ja.burhanrashid52.base.loadFromUrl
import ja.burhanrashid52.base.toast
import kotlinx.android.synthetic.main.fragment_player.*
import timber.log.Timber

/**
 * Created by Burhanuddin Rashid on 2/21/2018.
 */
class VideoPlayerFragment @SuppressLint("ValidFragment")
private constructor() : BaseFragment() {

    override fun getLayoutId() = R.layout.fragment_player

    companion object {
        val TAG = VideoPlayerFragment::class.java.simpleName
        private const val EXTRA_MOVIE_ID = "extra_movie_id"
        fun newInstance(movieID: Int) = VideoPlayerFragment().apply {
            arguments = bundleOf(
                    EXTRA_MOVIE_ID to movieID
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        }
    }

    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        dashboardViewModel = getActivityViewModel()

        val movieID = arguments?.getInt(EXTRA_MOVIE_ID) ?: 0

        showControllers(false)

        dashboardViewModel.getMoviesDetails(movieID).observe(this, Observer {

            it?.data?.let {
                imgPoster.loadFromUrl(it.poster)

                videoPlayer.setVideoURI(it.videoUrl.toUri())

                videoPlayer.setOnPreparedListener {
                    videoPlayer.start()
                    imgPoster.isGone = true
                }
            }
        })

        videoPlayer.setOnCompletionListener {
            videoPlayer.start()
        }

        btnChild.setOnClickListener {
            toast("Video Clicked")
        }

        imgFullScreen.setOnClickListener {
            activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }

        imgPlay.setOnClickListener {
            videoPlayer.start()
            showControllers(false)
        }

        dashboardViewModel.controllersListener.observe(this, Observer {
            when (it) {
                is ViewsEvents.SHOW -> showControllers(true)
                is ViewsEvents.HIDE -> showControllers(false)
                is ViewsEvents.CLICKED -> showControllers(!imgFullScreen.isVisible)
                is ViewsEvents.LONGPRESS -> TODO()
                is ViewsEvents.NONE -> TODO()
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        if (videoPlayer != null) {
            videoPlayer.stopPlayback()
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        Timber.e("Fragment Config Changes")
    }

    /**
     * Toggle the visibility of controller
     */
    private fun showControllers(isShow: Boolean) {
        imgFullScreen.isGone = !isShow
        imgPlay.isGone = !isShow
    }
}