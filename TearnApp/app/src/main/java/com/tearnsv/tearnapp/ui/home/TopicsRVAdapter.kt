package com.tearnsv.tearnapp.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tearnsv.tearnapp.R
import com.tearnsv.tearnapp.data.Subjects

class TopicsRVAdapter: RecyclerView.Adapter<TopicsRVAdapter.TopicsRVViewHolder>() {

    private var topicsRecommedations : List<Subjects>? = null

    class TopicsRVViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(subject : Subjects){
            itemView.findViewById<TextView>(R.id.label_topic_name).text =
                subject.name.capitalize()
            itemView.findViewById<TextView>(R.id.label_counter_topics).text =
                "${subject.courseCount} cursos"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicsRVViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_topics_recomended, parent, false)
        return TopicsRVViewHolder(view)
    }

    override fun onBindViewHolder(holder: TopicsRVViewHolder, position: Int) {
        topicsRecommedations?.let{
            val subject = it[position]
            holder.bind(subject)
        }
    }

    override fun getItemCount(): Int = topicsRecommedations?.size?:0

    fun setData(topics : List<Subjects>){
        this.topicsRecommedations = topics
        notifyDataSetChanged()
    }
}