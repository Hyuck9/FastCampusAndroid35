package kr.co.fastcampus.part4plus.movieapp.ui.components.movie

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.co.fastcampus.part4plus.movieapp.ui.theme.MovieAppTheme
import kr.co.fastcampus.part4plus.movieapp.ui.theme.Paddings

@Composable
fun CategoryRow() {
	Column {
		CategoryTitle("Action")
		LazyRow(
			contentPadding = PaddingValues(horizontal = 10.dp)
		) {
//			itemsIndexed
			item {
				MovieItem()
			}
			item {
				MovieItem()
			}
			item {
				MovieItem()
			}
			item {
				MovieItem()
			}
			item {
				MovieItem()
			}
		}
	}
}

@Composable
fun CategoryTitle(titleName: String) {
	Text(
		text = "Action",
		modifier = Modifier
			.padding(
				vertical = Paddings.large,
				horizontal = Paddings.extra
			),
		style = MaterialTheme.typography.h5
	)
}


@Preview
@Composable
fun CategoryRowPreview() {
	MovieAppTheme {
		CategoryRow()
	}
}