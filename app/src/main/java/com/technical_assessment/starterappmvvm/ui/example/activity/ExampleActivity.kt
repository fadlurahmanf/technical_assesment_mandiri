package com.technical_assessment.starterappmvvm.ui.example.activity

import androidx.work.*
import com.technical_assessment.starterappmvvm.BaseApp
import com.technical_assessment.starterappmvvm.base.BaseActivity
import com.technical_assessment.starterappmvvm.data.repository.example.ExampleRepository
import com.technical_assessment.starterappmvvm.databinding.ActivityExampleBinding
import com.technical_assessment.starterappmvvm.di.component.ExampleComponent
import com.technical_assessment.starterappmvvm.ui.example.viewmodel.ExampleViewModel
import javax.inject.Inject

class ExampleActivity : BaseActivity<ActivityExampleBinding>(ActivityExampleBinding::inflate) {
    lateinit var component:ExampleComponent

    @Inject
    lateinit var viewModel:ExampleViewModel

    @Inject
    lateinit var exampleRepository: ExampleRepository

    override fun initSetup() {
        initObserver()

        binding?.button1?.setOnClickListener {}
    }


    private fun initObserver() {}


    override fun inject() {
        component = (applicationContext as BaseApp).applicationComponent.exampleComponent().create()
        component.inject(this)
    }

}
