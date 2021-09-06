package com.example.googlebooks.repository

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.example.googlebooks.interfaces.RepositoryCallback
import com.example.googlebooks.model.Book
import com.example.googlebooks.singleton.MySingleton

class BookRepository (private val context: Context, private val toast: Toast) {

    companion object {
        private var bookArray : ArrayList<Book> = ArrayList()
        private const val url = "https://www.googleapis.com/books/v1/volumes?q=flowers&startIndex=0&maxResults=10"
    }

    fun getData(repositoryCallback: RepositoryCallback) {
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                val jsonArray = response.optJSONArray("items")
                if(jsonArray != null) {
                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.optJSONObject(i)
                        val volumeInfo = jsonObject?.optJSONObject("volumeInfo")
                        val imagesLinks = volumeInfo?.optJSONObject("imageLinks")
                        var thumbnail = imagesLinks?.optString("thumbnail")
                        thumbnail = thumbnail?.substring(0, 4) + "s" + thumbnail?.substring(4)
                        val book = volumeInfo?.let {
                            Book(
                                it.optString("title"),
                                it.optString("description"),
                                thumbnail
                            )
                        }
                        if (book != null) {
                            bookArray.add(book)
                        }
                    }
                    repositoryCallback.onSuccess(bookArray)
                }
            },
            { error ->
                toast.show()
                Log.e("ERROR",error.toString())
            }
        )
// Access the RequestQueue through your singleton class.
        MySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest)
    }
}