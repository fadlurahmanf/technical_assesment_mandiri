package com.fadlurahmanf.starterappmvvm.ui.movie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fadlurahmanf.starterappmvvm.R
import com.fadlurahmanf.starterappmvvm.data.response.movie.DiscoverResponse

class MovieAdapter(var list:ArrayList<DiscoverResponse.Result>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var callBack: CallBack

    fun setCallBack(callBack: CallBack){
        this.callBack = callBack
    }

    inner class ViewHolder(view:View):RecyclerView.ViewHolder(view){
        val poster:ImageView = view.findViewById(R.id.iv_poster)
        val title:TextView = view.findViewById(R.id.tv_movie_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val result = list[position]

        val mHolder = holder as ViewHolder

        Glide.with(holder.poster)
            .load("https://image.tmdb.org/t/p/original${result.posterPath}")
            .centerCrop()
            .into(holder.poster)
        mHolder.title.text = result.originalTitle

        mHolder.itemView.setOnClickListener {
            callBack.onClicked(result)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface CallBack{
        fun onClicked(result:DiscoverResponse.Result)
    }
}