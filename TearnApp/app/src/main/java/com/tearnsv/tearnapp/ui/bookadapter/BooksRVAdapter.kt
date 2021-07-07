package com.tearnsv.tearnapp.ui.bookadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tearnsv.tearnapp.R
import com.tearnsv.tearnapp.data.ItemsBook

class BooksRVAdapter(private val itemClickListener: ItemClickListener) :
  RecyclerView.Adapter<BooksRVAdapter.BooksRVViewHolder>() {

  private var booksRecommended: List<ItemsBook>? = null

  class BooksRVViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(book: ItemsBook, itemClickListener: ItemClickListener) {
      val thumbnail = itemView.findViewById<ImageView>(R.id.book_image)
      val bookName = itemView.findViewById<TextView>(R.id.book_name)
      val bookDate = itemView.findViewById<TextView>(R.id.book_date)
      val bookAuthors = itemView.findViewById<TextView>(R.id.book_authors)

      if (book.volumeInfo.imageLinks?.thumbnail == null)
        thumbnail.setImageResource(DEFAULT_PHOTO)
      else
        Glide.with(itemView).load(book.volumeInfo.imageLinks.thumbnail)
          .placeholder(DEFAULT_PHOTO).into(thumbnail)

      bookName.text = book.volumeInfo.title ?: DEFAULT_TITLE
      bookDate.text = book.volumeInfo.publishedDate ?: DEFAULT_YEAR
      bookAuthors.text = book.volumeInfo.authors?.reduce { acc, author -> "$acc $author" }
        ?: DEFAULT_AUTHORS

      itemView.setOnClickListener {
        itemClickListener.onClickItem(book.volumeInfo.previewLink)
      }
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksRVViewHolder {
    val view = LayoutInflater.from(parent.context)
      .inflate(R.layout.item_book, parent, false)
    return BooksRVViewHolder(view)
  }

  override fun onBindViewHolder(holder: BooksRVViewHolder, position: Int) {
    booksRecommended?.let {
      val book = it[position]
      holder.bind(book, itemClickListener)
    }
  }

  override fun getItemCount(): Int = booksRecommended?.size ?: 0

  fun setData(booksRecommended: List<ItemsBook>) {
    this.booksRecommended = booksRecommended
    notifyDataSetChanged()
  }

  interface ItemClickListener {
    fun onClickItem(url: String)
  }

  companion object {
    const val DEFAULT_PHOTO = R.drawable.default_book
    const val DEFAULT_TITLE = "Desconocido"
    const val DEFAULT_YEAR = "Año desconocido"
    const val DEFAULT_AUTHORS = "Autores desconocidos"
  }
}