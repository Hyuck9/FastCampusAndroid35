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
				if (validName()) {
					nameLayout.error = null
					if (phoneLayout.isVisible) {
						confirmButton.isVisible = true
					} else {
						birthdayLayout.isVisible = true
						birthdayEditText.showKeyboard()
					}
				} else {
					confirmButton.isVisible = false
					nameLayout.error = "1자 이상의 한글을 입력해주세요."
				}
			}

			birthdayEditText.doAfterTextChanged {
				if (birthdayEditText.length() > 7) {
					if (validBirthday()) {
						birthdayLayout.error = null
						if (phoneLayout.isVisible) {
							confirmButton.isVisible = true
						} else {
							genderLayout.isVisible = true
							birthdayEditText.hideKeyboard()
						}
					} else {
						confirmButton.isVisible = false
						birthdayLayout.error = "생년월일 형식이 다릅니다."
					}
				}
			}

			birthdayEditText.setOnEditorActionListener(EditorInfo.IME_ACTION_DONE) {
				val isValid = validBirthday() && birthdayEditText.length() > 7
				if (isValid) {
					confirmButton.isVisible = phoneLayout.isVisible
					birthdayLayout.error = null
				} else {
					birthdayLayout.error = "생년월일 형식이 다릅니다."
				}
				birthdayEditText.hideKeyboard()
			}

			genderChipGroup.setOnCheckedStateChangeListener { group, checkedIds ->
				if (!telecomLayout.isVisible) {
					telecomLayout.isVisible = true
				}
			}

			telecomChipGroup.setOnCheckedStateChangeListener { group, checkedIds ->
				if (!phoneLayout.isVisible) {
					phoneLayout.isVisible = true
					phoneEditText.showKeyboard()
				}
			}

			phoneEditText.doAfterTextChanged {
				if (phoneEditText.length() > 10) {
					if (validPhone()) {
						phoneLayout.error = null
						confirmButton.isVisible = true
						phoneEditText.hideKeyboard()
					} else {
						phoneLayout.error = "전화번호 형식이 다릅니다."
						confirmButton.isVisible = false
					}
				}
			}

			phoneEditText.setOnEditorActionListener(EditorInfo.IME_ACTION_DONE) {
				confirmButton.isVisible = phoneEditText.length() > 9 && validPhone()
				phoneEditText.hideKeyboard()
			}
		}
	}

	fun onClickDone() {
		if (!validName()) {
			binding.nameLayout.error = "1자이상의 한글을 입력해주세요."
			return
		}

		if (!validBirthday()) {
			binding.birthdayLayout.error = "생년월일 형식이 다릅니다."
			return
		}

		if (!validPhone()) {
			binding.phoneLayout.error = "전화번호 형식이 다릅니다."
			return
		}
	}

	private fun validName() = !binding.nameEdit.text.isNullOrBlank()
			&& REGEX_NAME.toRegex().matches(binding.nameEdit.text!!)

	private fun validBirthday() = !binding.birthdayEditText.text.isNullOrBlank()
			&& REGEX_BIRTHDAY.toRegex().matches(binding.birthdayEditText.text!!)

	private fun validPhone() = !binding.phoneEditText.text.isNullOrBlank()
			&& REGEX_PHONE.toRegex().matches(binding.phoneEditText.text!!)


	companion object {
		private const val REGEX_NAME = "^[가-힣]{2,}\$"
		private const val REGEX_BIRTHDAY = "^(19|20)[0-9]{2}(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])"
		private const val REGEX_PHONE = "^01([016789])([0-9]{3,4})([0-9]{4})"
	}
}