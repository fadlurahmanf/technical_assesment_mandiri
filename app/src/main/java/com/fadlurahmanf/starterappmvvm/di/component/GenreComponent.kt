package com.fadlurahmanf.starterappmvvm.di.component

import com.fadlurahmanf.starterappmvvm.ui.genre.GenreActivity
import dagger.Subcomponent

@Subcomponent
interface GenreComponent {

    @Subcomponent.Factory
    interface Factory{
        fun create():GenreComponent
    }

    fun inject(activity:GenreActivity)
}