package com.technical_assessment.starterappmvvm.ui.movie

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.technical_assessment.starterappmvvm.base.BaseActivity
import com.technical_assessment.starterappmvvm.base.BaseState
import com.technical_assessment.starterappmvvm.data.response.movie.GenresResponse
import com.technical_assessment.starterappmvvm.databinding.ActivityGenreBinding
import com.technical_assessment.starterappmvvm.di.component.MovieComponent
import com.technical_assessment.starterappmvvm.ui.movie.adapter.GenreAdapter
import com.technical_assessment.starterappmvvm.ui.movie.viewmodel.MovieViewModel
import javax.inject.Inject

class GenreActivity : BaseActivity<ActivityGenreBinding>(ActivityGenreBinding::inflate) {

    @Inject
    lateinit var viewModel:MovieViewModel

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
                val intent = Intent(this@GenreActivity, ListMovieActivity::class.java)
                intent.putExtra(ListMovieActivity.GENRE, genre.name)
                startActivity(intent)
            }
        })
        binding?.rvGenre?.layoutManager = GridLayoutManager(this, 2)
        binding?.rvGenre?.adapter = adapter
    }

    private fun initObserver() {
        viewModel.state.observe(this){
            if (it.getGenreState == BaseState.LOADING){
                binding?.pb?.visibility = View.VISIBLE
                binding?.tvError?.visibility = View.GONE
            }else if (it.getGenreState == BaseState.SUCCESS){
                binding?.pb?.visibility = View.GONE
                binding?.rvGenre?.visibility = View.VISIBLE
                binding?.tvError?.visibility = View.GONE
                genres.clear()
                genres.addAll(it.genreData?.genres?: arrayListOf())
                adapter.notifyDataSetChanged()
            }else if (it.getGenreState == BaseState.FAILED){
                binding?.pb?.visibility = View.GONE
                binding?.rvGenre?.visibility = View.GONE
                binding?.tvError?.visibility = View.VISIBLE
                binding?.tvError?.text = it.errorGenreData?:""
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