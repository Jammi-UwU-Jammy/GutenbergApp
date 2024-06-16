package com.vivich.starlitapp.pagination

interface Pagination<Key, Item>{
    suspend fun loadNextPage()
    fun reset()
}