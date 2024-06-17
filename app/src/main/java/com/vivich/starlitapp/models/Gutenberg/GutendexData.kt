package com.vivich.starlitapp.models.Gutenberg

import com.google.gson.annotations.SerializedName

data class GBook(
    val id: Int = -1,
    val title: String = "",
    val authors: List<GAuthor> = emptyList(),
    val download_count: Int = -1,
    val formats: GFormats = GFormats()
)

data class GFormats(
    @SerializedName("image/jpeg")
    val imageUrl: String = ""
)

data class GAuthor(
    val name: String = "",
    val birth_year: Int = -1,
    val death_year: Int = -1
)


data class GBookList(
    @SerializedName("results")
    val data: List<GBook> = emptyList(),

    val count: Int = 0,
    val next: String = "",
    val previous: String = "",
)