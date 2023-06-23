package fastcampus.part3.chapter2

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import fastcampus.part3.chapter2.databinding.ActivityIdentityInputBinding
import fastcampus.part3.chapter2.util.ViewUtil.hideKeyboard
import fastcampus.part3.chapter2.util.ViewUtil.setOnEditorActionListener
import fastcampus.part3.chapter2.util.ViewUtil.showKeyboard
import fastcampus.part3.chapter2.util.ViewUtil.showKeyboardDelay

class IdentityInputActivity : AppCompatActivity() {

	private lateinit var binding: ActivityIdentityInputBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityIdentityInputBinding.inflate(layoutInflater)
		setContentView(binding.root)
		binding.view = this
		initView()
		binding.nameEdit.showKeyboardDelay()
	}

	private fun initView() {
		with(binding) {
			nameEdit.setOnEditorActionListener(EditorInfo.IME_ACTION_NEXT) {
				birthdayLayout.isVisible = true
				birthdayEdit.showKeyboard()
			}

			birthdayEdit.doAfterTextChanged {
				if (birthdayEdit.length() > 7) {
					genderLayout.isVisible = true
					birthdayEdit.hideKeyboard()
				}
			}

			genderChipGroup.setOnCheckedStateChangeListener { group, checkedIds ->
				if (!telecomLayout.isVisible) {
					telecomLayout.isVisible = true
				}
			}

			telecomChipGroup.setOnCheckedStateChangeListener { group, checkedIds ->
				if (!phoneLayout.isVisible) {
					phoneLayout.isVisible = true
					phoneEdit.showKeyboard()
				}
			}

			phoneEdit.doAfterTextChanged {
				if (phoneEdit.length() > 10) {
					confirmButton.isVisible = true
					phoneEdit.hideKeyboard()
				}
			}

			phoneEdit.setOnEditorActionListener(EditorInfo.IME_ACTION_DONE) {
				if (phoneEdit.length() > 9) {
					confirmButton.isVisible = true
					phoneEdit.hideKeyboard()
				}
			}
		}
	}
}