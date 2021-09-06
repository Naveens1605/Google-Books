package com.example.googlebooks.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.googlebooks.R
import com.example.googlebooks.glide.GlideApp
import com.example.googlebooks.model.Book

class BookListAdapter(private val book : ArrayList<Book>,private val progressBar: ProgressBar) : RecyclerView.Adapter<BookListAdapter.BookViewHolder>() {

    inner class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val description: TextView = itemView.findViewById(R.id.description)
        val thumbnail: ImageView = itemView.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_books, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        progressBar.visibility = View.GONE
        val currentBook = book[position]
        holder.title.text = currentBook.title
        holder.description.text = currentBook.description
        GlideApp.with(holder.title.context).load(currentBook.thumbnail).into(holder.thumbnail)
    }

    override fun getItemCount(): Int {
        return book.size
    }
}
