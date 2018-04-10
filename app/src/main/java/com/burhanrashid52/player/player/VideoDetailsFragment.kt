package com.burhanrashid52.player.player

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.View
import androidx.os.bundleOf
import com.burhanrashid52.player.R
import com.burhanrashid52.player.dashboard.DashboardViewModel
import ja.burhanrashid52.base.BaseFragment
import ja.burhanrashid52.base.getActivityViewModel
import kotlinx.android.synthetic.main.fragment_video_details.*
import android.text.method.ScrollingMovementMethod


/**
 * Created by Burhanuddin Rashid on 2/27/2018.
 */
class VideoDetailsFragment @SuppressLint("ValidFragment")
private constructor() : BaseFragment() {

    override fun getLayoutId() = R.layout.fragment_video_details


    private lateinit var dashBoardViewModel: DashboardViewModel

    companion object {
        val TAG = VideoPlayerFragment::class.java.simpleName
        private const val EXTRA_MOVIE_ID = "extra_movie_id"

        fun newInstance(movieId: Int) = VideoDetailsFragment().apply {
            arguments = bundleOf(
                    EXTRA_MOVIE_ID to movieId
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        dashBoardViewModel = getActivityViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movieID = arguments?.getInt(EXTRA_MOVIE_ID)!!
//        txtDescription.movementMethod = ScrollingMovementMethod()
        dashBoardViewModel.getMoviesDetails(movieID).observe(this, Observer {
            it?.data?.let {
                txtTitle.text = it.title
                txtYear.text = it.year.toString()
                txtDuration.text = it.duration
                txtGenre.text = it.genre
                txtDescription.text = it.description
            }
        })
    }
}