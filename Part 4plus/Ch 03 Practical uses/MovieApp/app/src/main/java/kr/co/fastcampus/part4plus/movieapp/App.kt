package kr.co.fastcampus.part4plus.movieapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App : Application() {

	override fun onCreate() {
		super.onCreate()
		Timber.plant(Timber.DebugTree())
	}

}