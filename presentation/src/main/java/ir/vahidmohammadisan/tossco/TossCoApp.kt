package ir.vahidmohammadisan.tossco

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TossCoApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}