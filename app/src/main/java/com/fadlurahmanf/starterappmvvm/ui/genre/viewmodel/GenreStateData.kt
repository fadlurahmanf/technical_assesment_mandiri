package com.fadlurahmanf.starterappmvvm.ui.genre.viewmodel

import com.fadlurahmanf.starterappmvvm.base.BaseState
import com.fadlurahmanf.starterappmvvm.data.response.movie.GenresResponse

data class GenreStateData(
    var state:BaseState ?= BaseState.IDLE,
    var genreData:GenresResponse ?= null,
    var errorGenreData:String ?= null
)
