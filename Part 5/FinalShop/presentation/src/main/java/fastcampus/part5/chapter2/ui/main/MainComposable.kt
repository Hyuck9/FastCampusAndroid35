package fastcampus.part5.chapter2.ui.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import fastcampus.part5.chapter2.R
import fastcampus.part5.chapter2.ui.component.BannerCard
import fastcampus.part5.chapter2.ui.component.BannerListCard
import fastcampus.part5.chapter2.ui.component.ProductCard
import fastcampus.part5.chapter2.viewmodel.MainViewModel
import fastcampus.part5.domain.model.Banner
import fastcampus.part5.domain.model.BannerList
import fastcampus.part5.domain.model.ModelType
import fastcampus.part5.domain.model.Product

@Composable
fun MainInsideScreen(viewModel: MainViewModel) {
	val modelList by viewModel.modelList.collectAsState(initial = listOf())
	val columnCount by viewModel.columCount.collectAsState()

	LazyVerticalGrid(columns = GridCells.Fixed(columnCount)) {
		items(
			count = modelList.size,
			span = { index ->
				val item = modelList[index]
				val spanCount = getSpanCountByType(item.type, columnCount)
				GridItemSpan(spanCount)
			}
		) {
			when (val item = modelList[it]) {
				is Product -> {
					ProductCard(product = item) {
						// TODO: 상세 화면 개발 시 추가
					}
				}
				is Banner -> {
					BannerCard(banner = item)
				}
				is BannerList -> {
					BannerListCard(model = item)
				}
			}
		}
	}
}

private fun getSpanCountByType(type: ModelType, defaultColumnCount: Int): Int {
	return when (type) {
		ModelType.PRODUCT -> 1
		ModelType.BANNER -> defaultColumnCount
		ModelType.BANNER_LIST -> defaultColumnCount
	}
}
