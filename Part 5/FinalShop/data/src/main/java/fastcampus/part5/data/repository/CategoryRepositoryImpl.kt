package fastcampus.part5.data.repository

import fastcampus.part5.domain.model.Category
import fastcampus.part5.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor() : CategoryRepository {

	override fun getCategories(): Flow<List<Category>> = flow {
		emit(
			listOf(
				Category.Top,
				Category.Outerwear,
				Category.Dress,
				Category.Pants,
				Category.Skirt,
				Category.Shoes,
				Category.Bag,
				Category.FashionAccessories
			)
		)
	}
}