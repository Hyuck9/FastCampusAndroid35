package kr.co.fastcampus.part4plus.movieapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import kr.co.fastcampus.part4plus.movieapp.features.common.viewmodel.ThemeViewModel

open class BaseFragment : Fragment() {
    protected val themeViewModel: ThemeViewModel by activityViewModels()
}