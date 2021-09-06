package com.example.googlebooks.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.googlebooks.interfaces.ViewModelCallback
import com.example.googlebooks.interfaces.RepositoryCallback
import com.example.googlebooks.model.Book
import com.example.googlebooks.repository.BookRepository
import kotlinx.coroutines.launch

class BookViewModel : ViewModel() {

    private lateinit var bookRepository: BookRepository
    private lateinit var books: ArrayList<Book>

    fun getData(viewModelCallback: ViewModelCallback,context : Context,toast: Toast) = viewModelScope.launch {
        bookRepository = BookRepository(context,toast)
        bookRepository.getData(object : RepositoryCallback{
            override fun onSuccess(books: ArrayList<Book>) {
                this@BookViewModel.books = books
                viewModelCallback.onSuccess(this@BookViewModel.books)
            }
        })
    }
}