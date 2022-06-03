package com.fadlurahmanf.starterappmvvm.ui.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fadlurahmanf.starterappmvvm.base.BaseActivity
import com.fadlurahmanf.starterappmvvm.base.BaseState
import com.fadlurahmanf.starterappmvvm.data.response.movie.DiscoverResponse
import com.fadlurahmanf.starterappmvvm.databinding.ActivityListMovieBinding
import com.fadlurahmanf.starterappmvvm.di.component.MovieComponent
import com.fadlurahmanf.starterappmvvm.ui.movie.adapter.MovieAdapter
import com.fadlurahmanf.starterappmvvm.ui.movie.viewmodel.MovieViewModel
import javax.inject.Inject

class ListMovieActivity : BaseActivity<ActivityListMovieBinding>(ActivityListMovieBinding::inflate) {

    companion object{
        const val GENRE = "GENRE"
    }

    private var genre:String ?= null
    private var page:Int = 1
    private var totalPage:Int = 1
    private var isLoading:Boolean = false

    @Inject
    lateinit var viewModel:MovieViewModel

    override fun initSetup() {
        genre = intent.getStringExtra(GENRE)
        if (genre == null){
            if (binding?.root != null){
                showSnackBar(binding!!.root, "Genre shouldn't be empty")
            }
            onBackPressed()
        }
        initView()
        initAdapter()
        initObserver()

        if (genre != null){
            viewModel.discoverMovie(genre?:"", 1)
        }
    }

    private lateinit var adapter:MovieAdapter
    private lateinit var gridLayoutManager:RecyclerView.LayoutManager
    private var listResult:ArrayList<DiscoverResponse.Result> = arrayListOf()
    private fun initAdapter() {
        adapter = MovieAdapter(listResult)
        gridLayoutManager = GridLayoutManager(this, 2)
        binding?.rvMovie?.layoutManager = gridLayoutManager
        binding?.rvMovie?.adapter = adapter

        binding?.rvMovie?.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (binding?.rvMovie?.canScrollVertically(1) == false && !isLoading && page<=totalPage){
                    binding?.pbPagination?.visibility = View.VISIBLE
                    viewModel.discoverMovie(genre?:"", page)
                }
            }
        })
    }

    private fun initObserver() {
        viewModel.state.observe(this){
            if (it.getDiscoverState == BaseState.LOADING){
                isLoading = true
                if (page == 1){
                    binding?.pb?.visibility = View.VISIBLE
                    binding?.pbPagination?.visibility = View.GONE
                }else{
                    binding?.pb?.visibility = View.GONE
                    binding?.pbPagination?.visibility = View.VISIBLE
                }
            } else if (it.getDiscoverState == BaseState.SUCCESS){
                isLoading = false
                totalPage = it.discoverData?.totalPages?:1
                page += 1
                binding?.rvMovie?.visibility = View.VISIBLE
                binding?.pb?.visibility = View.GONE
                binding?.pbPagination?.visibility = View.GONE
                listResult.addAll(it.discoverData?.results?: arrayListOf())
                adapter.notifyDataSetChanged()
            }else if (it.getDiscoverState == BaseState.FAILED){
                binding?.pb?.visibility = View.GONE
                binding?.pbPagination?.visibility = View.GONE
                isLoading = false
            }
        }
    }

    private fun initView() {
        binding?.tvToolbarTitle?.text = intent.getStringExtra(GENRE) ?: ""
    }

    private lateinit var component:MovieComponent
    override fun inject() {
        component = appComponent.movieComponent().create()
        component.inject(this)
    }
}