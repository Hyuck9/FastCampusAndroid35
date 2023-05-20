package fastcampus.part5.data.repository

import fastcampus.part5.data.datasource.ProductDataSource
import fastcampus.part5.domain.model.Category
import fastcampus.part5.domain.model.Product
import fastcampus.part5.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
	private val dataSource: ProductDataSource
) : CategoryRepository {

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

	override fun getProductsByCategory(category: Category): Flow<List<Product>> {
		return dataSource.getProducts().map { list ->
			list.filterIsInstance<Product>()
				.filter { product ->
				product.category.categoryId == category.categoryId
			}
		}
	}
}