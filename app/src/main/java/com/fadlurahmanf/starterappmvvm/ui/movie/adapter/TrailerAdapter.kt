package com.fadlurahmanf.starterappmvvm.ui.movie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fadlurahmanf.starterappmvvm.R
import com.fadlurahmanf.starterappmvvm.data.response.movie.MovieVideoResponse

class TrailerAdapter(var list:ArrayList<MovieVideoResponse.Results>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    inner class ViewHolder(view:View):RecyclerView.ViewHolder(view){
        val thumbnail:ImageView = view.findViewById(R.id.iv_video)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.item_trailer, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val result = list[position]

        val mHolder = holder as ViewHolder

        Glide.with(mHolder.thumbnail)
            .load("https://img.youtube.com/vi/${result.key}/0.jpg")
            .centerCrop()
            .into(mHolder.thumbnail)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}