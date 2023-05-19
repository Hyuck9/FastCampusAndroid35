package fastcampus.part5.chapter2.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fastcampus.part5.chapter2.R
import fastcampus.part5.domain.model.Carousel
import fastcampus.part5.domain.model.Product

@Composable
fun CarouselCard(
	carousel: Carousel,
	onClick: (Product) -> Unit
) {
	val scrollState = rememberLazyListState()
	Column {
		Text(
			fontSize = 14.sp,
			fontWeight = FontWeight.SemiBold,
			text = carousel.title,
			modifier = Modifier.padding(10.dp)
		)
		LazyRow(
			state = scrollState,
			modifier = Modifier
				.fillMaxWidth()
				.wrapContentHeight()
		) {
			items(carousel.productList.size) {
				CarouselProductCard(
					product = carousel.productList[it],
					onClick = onClick
				)
			}
		}
	}
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun CarouselProductCard(
	product: Product,
	onClick: (Product) -> Unit
) {
	Card(
		shape = RoundedCornerShape(8.dp),
		modifier = Modifier
			.width(150.dp)
			.wrapContentHeight()
			.padding(10.dp),
		onClick = { onClick(product) }
	) {
		Column(
			modifier = Modifier
				.fillMaxWidth()
				.padding(10.dp),
			verticalArrangement = Arrangement.Center,
			horizontalAlignment = Alignment.Start
		) {
			Image(
				painter = painterResource(id = R.drawable.product_image),
				contentScale = ContentScale.Crop,
				contentDescription = "description",
				modifier = Modifier
					.fillMaxWidth()
					.aspectRatio(1f)
			)
			Text(
				fontSize = 14.sp,
				text = product.productName
			)
			Price(product)
		}
	}
}