package com.burhanrashid52.player.dashboard

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.design.widget.BottomNavigationView
import android.support.transition.ChangeBounds
import android.support.transition.TransitionManager
import android.view.View
import android.view.animation.AnticipateOvershootInterpolator
import com.burhanrashid52.player.R
import com.burhanrashid52.player.VideoDetailsFragment
import com.burhanrashid52.player.VideoPlayerFragment
import com.burhanrashid52.player.animations.Events
import com.burhanrashid52.player.animations.VideoTouchHandler
import com.burhanrashid52.player.home.HomeFragment
import ja.burhanrashid52.base.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.fragment_player.*
import kotlinx.android.synthetic.main.layout_toolbar.*


/**
 * Created by Burhanuddin Rashid on 2/25/2018.
 */
class DashboardActivity : BaseActivity(), Events {


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


    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener {
        when (it.itemId) {
            R.id.navigation_home -> {
                loadFragment {
                    replace(R.id.frmHomeContainer, HomeFragment.newInstance(), HomeFragment.TAG)
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_library -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private lateinit var mDashboardViewModel: DashboardViewModel
    private lateinit var animationTouchListener: VideoTouchHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        paramsGlHorizontal = guidelineHorizontal.getParams()
        paramsGlVertical = guidelineVertical.getParams()
        paramsGlBottom = guidelineBottom.getParams()
        paramsGlMarginEnd = guidelineMarginEnd.getParams()


        supportActionBar?.title = "YouTube"
        mDashboardViewModel = getViewModel()

        bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        loadFragment {
            replace(R.id.frmHomeContainer, HomeFragment.newInstance(), HomeFragment.TAG)
        }

        mDashboardViewModel.moviesSelectionListener.observe(this, Observer {
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

        val videoUrl = "http://www.sample-videos.com/video/mp4/480/big_buck_bunny_480p_30mb.mp4"

        animationTouchListener = VideoTouchHandler(this, this)

        //animationTouchListener.isExpanded = true
        hide()

        frmVideoContainer.setOnTouchListener(animationTouchListener)
    }

    override fun onClick(view: View) {
        animationTouchListener.isExpanded = true
    }

    override fun onDismiss(view: View) {
        toast(if (removeFragment(VideoPlayerFragment.TAG)) "Removed" else "Failed")
    }

    override fun onScale(percentage: Float) {
        scaleVideo(percentage)
    }

    override fun onSwipe(percentage: Float) {
        swipeVideo(percentage)
    }

    override fun onBackPressed() {
        if (animationTouchListener.isExpanded) {
            animationTouchListener.isExpanded = false
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

    private fun hide() {
        with(constraintSet) {
            clone(rootContainer)

            setGuidelinePercent(guidelineHorizontal.id, 100F)
            setGuidelinePercent(guidelineVertical.id, 100F)
            setAlpha(frmDetailsContainer.id, 0F)

            TransitionManager.beginDelayedTransition(rootContainer, ChangeBounds().apply {
                interpolator = AnticipateOvershootInterpolator(1.0f)
                duration = 250
            })
            applyTo(rootContainer)
        }
    }
}
