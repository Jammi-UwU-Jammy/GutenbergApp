package com.vivich.starlitapp.ui.lobby.gutendex

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
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.vivich.starlitapp.R


@Composable
fun GBookScreen(
    navHostController: NavHostController = rememberNavController()
){
    val scrollState = rememberScrollState()

    Scaffold(
        modifier = Modifier,
        topBar = {
            GBookTopNav(onReturnClicked = {
                navHostController.popBackStack()
            })
        },
        bottomBar = { GBookDetailsBottomBar() },
        content = {
            Box(
                modifier = Modifier
                    .background(Color.Black)
                    .verticalScroll(scrollState)
                    .padding(it)
                    .fillMaxSize()
                    .paint(
                        painter = painterResource(id = R.drawable.placeholder_book_img),
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
                    BookCoverImage()
                    BookDescription()
                }
            }

        }
    )
}


@Composable
fun BookDescription() {
    Column(
        modifier = Modifier
            .padding(0.dp, 20.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(text = "Book Name", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.White)
            Text(text = "Author: Name", color = Color.White)
        }
        BookOverview()
        BookDetails()
    }
}

@Composable
fun BookDetails() {
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
            text = stringResource(id = R.string.Book_text_placeholder)
        )
    }
}

@Composable
fun BookOverview() {
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
            Text(text = "Book ID", color = Color.White)
            Text(text = "123", color = Color.White)
        }
        Column(
            modifier = Modifier
                .weight(1.0f)
                .padding(0.dp, 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(text = "Language", color = Color.White)
            Text(text = "En", color = Color.White)
        }
        Column(
            modifier = Modifier
                .weight(1.0f)
                .padding(0.dp, 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(text = "Download", color = Color.White)
            Text(text = "2312", color = Color.White)
        }
    }
}

@Composable
fun BookCoverImage(
    painter: Painter = painterResource(id = R.drawable.placeholder_book_img)
){
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth(.6f)
            .padding(10.dp, 30.dp, 0.dp, 10.dp),
        onClick = { /*TODO*/ }
    ){
        Image(
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
            painter = painter,
            contentDescription = "",
        )
    }
}

@Preview
@Composable
private fun GBookPreview() {
    GBookScreen()
}