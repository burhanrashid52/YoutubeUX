/**
 * Created by Burhanuddin on 2/24/2018.
 */
object Versions {
    var application_id = "com.burhanrashid52.player"
    var version_code = 1
    var version_name = "1.0"
    var compile_sdk_version = 27
    var target_sdk_version = 27
    var min_sdk_version = 17

    internal var support_lib = "27.0.2"
    internal var constraint_version = "1.1.0"
    internal var kotlin_version = "1.2.31"
    internal var dagger_version = "2.14.1"

    internal var junit_version = "4.12"
    internal var runner_version = "1.0.1"
    internal var espresso_core_version = "3.0.1"

    internal var gradle_plugin_version = "3.1.1"

    internal const val retrofit_version = "2.3.0"
    internal const val okhttp_version = "3.10.0"
    internal const val oki_version = "1.14.0"
    internal const val life_cycle_version = "1.1.0"
    internal const val room_version = "1.0.0"
    internal const val paging_version = "1.0.0-alpha5"
    internal const val glide_version = "4.6.1"

}

object PluginsDeps {
    var gradle_plugin = "com.android.tools.build:gradle:${Versions.gradle_plugin_version}"
}

object KotlinDeps {
    var kotlin_std_lib = "org.jetbrains.kotlin:kotlin-stdlib-jre7:${Versions.kotlin_version}"
    var kotlin_reflection = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin_version}"
    var kotlin_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin_version}"
    var android_ktx = "androidx.core:core-ktx:0.2"
}

object SupportDeps {
    var appcompact_v7 = "com.android.support:appcompat-v7:${Versions.support_lib}"
    var desgin = "com.android.support:design:${Versions.support_lib}"
    var vector_drawable = "com.android.support:support-vector-drawable:${Versions.support_lib}"
    var constraint_layout = "com.android.support.constraint:constraint-layout:${Versions.constraint_version}"
}

object OtherDeps {
    var timber = "com.jakewharton.timber:timber:4.6.1"
    var beautyLogger = "com.github.ihsanbal:LoggingInterceptor:2.0.5"
    var glide = "com.github.bumptech.glide:glide:${Versions.glide_version}"
    var glide_processer = "com.github.bumptech.glide:compiler:${Versions.glide_version}"
}

object DaggerDeps {
    var main = "com.google.dagger:dagger-android:${Versions.dagger_version}"
    var support = "com.google.dagger:dagger-android-support:${Versions.dagger_version}"// if you use the support libraries
    var compiler = "com.google.dagger:dagger-compiler:${Versions.dagger_version}"
    var processer = "com.google.dagger:dagger-android-processor:${Versions.dagger_version}"
}

object TestDeps {
    var junit = "junit:junit:${Versions.junit_version}"
    var runner = "com.android.support.test:runner:${Versions.runner_version}"
    var espresso_core = "com.android.support.test.espresso:espresso-core:${Versions.espresso_core_version}"
    val test_live_data = "android.arch.core:core-testing:${Versions.life_cycle_version}"
    val test_room = "android.arch.persistence.room:testing:${Versions.room_version}"
}

object RetrofitDeps {
    var main = "com.squareup.retrofit2:retrofit:${Versions.retrofit_version}"
    var okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp_version}"
    var oki = "com.squareup.okio:okio:${Versions.oki_version}"
    var logging = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp_version}"
    var gson = "com.google.code.gson:gson:2.6.2"
    var gson_converter = "com.squareup.retrofit2:converter-gson:2.0.1"
}

object RxDeps {
    var main = "io.reactivex.rxjava2:rxjava:2.1.10"
    var android = "io.reactivex.rxjava2:rxandroid:2.0.2"
    var retrofit = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit_version}"
}

object ArchComponents {
    // ViewModel and LiveData
    val life_cycle_ext = "android.arch.lifecycle:extensions:${Versions.life_cycle_version}"
    val life_cycle_compiler = "android.arch.lifecycle:compiler:${Versions.life_cycle_version}"

    // alternatively, just ViewModel
    val view_model = "android.arch.lifecycle:viewmodel:${Versions.life_cycle_version}"
    // alternatively, just LiveData
    val live_data = "android.arch.lifecycle:livedata:${Versions.life_cycle_version}"

    var room = "android.arch.persistence.room:runtime:${Versions.room_version}"
    val room_compiler = "android.arch.persistence.room:compiler:${Versions.room_version}"

    val paging = "android.arch.paging:runtime:${Versions.paging_version}"

}