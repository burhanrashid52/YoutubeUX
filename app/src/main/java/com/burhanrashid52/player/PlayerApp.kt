package com.burhanrashid52.player

import android.content.Context
import android.support.multidex.MultiDex
import ja.burhanrashid52.base.BaseApplication
import ja.burhanrashid52.base.di.components.BaseNetworkComponent


/**
 * Created by Burhanuddin on 2/25/2018.
 */
open class PlayerApp : BaseApplication() {

    companion object {
        lateinit var baseNetworkComponent: BaseNetworkComponent
    }

    override fun onCreate() {
        super.onCreate()
        baseNetworkComponent = baseDaggerNetworkComponent
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}