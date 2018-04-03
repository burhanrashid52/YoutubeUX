package com.burhanrashid52.player.animations

import android.annotation.SuppressLint
import android.app.Activity
import android.content.res.Resources
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import timber.log.Timber


/**
 * Created by Burhanuddin Rashid on 2/25/2018.
 *
 * This class is fully dependent on Dashboard activity layout it will perform youtube like
 * animation on Framelayout onTouchListener.The logic for animation resides here to make
 * it modular
 */
class VideoTouchHandler(activity: Activity, private var gestureEventsListener: GestureEvents) : View.OnTouchListener {

    companion object {
        val TAG = VideoTouchHandler::class.java.simpleName
        /**
         * Video limit params set minimum size a video can scale from both vertical
         * and horizontal directions
         */
        const val MIN_VERTICAL_LIMIT = 0.685F
        const val MIN_HORIZONTAL_LIMIT = 0.425F
        const val MIN_BOTTOM_LIMIT = 0.90F
        const val MIN_MARGIN_END_LIMIT = 0.975F

        /**
         * Define a threshold value to which when view moves above that threshold when
         * touch action is up than automatically scale to top else scale to the
         * minimum size
         */
        const val SCALE_THRESHOLD = 0.35F
        const val SWIPE_MIN_DISTANCE = 120

    }

    private val deviceHeight = Resources.getSystem().displayMetrics.heightPixels//activity.getDeviceHeight()
    private val deviceWidth = Resources.getSystem().displayMetrics.widthPixels//activity.getDeviceWidth()

    //Gesture controls and scroll flags
    private var gestureDetector = GestureDetector(activity, GestureControl())
    private var isTopScroll = false
    private var isSwipeScroll = false

    //Initialize touch variables
    private var startX = 0F
    private var startY = 0F
    private var dX = 0F
    private var dY = 0F
    private var percentVertical = 0F
    private var percentMarginMoved = MIN_MARGIN_END_LIMIT

    var isEnabled = true

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(view: View, event: MotionEvent): Boolean {
        if (!isEnabled) {
            return false
        }
        if (gestureDetector.onTouchEvent(event)) {
            gestureEventsListener.onClick(view)
            return true
        }
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                startX = event.rawX
                startY = event.rawY
                dX = view.x - startX
                dY = view.y - startY

            }

            MotionEvent.ACTION_MOVE -> {
                percentVertical = (event.rawY + dY) / deviceHeight.toFloat()
                val percentHorizontal = (event.rawX + dX) / deviceWidth.toFloat()

                when (getDirection(startX = startX, startY = startY, endX = event.rawX, endY = event.rawY)) {
                    is Direction.LEFT, is Direction.RIGHT -> {

                        //Don't perform swipe if video frame is expanded or scrolling up/down
                        if (!(!isTopScroll && !isExpanded)) return false

                        //set swipe flag to avoid up/down scroll
                        isSwipeScroll = true
                        //Prevent guidelines to go out of screen bound
                        val percentHorizontalMoved = Math.max(-0.25F, Math.min(VideoTouchHandler.MIN_HORIZONTAL_LIMIT, percentHorizontal))
                        percentMarginMoved = percentHorizontalMoved + (VideoTouchHandler.MIN_MARGIN_END_LIMIT - VideoTouchHandler.MIN_HORIZONTAL_LIMIT)

                        Timber.e("" + percentHorizontal)

                        gestureEventsListener.onSwipe(percentHorizontal)
                        //swipeVideo(percentHorizontal)
                    }
                    is Direction.UP, is Direction.DOWN, is Direction.NONE -> {

                        //Don't expand video when user is swiping the video when its not expanded
                        if (isSwipeScroll) return false

                        //set up/down flag to avoid swipe scroll
                        isTopScroll = true
                        gestureEventsListener.onScale(percentVertical)
                        //scaleVideo(percentVertical)
                    }
                }
            }

            MotionEvent.ACTION_UP -> {
                Timber.d("Up Percent $percentVertical")
                isTopScroll = false
                isSwipeScroll = false
                if (percentMarginMoved < 0.5) {
                    //dismiss the video player
                    gestureEventsListener.onDismiss(view)
                    resetValues()
                } else {
                    isExpanded = percentVertical < SCALE_THRESHOLD
                }
            }
        }
        return true
    }

    //Setup direction types from Direction sealed class
    private var left = Direction.LEFT()
    private var right = Direction.RIGHT()
    private var up = Direction.UP()
    private var down = Direction.DOWN()
    private var none = Direction.NONE()


    /**
     * return a Direction on which user is current scrolling by getting
     * start event coordinates when user press down and end event coordinates when user
     * moves the finger on view
     */
    private fun getDirection(startX: Float, startY: Float, endX: Float, endY: Float): Direction {
        val deltaX = endX - startX
        val deltaY = endY - startY

        return if (Math.abs(deltaX) > Math.abs(deltaY)) {
            //Scrolling Horizontal
            if (deltaX > 0) right else left
        } else {
            //Scrolling Vertical
            if (Math.abs(deltaY) > SWIPE_MIN_DISTANCE) {
                if (deltaY > 0) down else up
            } else {
                none
            }
        }
    }


    var isExpanded = true
        set(value) {
            field = value
            gestureEventsListener.onExpand(field)
        }


    fun show() {
        if (!isExpanded) {
            isExpanded = true
        }
    }

    private fun resetValues() {
        isTopScroll = false
        isSwipeScroll = false

        //Initialize touch variables
        startX = 0F
        startY = 0F
        dX = 0F
        dY = 0F
        percentVertical = 0F
        percentMarginMoved = MIN_MARGIN_END_LIMIT
    }
}


/* event.rawX + dX
 view.animate()
         .x(event.rawX + dX)
         .y(event.rawY + dY)
         .setDuration(0)
         .start()*/
