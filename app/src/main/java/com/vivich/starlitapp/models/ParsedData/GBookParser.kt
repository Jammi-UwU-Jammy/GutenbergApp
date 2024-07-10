package com.vivich.starlitapp.models.ParsedData

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element


fun extractHrefTable(html: String): MutableList<hrefElement> {
    val document: Document = Jsoup.parse(html)
//    val table = document.selectFirst("table.autotable")
//
//    val tbody: Element? = table?.selectFirst("tbody")
    val hrefList = mutableListOf<hrefElement>()
    document?.select("a.pginternal")?.forEach { element ->
        hrefList.add(
            hrefElement(title = element.text(), hrefLink = element.attr("href"))
        )
    }
    return hrefList
}


@Composable
fun HrefTable(
    modifier: Modifier = Modifier,
    hrefLinks: BookHrefLinks
){
    Column {
        (hrefLinks.hrefList).forEach{ href ->
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { /*TODO*/ }
            ){
                Text(text = href.title)
            }
        }
    }
}

@Composable
fun ChapterContent(
    modifier: Modifier = Modifier,
    chapterData: ChapterData
){
    Text(text = chapterData.title)
    Column{
        (chapterData.paragraphs).forEach{
            Text(text = it)
        }
    }
}