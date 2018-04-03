package com.burhanrashid52.player.dashboard

import android.arch.lifecycle.Observer
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.design.widget.BottomNavigationView
import android.support.transition.ChangeBounds
import android.support.transition.Transition
import android.support.transition.TransitionManager
import android.view.View
import android.view.animation.AnticipateOvershootInterpolator
import com.burhanrashid52.player.R
import com.burhanrashid52.player.player.VideoDetailsFragment
import com.burhanrashid52.player.player.VideoPlayerFragment
import com.burhanrashid52.player.activity.UserActivityFragment
import com.burhanrashid52.player.animations.GestureEvents
import com.burhanrashid52.player.animations.VideoTouchHandler
import com.burhanrashid52.player.home.HomeFragment
import com.burhanrashid52.player.library.LibraryFragment
import com.burhanrashid52.player.subscriptions.SubscriptionFragment
import com.burhanrashid52.player.trending.TrendingFragment
import ja.burhanrashid52.base.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_player.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import android.view.Menu
import timber.log.Timber


/**
 * Created by Burhanuddin Rashid on 2/25/2018.
 */
class DashboardActivity : BaseActivity(), GestureEvents {


    companion object {
        val TAG = DashboardActivity::class.java.simpleName
    }

    /*
    *  Setting up guideline parameters to change the
    *  guideline percent value as per user touch event
    */
    private lateinit var paramsGlHorizontal: ConstraintLayout.LayoutParams
    private lateinit var paramsGlVertical: ConstraintLayout.LayoutParams
    private lateinit var paramsGlBottom: ConstraintLayout.LayoutParams
    private lateinit var paramsGlMarginEnd: ConstraintLayout.LayoutParams

    private val constraintSet = ConstraintSet()

    private lateinit var dashboardViewModel: DashboardViewModel
    private lateinit var animationTouchListener: VideoTouchHandler


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
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        bottomNavigation.disableShiftMode(true)

        paramsGlHorizontal = guidelineHorizontal.getParams()
        paramsGlVertical = guidelineVertical.getParams()
        paramsGlBottom = guidelineBottom.getParams()
        paramsGlMarginEnd = guidelineMarginEnd.getParams()


        supportActionBar?.title = "YouTube"
        dashboardViewModel = getViewModel()

        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        loadFragment {
            replace(R.id.frmHomeContainer, HomeFragment.newInstance(), HomeFragment.TAG)
        }

        dashboardViewModel.moviesSelectionListener.observe(this, Observer {
            it?.let {
                animationTouchListener.show()

                loadFragment(transitionPairs = mapOf(getString(R.string.transition_poster) to imgPoster)) {
                    replace(R.id.frmVideoContainer, VideoPlayerFragment.newInstance(it.id), VideoPlayerFragment.TAG)
                }

                loadFragment {
                    replace(R.id.frmDetailsContainer, VideoDetailsFragment.newInstance(it.id), VideoDetailsFragment.TAG)
                }
                animationTouchListener.isExpanded = true
            }
        })

        //  val videoUrl = "http://www.sample-videos.com/video/mp4/480/big_buck_bunny_480p_30mb.mp4"

        hide()
        animationTouchListener = VideoTouchHandler(this, this)
        frmVideoContainer.setOnTouchListener(animationTouchListener)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home_menu, menu)

        val searchItem = menu.findItem(R.id.action_search)

//        val searchView = searchItem.actionView as SearchView
        //      searchView.queryHint = "Search People"
        // searchView.setOnQueryTextListener(this)

        return true
    }

    override fun onClick(view: View) {
        if (!animationTouchListener.isExpanded) {
            animationTouchListener.isExpanded = true
        } else {
            dashboardViewModel.onClicked()
        }
    }

    override fun onDismiss(view: View) {
        dismiss()
    }

    override fun onScale(percentage: Float) {
        dashboardViewModel.showControllers(false)
        scaleVideo(percentage)
    }

    override fun onSwipe(percentage: Float) {
        swipeVideo(percentage)
    }

    override fun onExpand(isExpanded: Boolean) {
        setViewExpanded(isExpanded)
    }

    override fun onBackPressed() {
        if (animationTouchListener.isExpanded) {
            animationTouchListener.isExpanded = false
            dashboardViewModel.showControllers(false)
            if (!isPortrait()) {
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            }
        } else {
            super.onBackPressed()
        }

    }

    /**
     * Scale the video as per given percentage of user scrolls
     * in up/down direction from current position
     */
    private fun scaleVideo(percentScrollUp: Float) {

        //Prevent guidelines to go out of screen bound
        val percentVerticalMoved = Math.max(0F, Math.min(VideoTouchHandler.MIN_VERTICAL_LIMIT, percentScrollUp))
        val movedPercent = percentVerticalMoved / VideoTouchHandler.MIN_VERTICAL_LIMIT
        val percentHorizontalMoved = VideoTouchHandler.MIN_HORIZONTAL_LIMIT * movedPercent
        val percentBottomMoved = 1F - movedPercent * (1F - VideoTouchHandler.MIN_BOTTOM_LIMIT)
        val percentMarginMoved = 1F - movedPercent * (1F - VideoTouchHandler.MIN_MARGIN_END_LIMIT)

        paramsGlHorizontal.guidePercent = percentVerticalMoved
        paramsGlVertical.guidePercent = percentHorizontalMoved
        paramsGlBottom.guidePercent = percentBottomMoved
        paramsGlMarginEnd.guidePercent = percentMarginMoved

        guidelineHorizontal.layoutParams = paramsGlHorizontal
        guidelineVertical.layoutParams = paramsGlVertical
        guidelineBottom.layoutParams = paramsGlBottom
        guidelineMarginEnd.layoutParams = paramsGlMarginEnd

        frmDetailsContainer.alpha = 1.0F - movedPercent
    }

    /**
     * Swipe animation on given percentage user has scroll on left/right
     * direction from the current position
     */
    private fun swipeVideo(percentScrollSwipe: Float) {

        //Prevent guidelines to go out of screen bound
        val percentHorizontalMoved = Math.max(-0.25F, Math.min(VideoTouchHandler.MIN_HORIZONTAL_LIMIT, percentScrollSwipe))
        val percentMarginMoved = percentHorizontalMoved + (VideoTouchHandler.MIN_MARGIN_END_LIMIT - VideoTouchHandler.MIN_HORIZONTAL_LIMIT)

        paramsGlVertical.guidePercent = percentHorizontalMoved
        paramsGlMarginEnd.guidePercent = percentMarginMoved

        guidelineHorizontal.layoutParams = paramsGlHorizontal
        guidelineMarginEnd.layoutParams = paramsGlMarginEnd
    }

    /**
     * Hide all video and video details fragment
     */
    private fun hide() = rootContainer.updateParams {

        setGuidelinePercent(guidelineHorizontal.id, 100F)
        setGuidelinePercent(guidelineVertical.id, 100F)
        setAlpha(frmDetailsContainer.id, 0F)

        TransitionManager.beginDelayedTransition(rootContainer, ChangeBounds().apply {
            interpolator = AnticipateOvershootInterpolator(1.0f)
            duration = 250
        })
    }

    /**
     * Expand or collapse the video fragment animation
     */
    private fun setViewExpanded(isExpanded: Boolean) = rootContainer.updateParams(constraintSet) {

        setGuidelinePercent(guidelineHorizontal.id, if (isExpanded) 0F else VideoTouchHandler.MIN_VERTICAL_LIMIT)
        setGuidelinePercent(guidelineVertical.id, if (isExpanded) 0F else VideoTouchHandler.MIN_HORIZONTAL_LIMIT)
        setGuidelinePercent(guidelineBottom.id, if (isExpanded) 1F else VideoTouchHandler.MIN_BOTTOM_LIMIT)
        setGuidelinePercent(guidelineMarginEnd.id, if (isExpanded) 1F else VideoTouchHandler.MIN_MARGIN_END_LIMIT)
        setAlpha(frmDetailsContainer.id, if (isExpanded) 1.0F else 0F)

        TransitionManager.beginDelayedTransition(rootContainer, ChangeBounds().apply {
            interpolator = android.view.animation.AnticipateOvershootInterpolator(1.0f)
            duration = 250
        })
    }

    /**
     * Show dismiss animation when user have moved
     * more than 50% horizontally
     */
    private fun dismiss() = rootContainer.updateParams(constraintSet) {

        setGuidelinePercent(guidelineVertical.id, VideoTouchHandler.MIN_HORIZONTAL_LIMIT - VideoTouchHandler.MIN_MARGIN_END_LIMIT)
        setGuidelinePercent(guidelineMarginEnd.id, 0F)

        TransitionManager.beginDelayedTransition(rootContainer, ChangeBounds().apply {
            interpolator = AnticipateOvershootInterpolator(1.0f)
            duration = 250
            addListener(object : Transition.TransitionListener {
                override fun onTransitionResume(transition: Transition) {
                }

                override fun onTransitionPause(transition: Transition) {
                }

                override fun onTransitionCancel(transition: Transition) {
                }

                override fun onTransitionStart(transition: Transition) {
                }

                override fun onTransitionEnd(transition: Transition) {
                    //Remove Video when swipe animation is ended
                    removeFragmentByID(R.id.frmVideoContainer)
                }
            })
        })
    }


    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        Timber.e("Config Changes")
        if (isPortrait()) {
            animationTouchListener.isEnabled = true
            enableFullScreen(false)
        } else {
            animationTouchListener.isEnabled = false
            if (!animationTouchListener.isExpanded) {
                animationTouchListener.isExpanded = true
            }
            enableFullScreen(true)
        }
    }
}
