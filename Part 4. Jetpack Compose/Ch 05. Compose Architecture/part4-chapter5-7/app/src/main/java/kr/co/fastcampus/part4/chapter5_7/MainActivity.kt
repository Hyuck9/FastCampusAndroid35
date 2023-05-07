package kr.co.fastcampus.part4.chapter5_7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.AndroidEntryPoint
import kr.co.fastcampus.part4.chapter5_7.ui.theme.DITheme
import kr.co.fastcampus.part4.chapter5_7.viewmodel.GithubViewModel

// 단계 3: Activity에 @AndroidEntryPoint를 넣어줍시다.
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			DITheme {
				// A surface container using the 'background' color from the theme
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = MaterialTheme.colorScheme.background
				) {
					ReposScreen()
				}
			}
		}
	}
}

@Composable
fun ReposScreen(viewModel: GithubViewModel = viewModel()) {
	LazyColumn {
		item {
			Button(onClick = {
				viewModel.getRepos()
			}) {
				Text("리포지토리 가져오기")
			}
		}
		items(viewModel.repos) {
			Text(it.name)
		}
	}
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
	DITheme {
		ReposScreen()
	}
}