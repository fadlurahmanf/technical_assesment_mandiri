package com.technical_assessment.starterappmvvm.di.component

import com.technical_assessment.starterappmvvm.ui.SplashActivity
import com.technical_assessment.starterappmvvm.ui.core.dialog.DefaultLoadingDialog
import dagger.Subcomponent

@Subcomponent
interface CoreComponent {

    @Subcomponent.Factory
    interface Factory{
        fun create():CoreComponent
    }

    fun inject(activity:SplashActivity)
    fun inject(dialog:DefaultLoadingDialog)
}