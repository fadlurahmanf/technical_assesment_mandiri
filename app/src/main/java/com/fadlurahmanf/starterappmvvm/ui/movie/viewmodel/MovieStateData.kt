package com.fadlurahmanf.starterappmvvm.ui.movie.viewmodel

import com.fadlurahmanf.starterappmvvm.base.BaseState
import com.fadlurahmanf.starterappmvvm.data.response.movie.DiscoverResponse
import com.fadlurahmanf.starterappmvvm.data.response.movie.GenresResponse

data class MovieStateData(
    var getGenreState:BaseState ?= BaseState.IDLE,
    var genreData:GenresResponse ?= null,
    var errorGenreData:String ?= null,

    var getDiscoverState:BaseState ?= BaseState.IDLE,
    var discoverData:DiscoverResponse ?= null,
    var errorDiscoverData:String ?= null,
)
