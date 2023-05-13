package kr.co.fastcampus.part4plus.movieapp.features.detail.presentation.output

import kr.co.fastcampus.part4plus.movieapp.features.common.entity.MovieDetailEntity

sealed class MovieDetailState {
    object Initial : MovieDetailState()
    class Main(val movieDetailEntity: MovieDetailEntity) : MovieDetailState()
}