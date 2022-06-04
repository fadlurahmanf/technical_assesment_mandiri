package com.technical_assessment.starterappmvvm.ui.movie.viewmodel

import com.technical_assessment.starterappmvvm.base.BaseState
import com.technical_assessment.starterappmvvm.data.response.movie.*

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
    var errorMovieVideo:String ?= null,

    var reviewState:BaseState ?= BaseState.IDLE,
    var reviewData:ReviewResponse ?= null,
    var errorReview:String ?= null
)
