package ja.burhanrashid52.base.di.modules

import android.app.Application

import dagger.Module
import dagger.Provides
import ja.burhanrashid52.base.di.scopes.BaseScope

@Module
open class BaseAppModule(private var mApplication: Application) {

    @Provides
    @BaseScope
    open fun provideApplication() = mApplication
}