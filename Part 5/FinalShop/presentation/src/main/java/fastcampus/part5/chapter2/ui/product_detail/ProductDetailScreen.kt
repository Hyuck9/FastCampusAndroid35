package fastcampus.part5.chapter2.ui.product_detail

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun ProductDetailScreen(productId: String) {
	Text(text = "ProductDetailScreen - $productId")
}