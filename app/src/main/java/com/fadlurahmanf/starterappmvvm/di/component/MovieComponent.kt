package com.fadlurahmanf.starterappmvvm.di.component

import com.fadlurahmanf.starterappmvvm.ui.genre.GenreActivity
import dagger.Subcomponent

@Subcomponent
interface MovieComponent {

    @Subcomponent.Factory
    interface Factory{
        fun create():MovieComponent
    }

    fun inject(activity:GenreActivity)
}