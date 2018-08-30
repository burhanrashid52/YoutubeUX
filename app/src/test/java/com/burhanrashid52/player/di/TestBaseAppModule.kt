package com.burhanrashid52.player.di

import android.app.Application
import dagger.Module
import dagger.Provides
import ja.burhanrashid52.base.di.modules.BaseAppModule
import ja.burhanrashid52.base.di.scopes.BaseScope

/**
 *
 * @author <a href="https://github.com/burhanrashid52">Burhanuddin Rashid</a>
 * @since 6/22/2018
 */

@Module
class TestBaseAppModule(private var mApplication: Application) : BaseAppModule(mApplication) {

    @Provides
    @BaseScope
    override fun provideApplication() = mApplication
}