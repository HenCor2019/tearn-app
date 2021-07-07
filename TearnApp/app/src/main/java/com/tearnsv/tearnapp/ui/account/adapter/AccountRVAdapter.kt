package com.tearnsv.tearnapp.ui.account.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tearnsv.tearnapp.R
import com.tearnsv.tearnapp.data.Tutor

class AccountRVAdapter(
    private val onClickHandler : ItemClickListener
) : RecyclerView.Adapter<AccountRVAdapter.AccountViewHolder>() {

    private var tutors: List<Tutor>? = null

    class AccountViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(tutor: Tutor,
                 onClickHandler: ItemClickListener) {
            val userImg = itemView.findViewById<ImageView>(R.id.img_tutor)
            val username = itemView.findViewById<TextView>(R.id.label_name_tutor)
            val userSubjects = itemView.findViewById<TextView>(R.id.label_topics_tutor)
            val userPunctuation = itemView.findViewById<TextView>(R.id.label_punctuation)
            val iconFav = itemView.findViewById<ImageView>(R.id.icon_fav_tutor)

            iconFav.setColorFilter(Color.parseColor("#ff0000"))

            username.text = tutor.username
            userSubjects.text = tutor.subjects?.reduce { acc, subject -> "$acc, $subject" }
            userPunctuation.text = tutor.puntuation.toString()

            Glide.with(itemView).load(tutor.imgUrl).centerCrop()
                .placeholder(R.drawable.default_photo).into(userImg)

            itemView.setOnClickListener {
                onClickHandler.onClickListener(tutor.id)
            }

            iconFav.setOnClickListener{
                onClickHandler.onClickFavButton(tutor.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_tutors_recomended, parent, false)
        return AccountViewHolder(view)
    }

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        tutors?.let {
            val tutor = it[position]
            holder.bind(tutor,onClickHandler)
        }
    }

    override fun getItemCount(): Int = tutors?.size ?: 0

    fun setData(tutors: List<Tutor>) {
        this.tutors = tutors
        notifyDataSetChanged()
    }

    interface ItemClickListener {
        fun onClickFavButton(id: String)
        fun onClickListener(id: String)
    }
}

