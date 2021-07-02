package com.tearnsv.tearnapp.ui.account.adapter

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tearnsv.tearnapp.R
import com.tearnsv.tearnapp.data.SubjectResponse

class SubjectRVAdapter(private val itemClickListener: ItemClickListener) :
    RecyclerView.Adapter<SubjectRVAdapter.SubjectViewHolder>() {

    private var subjectsNames: List<String>? = null

    class SubjectViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(subjectName: String, itemClickListener: ItemClickListener) {
            val name = itemView.findViewById<TextView>(R.id.subject_name)
            val iconDelete = itemView.findViewById<ImageView>(R.id.action_delete)
            iconDelete.setOnClickListener {
                itemClickListener.onIconDeleteListener(subjectName)
            }

            name.text = subjectName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_subject, parent, false)
        return SubjectViewHolder(view)
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        subjectsNames?.let {
            val subjectName = it[position]
            holder.bind(subjectName, itemClickListener)
        }
    }

    fun setData(subjects: List<String>) {
        subjectsNames = subjects
        notifyDataSetChanged()
    }

    override fun getItemCount() = subjectsNames?.size ?: 0

    interface ItemClickListener {
        fun onIconDeleteListener(name: String)
    }

}