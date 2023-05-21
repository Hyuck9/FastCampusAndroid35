package fastcampus.part5.chapter2.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import fastcampus.part5.chapter2.R
import fastcampus.part5.chapter2.delegate.ProductDelegate
import fastcampus.part5.chapter2.model.ProductVM
import fastcampus.part5.chapter2.ui.theme.Purple200
import fastcampus.part5.domain.model.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProductCard(navHostController: NavHostController, presentationVM: ProductVM) {
	Card(
		shape = RoundedCornerShape(8.dp),
		modifier = Modifier
			.fillMaxWidth()
			.height(intrinsicSize = IntrinsicSize.Max)
			.padding(10.dp)
			.shadow(elevation = 10.dp),
		onClick = { presentationVM.openProduct(navHostController, presentationVM.model) }
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
				fontWeight = FontWeight.SemiBold,
				text = presentationVM.model.shop.shopName
			)
			Text(
				fontSize = 14.sp,
				text = presentationVM.model.productName
			)
			Price(presentationVM.model)
		}
	}
}

@Composable
fun Price(product: Product) {
	when (product.price.salesStatus) {
		SalesStatus.ON_SALE -> {
			Text(
				fontSize = 18.sp,
				fontWeight = FontWeight.Bold,
				text = "${product.price.originPrice}원"
			)
		}
		SalesStatus.ON_DISCOUNT -> {
			Text(
				fontSize = 14.sp,
				text = "${product.price.originPrice}원",
				style = TextStyle(textDecoration = TextDecoration.LineThrough)
			)
			Text(
				fontSize = 18.sp,
				fontWeight = FontWeight.Bold,
				color = Purple200,
				text = "${product.price.finalPrice}원"
			)
		}
		SalesStatus.SOLD_OUT -> {
			Text(
				fontSize = 18.sp,
				color = Color(0xFF666666),
				text = "판매 종료"
			)
		}
	}
}



@Preview
@Composable
private fun PreviewProductCard() {
	ProductCard(
		rememberNavController(),
		ProductVM(
			model = Product(
				productId = "1",
				productName = "상품 이름",
				imageUrl = "",
				price = Price(
					30000,
					30000,
					SalesStatus.ON_SALE
				),
				category = Category.Top,
				shop = Shop(
					"1",
					"샵 이름",
					""
				),
				isNew = false,
				isFreeShipping = false
			),
			object : ProductDelegate {
				override fun openProduct(navHostController: NavHostController, product: Product) {}
			}
		)
	)
}

@Preview
@Composable
private fun PreviewProductCardDiscount() {
	ProductCard(
		rememberNavController(),
		ProductVM(
			model = Product(
				productId = "1",
				productName = "상품 이름",
				imageUrl = "",
				price = Price(
					30000,
					20000,
					SalesStatus.ON_DISCOUNT
				),
				category = Category.Top,
				shop = Shop(
					"1",
					"샵 이름",
					""
				),
				isNew = false,
				isFreeShipping = false
			),
			object : ProductDelegate {
				override fun openProduct(navHostController: NavHostController, product: Product) {}
			}
		)
	)
}

@Preview
@Composable
private fun PreviewProductCardSoldOut() {
	ProductCard(
		rememberNavController(),
		ProductVM(
			model = Product(
				productId = "1",
				productName = "상품 이름",
				imageUrl = "",
				price = Price(
					30000,
					20000,
					SalesStatus.SOLD_OUT
				),
				category = Category.Top,
				shop = Shop(
					"1",
					"샵 이름",
					""
				),
				isNew = false,
				isFreeShipping = false
			),
			object : ProductDelegate {
				override fun openProduct(navHostController: NavHostController, product: Product) {}
			}
		)
	)
}