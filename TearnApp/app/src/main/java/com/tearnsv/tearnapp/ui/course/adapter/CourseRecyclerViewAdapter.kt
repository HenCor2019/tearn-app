package com.tearnsv.tearnapp.ui.course.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tearnsv.tearnapp.R
import com.tearnsv.tearnapp.data.TutorFromCourse

class CourseRecyclerViewAdapter(private val onClickHandler: OnClickHandler) :
    RecyclerView.Adapter<CourseRecyclerViewAdapter.CourseViewHolder>() {

    private var tutors: List<TutorFromCourse>? = null
    private var favTutors : List<String>? = null

    class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(tutor: TutorFromCourse,
                 onClickHandler: OnClickHandler,
                 favTutors: List<String>) {
            val tutorImg = itemView.findViewById<ImageView>(R.id.img_tutor)
            val tutorName = itemView.findViewById<TextView>(R.id.label_name_tutor)
            val tutorSubject = itemView.findViewById<TextView>(R.id.label_topics_tutor)
            val tutorPunctuation = itemView.findViewById<TextView>(R.id.label_punctuation)

            val iconFav = itemView.findViewById<ImageView>(R.id.icon_fav_tutor)

            val isFav = isFav(tutor.id, favTutors)
            if(isFav) iconFav.setColorFilter(Color.parseColor("#ff0000"))
            else iconFav.setColorFilter(Color.parseColor("#707070"))

            Glide.with(itemView).load(tutor.imgUrl).placeholder(R.drawable.default_photo)
                .into(tutorImg)

            tutorName.text = tutor.fullName
            tutorPunctuation.text = tutor.puntuation.toString()
            tutorSubject.text = tutor.subjects.reduce { acc, subject -> "$acc $subject" }

            itemView.setOnClickListener { onClickHandler.onClickListener(tutor.id) }
            iconFav.setOnClickListener{ onClickHandler.onClickFavButton(tutor.id) }
        }

        fun isFav(id : String, favTutors: List<String>): Boolean{
            var exists = false
            favTutors.forEach{
                if(it == id) exists = true
            }
            return exists
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
            holder.bind(tutor, onClickHandler, favTutors!!)
        }
    }

    override fun getItemCount(): Int = tutors?.size ?: 0

    fun setData(tutors: List<TutorFromCourse>) {
        this.tutors = tutors
        notifyDataSetChanged()
    }

    fun setFavTutors(favTutors : List<String>){
        this.favTutors = favTutors
        notifyDataSetChanged()
    }

    interface OnClickHandler {
        fun onClickFavButton(id: String)
        fun onClickListener(id: String)
    }
}
