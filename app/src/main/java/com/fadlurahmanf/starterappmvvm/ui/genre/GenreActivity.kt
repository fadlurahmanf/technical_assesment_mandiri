package com.fadlurahmanf.starterappmvvm.ui.genre

import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.fadlurahmanf.starterappmvvm.base.BaseActivity
import com.fadlurahmanf.starterappmvvm.base.BaseState
import com.fadlurahmanf.starterappmvvm.data.response.movie.GenresResponse
import com.fadlurahmanf.starterappmvvm.databinding.ActivityGenreBinding
import com.fadlurahmanf.starterappmvvm.di.component.MovieComponent
import com.fadlurahmanf.starterappmvvm.ui.genre.adapter.GenreAdapter
import com.fadlurahmanf.starterappmvvm.ui.genre.viewmodel.GenreViewModel
import javax.inject.Inject

class GenreActivity : BaseActivity<ActivityGenreBinding>(ActivityGenreBinding::inflate) {

    @Inject
    lateinit var viewModel:GenreViewModel

    override fun initSetup() {
        supportActionBar?.hide()
        initAdapter()
        initObserver()
        viewModel.getAllGenre()
    }

    private var genres:ArrayList<GenresResponse.Genre> = arrayListOf()
    private lateinit var adapter:GenreAdapter
    private fun initAdapter() {
        adapter = GenreAdapter(genres)
        adapter.setCallBack(object : GenreAdapter.CallBack{
            override fun onClicked(genre: GenresResponse.Genre) {

            }
        })
        binding?.rvGenre?.layoutManager = GridLayoutManager(this, 2)
        binding?.rvGenre?.adapter = adapter
    }

    private fun initObserver() {
        viewModel.state.observe(this){
            if (it.state == BaseState.LOADING){
                binding?.pb?.visibility = View.VISIBLE
            }else if (it.state == BaseState.SUCCESS){
                binding?.pb?.visibility = View.GONE
                binding?.rvGenre?.visibility = View.VISIBLE
                genres.clear()
                genres.addAll(it.genreData?.genres?: arrayListOf())
                adapter.notifyDataSetChanged()
            }else if (it.state == BaseState.FAILED){
                binding?.pb?.visibility = View.GONE
                if (binding?.root != null){
                    showSnackBar(binding!!.root, it.errorGenreData?:"")
                }
            }
        }
    }

    private lateinit var component:MovieComponent
    override fun inject() {
        component = appComponent.movieComponent().create()
        component.inject(this)
    }

}