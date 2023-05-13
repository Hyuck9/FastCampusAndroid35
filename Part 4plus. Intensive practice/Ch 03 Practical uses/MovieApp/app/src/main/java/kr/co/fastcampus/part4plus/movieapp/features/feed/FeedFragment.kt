package kr.co.fastcampus.part4plus.movieapp.features.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kr.co.fastcampus.part4plus.movieapp.features.feed.presentation.output.FeedUiEffect
import kr.co.fastcampus.part4plus.movieapp.features.feed.presentation.screen.FeedScreen
import kr.co.fastcampus.part4plus.movieapp.features.feed.presentation.viewmodel.FeedViewModel
import kr.co.fastcampus.part4plus.movieapp.ui.navigation.safeNavigate
import kr.co.fastcampus.part4plus.movieapp.ui.theme.MovieAppTheme

@AndroidEntryPoint
class FeedFragment : Fragment() {

	private val viewModel: FeedViewModel by viewModels()

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		observeUiEffects()
		return ComposeView(requireContext()).apply {
			setContent {
				MovieAppTheme {
					FeedScreen(
						feedStateHolder = viewModel.output.feedState.collectAsState(),
						input = viewModel.input
					)
				}
			}
		}
	}

	private fun observeUiEffects() {
		val navController = findNavController()
		lifecycleScope.launch {
			repeatOnLifecycle(Lifecycle.State.RESUMED) {
				viewModel.output.feedUiEffect.collectLatest {
					when (it) {
						is FeedUiEffect.OpenMovieDetail -> {
							navController.safeNavigate(
								FeedFragmentDirections.actionFeedToDetail(it.movieName)
							)
						}
						is FeedUiEffect.OpenInfoDialog -> {
							/*navController.safeNavigate(
								FeedFragmentDirections.actionFeedToInfo()
							)*/
						}
					}
				}
			}
		}
	}
}