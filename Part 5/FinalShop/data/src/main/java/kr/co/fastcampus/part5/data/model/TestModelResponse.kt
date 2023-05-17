package kr.co.fastcampus.part5.data.model

import kr.co.fastcampus.part5.domain.model.TestModel

class TestModelResponse(val name: String?)

fun TestModelResponse.toDomainModel() : TestModel? {
	if (name != null) {
		return TestModel(name)
	}
	return null
}