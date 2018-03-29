package com.burhanrashid52.player.animations

import android.view.GestureDetector
import android.view.MotionEvent

/**
 * Created by Burhanuddin Rashid on 2/27/2018.
 */
class GestureControl : GestureDetector.SimpleOnGestureListener() {
    override fun onSingleTapUp(event: MotionEvent): Boolean {
        return true
    }
}