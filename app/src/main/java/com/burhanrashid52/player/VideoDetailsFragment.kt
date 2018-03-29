package com.burhanrashid52.player

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.os.bundleOf
import ja.burhanrashid52.base.BaseFragment
import ja.burhanrashid52.base.toast
import kotlinx.android.synthetic.main.fragment_video_details.*

/**
 * Created by Burhanuddin Rashid on 2/27/2018.
 */
class VideoDetailsFragment @SuppressLint("ValidFragment")
private constructor() : BaseFragment() {

    override fun getLayoutId() = R.layout.fragment_video_details

    companion object {
        val TAG = VideoPlayerFragment::class.java.simpleName
        const val EXTRA_MOVIE_ID = "extar_movie_id"
        fun newInstance() = VideoDetailsFragment()

        fun newInstance(movieId: Int) = VideoDetailsFragment().apply {
            arguments = bundleOf(
                    EXTRA_MOVIE_ID to movieId
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button.setOnClickListener {
            toast("Clicked")
        }
    }
}