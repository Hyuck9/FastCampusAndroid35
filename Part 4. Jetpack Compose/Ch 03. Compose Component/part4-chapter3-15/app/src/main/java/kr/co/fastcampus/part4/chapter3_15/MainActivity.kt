package kr.co.fastcampus.part4.chapter3_15

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kr.co.fastcampus.part4.chapter3_15.ui.theme.TopAppBarTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			TopAppBarTheme {
				// A surface container using the 'background' color from the theme
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = MaterialTheme.colorScheme.background
				) {
					TopBarEx("Android")
				}
			}
		}
	}
}

@Composable
fun TopBarEx(name: String) {
	Column {
		// 스텝 1: TopAppBar를 만들고 title 항목을 채워봅시다.
//		SmallTopAppBar(title = { Text("TopAppBar") })

		// 스텝 2: navigationIcon 파라미터를 채워봅시다.
		// IconButton을 만들고 자식으로 Icon을 넣읍시다.
		// 아이콘은 Icons.Filled.ArrowBack을 채웁시다.
		// onClick은 비워둡시다.
//		SmallTopAppBar(
//			title = { Text("TopAppBar") },
//			navigationIcon = {
//				IconButton(onClick = { /*TODO*/ }) {
//					Icon(
//						imageVector = Icons.Filled.ArrowBack,
//						contentDescription = "업 네비게이션"
//					)
//				}
//			}
//		)

		// 스텝 3: actions를 추가해봅시다.
		// Icons.Filled의 여러 아이콘을 이용해봅시다.
		SmallTopAppBar(
			title = { Text("TopAppBar") },
			navigationIcon = {
				IconButton(onClick = { /*TODO*/ }) {
					Icon(
						imageVector = Icons.Filled.ArrowBack,
						contentDescription = "업 네비게이션"
					)
				}
			},
			actions = {
				IconButton(onClick = { /*TODO*/ }) {
					Icon(
						imageVector = Icons.Filled.Search,
						contentDescription = "검색"
					)
				}
				IconButton(onClick = { /*TODO*/ }) {
					Icon(
						imageVector = Icons.Filled.Settings,
						contentDescription = "설정"
					)
				}
				IconButton(onClick = { /*TODO*/ }) {
					Icon(
						imageVector = Icons.Filled.AccountBox,
						contentDescription = "계정"
					)
				}
			}
		)

		// 스텝 4: TopAppBar content 파라미터 버전을 만들어봅시다.
		// TODO: Material3는 없는 것 같음?

		Text(text = "Hello $name!")
	}
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
	TopAppBarTheme {
		TopBarEx("Android")
	}
}