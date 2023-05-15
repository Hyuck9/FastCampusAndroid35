package kr.co.fastcampus.part4plus.chapter2.ui.content

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kr.co.fastcampus.part4plus.chapter2.R
import kr.co.fastcampus.part4plus.chapter2.model.memos

private val MinTitleOffset = 20.dp
private val MaxTitleOffset = 100.dp
private val HzPadding = Modifier.padding(horizontal = 24.dp)

@Composable
fun ContentScreen(memoId: Int) {
	val memo = remember(memos) { memos.single { it.id == memoId } }

	Box(Modifier.fillMaxSize()) {
		val scroll = rememberScrollState(0)
		Body(scroll)
//		Title(memo.text, scroll.value)
//		TODO: scroll.value 를 람다로 변경
		Title(memo.text) { scroll.value }
	}
}

@Composable
private fun Body(
	scroll: ScrollState
) {
	Column(
		modifier = Modifier.background(Color.White)
	) {
		Spacer(
			modifier = Modifier
				.fillMaxWidth()
				.height(MaxTitleOffset)
		)
		Column(
			modifier = Modifier
				.verticalScroll(scroll)
		) {
			Surface(Modifier.fillMaxWidth()) {
				Column {
					Spacer(Modifier.height(110.dp))
					Text(
						text = stringResource(R.string.detail_placeholder),
						style = MaterialTheme.typography.body1,
						color = Color.Black,
						modifier = HzPadding
					)

					Spacer(Modifier.height(16.dp))
				}
			}
		}
	}
}

@Composable
//private fun Title(memoText: String, scroll: Int) {
private fun Title(memoText: String, scrollProvider: () -> Int) {
	val maxOffset = with(LocalDensity.current) { MaxTitleOffset.toPx() }
	val minOffset = with(LocalDensity.current) { MinTitleOffset.toPx() }

	Column(
		modifier = Modifier
			.heightIn(min = MaxTitleOffset)
//		    TODO: scroll이 "Int" 이고, offset 값에 scroll이 들어가 있기 때문에
//			TODO: Title이 호출될 때 마다 offset이 계속 바뀌고, 바뀔 때마다 Column 전체에 Recomposition이 발생
//			TODO: scroll: Int 값을 람다로 바꾸면 최적화 된다.
//			TODO: --> 값을 직접 읽지 않고, 람다를 통해서 전해줘서 "composition 단계" 이후에 "Layout 단계"에 값이 들어가기 때문에 Recomposition이 일어나지 않게 된다.
//			TODO: 이러한 기법을 "상태읽기연기" 라고 한다.
//			.offset {
//				val offset = (maxOffset - scroll).coerceAtLeast(minOffset)
//				IntOffset(x = 0, y = offset.toInt())
//			}
			.offset {
//				val offset = (maxOffset - scrollProvider).coerceAtLeast(minOffset)
				val offset = (maxOffset - scrollProvider()).coerceAtLeast(minOffset)
				IntOffset(x = 0, y = offset.toInt())
			}
			.fillMaxWidth()
			.background(Color.White)
	) {
		Text(
			text = memoText,
			style = MaterialTheme.typography.h4,
			color = Color.Black,
			modifier = HzPadding
		)
		Spacer(Modifier.height(4.dp))
		Text(
			text = "MEMO",
			style = MaterialTheme.typography.h6,
			color = MaterialTheme.colors.primaryVariant,
			modifier = HzPadding
		)

		Spacer(Modifier.height(8.dp))
	}
}