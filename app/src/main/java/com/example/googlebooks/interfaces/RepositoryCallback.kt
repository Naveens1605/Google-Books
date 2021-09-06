package com.example.googlebooks.interfaces

import com.example.googlebooks.model.Book

interface RepositoryCallback {
    fun onSuccess(books : ArrayList<Book>)
}