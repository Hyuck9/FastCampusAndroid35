package kr.co.fastcampus.part4plus.movieapp.ui.components.dialog

import androidx.compose.runtime.Composable
import kr.co.fastcampus.part4plus.movieapp.ui.models.dialog.DialogButton
import kr.co.fastcampus.part4plus.movieapp.ui.models.dialog.DialogContent
import kr.co.fastcampus.part4plus.movieapp.ui.models.dialog.DialogText
import kr.co.fastcampus.part4plus.movieapp.ui.models.dialog.DialogTitle

@Composable
fun DialogPopup.Rating(
	movieName: String,
	rating: Float,
	buttons: List<DialogButton>
) {
	BaseDialogPopup(
		dialogTitle = DialogTitle.Large("Rate your Score!"),
		dialogContent = DialogContent.Rating(
			DialogText.Rating(
				text = movieName,
				rating = rating
			)
		),
		buttons = buttons
	)
}