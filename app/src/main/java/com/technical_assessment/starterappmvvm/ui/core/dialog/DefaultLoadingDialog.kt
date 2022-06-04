package com.technical_assessment.starterappmvvm.ui.core.dialog

import com.technical_assessment.starterappmvvm.BaseApp
import com.technical_assessment.starterappmvvm.base.BaseDialog
import com.technical_assessment.starterappmvvm.databinding.DialogDefaultLoadingBinding
import com.technical_assessment.starterappmvvm.di.component.CoreComponent

class DefaultLoadingDialog:BaseDialog<DialogDefaultLoadingBinding>(DialogDefaultLoadingBinding::inflate) {

    lateinit var component:CoreComponent

    override fun inject() {
        component = (requireActivity().applicationContext as BaseApp).applicationComponent.coreComponent().create()
        component.inject(this)
    }

    override fun setup() {

    }
}