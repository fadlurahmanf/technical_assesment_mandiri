package com.fadlurahmanf.starterappmvvm.ui.movie

import android.view.View
import com.bumptech.glide.Glide
import com.fadlurahmanf.starterappmvvm.base.BaseActivity
import com.fadlurahmanf.starterappmvvm.base.BaseState
import com.fadlurahmanf.starterappmvvm.data.response.movie.DiscoverResponse
import com.fadlurahmanf.starterappmvvm.data.response.movie.MovieResponse
import com.fadlurahmanf.starterappmvvm.data.response.movie.MovieVideoResponse
import com.fadlurahmanf.starterappmvvm.data.response.movie.ReviewResponse
import com.fadlurahmanf.starterappmvvm.databinding.ActivityDetailMovieBinding
import com.fadlurahmanf.starterappmvvm.di.component.MovieComponent
import com.fadlurahmanf.starterappmvvm.ui.movie.adapter.ReviewAdapter
import com.fadlurahmanf.starterappmvvm.ui.movie.adapter.TrailerAdapter
import com.fadlurahmanf.starterappmvvm.ui.movie.viewmodel.MovieViewModel
import javax.inject.Inject

class DetailMovieActivity : BaseActivity<ActivityDetailMovieBinding>(ActivityDetailMovieBinding::inflate) {

    companion object{
        const val RESULT = "RESULT"
    }

    @Inject
    lateinit var viewModel:MovieViewModel

    private var result:DiscoverResponse.Result ?= null
    private var movie:MovieResponse ?= null

    override fun initSetup() {
        result = intent.getParcelableExtra(RESULT)
        initAdapter()
        initView()
        initObserver()
        if (result?.id != null){
            viewModel.getDetailMovie(result!!.id!!)
        }else{
            if (binding?.root != null){
                showSnackBar(binding!!.root, "There is no content here")
            }
        }
    }

    private lateinit var trailerAdapter:TrailerAdapter
    private var movieVideos:ArrayList<MovieVideoResponse.Results> = arrayListOf()

    private lateinit var reviewAdapter:ReviewAdapter
    private var reviews:ArrayList<ReviewResponse.Results> = arrayListOf()
    private fun initAdapter(){
        trailerAdapter = TrailerAdapter(movieVideos)
        binding?.rvTrailer?.adapter = trailerAdapter

        reviewAdapter = ReviewAdapter(reviews)
        binding?.rvReview?.adapter = reviewAdapter
    }

    private fun initView() {
        binding?.tvToolbarTitle?.text = result?.title ?: ""
        Glide.with(binding!!.ivPoster)
            .load("https://image.tmdb.org/t/p/original${result?.backdropPath}")
            .centerCrop()
            .into(binding!!.ivPoster)
    }

    private fun initObserver() {
        viewModel.state.observe(this){
            if (it.detailMovieState == BaseState.LOADING){
                binding?.pb?.visibility = View.VISIBLE
                binding?.llMain?.visibility = View.GONE
            }else if (it.detailMovieState == BaseState.SUCCESS){
                movie = it.detailMovieData
                binding?.pb?.visibility = View.GONE
                binding?.llMain?.visibility = View.VISIBLE
                binding?.tvMovieTitle?.text = movie?.title ?: ""
                binding?.tvMovieDescription?.text = movie?.overview ?: ""
            }else if (it.detailMovieState == BaseState.FAILED){
                binding?.pb?.visibility = View.GONE
                binding?.llMain?.visibility = View.GONE
                showSnackBar(binding!!.root, it.errorDetailMovie?:"")
            }

            if (it.movieVideoState == BaseState.LOADING){
                binding?.llTrailer?.visibility = View.GONE
            }else if (it.movieVideoState == BaseState.SUCCESS){
                binding?.llTrailer?.visibility = View.VISIBLE
                movieVideos.clear()
                movieVideos.addAll(it.movieVideoData?.results?: arrayListOf())
                trailerAdapter.notifyDataSetChanged()
            }else if (it.movieVideoState == BaseState.FAILED){
                binding?.llTrailer?.visibility = View.GONE
            }

            if (it.reviewState == BaseState.LOADING){
                binding?.llReview?.visibility = View.GONE
            }else if (it.reviewState == BaseState.SUCCESS){
                binding?.llReview?.visibility = View.VISIBLE
                reviews.clear()
                reviews.addAll(it.reviewData?.results?: arrayListOf())
                reviewAdapter.notifyDataSetChanged()
            }else if (it.reviewState == BaseState.FAILED){
                binding?.llReview?.visibility = View.GONE
            }
        }
    }

    private lateinit var component:MovieComponent
    override fun inject() {
        component = appComponent.movieComponent().create()
        component.inject(this)
    }

}