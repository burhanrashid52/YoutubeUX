package com.burhanrashid52.player.home

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.burhanrashid52.player.R
import com.burhanrashid52.player.dashboard.DashboardViewModel
import ja.burhanrashid52.base.BaseFragment
import ja.burhanrashid52.base.getActivityViewModel
import ja.burhanrashid52.base.loadFragment
import ja.burhanrashid52.base.repo.Status.*
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Created by Burhanuddin Rashid on 3/6/2018.
 */
class HomeFragment @SuppressLint("ValidFragment")
private constructor() : BaseFragment() {

    override fun getLayoutId() = R.layout.fragment_home

    companion object {
        val TAG = HomeFragment::class.java.simpleName
        fun newInstance() = HomeFragment()
    }

    private val homeAdapter = HomeAdapter {
        dashboardViewModel.loadVideo(it)
    }
    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dashboardViewModel = getActivityViewModel()
        rvMovies.layoutManager = LinearLayoutManager(activity)
        rvMovies.adapter = homeAdapter

        dashboardViewModel.movies.observe(this, Observer {
            when (it?.status) {
                SUCCESS -> {
                    it.data?.let {
                        homeAdapter.moviesList = it.toMutableList()
                    }
                }
                ERROR -> {

                }
                LOADING -> {

                }
            }
        })
    }

}