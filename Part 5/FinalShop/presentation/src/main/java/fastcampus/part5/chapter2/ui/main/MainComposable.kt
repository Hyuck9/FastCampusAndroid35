package fastcampus.part5.chapter2.ui.main

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import fastcampus.part5.chapter2.ui.common.ProductCard
import fastcampus.part5.chapter2.viewmodel.MainViewModel

@Composable
fun MainInsideScreen(viewModel: MainViewModel) {
	val productList by viewModel.productList.collectAsState(initial = listOf())
	LazyColumn {
		items(productList.size) {
			ProductCard(product = productList[it]) {
				// TODO: 상세 화면 개발 시 추가
			}
		}
	}
}