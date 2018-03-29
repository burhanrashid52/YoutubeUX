package com.burhanrashid52.player

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.os.Bundle
import androidx.net.toUri
import androidx.os.bundleOf
import androidx.view.isGone
import com.burhanrashid52.player.dashboard.DashboardViewModel
import ja.burhanrashid52.base.BaseFragment
import ja.burhanrashid52.base.getActivityViewModel
import ja.burhanrashid52.base.loadFromUrl
import ja.burhanrashid52.base.toast
import kotlinx.android.synthetic.main.fragment_player.*

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
    }

    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        dashboardViewModel = getActivityViewModel()

        val movieID = arguments?.getInt(EXTRA_MOVIE_ID) ?: 0

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
    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
        if (videoPlayer != null) {
            videoPlayer.stopPlayback()
        }
    }
}