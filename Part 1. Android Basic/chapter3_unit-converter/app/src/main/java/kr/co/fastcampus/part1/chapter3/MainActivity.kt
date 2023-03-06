package kr.co.fastcampus.part1.chapter3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.widget.addTextChangedListener
import kr.co.fastcampus.part1.chapter3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

	private lateinit var binding: ActivityMainBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)

		val outputTextView = binding.outputTextView
		val outputUnitTextView = binding.outputUnitTextView
		val inputEditText = binding.inputEditText
		val inputUnitTextView = binding.inputUnitTextView
		val swapImageButton = binding.swapImageBugtton
		var cmToM = true

		var inputNumber = 0

		inputEditText.addTextChangedListener { text ->
			inputNumber = if (text.isNullOrEmpty()) 0 else text.toString().toInt()
			Log.d("inputNumber", inputNumber.toString())

			if (cmToM) {
				outputTextView.text = inputNumber.times(0.01).toString()
			} else {
				outputTextView.text = inputNumber.times(100).toString()
			}
		}
		swapImageButton.setOnClickListener {
			cmToM = cmToM.not()
			if (cmToM) {
				inputUnitTextView.text = "cm"
				outputUnitTextView.text = "m"
				outputTextView.text = inputNumber.times(0.01).toString()
			} else {
				inputUnitTextView.text = "m"
				outputUnitTextView.text = "cm"
				outputTextView.text = inputNumber.times(100).toString()
			}
		}
	}
}