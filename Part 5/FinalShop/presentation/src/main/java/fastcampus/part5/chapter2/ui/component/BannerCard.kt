package fastcampus.part5.chapter2.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import fastcampus.part5.chapter2.R
import fastcampus.part5.domain.model.Banner

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BannerCard(
	banner: Banner,
	onClick: (Banner) -> Unit
) {
	Card(
		shape = RoundedCornerShape(12.dp),
		modifier = Modifier
			.fillMaxWidth()
			.padding(10.dp)
			.shadow(20.dp),
		onClick = { onClick(banner) }
	) {
		Image(
			painter = painterResource(id = R.drawable.product_image),
			contentDescription = "description",
			contentScale = ContentScale.Crop,
			modifier = Modifier
				.fillMaxWidth()
				.aspectRatio(2f)    // width/height -> width : 2, height : 1 비율
		)
	}
}