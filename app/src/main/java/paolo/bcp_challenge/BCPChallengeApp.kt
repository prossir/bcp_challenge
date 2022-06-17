package paolo.bcp_challenge

import androidx.multidex.BuildConfig
import androidx.multidex.MultiDexApplication
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber


@HiltAndroidApp
class BCPChallengeApp: MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        initElegantDebugging()
        initBackwardsCompatibleTime()
        //WorkManagerAdmin.initWorkManagers(this)
    }

    private fun initElegantDebugging() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun initBackwardsCompatibleTime() {
        AndroidThreeTen.init(this)
    }

}