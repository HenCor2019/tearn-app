package com.tearnsv.tearnapp.ui.tutorPerfil

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tearnsv.tearnapp.R
import com.tearnsv.tearnapp.data.Commentaries

class TutorCommentariesAdapter : RecyclerView.Adapter<TutorCommentariesAdapter.TutorValorationsViewHolder>() {

    private var commentaries : List<Commentaries>? = null

    class TutorValorationsViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bind(commentary: Commentaries){
            var imageView = itemView.findViewById<ImageView>(R.id.image_user_valoration)

            itemView.findViewById<TextView>(R.id.label_user_valoration_name)
                .text = commentary.author.username

            itemView.findViewById<TextView>(R.id.label_user_valoration_puntuation)
                .text = commentary.puntuation.toString()

            itemView.findViewById<TextView>(R.id.label_user_valoration_description)
                .text = commentary.description

            Glide.with(itemView)
                .load(commentary.author.imgUrl)
                .centerCrop()
                .placeholder(R.drawable.default_photo)
                .into(imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TutorValorationsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_tutor_valoration, parent, false)
        return TutorValorationsViewHolder(view)
    }

    override fun onBindViewHolder(holder: TutorValorationsViewHolder, position: Int) {
        commentaries?.let {
            val commentary = it[position]
            holder.bind(commentary)
        }
    }

    override fun getItemCount(): Int = commentaries?.size?:0

    fun setData(commentaries: List<Commentaries>){
        this.commentaries = commentaries
        notifyDataSetChanged()
    }
}