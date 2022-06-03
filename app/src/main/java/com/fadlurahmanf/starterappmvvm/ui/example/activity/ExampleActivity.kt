package com.fadlurahmanf.starterappmvvm.ui.example.activity

import androidx.work.*
import com.fadlurahmanf.starterappmvvm.BaseApp
import com.fadlurahmanf.starterappmvvm.base.BaseActivity
import com.fadlurahmanf.starterappmvvm.data.repository.example.ExampleRepository
import com.fadlurahmanf.starterappmvvm.databinding.ActivityExampleBinding
import com.fadlurahmanf.starterappmvvm.di.component.ExampleComponent
import com.fadlurahmanf.starterappmvvm.extension.observeOnce
import com.fadlurahmanf.starterappmvvm.ui.example.viewmodel.ExampleViewModel
import com.google.android.material.snackbar.Snackbar
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
