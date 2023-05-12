package kr.co.fastcampus.part4plus.movieapp.features.feed.presentation.output

sealed class FeedState {
	object Loading : FeedState()

	class Main(
		val movieList: List<MovieFeedItemEntity>
	) : FeedState()

	class Failed(
		val reason: String
	) : FeedState()
}