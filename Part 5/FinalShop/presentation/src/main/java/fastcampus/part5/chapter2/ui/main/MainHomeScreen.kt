package fastcampus.part5.chapter2.ui.main

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import fastcampus.part5.chapter2.model.*
import fastcampus.part5.chapter2.ui.component.*
import fastcampus.part5.chapter2.viewmodel.MainViewModel
import fastcampus.part5.domain.model.ModelType

@Composable
fun MainHomeScreen(navController: NavHostController, viewModel: MainViewModel) {
	val modelList by viewModel.modelList.collectAsState(initial = listOf())
	val columnCount by viewModel.columCount.collectAsState()

	val testId = "ca-app-pub-3940256099942544/6300978111"
	val adId = "ca-app-pub-3897260489773203/2972373963"
	val adRequest = AdRequest.Builder().build()

	LazyVerticalGrid(columns = GridCells.Fixed(columnCount)) {
		items(
			count = modelList.size,
			span = { index ->
				val item = modelList[index]
				val spanCount = getSpanCountByType(item.model.type, columnCount)
				GridItemSpan(spanCount)
			}
		) {
			when (val item = modelList[it]) {
				is BannerVM -> BannerCard(presentationVM = item)
				is BannerListVM -> BannerListCard(presentationVM = item)
				is ProductVM -> ProductCard(navHostController = navController, presentationVM = item)
				is CarouselVM -> CarouselCard(navHostController = navController, presentationVM = item)
				is RankingVM -> RankingCard(navHostController = navController, presentationVM = item)
			}
		}
	}

	AndroidView(modifier = Modifier
		.fillMaxWidth()
		.height(50.dp),
		factory = {
			AdView(it).apply {
				setAdSize(AdSize.BANNER)
				adUnitId = testId
				loadAd(adRequest)
			}
		}, update = {
			it.loadAd(adRequest)
		})
}

private fun getSpanCountByType(type: ModelType, defaultColumnCount: Int): Int {
	return when (type) {
		ModelType.PRODUCT -> 1
		ModelType.BANNER,
		ModelType.BANNER_LIST,
		ModelType.CAROUSEL,
		ModelType.RANKING -> defaultColumnCount
	}
}
