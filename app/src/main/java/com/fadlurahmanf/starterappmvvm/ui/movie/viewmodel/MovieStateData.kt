package com.fadlurahmanf.starterappmvvm.ui.movie.viewmodel

import com.fadlurahmanf.starterappmvvm.base.BaseState
import com.fadlurahmanf.starterappmvvm.data.response.movie.DiscoverResponse
import com.fadlurahmanf.starterappmvvm.data.response.movie.GenresResponse
import com.fadlurahmanf.starterappmvvm.data.response.movie.MovieResponse
import com.fadlurahmanf.starterappmvvm.data.response.movie.MovieVideoResponse

data class MovieStateData(
    var getGenreState:BaseState ?= BaseState.IDLE,
    var genreData:GenresResponse ?= null,
    var errorGenreData:String ?= null,

    var getDiscoverState:BaseState ?= BaseState.IDLE,
    var discoverData:DiscoverResponse ?= null,
    var errorDiscoverData:String ?= null,

    var detailMovieState:BaseState ?= BaseState.IDLE,
    var detailMovieData:MovieResponse ?= null,
    var errorDetailMovie:String ?= null,

    var movieVideoState:BaseState ?= BaseState.IDLE,
    var movieVideoData:MovieVideoResponse ?= null,
    var errorMovieVideo:String ?= null
)
