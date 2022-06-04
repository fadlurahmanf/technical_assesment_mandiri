package com.fadlurahmanf.starterappmvvm.ui.movie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.fadlurahmanf.starterappmvvm.base.BaseActivity
import com.fadlurahmanf.starterappmvvm.base.BaseState
import com.fadlurahmanf.starterappmvvm.data.response.movie.DiscoverResponse
import com.fadlurahmanf.starterappmvvm.data.response.movie.ReviewResponse
import com.fadlurahmanf.starterappmvvm.databinding.ActivityListReviewBinding
import com.fadlurahmanf.starterappmvvm.di.component.MovieComponent
import com.fadlurahmanf.starterappmvvm.ui.movie.adapter.ReviewAdapter
import com.fadlurahmanf.starterappmvvm.ui.movie.viewmodel.MovieViewModel
import javax.inject.Inject

class ListReviewActivity : BaseActivity<ActivityListReviewBinding>(ActivityListReviewBinding::inflate) {

    companion object{
        const val RESULT = "RESULT"
    }

    @Inject
    lateinit var viewModel:MovieViewModel
    private var result:DiscoverResponse.Result ?= null

    private var page:Int = 1
    private var totalPage:Int = 1
    private var isLoading:Boolean = false

    override fun initSetup() {
        result = intent.getParcelableExtra(RESULT)
        initAdapter()
        initObserver()
        if (result != null && result?.id != null){
            viewModel.getReview(result?.id?:0, 1)
        }else{
            showSnackBar(binding!!.root, "There is no content here")
        }
    }

    private lateinit var adapter:ReviewAdapter
    private var reviews:ArrayList<ReviewResponse.Results> = arrayListOf()
    private fun initAdapter() {
        adapter = ReviewAdapter(reviews, true)
        binding?.rvReview?.adapter = adapter

        binding?.rvReview?.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (binding?.rvReview?.canScrollVertically(1) == false && !isLoading && page<=totalPage){
                    viewModel.getReview(result?.id?:0, page)
                }
            }
        })
    }

    private fun initObserver() {
        viewModel.state.observe(this){
            if (it.reviewState == BaseState.LOADING){
                isLoading = true
                if (page == 1){
                    binding?.pb?.visibility = View.VISIBLE
                    binding?.pbPagination?.visibility = View.GONE
                }else{
                    binding?.pbPagination?.visibility = View.VISIBLE
                    binding?.pb?.visibility = View.GONE
                }
            }else if (it.reviewState == BaseState.SUCCESS){
                isLoading = false
                totalPage = it.reviewData?.totalPages?:1
                page += 1
                binding?.pb?.visibility = View.GONE
                binding?.pbPagination?.visibility = View.GONE
                reviews.addAll(it.reviewData?.results?: arrayListOf())
                adapter.notifyDataSetChanged()
            }else if (it.reviewState == BaseState.FAILED){
                isLoading = false
                binding?.pb?.visibility = View.GONE
                binding?.pbPagination?.visibility = View.GONE
                showSnackBar(binding!!.root, it.errorReview?:"")
            }
        }
    }

    private lateinit var component: MovieComponent
    override fun inject() {
        component = appComponent.movieComponent().create()
        component.inject(this)
    }

}