package com.technical_assessment.starterappmvvm.data.api.movie

import com.technical_assessment.starterappmvvm.data.response.movie.*
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("genre/movie/list")
    fun getGenres() : Observable<GenresResponse>

    @GET("discover/movie")
    fun discoverMovieByGenre(
        @Query("with_genres") withGenre:String,
        @Query("page") page:Int,
        @Query("with_watch_monetization_types") withWatchMonetizationType:String = "flatrate",
        @Query("sort_by") sortBy:String = "popularity.desc"
    ) : Observable<DiscoverResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetail(
        @Path("movie_id") movieId:Int
    ) : Observable<MovieResponse>

    @GET("movie/{movie_id}/videos")
    fun getMovieVideo(
        @Path("movie_id") movieId:Int
    ) : Observable<MovieVideoResponse>

    @GET("movie/{movie_id}/reviews")
    fun getMovieReview(
        @Path("movie_id") movieId:Int,
        @Query("page") page:Int
    ) : Observable<ReviewResponse>
}