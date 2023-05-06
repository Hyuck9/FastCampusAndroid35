package kr.co.fastcampus.part4.chapter4_6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import kr.co.fastcampus.part4.chapter4_6.ui.theme.Card2Theme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			Card2Theme {
				// A surface container using the 'background' color from the theme
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = MaterialTheme.colorScheme.background
				) {
					Column(modifier = Modifier.fillMaxWidth()) {
						CardEx(cardData)
						CardEx(cardData)
						CardEx(cardData)
					}
				}
			}
		}
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardEx(cardData: CardData) {
	val placeHolderColor = Color(0x33000000)

	Card(
		elevation = CardDefaults.cardElevation(8.dp),
		modifier = Modifier.padding(4.dp),
	) {
		// 단계 1: 아래의 Row 레이아웃을 ConstraintLayout로 바꾸어 봅시다.
		ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
		}

		Row(
			verticalAlignment = Alignment.CenterVertically,
			modifier = Modifier.padding(8.dp)
		) {
			AsyncImage(
				model = cardData.imageUri,
				contentDescription = cardData.imageDescription,
				contentScale = ContentScale.Crop,
				placeholder = ColorPainter(color = placeHolderColor),
				modifier = Modifier
					.clip(CircleShape)
					.size(40.dp)
			)
			Spacer(modifier = Modifier.size(8.dp))
			Column {
				Text(text = cardData.author)
				Text(text = cardData.description)
			}
		}
	}
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
	Card2Theme {
		CardEx(cardData)
	}
}

data class CardData(
	val imageUri: String,
	val imageDescription: String,
	val author: String,
	val description: String
)

val cardData = CardData(
	imageUri = "https://raw.githubusercontent.com/Fastcampus-Android-Lecture-Project-2023/part4-chapter3/main/part4-chapter3-10/app/src/main/res/drawable-xhdpi/wall.jpg",
	imageDescription = "엔텔로프 캐년",
	author = "Dalinaum",
	description = "엔텔로프 캐년은 죽기 전에 꼭 봐야할 절경으로 소개되었습니다."
)