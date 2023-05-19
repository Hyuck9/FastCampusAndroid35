package fastcampus.part5.chapter2.ui.main

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import fastcampus.part5.chapter2.ui.component.BannerCard
import fastcampus.part5.chapter2.ui.component.BannerListCard
import fastcampus.part5.chapter2.ui.component.CarouselCard
import fastcampus.part5.chapter2.ui.component.ProductCard
import fastcampus.part5.chapter2.viewmodel.MainViewModel
import fastcampus.part5.domain.model.*

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
				is Banner -> {
					BannerCard(banner = item) { banner ->
						viewModel.openBanner(banner)
					}
				}
				is BannerList -> {
					BannerListCard(bannerList = item) { bannerList ->
						viewModel.openBannerList(bannerList)
					}
				}
				is Product -> {
					ProductCard(product = item) { product ->
						viewModel.openProduct(product)
					}
				}
				is Carousel -> {
					CarouselCard(carousel = item) { product ->
						viewModel.openCarouselProduct(product)
					}
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
		ModelType.CAROUSEL -> defaultColumnCount
	}
}
