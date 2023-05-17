package kr.co.fastcampus.part5.data.repository

import kr.co.fastcampus.part5.data.datasource.TestDataSource
import kr.co.fastcampus.part5.data.model.toDomainModel
import kr.co.fastcampus.part5.domain.model.TestModel
import kr.co.fastcampus.part5.domain.repository.TestRepository

class TestRepositoryImpl(val dataSource: TestDataSource) : TestRepository {
	override fun getTestData(): TestModel? {
		return dataSource.getTestModelResponse().toDomainModel()
	}
}