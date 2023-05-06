package kr.co.fastcampus.part4.chapter3_17

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.co.fastcampus.part4.chapter3_17.ui.theme.ScaffoldTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			ScaffoldTheme {
				// A surface container using the 'background' color from the theme
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = MaterialTheme.colorScheme.background
				) {
					ScaffoldEx()
				}
			}
		}
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckBoxWithContent(
	checked: Boolean,
	toggleState: () -> Unit,
	content: @Composable RowScope.() -> Unit
) {
	Row(
		verticalAlignment = Alignment.CenterVertically,
		modifier = Modifier.clickable { toggleState() }
	) {
		Checkbox(
			checked = checked,
			onCheckedChange = { toggleState() },
		)
		content()
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldEx() {
	var checked by remember { mutableStateOf(false) }

	Scaffold(topBar = {
		// 스텝 1: `topBar`를 `TopAppBar`로 채워 봅시다.

	}) {
		Surface(modifier = Modifier.padding(it)) {
			// 스텝 2: 아래에 CheckBoxWithContent를 넣어봅시다.

		}
	}
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
	ScaffoldTheme {
		ScaffoldEx()
	}
}