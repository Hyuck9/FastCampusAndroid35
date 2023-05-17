package kr.co.fastcampus.part5.domain.repository

import kr.co.fastcampus.part5.domain.model.TestModel

interface TestRepository {
	fun getTestData(): TestModel?
}