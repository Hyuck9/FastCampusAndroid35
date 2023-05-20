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
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import fastcampus.part5.chapter2.R
import fastcampus.part5.chapter2.model.RankingVM
import fastcampus.part5.domain.model.Product

@OptIn(ExperimentalPagerApi::class)
@Composable
fun RankingCard(navHostController: NavHostController, presentationVM: RankingVM) {
	val pagerState = rememberPagerState()
	val pageCount = presentationVM.model.productList.size / DEFAULT_RANKING_ITEM_COUNT

	Column {
		Text(
			text = presentationVM.model.title,
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
				RankingProductCard(index * 3, presentationVM.model.productList[index * 3]) { product ->
					presentationVM.openRankingProduct(navHostController, product)
				}
				RankingProductCard(index * 3 + 1, presentationVM.model.productList[index * 3 + 1]) { product ->
					presentationVM.openRankingProduct(navHostController, product)
				}
				RankingProductCard(index * 3 + 2, presentationVM.model.productList[index * 3 + 2]) { product ->
					presentationVM.openRankingProduct(navHostController, product)
				}
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