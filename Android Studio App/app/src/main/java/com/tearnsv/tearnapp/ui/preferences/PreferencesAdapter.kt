package com.tearnsv.tearnapp.ui.preferences

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.tearnsv.tearnapp.R
import com.tearnsv.tearnapp.data.Category

class PreferencesAdapter(private val onClickSuccess: OnClickSuccess) :
    RecyclerView.Adapter<PreferencesAdapter.PreferencesViewHolder>(){

    private var categories : List<Category>? = null

    class PreferencesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bind(category: Category, onClickSuccess: OnClickSuccess){
            var labelCategory = itemView.findViewById<TextView>(R.id.label_category_preferences)
            var layoutCategory = itemView.findViewById<ConstraintLayout>(R.id.preferences_layout)

            labelCategory.text = category.name.capitalize()

            labelCategory.setOnClickListener{

                val isSuccessAction = onClickSuccess.onClickSuccess(category)

                if(!isSuccessAction)
                    return@setOnClickListener

                if(category.isPreference){
                    layoutCategory.setBackgroundColor(Color.parseColor("#665F70"))
                    labelCategory.setTextColor(Color.WHITE)
                    category.isPreference = false
                } else{
                    layoutCategory.setBackgroundColor(Color.parseColor("#67EA67"))
                    labelCategory.setTextColor(Color.BLACK)
                    category.isPreference = true
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreferencesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_preferences, parent, false)
        return PreferencesViewHolder(view)
    }

    override fun onBindViewHolder(holder: PreferencesViewHolder, position: Int) {
        categories?.let{
            val category = it[position]
            holder.bind(category,onClickSuccess)
        }
    }

    override fun getItemCount(): Int = categories?.size?:0

    fun setData(categories: List<Category>){
        this.categories = categories
        notifyDataSetChanged()
    }

    interface OnClickSuccess{
        fun onClickSuccess(category: Category): Boolean
    }
}

