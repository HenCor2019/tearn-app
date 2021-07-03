package com.tearnsv.tearnapp.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tearnsv.tearnapp.R
import com.tearnsv.tearnapp.data.Tutors

class TutorsRVAdapter(
    private val onClickHandler: OnClickHandler):
    RecyclerView.Adapter<TutorsRVAdapter.TutorsRVViewHolder>() {

    private var tutorsRecommendations : List<Tutors>? = null
    private var favTutors : List<String>? = null

    class TutorsRVViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bind(tutor : Tutors,
                 onClickHandler: OnClickHandler,
                 favTutors: List<String>){
            itemView.findViewById<TextView>(R.id.label_name_tutor).text =
                tutor.fullName.capitalize()
            itemView.findViewById<TextView>(R.id.label_punctuation).text =
                tutor.puntuation.toString()

            val imageView = itemView.findViewById<ImageView>(R.id.img_tutor)
            val iconFav = itemView.findViewById<ImageView>(R.id.icon_fav_tutor)

            val isFav = isFav(tutor.id, favTutors)
            if(isFav) iconFav.setColorFilter(Color.parseColor("#ff0000"))
            else iconFav.setColorFilter(Color.parseColor("#707070"))

            var topicsTutor = ""
            tutor.subjects.forEach {
                topicsTutor += "${it}, "
            }

            itemView.findViewById<TextView>(R.id.label_topics_tutor).text =
                topicsTutor.dropLast(2)

            Glide.with(itemView)
                .load(tutor.imgUrl)
                .centerCrop()
                .placeholder(R.drawable.default_photo)
                .into(imageView)

            itemView.setOnClickListener {
                onClickHandler.onCLickItem(tutor.id)
            }

            iconFav.setOnClickListener{
                onClickHandler.onClickFavButton(tutor.id)
            }
        }

        fun isFav(id : String, favTutors: List<String>): Boolean{
            var exists = false
            favTutors.forEach{
                if(it == id) exists = true
            }
            return exists
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TutorsRVViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_tutors_recomended, parent, false)
        return TutorsRVViewHolder(view)
    }

    override fun onBindViewHolder(holder: TutorsRVViewHolder, position: Int) {
        tutorsRecommendations?.let{
            val tutor = it[position]
            holder.bind(tutor, onClickHandler,favTutors!!)
        }
    }

    override fun getItemCount(): Int = tutorsRecommendations?.size?:0

    fun setData(tutors : List<Tutors>){
        this.tutorsRecommendations = tutors
        notifyDataSetChanged()
    }

    fun setFavTutors(favTutors : List<String>){
        this.favTutors = favTutors
        notifyDataSetChanged()
    }

    interface OnClickHandler{
        fun onClickFavButton(id: String)
        fun onCLickItem(id : String)
    }
}