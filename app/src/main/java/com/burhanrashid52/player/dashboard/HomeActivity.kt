package com.burhanrashid52.player.dashboard

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import com.burhanrashid52.player.R
import com.burhanrashid52.player.animations.VideoTouchHandler
import com.burhanrashid52.player.home.HomeFragment
import com.burhanrashid52.player.library.LibraryFragment
import com.burhanrashid52.player.player.VideoDetailsFragment
import com.burhanrashid52.player.player.VideoPlayerFragment
import com.burhanrashid52.player.subscriptions.SubscriptionFragment
import com.burhanrashid52.player.trending.TrendingFragment
import com.burhanrashid52.player.useractivity.UserActivityFragment
import ja.burhanrashid52.base.BaseActivity
import ja.burhanrashid52.base.getViewModel
import ja.burhanrashid52.base.loadFragment
import kotlinx.android.synthetic.main.activity_youtube.*
import kotlinx.android.synthetic.main.fragment_player.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class HomeActivity : BaseActivity() {

    private lateinit var dashboardViewModel: DashboardViewModel
   // private lateinit var animationTouchListener: VideoTouchHandler


    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
        when (it.itemId) {
            R.id.navigation_home -> {
                loadFragment {
                    replace(R.id.frmHomeContainer, HomeFragment.newInstance(), HomeFragment.TAG)
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_library -> {
                loadFragment {
                    replace(R.id.frmHomeContainer, LibraryFragment.newInstance(), LibraryFragment.TAG)
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                loadFragment {
                    replace(R.id.frmHomeContainer, UserActivityFragment.newInstance(), UserActivityFragment.TAG)
                }
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_trending -> {
                loadFragment {
                    replace(R.id.frmHomeContainer, TrendingFragment.newInstance(), TrendingFragment.TAG)
                }
                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_subscription -> {
                loadFragment {
                    replace(R.id.frmHomeContainer, SubscriptionFragment.newInstance(), SubscriptionFragment.TAG)
                }
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_youtube)
        setSupportActionBar(toolbar)

        bottomNavigation.disableShiftMode(true)

        supportActionBar?.title = "YouTube"
        dashboardViewModel = getViewModel()

        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        loadFragment {
            replace(R.id.frmHomeContainer, HomeFragment.newInstance(), HomeFragment.TAG)
        }

        dashboardViewModel.moviesSelectionListener.observe(this, Observer { movies ->
            movies?.let {
           //     animationTouchListener.show()

                loadFragment(transitionPairs = mapOf(getString(R.string.transition_poster) to imgPoster)) {
                    replace(R.id.frmVideoContainer, VideoPlayerFragment.newInstance(it.id), VideoPlayerFragment.TAG)
                }

                loadFragment {
                    replace(R.id.frmDetailsContainer, VideoDetailsFragment.newInstance(it.id), VideoDetailsFragment.TAG)
                }
            //    animationTouchListener.isExpanded = true
            }
        })

        // animationTouchListener = VideoTouchHandler(this, this)
        // frmVideoContainer.setOnTouchListener(animationTouchListener)
    }
}
