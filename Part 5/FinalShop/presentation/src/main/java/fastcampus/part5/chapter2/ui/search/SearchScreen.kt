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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import fastcampus.part5.chapter2.ui.component.ProductCard
import fastcampus.part5.chapter2.ui.theme.Purple200
import fastcampus.part5.chapter2.ui.theme.Purple500
import fastcampus.part5.chapter2.viewmodel.search.SearchViewModel
import fastcampus.part5.domain.model.SearchFilter
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SearchScreen(
	navHostController: NavHostController,
	viewModel: SearchViewModel = hiltViewModel()
) {
	val searchFilter by viewModel.searchFilters.collectAsState()
	val sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
	val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)
	val scope = rememberCoroutineScope()
	var currentFilterType by remember { mutableStateOf<SearchFilter.Type?>(null) }

	BottomSheetScaffold(
		scaffoldState = scaffoldState,
		sheetShape = RoundedCornerShape(10.dp, 10.dp, 0.dp, 0.dp),
		sheetContent = {
			when (currentFilterType) {
				SearchFilter.Type.CATEGORY -> {
					val categoryFilter = searchFilter.first { it is SearchFilter.CategoryFilter } as SearchFilter.CategoryFilter
					SearchFilterCategoryContent(filter = categoryFilter) {
						scope.launch {
							currentFilterType = null
							sheetState.collapse()
						}
						viewModel.updateFilter(it)
					}
				}
				SearchFilter.Type.PRICE -> {
					val priceFilter = searchFilter.first { it is SearchFilter.PriceFilter } as SearchFilter.PriceFilter
					SearchFilterPriceContent(filter = priceFilter) {
						scope.launch {
							currentFilterType = null
							sheetState.collapse()
						}
						viewModel.updateFilter(it)
					}
				}
				else -> {}
			}
		},
		sheetPeekHeight = 0.dp
	) {
		SearchContent(
			viewModel = viewModel,
			navHostController = navHostController
		) {
			scope.launch {
				currentFilterType = it
				sheetState.expand()
			}
		}
	}
}

@Composable
fun SearchFilterCategoryContent(
	filter: SearchFilter.CategoryFilter,
	onCompleteFilter: (SearchFilter) -> Unit
) {
	Column(
		modifier = Modifier
			.fillMaxWidth()
			.height(300.dp)
	) {
		Text(
			text = "카테고리 필터",
			fontWeight = FontWeight.SemiBold,
			fontSize = 20.sp,
			modifier = Modifier.padding(10.dp)
		)
		LazyColumn(
			modifier = Modifier.fillMaxSize(),
			contentPadding = PaddingValues(10.dp),
		) {
			items(filter.categories.size) { index ->
				val category = filter.categories[index]
				Button(
					onClick = {
						filter.selectedCategory = category
						onCompleteFilter(filter)
					},
					colors = ButtonDefaults.buttonColors(backgroundColor = if (filter.selectedCategory == category) Purple500 else Purple200)
				) {
					Text(
						fontSize = 18.sp,
						text = category.categoryName
					)
				}
			}
		}
	}
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SearchFilterPriceContent(
	filter: SearchFilter.PriceFilter,
	onCompleteFilter: (SearchFilter) -> Unit
) {
	var sliderValues by remember {
		val selectedRange = filter.selectedRange
		if (selectedRange == null) {
			mutableStateOf(filter.priceRange.first..filter.priceRange.second)
		} else {
			mutableStateOf(selectedRange.first..selectedRange.second)
		}
	}

	Column(
		modifier = Modifier
			.fillMaxWidth()
			.height(300.dp)
			.padding(20.dp)
	) {
		Row(modifier = Modifier.fillMaxWidth()) {
			Text(
				text = "가격 필터",
				fontWeight = FontWeight.SemiBold,
				fontSize = 20.sp,
				modifier = Modifier.padding(10.dp)
			)
			Spacer(modifier = Modifier.weight(1f))
			Button(
				onClick = {
					filter.selectedRange = sliderValues.start to sliderValues.endInclusive
					onCompleteFilter(filter)
				},
				colors = ButtonDefaults.buttonColors(backgroundColor = Purple200)
			) {
				Text(
					fontSize = 18.sp,
					text = "완료"
				)
			}
		}
		RangeSlider(
			values = sliderValues,
			onValueChange = {
				sliderValues = it
			},
			valueRange = filter.priceRange.first..filter.priceRange.second,
			steps = 9
		)
		Text(text = "최저가: ${sliderValues.start} ~ 최고가: ${sliderValues.endInclusive}")
	}
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchContent(
	viewModel: SearchViewModel,
	navHostController: NavHostController,
	openFilterDialog: (SearchFilter.Type) -> Unit
) {
	val searchResult by viewModel.searchResult.collectAsState()
	val searchFilters by viewModel.searchFilters.collectAsState()
	val searchKeywords by viewModel.searchKeywords.collectAsState(initial = listOf())
	var keyword by remember { mutableStateOf("") }
	val keyboardController = LocalSoftwareKeyboardController.current

	Column {
		SearchBox(
			keyword = keyword,
			onValueChange = { keyword = it },
			searchAction = {
				viewModel.search(keyword = keyword)
				keyboardController?.hide()
			}
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
					val currentKeyword = searchKeywords.reversed()[index].keyword
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
			Row(
				modifier = Modifier
					.fillMaxWidth()
					.padding(10.dp)
			) {
				Button(onClick = { openFilterDialog(SearchFilter.Type.CATEGORY) }) {
					val filter = searchFilters.find { it.type == SearchFilter.Type.CATEGORY } as? SearchFilter.CategoryFilter

					if (filter?.selectedCategory == null) {
						Text(text = "Category")
					} else {
						Text(text = "${filter.selectedCategory?.categoryName}")
					}
				}
				Spacer(modifier = Modifier.width(20.dp))
				Button(onClick = { openFilterDialog(SearchFilter.Type.PRICE) }) {
					val filter = searchFilters.find { it.type == SearchFilter.Type.PRICE } as? SearchFilter.PriceFilter

					if (filter?.selectedRange == null) {
						Text(text = "Price")
					} else {
						Text(text = "${filter.selectedRange?.first} ~ ${filter.selectedRange?.second}")
					}
				}
			}
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