package com.fadlurahmanf.starterappmvvm.data.entity.movie

import com.fadlurahmanf.starterappmvvm.base.network.AbstractNetwork
import com.fadlurahmanf.starterappmvvm.data.api.movie.MovieApi
import javax.inject.Inject

class MovieEntity @Inject constructor() : AbstractNetwork<MovieApi>() {
    override fun getApi(): Class<MovieApi> {
        return MovieApi::class.java
    }

    fun getAllGenre() = networkService(15).getGenres()
}