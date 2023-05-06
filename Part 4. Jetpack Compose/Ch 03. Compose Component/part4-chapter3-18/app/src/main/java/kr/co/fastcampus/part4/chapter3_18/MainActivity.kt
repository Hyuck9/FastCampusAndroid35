package kr.co.fastcampus.part4.chapter3_18

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kr.co.fastcampus.part4.chapter3_18.ui.theme.CatalogTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			CatalogTheme {
				// A surface container using the 'background' color from the theme
				Surface(
					modifier = Modifier.fillMaxSize(),
					color = MaterialTheme.colorScheme.background
				) {
					CatalogEx(items)
				}
			}
		}
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Item(itemData: ItemData) {
	// 스텝 1: catalog1.png를 참고해 카드 레이아웃을 완성하세요.
	Card(
		elevation = CardDefaults.cardElevation(8.dp),
		modifier = Modifier.padding(16.dp)
	) {
		Column(
			modifier = Modifier.padding(8.dp)
		) {
			Image(
				painter = painterResource(id = itemData.imageId),
				contentDescription = itemData.title
			)
			Spacer(modifier = Modifier.size(8.dp))
			Text(
				text = itemData.title,
				style = MaterialTheme.typography.headlineMedium
			)
			Spacer(modifier = Modifier.size(8.dp))
			Text(text = itemData.description)
		}
	}
}

@Preview(showBackground = true)
@Composable
fun ItemPreview() {
	CatalogTheme {
		Item(
			ItemData(
				imageId = R.drawable.a1,
				title = "해변 놀이 공원",
				description = "해변 놀이 공원 설명입니다. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
			)
		)
	}
}

@Composable
fun CatalogEx(itemList: List<ItemData>) {
	LazyColumn {
		// 스텝 2: `items(itemList)`를 이용해 Item을 반복해서
		// 컬럼에 추가하세요.
		items(itemList) { item ->
			Item(item)
		}
	}
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
	CatalogTheme {
		CatalogEx(items)
	}
}

data class ItemData(
	@DrawableRes val imageId: Int,
	val title: String,
	val description: String,
)

val items = listOf(
	ItemData(
		imageId = R.drawable.a1,
		title = "해변 놀이 공원",
		description = "해변 놀이 공원 설명입니다. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
	),
	ItemData(
		imageId = R.drawable.a2,
		title = "캐년",
		description = "미국의 캐년입니다. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
	),
	ItemData(
		imageId = R.drawable.a3,
		title = "워터월드",
		description = "워터월드입니다. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
	),
	ItemData(
		imageId = R.drawable.a4,
		title = "미국의 캐년",
		description = "미국의 캐년입니다. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
	),
	ItemData(
		imageId = R.drawable.a5,
		title = "라스베가스",
		description = "라스베가스입니다. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
	),
	ItemData(
		imageId = R.drawable.a6,
		title = "호르슈 밴드",
		description = "호르슈 밴드입니다. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
	),
	ItemData(
		imageId = R.drawable.a7,
		title = "미국의 길",
		description = "미국의 길입니다. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
	),
	ItemData(
		imageId = R.drawable.a8,
		title = "엔텔로프 캐년",
		description = "엔텔로프 캐년입니다. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
	),
	ItemData(
		imageId = R.drawable.a9,
		title = "그랜드 캐년",
		description = "그랜드 캐년입니다. Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. "
	),
)