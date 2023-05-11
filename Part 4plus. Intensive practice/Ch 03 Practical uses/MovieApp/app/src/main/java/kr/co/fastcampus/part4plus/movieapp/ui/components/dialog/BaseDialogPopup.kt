package kr.co.fastcampus.part4plus.movieapp.ui.components.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kr.co.fastcampus.part4plus.movieapp.ui.components.dialog.components.button.DialogButtonsColumn
import kr.co.fastcampus.part4plus.movieapp.ui.components.dialog.components.content.DialogContentWrapper
import kr.co.fastcampus.part4plus.movieapp.ui.components.dialog.components.title.DialogTitleWrapper
import kr.co.fastcampus.part4plus.movieapp.ui.models.dialog.DialogButton
import kr.co.fastcampus.part4plus.movieapp.ui.models.dialog.DialogContent
import kr.co.fastcampus.part4plus.movieapp.ui.models.dialog.DialogTitle
import kr.co.fastcampus.part4plus.movieapp.ui.theme.Paddings
import kr.co.fastcampus.part4plus.movieapp.ui.theme.colorScheme

@Composable
fun BaseDialogPopup(
	dialogTitle: DialogTitle? = null,
	dialogContent: DialogContent? = null,
	buttons: List<DialogButton>? = null
) {
	Card(
		modifier = Modifier.fillMaxWidth(),
		elevation = Paddings.none,
		backgroundColor = MaterialTheme.colorScheme.background,
		shape = MaterialTheme.shapes.large
	) {
		Column(
			modifier = Modifier.fillMaxWidth()
		) {
			dialogTitle?.let {
				DialogTitleWrapper(it)
			}
			Column(
				modifier = Modifier
					.background(Color.Transparent)
					.fillMaxWidth()
					.padding(
						start = Paddings.xLarge,
						end = Paddings.xLarge,
						bottom = Paddings.xLarge,
					)
			) {
				dialogContent?.let { DialogContentWrapper(it) }
				buttons?.let { DialogButtonsColumn(it) }
			}
		}
	}
}




