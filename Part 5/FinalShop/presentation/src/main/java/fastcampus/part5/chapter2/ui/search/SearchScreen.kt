package fastcampus.part5.chapter2.ui.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import fastcampus.part5.chapter2.ui.component.ProductCard
import fastcampus.part5.chapter2.viewmodel.search.SearchViewModel

@Composable
fun SearchScreen(
	navHostController: NavHostController,
	viewModel: SearchViewModel = hiltViewModel()
) {
	val searchResult by viewModel.searchResult.collectAsState()
	val searchKeywords by viewModel.searchKeywords.collectAsState(initial = listOf())
	var keyword by remember { mutableStateOf("") }

	Column {
		SearchBox(
			keyword = keyword,
			onValueChange = { keyword = it },
			searchAction = { viewModel.search(keyword = keyword) }
		)
		
		if (searchResult.isEmpty()) {
			Text(
				modifier = Modifier.padding(6.dp),
				fontSize = 20.sp,
				fontWeight = FontWeight.Bold,
				text = "최근 검색어"
			)
			LazyColumn(
				modifier = Modifier.fillMaxSize(),
				contentPadding = PaddingValues(10.dp)
			) {
				items(searchKeywords.size) { index ->
					val currentKeyword = searchKeywords.reversed()[index].keyword   // 가장 최근 검색어가 상단으로 오도록
					Button(
						onClick = {
							keyword = currentKeyword
							viewModel.search(keyword)
						},
						colors = ButtonDefaults.buttonColors(backgroundColor = Color.Unspecified)
					) {
						Text(
							text = currentKeyword,
							fontSize = 18.sp
						)
					}
				}
			}
		} else {
			LazyColumn(
				modifier = Modifier.fillMaxSize(),
				contentPadding = PaddingValues(10.dp)
			) {
				items(searchResult.size) { index -> 
					ProductCard(
						navHostController = navHostController,
						presentationVM = searchResult[index]
					)
				}
			}
		}
	}
}

@Composable
fun SearchBox(
	keyword: String,
	onValueChange: (String) -> Unit,
	searchAction: () -> Unit
) {
	Row(Modifier.fillMaxWidth()) {
		TextField(
			value = keyword,
			onValueChange = onValueChange,
			placeholder = { Text(text = "검색어를 입력해주세요.") },
			shape = RoundedCornerShape(8.dp),
			keyboardOptions = KeyboardOptions(
				imeAction = ImeAction.Search,
				keyboardType = KeyboardType.Text,
				capitalization = KeyboardCapitalization.Words,
				autoCorrect = true
			),
			keyboardActions = KeyboardActions(
				onSearch = { searchAction() }
			),
			modifier = Modifier
				.fillMaxWidth()
				.padding(10.dp),
			maxLines = 1,
			singleLine = true,
			leadingIcon = { Icon(Icons.Filled.Search, "SearchIcon") }
		)
	}
}