package com.vivich.starlitapp.models.ParsedData

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element


fun extractHrefTable(html: String): MutableList<hrefElement> {
    val document: Document = Jsoup.parse(html)

    val hrefList = mutableListOf<hrefElement>()
    document.select("a.pginternal")?.forEach { element ->
        hrefList.add(
            hrefElement(title = element.text(), hrefLink = element.attr("href"))
        )
    }
    return hrefList
}

fun extractASection(
    html: String, title: String = "",
    sectionId: String
): ChapterData {
    val document: Document = Jsoup.parse(html)
    val paragraphs = mutableListOf<String>()
    document.getElementById(sectionId)?.siblingElements()?.forEach{ element->
        paragraphs.add(element.text())
    }
    return ChapterData(
        title = title,
        paragraphs = paragraphs
    )
}


@Composable
fun HrefTable(
    modifier: Modifier = Modifier,
    hrefLinks: BookHrefLinks,
    html: String
){
    Column {
        (hrefLinks.hrefList).forEach{ href ->
            Button(
                shape = RectangleShape,
                border = BorderStroke(0.dp, color = Color.Transparent),
                colors = ButtonColors(
                    containerColor = Color.hsl(222f, .4f, .7f, .2f),
                    contentColor = Color.Black,
                    disabledContentColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent
                ),
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                }
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