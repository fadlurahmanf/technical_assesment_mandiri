package com.technical_assessment.starterappmvvm.data.entity.movie

import com.technical_assessment.starterappmvvm.base.network.AbstractNetwork
import com.technical_assessment.starterappmvvm.data.api.movie.MovieApi
import javax.inject.Inject

class MovieEntity @Inject constructor() : AbstractNetwork<MovieApi>() {
    override fun getApi(): Class<MovieApi> {
        return MovieApi::class.java
    }

    fun getAllGenre() = networkService(15).getGenres()

    fun discoverMovie(genre:String, page:Int) = networkService(15).discoverMovieByGenre(genre, page)

    fun getDetailMovie(movieId:Int) = networkService(15).getMovieDetail(movieId)

    fun getMovieVideo(movieId: Int) = networkService(15).getMovieVideo(movieId)

    fun getMovieReview(movieId: Int, page: Int) = networkService(15).getMovieReview(movieId, page)
}