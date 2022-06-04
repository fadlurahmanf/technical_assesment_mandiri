package com.fadlurahmanf.starterappmvvm.di.component

import com.fadlurahmanf.starterappmvvm.ui.movie.DetailMovieActivity
import com.fadlurahmanf.starterappmvvm.ui.movie.GenreActivity
import com.fadlurahmanf.starterappmvvm.ui.movie.ListMovieActivity
import dagger.Subcomponent

@Subcomponent
interface MovieComponent {

    @Subcomponent.Factory
    interface Factory{
        fun create():MovieComponent
    }

    fun inject(activity:GenreActivity)
    fun inject(activity:ListMovieActivity)
    fun inject(activity:DetailMovieActivity)
}