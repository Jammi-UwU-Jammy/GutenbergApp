package com.vivich.starlitapp.models.ParsedData


data class BookHrefLinks(
    val htmlUrl: String = "",
    val hrefList: List<String> = emptyList(),
)

data class ChapterData(
    val title: String = "",
    val paragraphs: List<String> = emptyList()
)