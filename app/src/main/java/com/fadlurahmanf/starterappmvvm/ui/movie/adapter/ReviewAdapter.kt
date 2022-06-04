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

class ReviewAdapter(var list:ArrayList<ReviewResponse.Results>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class ViewHolder(view:View):RecyclerView.ViewHolder(view){
        val ivAuthor:ImageView = view.findViewById(R.id.iv_review_author)
        val name:TextView = view.findViewById(R.id.tv_author_name)
        val review:TextView = view.findViewById(R.id.tv_review)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.item_review, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val review = list[position]
        val photo = review.authorDetails?.avatarPath?.replaceFirst("/", "")

        val mHolder = holder as ViewHolder

        Glide.with(mHolder.ivAuthor)
            .load(photo)
            .centerCrop()
            .into(mHolder.ivAuthor)

        mHolder.name.text = "${review.authorDetails?.name} - ${review.authorDetails?.username}"
        mHolder.review.text = if(review.content?.length?:0 >= 100) "${review.content?.substring(0, 100)} ... more" else review.content
    }

    override fun getItemCount(): Int {
        return list.size
    }
}