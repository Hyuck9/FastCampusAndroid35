package kr.co.fastcampus.part4plus.movieapp

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import kr.co.fastcampus.part4plus.movieapp.features.common.viewmodel.ThemeViewModel

open class BaseDialogFragment : DialogFragment() {
    protected val themeViewModel: ThemeViewModel by activityViewModels()
}