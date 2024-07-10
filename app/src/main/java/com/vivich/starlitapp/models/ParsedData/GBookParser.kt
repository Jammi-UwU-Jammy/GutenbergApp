package com.vivich.starlitapp.models.ParsedData

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.Paragraph
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element


fun extractHrefTable(html: String): MutableList<String> {
    val document: Document = Jsoup.parse(html)
    val table = document.selectFirst("table.autotable")

    val tbody: Element? = table?.selectFirst("tbody")
    val hrefList = mutableListOf<String>()
    tbody?.select("a.pginternal")?.forEach { element ->
        val href = element.attr("href")
        hrefList.add(href)
    }
    return hrefList
}


@Composable
fun HrefTable(
    modifier: Modifier = Modifier,
    hrefList: BookHrefLinks
){
    LazyColumn {
        items(hrefList.hrefList){ href ->
            Text(text = hrefList.htmlUrl + href)
        }
    }
}

@Composable
fun ChapterContent(
    modifier: Modifier = Modifier,
    chapterData: ChapterData
){
    Text(text = chapterData.title)
    LazyColumn {
        items(chapterData.paragraphs){
            Text(text = it)
        }
    }
}