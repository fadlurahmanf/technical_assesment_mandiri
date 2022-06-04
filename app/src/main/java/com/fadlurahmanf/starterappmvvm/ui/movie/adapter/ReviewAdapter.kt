package com.fadlurahmanf.starterappmvvm.ui.movie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fadlurahmanf.starterappmvvm.R
import com.fadlurahmanf.starterappmvvm.data.response.movie.ReviewResponse

class ReviewAdapter(var list:ArrayList<ReviewResponse.Results>, var isFull:Boolean) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class ViewHolder2(view:View):RecyclerView.ViewHolder(view){
        val ivAuthor:ImageView = view.findViewById(R.id.iv_review_author)
        val name:TextView = view.findViewById(R.id.tv_author_name)
        val review:TextView = view.findViewById(R.id.tv_review)
        val reviewScore:TextView = view.findViewById(R.id.tv_review_score)
    }

    inner class ViewHolder1(view:View):RecyclerView.ViewHolder(view){
        val ivAuthor:ImageView = view.findViewById(R.id.iv_review_author)
        val name:TextView = view.findViewById(R.id.tv_author_name)
        val review:TextView = view.findViewById(R.id.tv_review)
        val reviewScore:TextView = view.findViewById(R.id.tv_review_score)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val notFullView:View = LayoutInflater.from(parent.context).inflate(R.layout.item_review, parent, false)
        val fullView:View = LayoutInflater.from(parent.context).inflate(R.layout.item_review_full, parent, false)
        return when(viewType){
            1 -> ViewHolder1(fullView)
            else -> ViewHolder2(notFullView)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val review = list[position]
        val photo = review.authorDetails?.avatarPath?.replaceFirst("/", "")

        if (holder.itemViewType == 1){
            val mHolder = holder as ViewHolder1

            Glide.with(mHolder.ivAuthor)
                .load(photo)
                .centerCrop()
                .into(mHolder.ivAuthor)

            mHolder.name.text = "${review.authorDetails?.name} - ${review.authorDetails?.username}"
            mHolder.review.text = review.content
            if (review.authorDetails?.rating == null){
                mHolder.reviewScore.visibility = View.INVISIBLE
            }else{
                mHolder.reviewScore.text = "${review.authorDetails?.rating?.toString()}/10"
            }
        }else if (holder.itemViewType == 2){
            val mHolder = holder as ViewHolder2

            Glide.with(mHolder.ivAuthor)
                .load(photo)
                .centerCrop()
                .into(mHolder.ivAuthor)

            mHolder.name.text = "${review.authorDetails?.name} - ${review.authorDetails?.username}"
            mHolder.review.text = if(review.content?.length?:0 >= 100) "${review.content?.substring(0, 100)} ... more" else review.content
            if (review.authorDetails?.rating == null){
                mHolder.reviewScore.visibility = View.INVISIBLE
            }else{
                mHolder.reviewScore.text = "${review.authorDetails?.rating?.toString()}/10"
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return when(isFull){
            true -> 1
            else -> 2
        }
    }
}