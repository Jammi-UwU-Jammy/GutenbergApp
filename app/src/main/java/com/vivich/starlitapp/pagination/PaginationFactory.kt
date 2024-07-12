package com.vivich.starlitapp.pagination
import android.util.Log
import com.vivich.starlitapp.models.ParsedData.ChapterData
import retrofit2.Response

class PaginationFactory<Key, Item>(
    private val initialPage: Key,
    private inline val onLoadUpdated: (Boolean) -> Unit,
    private inline val onRequest:suspend (nextPage: Key)-> Response<Item>,
    private inline val getNextKey:suspend (Item)-> Key,
    private inline val onError:suspend (Throwable?)-> Unit,
    private inline val onSuccess:suspend (items: Item, newPage: Key)-> Unit,
) : Pagination<Key, Item>{
    private var currentKey = initialPage
    private var isMakingRequest = false

    override suspend fun loadNextPage() {
        if (isMakingRequest){
            return
        }
        isMakingRequest = true
        onLoadUpdated(true)
        try{
            val response = onRequest(currentKey)
            if (response.isSuccessful){
                isMakingRequest = false
                val items = response.body()!!
                currentKey = getNextKey(items)!!
                onSuccess(items, currentKey)
                onLoadUpdated(false)
            }

        }catch (e : Exception){
            onError(e)
            onLoadUpdated(false)
        }

    }

    override fun reset() {
        currentKey = initialPage
    }
}

class ContentPaginationFactory<Key, Item>(
    private val initialPage: Key,
    private inline val onLoadUpdated: (Boolean) -> Unit,
    private inline val onRequest:suspend (nextPage: Key)-> Item,
    private inline val getNextKey:suspend (Item)-> Key,
    private inline val onError:suspend (Throwable?)-> Unit,
    private inline val onSuccess:suspend (items: Item, newPage: Key)-> Unit,
):Pagination<Key, Item>{
    private var isMakingRequest = false
    private var currentKey = initialPage

    override suspend fun loadNextPage() {
        if (isMakingRequest){
            return
        }
        isMakingRequest = true
        onLoadUpdated(true)
        try{
            val response = onRequest(currentKey)
            isMakingRequest = false
            currentKey = getNextKey(response)
            onSuccess(response, currentKey)
            onLoadUpdated(false)

        }catch (e : Exception){
            onError(e)
            onLoadUpdated(false)
        }

    }

    override fun reset() {
        currentKey = initialPage
    }

}