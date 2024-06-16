package com.vivich.starlitapp.models.Starlit

abstract class BookData(
    val id: Int,
    val title: String,
    val author: String,
    val imageURL: String = "",
    val languages: String = ""
)