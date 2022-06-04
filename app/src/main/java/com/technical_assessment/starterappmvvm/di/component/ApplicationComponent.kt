package com.technical_assessment.starterappmvvm.di.component

import android.content.Context
import com.technical_assessment.starterappmvvm.BaseApp
import dagger.BindsInstance
import dagger.Component


@Component(modules = [])
interface ApplicationComponent {
    fun inject(app: BaseApp)
    fun exampleComponent():ExampleComponent.Factory
    fun coreComponent():CoreComponent.Factory
    fun movieComponent():MovieComponent.Factory

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context) : ApplicationComponent
    }
}