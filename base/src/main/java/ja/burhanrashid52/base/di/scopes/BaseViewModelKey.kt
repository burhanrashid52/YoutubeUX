package ja.burhanrashid52.base.di.scopes

import android.arch.lifecycle.ViewModel
import java.lang.annotation.Retention
import dagger.MapKey
import java.lang.annotation.RetentionPolicy
import kotlin.reflect.KClass

@MustBeDocumented
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER)
@Retention(RetentionPolicy.RUNTIME)
@MapKey
annotation class BaseViewModelKey(val value: KClass<out ViewModel>)