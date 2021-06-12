package com.tearnsv.tearnapp.ui.course.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tearnsv.tearnapp.R
import com.tearnsv.tearnapp.data.TutorFromCourse

class CourseRecyclerViewAdapter(private val itemClickListener: ItemClickListener) :
    RecyclerView.Adapter<CourseRecyclerViewAdapter.CourseViewHolder>() {

    private var tutors: List<TutorFromCourse>? = null

    class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(tutor: TutorFromCourse, itemClickListener: ItemClickListener) {
            val tutorImg = itemView.findViewById<ImageView>(R.id.img_tutor)
            val tutorName = itemView.findViewById<TextView>(R.id.label_name_tutor)
            val tutorSubject = itemView.findViewById<TextView>(R.id.label_topics_tutor)
            val tutorPunctuation = itemView.findViewById<TextView>(R.id.label_punctuation)

            itemView.setOnClickListener { itemClickListener.onClickListener(tutor.id) }

            Glide.with(itemView).load(tutor.imgUrl).placeholder(R.drawable.default_photo)
                .into(tutorImg)

            tutorName.text = tutor.fullName
            tutorPunctuation.text = tutor.puntuation.toString()
            tutorSubject.text = tutor.subjects.reduce { acc, subject -> "$acc $subject" }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_tutors_recomended, parent, false)
        return CourseViewHolder(view)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        tutors?.let {
            val tutor = it[position]
            holder.bind(tutor, itemClickListener)
        }
    }

    override fun getItemCount(): Int = tutors?.size ?: 0

    fun setData(tutors: List<TutorFromCourse>) {
        this.tutors = tutors
        notifyDataSetChanged()
    }

    interface ItemClickListener {
        fun onClickListener(id: String)
    }
}
