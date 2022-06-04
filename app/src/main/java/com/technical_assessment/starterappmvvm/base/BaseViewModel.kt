package com.technical_assessment.starterappmvvm.base

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseViewModel:ViewModel() {
    fun disposable() = CompositeDisposable()
}