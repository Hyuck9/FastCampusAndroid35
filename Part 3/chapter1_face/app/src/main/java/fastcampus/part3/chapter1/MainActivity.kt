package fastcampus.part3.chapter1

import android.graphics.PointF
import android.graphics.RectF
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.SizeF
import androidx.core.view.isVisible
import androidx.transition.TransitionManager
import fastcampus.part3.chapter1.databinding.ActivityMainBinding
import fastcampus.part3.face_recognition.Camera
import fastcampus.part3.face_recognition.recognition.FaceAnalyzerListener

class MainActivity : AppCompatActivity(), FaceAnalyzerListener {

	private lateinit var binding: ActivityMainBinding

	private val camera = Camera(this)

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)
		setProgressText("시작하기를 눌러주세요.")
		camera.initCamera(binding.cameraLayout, this@MainActivity)

		binding.startDetectButton.setOnClickListener {
			it.isVisible = false
			binding.faceOverlayView.reset()
			camera.startFaceDetect()
			setProgressText("얼굴을 보여주세요.")
		}

	}

	override fun detect() {}
	override fun stopDetect() {
		camera.stopFaceDetect()
		reset()
	}

	override fun notDetect() {
		binding.faceOverlayView.reset()
	}
	override fun detectProgress(progress: Float, message: String) {
		setProgressText(message)
		binding.faceOverlayView.setProgress(progress)
	}

	override fun faceSize(rectF: RectF, sizeF: SizeF, pointF: PointF) {
		binding.faceOverlayView.setSize(rectF, sizeF, pointF)
	}


	override fun onRequestPermissionsResult(
		requestCode: Int,
		permissions: Array<out String>,
		grantResults: IntArray
	) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults)
		camera.onRequestPermissionsResult(requestCode, permissions, grantResults)
	}

	private fun reset() {
		binding.startDetectButton.isVisible = true
	}

	/**
	 * 텍스트가 변경될 때마다 자동으로 애니메이션 처리
	 */
	private fun setProgressText(text: String) {
		TransitionManager.beginDelayedTransition(binding.root)
		binding.progressTextView.text = text
	}
}