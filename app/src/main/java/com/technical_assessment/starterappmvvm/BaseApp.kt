package com.technical_assessment.starterappmvvm

import android.app.Application
import com.technical_assessment.starterappmvvm.di.component.ApplicationComponent
import com.technical_assessment.starterappmvvm.di.component.DaggerApplicationComponent


class BaseApp : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        initInjection()
    }

    private fun initInjection(){
        applicationComponent = DaggerApplicationComponent.factory().create(this)
        applicationComponent.inject(this)
//        applicationComponent = DaggerApplicationComponent.builder().application(this).buildComponent()
//        applicationComponent.inject(this)
    }
}