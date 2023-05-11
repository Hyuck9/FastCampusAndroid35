package kr.co.fastcampus.part4plus.movieapp.ui.components.movie

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.co.fastcampus.part4plus.movieapp.R
import kr.co.fastcampus.part4plus.movieapp.ui.theme.Paddings

private val CARD_WIDTH = 150.dp
private val ICON_SIZE = 12.dp

@Composable
fun MovieItem() {
	Column(
		modifier = Modifier
			.width(CARD_WIDTH)
			.padding(Paddings.large)
	) {
		Poster(
			modifier = Modifier
				.width(CARD_WIDTH)
		)

		Text(
			text = "The Lord the Ring 1",
			maxLines = 1,
			overflow = TextOverflow.Ellipsis,
			modifier = Modifier
				.padding(top = Paddings.large),
			style = MaterialTheme.typography.body2
		)

		Row(
			modifier = Modifier
				.padding(vertical = Paddings.medium),
			verticalAlignment = Alignment.CenterVertically
		) {
			Icon(
				modifier = Modifier
					.padding(Paddings.small)
					.size(ICON_SIZE),
				imageVector = ImageVector.vectorResource(id = R.drawable.ic_rating),
				tint = Color.Black.copy(alpha = 0.5f),
				contentDescription = "rating icon"
			)
			Text(
				text = "5.0",
				style = MaterialTheme.typography.body2,
				color = MaterialTheme.colors.onSurface.copy(
					alpha = 0.5f
				)
			)
		}
	}
}

@Composable
fun Poster(
	modifier: Modifier
) {
	Card(
		modifier = Modifier
			.fillMaxWidth()
			.height(200.dp)
	) {
		Box(
			modifier = Modifier.background(Color.Blue)
		)
	}
}

@Preview
@Composable
fun MovieItemPreview() {
	MovieItem()
}