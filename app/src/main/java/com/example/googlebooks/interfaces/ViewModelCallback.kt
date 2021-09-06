package com.example.googlebooks.interfaces

import com.example.googlebooks.model.Book

interface ViewModelCallback {
    fun onSuccess(book : ArrayList<Book>)
}