package com.technical_assessment.starterappmvvm.di.component

import com.technical_assessment.starterappmvvm.ui.movie.DetailMovieActivity
import com.technical_assessment.starterappmvvm.ui.movie.GenreActivity
import com.technical_assessment.starterappmvvm.ui.movie.ListMovieActivity
import com.technical_assessment.starterappmvvm.ui.movie.ListReviewActivity
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
    fun inject(activity:ListReviewActivity)
}