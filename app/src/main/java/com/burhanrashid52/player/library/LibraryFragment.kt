package com.burhanrashid52.player.library

import android.os.Bundle
import android.view.View
import com.burhanrashid52.player.R
import ja.burhanrashid52.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_trending.*

class LibraryFragment : BaseFragment() {

    companion object {
        val TAG = LibraryFragment::class.java.simpleName
        fun newInstance() = LibraryFragment()
    }

    override fun getLayoutId() = R.layout.fragment_trending

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        txtLabel.text = "Videos Library"
    }
}