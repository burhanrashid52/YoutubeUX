package ja.burhanrashid52.base

import android.support.v7.app.AppCompatActivity
import android.view.Window
import android.view.WindowManager

/**
 * Created by Burhanuddin on 2/21/2018.
 */
open class BaseActivity : AppCompatActivity() {

    fun makeFullScreen() {
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }
}