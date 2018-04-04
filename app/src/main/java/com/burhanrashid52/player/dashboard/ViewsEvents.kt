package com.burhanrashid52.player.dashboard

/**
 * Created by Burhanuddin Rashid on 2/27/2018.
 */

/**
 * ViewsEvents sealed class used as enum for defining different events for views
 */
sealed class ViewsEvents {
    class CLICKED : ViewsEvents()
    class SHOW : ViewsEvents()
    class HIDE : ViewsEvents()
    class LONGPRESS : ViewsEvents()
    class NONE : ViewsEvents()
}