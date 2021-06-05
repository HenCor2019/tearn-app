package com.tearnsv.tearnapp.ui.preferences

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tearnsv.tearnapp.R
import com.tearnsv.tearnapp.data.Preference

class PreferencesSelectedAdapter :
        RecyclerView.Adapter<PreferencesSelectedAdapter.PreferencesSelectedViewHolder>() {

    private var preferencesSelected : List<Preference>? = null

    class PreferencesSelectedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(preference: Preference){
            var labelPreference = itemView.findViewById<TextView>(R.id.label_preference)
            labelPreference.text = preference.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreferencesSelectedViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_preferences_selected, parent, false)
        return PreferencesSelectedViewHolder(view)
    }

    override fun onBindViewHolder(holder: PreferencesSelectedViewHolder, position: Int) {
        preferencesSelected?.let{
            val preference = it[position]
            holder.bind(preference)
        }
    }

    override fun getItemCount(): Int = preferencesSelected?.size?:0

    fun setData(preferences : List<Preference>){
        this.preferencesSelected = preferences
        notifyDataSetChanged()
    }
}