package com.fadlurahmanf.starterappmvvm.base.network

import com.fadlurahmanf.starterappmvvm.BuildConfig

abstract class AbstractNetwork<T>(): BaseNetwork<T>() {

    override fun getBaseUrl(): String {
        return BuildConfig.BASE_DEV_URL
    }


}