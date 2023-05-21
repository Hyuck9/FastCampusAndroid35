package fastcampus.part5.chapter2.ui.product_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import fastcampus.part5.chapter2.R
import fastcampus.part5.chapter2.ui.theme.Purple200
import fastcampus.part5.chapter2.viewmodel.product_detail.ProductDetailViewModel

@Composable
fun ProductDetailScreen(
	productId: String,
	viewModel: ProductDetailViewModel = hiltViewModel()
) {
	val product by viewModel.product.collectAsState()

	LaunchedEffect(key1 = productId) {
		viewModel.updateProduct(productId)
	}

	Column(
		modifier = Modifier.fillMaxSize()
	) {
		Box(
			modifier = Modifier.weight(1f),
			contentAlignment = Alignment.Center
		) {
			Image(
				painter = painterResource(id = R.drawable.product_image),
				contentDescription = null,
				modifier = Modifier
					.fillMaxWidth()
					.height(250.dp)
					.align(Alignment.Center),
				contentScale = ContentScale.Crop
			)
		}
		Spacer(modifier = Modifier.height(12.dp))
		Card(
			modifier = Modifier
				.fillMaxWidth()
				.weight(2f),
			elevation = 0.dp,
			shape = RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp)
		) {
			Column(
				modifier = Modifier.padding(16.dp),
				verticalArrangement = Arrangement.Top
			) {
				Row(
					verticalAlignment = Alignment.CenterVertically,
					modifier = Modifier.padding(10.dp)
				) {
					Card(
						modifier = Modifier.size(50.dp),
						shape = CircleShape
					) {
						Image(
							painter = painterResource(id = R.drawable.product_image),
							contentDescription = null,
							modifier = Modifier
								.fillMaxSize(),
							contentScale = ContentScale.Crop
						)
					}
					Text(
						text = "${product?.shop?.shopName}에서 판매중인 상품",
						fontSize = 16.sp
					)
				}
				Text(
					text = "${product?.productName}",
					fontWeight = FontWeight.SemiBold,
					fontSize = 18.sp
				)
				Spacer(modifier = Modifier.height(12.dp))
				Text(
					text = "${product?.productName}의 상품 상세 페이지 설명글입니다.",
					fontSize = 12.sp
				)
			}
		}
		Row(
			modifier = Modifier
				.fillMaxWidth()
				.padding(16.dp),
			verticalAlignment = Alignment.Bottom
		) {
			Text(
				text = "${product?.price?.finalPrice}",
				fontSize = 24.sp,
				fontWeight = FontWeight.Bold
			)

			Spacer(modifier = Modifier.width(12.dp))

			Button(
				onClick = { viewModel.addCard(productId) },
				colors = ButtonDefaults.buttonColors(
					backgroundColor = Purple200
				),
				shape = RoundedCornerShape(12.dp)
			) {
				Text(
					text = "카트에 담기",
					modifier = Modifier
						.fillMaxWidth()
						.padding(5.dp),
					fontSize = 16.sp
				)
			}
		}
	}
}