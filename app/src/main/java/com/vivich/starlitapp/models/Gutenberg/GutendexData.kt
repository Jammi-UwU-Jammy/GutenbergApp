package com.vivich.starlitapp.models.Gutenberg

data class GBook(
    val id: Int,
    val title: String,
    val GAuthors: List<GAuthor>,
    val download_count: Int
)

data class GAuthor(
    val name: String,
    val birth_year: Int,
    val death_year: Int
)

data class MetaData(
    val count: Int,
    val next: String,
    val previous: String,
)

data class GBookList(
    val data: List<GBook>,
    val metaData: MetaData
)