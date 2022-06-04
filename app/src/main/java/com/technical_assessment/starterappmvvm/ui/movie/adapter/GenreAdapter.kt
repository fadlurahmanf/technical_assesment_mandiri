package com.technical_assessment.starterappmvvm.ui.movie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.technical_assessment.starterappmvvm.R
import com.technical_assessment.starterappmvvm.data.response.movie.GenresResponse

class GenreAdapter(var list:ArrayList<GenresResponse.Genre>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var callBack: CallBack

    fun setCallBack(callBack: CallBack){
        this.callBack = callBack
    }

    inner class ViewHolder(view:View):RecyclerView.ViewHolder(view){
        val tvGenre:TextView = view.findViewById(R.id.tv_genre)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.item_genre, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val genre = list[position]
        val mHolder = holder as ViewHolder
        mHolder.tvGenre.text = genre.name

        mHolder.itemView.setOnClickListener {
            callBack.onClicked(genre)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface CallBack{
        fun onClicked(genre:GenresResponse.Genre)
    }
}