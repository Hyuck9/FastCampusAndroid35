package fastcampus.part1.chapter4

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.DatePicker
import androidx.core.view.isVisible
import fastcampus.part1.chapter4.databinding.ActivityEditBinding

class EditActivity : AppCompatActivity() {
	private lateinit var binding: ActivityEditBinding

	@SuppressLint("SetTextI18n")
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityEditBinding.inflate(layoutInflater)
		setContentView(binding.root)

		binding.bloodTypeSpinner.adapter = ArrayAdapter.createFromResource(
			this,
			R.array.blood_types,
			android.R.layout.simple_list_item_1
		)

		binding.birthLayer.setOnClickListener {
			val listener = OnDateSetListener { _, year, month, dayOfMonth ->
				binding.birthEditText.text = "$year-${month.inc()}-$dayOfMonth"
			}
			DatePickerDialog(
				this,
				listener,
				2000,
				1,
				1
			).show()
		}

		binding.warningCheckBox.setOnCheckedChangeListener { _, isChecked ->
			binding.warningEditText.isVisible = isChecked
		}

		binding.warningEditText.isVisible = binding.warningCheckBox.isChecked
	}
}