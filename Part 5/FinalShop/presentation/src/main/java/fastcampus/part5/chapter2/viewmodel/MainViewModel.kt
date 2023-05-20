package fastcampus.part5.chapter2.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fastcampus.part5.domain.model.Banner
import fastcampus.part5.domain.model.BannerList
import fastcampus.part5.domain.model.Product
import fastcampus.part5.domain.usecase.CategoryUseCase
import fastcampus.part5.domain.usecase.MainUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
	mainUseCase: MainUseCase,
	categoryUseCase: CategoryUseCase
) : ViewModel() {

	private val _columnCount = MutableStateFlow(DEFAULT_COLUMN_COUNT)
	val columCount: StateFlow<Int> = _columnCount

	val modelList = mainUseCase.getModelList()
	val categories = categoryUseCase.getCategories()

	fun openSearchForm() {
		println("openSearchForm")
	}

	fun updateColumnCount(count: Int) {
		viewModelScope.launch {
			_columnCount.emit(count)
		}
	}

	fun openProduct(product: Product) {

	}

	fun openCarouselProduct(product: Product) {

	}

	fun openRankingProduct(product: Product) {

	}

	fun openBanner(banner: Banner) {

	}

	fun openBannerList(bannerList: BannerList) {

	}

	companion object {
		private const val DEFAULT_COLUMN_COUNT = 2
	}
}