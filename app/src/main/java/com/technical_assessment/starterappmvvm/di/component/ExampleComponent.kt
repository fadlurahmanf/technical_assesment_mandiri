package com.technical_assessment.starterappmvvm.di.component

import com.technical_assessment.starterappmvvm.ui.example.activity.ExampleActivity
import dagger.Subcomponent

@Subcomponent
interface ExampleComponent {

    @Subcomponent.Factory
    interface Factory{
        fun create():ExampleComponent
    }

    fun inject(activity: ExampleActivity)
}