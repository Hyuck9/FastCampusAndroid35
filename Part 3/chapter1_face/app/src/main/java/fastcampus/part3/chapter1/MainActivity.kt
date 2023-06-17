package fastcampus.part3.chapter1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import fastcampus.part3.chapter1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

	private lateinit var binding: ActivityMainBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater).apply {
			setContentView(root)

			startDetectButton.setOnClickListener {
				it.isVisible = false
			}
		}
	}
}