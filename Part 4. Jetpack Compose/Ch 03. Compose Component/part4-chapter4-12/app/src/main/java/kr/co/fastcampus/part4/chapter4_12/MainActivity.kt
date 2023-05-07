package kr.co.fastcampus.part4.chapter4_12

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kr.co.fastcampus.part4.chapter4_12.ui.theme.BottomAppBarTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			BottomAppBarTheme {
				// A surface container using the 'background' color from the theme
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = MaterialTheme.colorScheme.background
				) {
					BottomAppBarEx()
				}
			}
		}
	}
}

@Composable
fun BottomAppBarEx() {
//	val scaffoldState = rememberScaffoldState()
	val coroutineScope = rememberCoroutineScope()

	// 단계 1: `Scaffold`에 `scaffoldState`를 설정합니다.

	// 단계 2: `bottomBar` 파라미터에 `BottomAppBar`를 넣읍시다.
	// 내용은 텍스트와 버튼을 넣어 봅시다. 버튼에는 `snackBar`를
	// 연동해 메시지를 출력합니다.

	// 단계 3: 더하기와 빼기 버튼을 추가로 만들고 `MutableState`
	// 만듭시다. `Scaffold`의 `content`에 `Text`를 넣어 카운터를 출력하게
	// 합시다.
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
	BottomAppBarTheme {
		BottomAppBarEx()
	}
}