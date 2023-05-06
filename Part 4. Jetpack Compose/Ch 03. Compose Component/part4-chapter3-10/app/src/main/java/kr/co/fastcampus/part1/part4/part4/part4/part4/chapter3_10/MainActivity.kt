package kr.co.fastcampus.part1.part4.part4.part4.part4.chapter3_10

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kr.co.fastcampus.part1.part4.part4.part4.part4.chapter3_10.ui.theme.ImageTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			ImageTheme {
				// A surface container using the 'background' color from the theme
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = MaterialTheme.colorScheme.background
				) {
					Greeting()
				}
			}
		}
	}
}

@Composable
fun Greeting() {
	Column {
		// 스텝 1: Image를 만들어봅시다.
		// painter 항목에 painterResource(id = R.drawable.wall)
		// contentDescription에 엔텔로프 캐년이라고 넣읍시다.

		// Image

		// 스텝 2: 두 번째 Image를 만들어봅시다.
		// imageVector에 Icons.Filled.Settings를 설정해봅시다.

		// Image
	}
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
	ImageTheme {
		Greeting()
	}
}