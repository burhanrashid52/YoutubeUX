package com.burhanrashid52.player

import ja.burhanrashid52.base.BaseApplication
import ja.burhanrashid52.base.di.components.BaseNetworkComponent


/**
 * Created by Burhanuddin on 2/25/2018.
 */
class PlayerApp : BaseApplication() {

    companion object {
        lateinit var baseNetworkComponent: BaseNetworkComponent
    }

    override fun onCreate() {
        super.onCreate()
        baseNetworkComponent = baseDaggerNetworkComponent
    }
}