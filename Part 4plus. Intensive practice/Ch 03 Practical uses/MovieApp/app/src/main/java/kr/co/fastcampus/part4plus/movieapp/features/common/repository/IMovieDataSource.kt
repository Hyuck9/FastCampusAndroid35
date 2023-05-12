package kr.co.fastcampus.part4plus.movieapp.features.common.repository

interface IMovieDataSource {
	suspend fun getMovieList()
}