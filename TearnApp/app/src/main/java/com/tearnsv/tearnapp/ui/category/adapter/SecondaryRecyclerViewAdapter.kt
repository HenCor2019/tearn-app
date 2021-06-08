package com.tearnsv.tearnapp.ui.category.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tearnsv.tearnapp.R
import com.tearnsv.tearnapp.data.CourseResponse

class SecondaryRecyclerViewAdapter(private var courses: List<CourseResponse>, private val itemClickListener: PrincipalRecyclerViewAdapter.ItemClickListener) :
    RecyclerView.Adapter<SecondaryRecyclerViewAdapter.SecondaryViewHolder>() {

    class SecondaryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(course: CourseResponse, itemClickListener: PrincipalRecyclerViewAdapter.ItemClickListener) {
            val name = itemView.findViewById<TextView>(R.id.course_name)
            name.text = course.name

            itemView.setOnClickListener {
                itemClickListener.onClickListener(course._id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SecondaryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category_course, parent, false)
        return SecondaryViewHolder(view)
    }

    override fun onBindViewHolder(holder: SecondaryViewHolder, position: Int) {
        courses.let {
            val course = it[position]
            holder.bind(course, itemClickListener)
        }
    }

    override fun getItemCount(): Int = courses?.size ?: 0

    fun setData(courses: List<CourseResponse>) {
        this.courses = courses
        notifyDataSetChanged()
    }


}
