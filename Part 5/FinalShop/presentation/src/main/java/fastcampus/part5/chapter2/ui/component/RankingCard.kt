package fastcampus.part5.chapter2.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import fastcampus.part5.domain.model.Product
import fastcampus.part5.domain.model.Ranking
import fastcampus.part5.chapter2.R

@OptIn(ExperimentalPagerApi::class)
@Composable
fun RankingCard(
	ranking: Ranking,
	onClick: (Product) -> Unit
) {
	val pagerState = rememberPagerState()
	val pageCount = ranking.productList.size / DEFAULT_RANKING_ITEM_COUNT

	Column {
		Text(
			text = ranking.title,
			fontSize = 16.sp,
			fontWeight = FontWeight.SemiBold,
			modifier = Modifier.padding(start = 10.dp)
		)
		HorizontalPager(
			count = pageCount,
			state = pagerState,
			contentPadding = PaddingValues(end = 50.dp)
		) { index ->
			Column {
				RankingProductCard(index * 3, ranking.productList[index * 3], onClick)
				RankingProductCard(index * 3 + 1, ranking.productList[index * 3 + 1], onClick)
				RankingProductCard(index * 3 + 2, ranking.productList[index * 3 + 2], onClick)
			}
		}
	}
}

@Composable
fun RankingProductCard(
	index: Int,
	product: Product,
	onClick: (Product) -> Unit
) {
	Row(
		modifier = Modifier
			.padding(10.dp)
			.fillMaxWidth()
	) {
		Text(
			text = "${index + 1}",
			fontWeight = FontWeight.Bold,
			modifier = Modifier.padding(end = 10.dp)
		)
		Image(
			painter = painterResource(id = R.drawable.product_image),
			contentDescription = "description",
			contentScale = ContentScale.Crop,
			modifier = Modifier
				.width(80.dp)
				.aspectRatio(0.7f)   // width/height -> width : 2, height : 1 비율
		)
		Column(
			modifier = Modifier.padding(start = 10.dp)
		) {
			Text(
				text = product.shop.shopName,
				fontSize = 14.sp,
				modifier = Modifier.padding(top = 10.dp)
			)
			Text(
				text = product.productName,
				fontSize = 14.sp,
				modifier = Modifier.padding(top = 10.dp)
			)
			Price(product = product)
		}
	}
}

private const val DEFAULT_RANKING_ITEM_COUNT = 3