package com.burhanrashid52.player.di.components

import com.burhanrashid52.player.dashboard.DashboardViewModel
import com.burhanrashid52.player.dashboard.HomeActivity
import com.burhanrashid52.player.di.modules.AppComponent
import com.burhanrashid52.player.di.scopes.AppScope
import dagger.Component
import ja.burhanrashid52.base.di.components.BaseNetworkComponent

/**
 * Created by Burhanuddin Rashid on 3/1/2018.
 */

@AppScope
@Component(dependencies = [BaseNetworkComponent::class], modules = [AppComponent::class])
interface ActivityComponent {
    fun inject(dashboardViewModel: DashboardViewModel)
    fun inject(dashboardViewModel: HomeActivity)
}

