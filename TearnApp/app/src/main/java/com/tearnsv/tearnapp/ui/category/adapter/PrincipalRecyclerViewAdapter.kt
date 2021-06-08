package com.tearnsv.tearnapp.ui.category.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tearnsv.tearnapp.R
import com.tearnsv.tearnapp.data.SubjectResponse

class PrincipalRecyclerViewAdapter(private val itemClickListener: ItemClickListener) :
    RecyclerView.Adapter<PrincipalRecyclerViewAdapter.PrincipalViewHolder>() {

    private var subjects: List<SubjectResponse>? = null

    class PrincipalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val viewPool = RecyclerView.RecycledViewPool()

        fun bind(subject: SubjectResponse, itemClickListener: ItemClickListener) {
            val name = itemView.findViewById<TextView>(R.id.subject_name)
            name.text = subject.name
            val recyclerCourses = itemView.findViewById<RecyclerView>(R.id.recycler_view_courses)
            val secondaryRVAdapter = SecondaryRecyclerViewAdapter(subject.courses, itemClickListener)

            recyclerCourses.apply {
                layoutManager = LinearLayoutManager(recyclerCourses.context)
                adapter = secondaryRVAdapter
                setRecycledViewPool(viewPool)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrincipalViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_one_subject, parent, false)
        return PrincipalViewHolder(view)
    }

    override fun onBindViewHolder(holder: PrincipalViewHolder, position: Int) {
        subjects?.let {
            val subject = it[position]
            holder.bind(subject, itemClickListener)
        }
    }

    override fun getItemCount(): Int = subjects?.size ?: 0

    fun setData(subjects: List<SubjectResponse>) {
        this.subjects = subjects
        notifyDataSetChanged()
    }

    interface ItemClickListener{
        fun onClickListener(id: String)

    }

}