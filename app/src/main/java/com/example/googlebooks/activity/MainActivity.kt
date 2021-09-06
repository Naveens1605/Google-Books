package com.example.googlebooks.activity

import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.googlebooks.R
import com.example.googlebooks.adapter.BookListAdapter
import com.example.googlebooks.interfaces.ViewModelCallback
import com.example.googlebooks.model.Book
import com.example.googlebooks.viewmodel.BookViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val progressBar = findViewById<ProgressBar>(R.id.progress_circular)
        val toast = Toast.makeText(this,"No Data Found",Toast.LENGTH_LONG)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)
        val viewModel = ViewModelProvider(this).get(BookViewModel::class.java)
        viewModel.getData(object : ViewModelCallback{
            override fun onSuccess(book: ArrayList<Book>) {
                Log.d("TAG",book.toString())
                val adapter = BookListAdapter(book,progressBar)
                recyclerView.adapter = adapter
            }
        },this,toast)
    }
}