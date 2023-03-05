package kr.co.fastcampus.part1.chapter2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		val numberTextView = findViewById<TextView>(R.id.numberTextView)
		val resetButton = findViewById<Button>(R.id.resetButton)
		val plusButton = findViewById<Button>(R.id.plusButton)

		resetButton.setOnClickListener { Log.d("onClick", "리셋 버튼 클릭") }
		plusButton.setOnClickListener { Log.d("onClick", "플러스 버튼 클릭") }
	}
}