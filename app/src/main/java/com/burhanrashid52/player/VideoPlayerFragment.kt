package com.burhanrashid52.player

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.support.transition.TransitionInflater
import android.view.LayoutInflater
import androidx.content.systemService
import androidx.net.toUri
import androidx.os.bundleOf
import androidx.view.isGone
import androidx.view.isVisible
import com.burhanrashid52.player.dashboard.DashboardViewModel
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

        dashboardViewModel.playerGestureListener.observe(this, Observer {
            it?.let {
                showControllers(!imgFullScreen.isVisible)
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

    private fun showControllers(isShow: Boolean) {
        imgFullScreen.isGone = !isShow
        imgPlay.isGone = !isShow
    }
}