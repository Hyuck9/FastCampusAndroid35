package kr.co.fastcampus.part4plus.chapter2.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kr.co.fastcampus.part4plus.chapter2.model.Memo
import kr.co.fastcampus.part4plus.chapter2.model.memos
import kr.co.fastcampus.part4plus.chapter2.ui.theme.MemoAppTheme

@Composable
fun HomeScreen(homeState: HomeState) {
	MemoAppTheme {
		Surface(
			modifier = Modifier.fillMaxSize(),
			color = MaterialTheme.colors.background
		) {
//			val memoList = remember { memos }
			val memoList = remember { memos.sortedBy { it.id }.toMutableStateList() }
			val onClickAction: (Int) -> Unit = {
				homeState.showContent(
					it
				)
			}

			Column {
				AddMemo(memoList)
				MemoList(onClickAction, memoList)
			}
		}
	}
}

@Composable
fun AddMemo(memoList: SnapshotStateList<Memo>) {
	val inputValue = remember { mutableStateOf("") }
	var count by remember { mutableStateOf(0) }

	Row(
		modifier = Modifier
			.padding(all = 16.dp)
			.height(100.dp),
		horizontalArrangement = Arrangement.End
	) {
		TextField(
			modifier = Modifier
				.fillMaxHeight()
				.weight(1f),
			value = inputValue.value,
			onValueChange = { textFieldValue -> inputValue.value = textFieldValue }
		)
		Button(
			onClick = {
				memoList.add(
					index = 0,
					Memo(memoList.size, inputValue.value)
				)
				inputValue.value = ""
				count++
			},
			modifier = Modifier
				.wrapContentWidth()
				.fillMaxHeight()
		) {
			Text("ADD\n$count")
			count++
		}
	}
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ColumnScope.MemoList(onClickAction: (Int) -> Unit, memoList: SnapshotStateList<Memo>) {
	LazyColumn(
		modifier = Modifier
			.weight(1f)
	) {
		/*
		* TODO 1: items에 key 값이 지정되어있지 않기 때문에
		*  list item이 추가되거나 하면 list에 있는 모든 아이템들에 recomposition이 일어남.
		*  유니크한 key 값만 지정해 주면 해결 가능
		* */
		/*
		* TODO 2: items 안에 sortedBy (정렬) 등의 계산이 들어가면
		*  아이템에 변화가 있지 않더라도 매번 recompose 된다.
		*  정렬을 remember로 빼면 해결 가능
		* */
		items(
			items = memoList,
			key = { it.id }
		) { memo ->
			Card(
				modifier = Modifier
					.height(100.dp)
					.background(Color.White)
					.padding(
						horizontal = 16.dp,
						vertical = 8.dp
					)
					.fillMaxWidth(),
				backgroundColor = Color.LightGray,
				onClick = {
					onClickAction(memo.id)
				}
			) {
				Text(
					text = memo.text,
					modifier = Modifier.fillMaxSize()
				)
			}
		}
	}
}