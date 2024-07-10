package com.vivich.starlitapp.models.parsers

import it.skrape.core.document
import it.skrape.fetcher.HttpFetcher
import it.skrape.fetcher.response
import it.skrape.fetcher.skrape
import it.skrape.selects.eachHref
import it.skrape.selects.eachText
import it.skrape.selects.html5.a
import it.skrape.selects.html5.p

data class MySimpleDataClass(
    val httpStatusCode: Int,
    val httpStatusMessage: String,
    val paragraph: String,
    val allParagraphs: List<String>,
    val allLinks: List<String>
)

class HtmlExtractionService {

    fun extract() {
        val extracted = skrape(HttpFetcher) {
            request {
                url = "http://localhost:8080"
            }

            response {
                MySimpleDataClass(
                    httpStatusCode = status { code },
                    httpStatusMessage = status { message },
                    allParagraphs = document.p { findAll { eachText } },
                    paragraph = document.p { findFirst { text } },
                    allLinks = document.a { findAll { eachHref } }
                )
            }
        }
        print(extracted)
    }
}