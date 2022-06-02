package com.fadlurahmanf.starterappmvvm.di.component

import android.content.Context
import com.fadlurahmanf.starterappmvvm.BaseApp
import dagger.BindsInstance
import dagger.Component


@Component(modules = [])
interface ApplicationComponent {
    fun inject(app: BaseApp)
    fun exampleComponent():ExampleComponent.Factory
    fun coreComponent():CoreComponent.Factory
    fun genreComponent():GenreComponent.Factory

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context) : ApplicationComponent
    }
}