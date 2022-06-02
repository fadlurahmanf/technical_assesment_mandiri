package com.fadlurahmanf.starterappmvvm.ui.genre

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fadlurahmanf.starterappmvvm.base.BaseActivity
import com.fadlurahmanf.starterappmvvm.databinding.ActivityGenreBinding
import com.fadlurahmanf.starterappmvvm.di.component.GenreComponent

class GenreActivity : BaseActivity<ActivityGenreBinding>(ActivityGenreBinding::inflate) {
    override fun initSetup() {

    }

    private lateinit var component:GenreComponent
    override fun inject() {
        component = appComponent.genreComponent().create()
        component.inject(this)
    }

}