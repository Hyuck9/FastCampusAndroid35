package kr.co.fastcampus.part4.chapter3_13

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kr.co.fastcampus.part4.chapter3_13.ui.theme.CheckBoxTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			CheckBoxTheme {
				// A surface container using the 'background' color from the theme
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = MaterialTheme.colorScheme.background
				) {
					CheckBoxEx()
				}
			}
		}
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckBoxEx() {
	Row(verticalAlignment = Alignment.CenterVertically) {
		// 스텝 1: Checkbox를 만들어봅시다. checked 속성은 false
		// onCheckedChange는 비워둡시다.
//		Checkbox(checked = false, onCheckedChange = {})

		// 스텝 2: onCheckedChange에서 boolean 값 변수를 바꾸고
		// checked에서 그 값을 반영해봅시다. (잘 되지 않습니다.)
//		var checked = false
//		Checkbox(
//			checked = checked,
//			onCheckedChange = {
//				checked  = !checked
//			}
//		)

//		// destruction (비구조화, 반구조화, 구조분해)
//		val (a, b) = listOf(2, 3)

		// 스텝 3: boolean 대신 remember { mutableStateOf(false) }를
		// 사용하여 상태를 도입합시다. (value 프로퍼티를 이용해야 합니다.)
//		val checked = remember { mutableStateOf(false) }
//		Checkbox(
//			checked = checked.value,
//			onCheckedChange = {
//				checked.value  = !checked.value
//			}
//		)

		// delegated properties : 위임된 속성
		// checked가 프로퍼티인 것 처럼
		// 스텝 4: delegated properties로 변경해봅시다.
//		var checked by remember { mutableStateOf(false) }
//		Checkbox(
//			checked = checked,
//			onCheckedChange = {
//				checked  = !checked
//			}
//		)

		// 비구조화
		// 스텝 5: destruction으로 상태를 받아서 사용해봅시다.
		val (checked, setChecked) = remember { mutableStateOf(false) }
		Checkbox(
			checked = checked,
			onCheckedChange = setChecked
		)

		// Checkbox를 앞에 넣어주세요.
		Text(
			text = "프로그래머입니까?",
			modifier = Modifier.clickable {
				setChecked(!checked)
			}
		)
	}
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
	CheckBoxTheme {
		CheckBoxEx()
	}
}