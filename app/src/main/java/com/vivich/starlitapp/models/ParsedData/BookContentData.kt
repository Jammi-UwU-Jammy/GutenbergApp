package com.vivich.starlitapp.models.ParsedData


data class hrefElement(
    val title: String,
    val hrefLink: String
)

data class BookHrefLinks(
    val htmlUrl: String = "",
    val hrefList: List<hrefElement> = emptyList(),
)

data class ChapterData(
    val title: String = "Empty title",
    val paragraphs: List<String> = emptyList()
)