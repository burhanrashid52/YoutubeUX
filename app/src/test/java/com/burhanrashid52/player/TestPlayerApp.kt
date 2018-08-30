package com.burhanrashid52.player

import com.burhanrashid52.player.di.TestBaseAppModule
import ja.burhanrashid52.base.di.components.DaggerBaseNetworkComponent

/**
 *
 * @author <a href="https://github.com/burhanrashid52">Burhanuddin Rashid</a>
 * @since 6/22/2018
 */

class TestPlayerApp : PlayerApp() {

    override fun onCreate() {
        super.onCreate()
        baseNetworkComponent = DaggerBaseNetworkComponent
                .builder()
                .baseAppModule(TestBaseAppModule(this))
                .build()
    }
}