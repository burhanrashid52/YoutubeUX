package ja.burhanrashid52.base

import android.app.Application
import ja.burhanrashid52.base.di.components.BaseNetworkComponent
import ja.burhanrashid52.base.di.components.DaggerBaseNetworkComponent
import ja.burhanrashid52.base.di.modules.BaseAppModule
import timber.log.Timber

/**
 * Created by Burhanuddin Rashid on 3/1/2018.
 */
open class BaseApplication : Application() {

    protected lateinit var baseDaggerNetworkComponent: BaseNetworkComponent

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        baseDaggerNetworkComponent =
                DaggerBaseNetworkComponent.builder()
                        .baseAppModule(BaseAppModule(this))
                        .build()
    }
}