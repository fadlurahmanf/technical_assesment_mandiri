package com.technical_assessment.starterappmvvm.ui.movie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.technical_assessment.starterappmvvm.R
import com.technical_assessment.starterappmvvm.data.response.movie.MovieVideoResponse

class TrailerAdapter(var list:ArrayList<MovieVideoResponse.Results>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var callBack: CallBack

    fun setCallBack(callBack: CallBack){
        this.callBack = callBack
    }

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

        mHolder.itemView.setOnClickListener {
            this.callBack.onClicked(result)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface CallBack{
        fun onClicked(movie:MovieVideoResponse.Results)
    }
}