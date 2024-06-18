package com.vivich.starlitapp.ui.lobby.gutendex

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vivich.starlitapp.ui.shared.BlurredImage
import com.vivich.starlitapp.ui.shared.StandardBookImage

@Composable
fun GBookScreen(
    modifier: Modifier = Modifier
){
    Scaffold(
        topBar = {},
        bottomBar = {}
    ){
        Box(modifier = Modifier.padding(it)){
            BlurredImage(
                modifier= Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
            )
            Column(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxSize()
            ){
                Spacer(modifier = Modifier.fillMaxHeight(.25f))
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black)
                ){

                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 100.dp)
            ){
                StandardBookImage(Modifier
                    .fillMaxWidth(.4f))
                Spacer(modifier = Modifier.width(10.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth(.6f)
                ){
                    Text(
                        style = MaterialTheme.typography.headlineSmall,
                        text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit."
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(text = "Author: Lorem ipsum")
                }
            }
        }
    }
}

@Preview
@Composable
private fun GBookPreview(modifier: Modifier = Modifier) {
    GBookScreen()
}