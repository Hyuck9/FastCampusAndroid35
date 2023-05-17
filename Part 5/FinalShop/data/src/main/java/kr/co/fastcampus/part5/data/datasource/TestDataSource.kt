package kr.co.fastcampus.part5.data.datasource

import kr.co.fastcampus.part5.data.model.TestModelResponse

class TestDataSource {

	fun getTestModelResponse(): TestModelResponse {
		return TestModelResponse("response")
	}
}