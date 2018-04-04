package com.burhanrashid52.player.animations

import android.view.View


/**
 * Created by Burhanuddin Rashid on 2/27/2018.
 *
 * Event callback listener interface to pass events to the UI to perform actions
 */
interface GestureEvents {
    fun onClick(view: View)
    fun onDismiss(view: View)
    fun onScale(percentage: Float)
    fun onSwipe(percentage: Float)
    fun onExpand(isExpanded: Boolean)
}