package fastcampus.part5.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fastcampus.part5.data.repository.*
import fastcampus.part5.domain.repository.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

	@Binds
	@Singleton
	fun bindTempRepository(tempRepositoryImpl: TempRepositoryImpl): TempRepository

	@Binds
	@Singleton
	fun bindMainRepository(mainRepositoryImpl: MainRepositoryImpl): MainRepository

	@Binds
	@Singleton
	fun bindCategoryRepository(categoryRepositoryImpl: CategoryRepositoryImpl): CategoryRepository

	@Binds
	@Singleton
	fun bindProductDetailRepository(productDetailRepositoryImpl: ProductDetailRepositoryImpl): ProductDetailRepository

	@Binds
	@Singleton
	fun bindSearchRepository(searchRepositoryImpl: SearchRepositoryImpl): SearchRepository
}