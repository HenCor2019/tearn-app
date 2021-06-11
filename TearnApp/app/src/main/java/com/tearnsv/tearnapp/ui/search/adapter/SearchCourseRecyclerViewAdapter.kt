package com.tearnsv.tearnapp.ui.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tearnsv.tearnapp.R
import com.tearnsv.tearnapp.data.SearchCourse

class SearchCourseRecyclerViewAdapter(private val itemClickListener: SearchCourseRecyclerViewAdapter.ItemClickListener) :
    RecyclerView.Adapter<SearchCourseRecyclerViewAdapter.CourseSearchViewHolder>() {

    private var courses: List<SearchCourse>? = null

    class CourseSearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val courseCountString = "tutores"
        fun bind(course: SearchCourse, itemClickListener: ItemClickListener) {
            val courseName = itemView.findViewById<TextView>(R.id.course_search_name)
            val courseCount = itemView.findViewById<TextView>(R.id.course_search_tutors)
            courseName.text = course.name
            courseCount.text = "${course.tutorsCount} $courseCountString"

            itemView.setOnClickListener { itemClickListener.onClickListener(course.id) }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseSearchViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_search_course, parent, false)
        return CourseSearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: CourseSearchViewHolder, position: Int) {
        courses?.let {
            val course = it[position]
            holder.bind(course, itemClickListener)
        }
    }

    override fun getItemCount(): Int = courses?.size ?: 0

    fun setData(courses: List<SearchCourse>) {
        this.courses = courses
        notifyDataSetChanged()
    }

    interface ItemClickListener{
        fun onClickListener(id: String)
    }
}
