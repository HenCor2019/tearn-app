package com.tearnsv.tearnapp.ui.categories.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tearnsv.tearnapp.R
import com.tearnsv.tearnapp.data.Category

class CategoryRecyclerViewAdapter(private val itemClickListener: ItemClickListener) :
    RecyclerView.Adapter<CategoryRecyclerViewAdapter.CategoryViewHolder>() {

    private var results: List<Category>? = null

    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(result: Category, itemClickListener: ItemClickListener) {
            val name = itemView.findViewById<TextView>(R.id.category_name)
            val image = itemView.findViewById<ImageView>(R.id.category_image)

            name.text = result.name
            Glide.with(itemView).load(result.imgUrl).placeholder(R.drawable.ic_launcher_background)
                .into(image)

            itemView.setOnClickListener { itemClickListener.onClickListener(result.id) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category_principal, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        results?.let {
            val result = it[position]
            holder.bind(result, itemClickListener)
        }
    }

    override fun getItemCount(): Int = results?.size ?: 0

    fun setData(results: List<Category>) {
        this.results = results
        notifyDataSetChanged()
    }

    interface ItemClickListener {
        fun onClickListener(id: String)
    }
}