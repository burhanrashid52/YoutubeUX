package com.burhanrashid52.player.subscriptions

import android.os.Bundle
import android.view.View
import com.burhanrashid52.player.R
import ja.burhanrashid52.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_trending.*

class SubscriptionFragment : BaseFragment() {

    companion object {
        val TAG = SubscriptionFragment::class.java.simpleName

        fun newInstance() = SubscriptionFragment()
    }

    override fun getLayoutId() = R.layout.fragment_trending

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        txtLabel.text = "Subscriptions"
    }
}