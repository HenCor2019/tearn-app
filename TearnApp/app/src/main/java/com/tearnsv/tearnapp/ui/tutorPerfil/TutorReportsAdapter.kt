package com.tearnsv.tearnapp.ui.tutorPerfil

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tearnsv.tearnapp.R
import java.text.SimpleDateFormat
import java.util.*

class TutorReportsAdapter(private var onClick : (List<String>)->Unit): RecyclerView.Adapter<TutorReportsAdapter.TutorReportsViewHolder>() {

    private var reports : List<String>? = null

    class TutorReportsViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bind(report: String, onClick : (List<String>)->Unit){
            itemView.findViewById<TextView>(R.id.course_name).text = report

            itemView.setOnClickListener{
                val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
                val currentDate = sdf.format(Date())
                Log.e("fecha", currentDate)
                val data = listOf(report,currentDate)
                onClick(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TutorReportsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category_course,parent,false)
        return TutorReportsViewHolder(view)
    }

    override fun onBindViewHolder(holder: TutorReportsViewHolder, position: Int) {
        reports?.let {
            var report = it[position]
            holder.bind(report, onClick)
        }
    }

    override fun getItemCount(): Int = reports?.size?:0

    fun setData(reports : List<String>){
        this.reports = reports
        notifyDataSetChanged()
    }
}