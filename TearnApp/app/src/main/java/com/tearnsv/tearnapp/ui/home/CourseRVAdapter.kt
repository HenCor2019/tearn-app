package com.tearnsv.tearnapp.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tearnsv.tearnapp.R
import com.tearnsv.tearnapp.data.CoursePreferences

class CourseRVAdapter(private val courseItemListener: CourseItemListener) :
  RecyclerView.Adapter<CourseRVAdapter.TopicsRVViewHolder>() {

  private var coursesRecommendations: List<CoursePreferences>? = null

  class TopicsRVViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(course: CoursePreferences, courseItemListener: CourseItemListener) {
      itemView.findViewById<TextView>(R.id.label_course_name).text =
        course.name.capitalize()
      itemView.findViewById<TextView>(R.id.label_counter_courses).text =
        "${course.tutorCount} tutores"

      itemView.setOnClickListener { courseItemListener.onCourseClick(course.id) }
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicsRVViewHolder {
    val view = LayoutInflater.from(parent.context)
      .inflate(R.layout.item_topics_recomended, parent, false)
    return TopicsRVViewHolder(view)
  }

  override fun onBindViewHolder(holder: TopicsRVViewHolder, position: Int) {
    coursesRecommendations?.let {
      val course = it[position]
      holder.bind(course, courseItemListener)
    }
  }

  override fun getItemCount(): Int = coursesRecommendations?.size ?: 0

  fun setData(courses: List<CoursePreferences>) {
    this.coursesRecommendations = courses
    notifyDataSetChanged()
  }


  interface CourseItemListener {
    fun onCourseClick(id: String)
  }
}