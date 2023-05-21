package fastcampus.part5.di

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

	override fun onCreate() {
		super.onCreate()

		KakaoSdk.init(this,"a3add53dfca8288cd175bde0b3b88ec3")
	}
}