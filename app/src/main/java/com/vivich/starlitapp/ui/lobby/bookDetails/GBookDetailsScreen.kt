package com.vivich.starlitapp.ui.lobby.bookDetails

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardColors
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Scaffold

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.vivich.starlitapp.R
import com.vivich.starlitapp.models.Gutenberg.GBook
import com.vivich.starlitapp.ui.theme.contentGraySmall


@Composable
fun GBookScreen(
    gBook: GBook,
    navHostController: NavHostController = rememberNavController(),
    loadBookAction: () -> Unit = {}
){
    val scrollState = rememberScrollState()

    Scaffold(
        modifier = Modifier,
        topBar = {
            GBookTopNav(onReturnClicked = {
                navHostController.popBackStack()
            })
        },
        bottomBar = {
            GBookDetailsBottomBar(
                bookId = gBook.id,
                navHostController = navHostController,
                buttonOnClick = loadBookAction
            )
        },
        content = {
            Box(
                modifier = Modifier
                    .background(Color.Black)
                    .verticalScroll(scrollState)
                    .padding(it)
                    .fillMaxSize()
                    .paint(
                        painter = rememberAsyncImagePainter(model = gBook.formats.imageUrl),
                        contentScale = ContentScale.FillHeight,
                        colorFilter = ColorFilter.tint(
                            Color.hsl(0f, 1F, 0f, 0.85f),
                            BlendMode.SrcAtop
                        )
                    ),
                contentAlignment = Alignment.TopStart
            ){
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    BookCoverImage(
                        painter = rememberAsyncImagePainter(model = gBook.formats.imageUrl)
                    )
                    BookDescription(gBook)
                }
            }

        }
    )
}


@Composable
fun BookDescription(
    gBook: GBook
){
    Column(
        modifier = Modifier
            .padding(0.dp, 20.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(text = gBook.title, fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
            Text(text = gBook.getAuthorsNames(), color = Color.White)
        }
        BookOverview(
            bookID = gBook.id,
            language = gBook.getLanguages(),
            downloads = gBook.download_count,
            copyright =if (gBook.copyright) "Yes" else "No"
        )
        BookDetails(gBook)
    }
}

@Composable
fun BookDetails(
    gBook: GBook
){
    Row(
        Modifier
            .fillMaxWidth(0.8f)
    ){
        val modifier = Modifier.weight(1f)
        val textAlign = TextAlign.Center

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.clickable {
            }
        ){
            Text(text = "Details", textAlign = textAlign, color = Color.White)
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.clickable {
            }
        ){
            Text(text = "Author",  textAlign = textAlign, color = Color.LightGray)
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.clickable {
            }
        ){
            Text(text = "Review",  textAlign = textAlign, color = Color.LightGray)
        }
    }
    ElevatedCard(
        onClick = { /*TODO*/ },
        modifier = Modifier.fillMaxSize(),
        colors = CardColors(
            Color.White,
            Color.DarkGray,
            Color.Unspecified,
            Color.Unspecified
        )
    ){
        Text(
            modifier = Modifier.padding(20.dp),
            text = "This \"${gBook.title}\" distribution belongs to Project Gutenberg (https://www.gutenberg.org/).\n" +
                    "You can find more info on the website using the book ID."
        )
    }
}

@Composable
fun BookOverview(
    bookID: Int = -1,
    language: String = "None",
    downloads: Int = -1,
    copyright: String = "None"
) {
    Row(
        Modifier
            .padding(10.dp, 20.dp)
            .fillMaxWidth()
            .border(BorderStroke(2.dp, color = Color.LightGray), CircleShape)
    ){
        Column(
            modifier = Modifier
                .weight(1.0f)
                .padding(0.dp, 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(text = "Book ID", color = Color.White, style = contentGraySmall)
            Text(text = bookID.toString(), color = Color.White)
        }
        Column(
            modifier = Modifier
                .weight(1.0f)
                .padding(0.dp, 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(text = "Language", color = Color.White, style = contentGraySmall)
            Text(text = language, color = Color.White)
        }
        Column(
            modifier = Modifier
                .weight(1.0f)
                .padding(0.dp, 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(text = "Downloads", color = Color.White, style = contentGraySmall)
            Text(text = downloads.toString(), color = Color.White)
        }
        Column(
            modifier = Modifier
                .weight(1.0f)
                .padding(0.dp, 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(text = "Copyright", color = Color.White, style = contentGraySmall)
            Text(text = copyright, color = Color.White)
        }
    }
}

@Composable
fun BookCoverImage(
    painter: Painter = painterResource(id = R.drawable.placeholder_book_img)
){
    ElevatedCard(
        modifier = Modifier
            .width(240.dp)
            .height(360.dp)
            .padding(10.dp, 30.dp, 0.dp, 10.dp),
        onClick = { /*TODO*/ }
    ){
        Image(
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillWidth,
            painter = painter,
            contentDescription = "",
        )
    }
}

@Preview
@Composable
private fun GBookPreview() {
    GBookScreen(GBook())
}