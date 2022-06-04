package com.technical_assessment.starterappmvvm.base.network

import com.technical_assessment.starterappmvvm.BuildConfig

abstract class AbstractNetwork<T>(): BaseNetwork<T>() {

    override fun getBaseUrl(): String {
        return BuildConfig.BASE_DEV_URL
    }


}