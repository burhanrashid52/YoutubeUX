package ja.burhanrashid52.base

import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.Window
import android.view.WindowManager

/**
 * Created by Burhanuddin on 2/21/2018.
 */
open class BaseActivity : AppCompatActivity() {

    /**
     * Is toggle the system UI flag i.e status and navigation bar for fullscreen mode
     */
    fun enableFullScreen(isEnabled: Boolean) {
        if (isEnabled) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        } else {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
        }
    }
}