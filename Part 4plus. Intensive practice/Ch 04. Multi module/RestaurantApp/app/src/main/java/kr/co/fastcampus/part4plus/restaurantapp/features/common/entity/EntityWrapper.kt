package kr.co.fastcampus.part4plus.restaurantapp.features.common.entity

sealed class EntityWrapper<T> {
    data class Success<T>(val entity: T) : EntityWrapper<T>()
    data class Fail<T>(val error: Throwable) : EntityWrapper<T>()
}