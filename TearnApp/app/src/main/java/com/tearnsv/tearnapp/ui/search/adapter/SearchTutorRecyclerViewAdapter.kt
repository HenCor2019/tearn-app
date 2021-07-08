package com.tearnsv.tearnapp.ui.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tearnsv.tearnapp.R
import com.tearnsv.tearnapp.data.TutorSearch

class SearchTutorRecyclerViewAdapter(private val itemClickListener: ItemClickListener) :
    RecyclerView.Adapter<SearchTutorRecyclerViewAdapter.TutorSearchViewHolder>() {

    private var tutors: List<TutorSearch>? = null

    class TutorSearchViewHolder
        (itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(tutor: TutorSearch, itemClickListener: ItemClickListener) {
            val userImg = itemView.findViewById<ImageView>(R.id.user_image)
            val username = itemView.findViewById<TextView>(R.id.user_name)
            val userSubjects = itemView.findViewById<TextView>(R.id.user_subject)
            val userPunctuation = itemView.findViewById<TextView>(R.id.user_punctuation)

            itemView.setOnClickListener { itemClickListener.onClickListener(tutor.id) }

            username.text = tutor.username
            userSubjects.text = tutor.subjects.reduce { acc, subject -> "$acc $subject" }
            userPunctuation.text = tutor.puntuation.toString()

            Glide.with(itemView).load(tutor.imgUrl).centerCrop()
                .placeholder(R.drawable.default_photo).into(userImg)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TutorSearchViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_tutor_search, parent, false)
        return TutorSearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: TutorSearchViewHolder, position: Int) {
        tutors?.let {
            val tutor = it[position]
            holder.bind(tutor, itemClickListener)
        }
    }

    override fun getItemCount(): Int = tutors?.size ?: 0

    fun setData(tutors: List<TutorSearch>) {
        this.tutors = tutors
        notifyDataSetChanged()
    }

    interface ItemClickListener {
        fun onClickListener(id: String)
    }
}
