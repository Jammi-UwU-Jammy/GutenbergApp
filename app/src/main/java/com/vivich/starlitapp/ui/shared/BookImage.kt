package com.vivich.starlitapp.ui.shared

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vivich.starlitapp.R

@Composable
fun StandardBookImage(){
    Image(
        modifier = Modifier
            .clip(RoundedCornerShape(5.dp))
            .size(120.dp, 200.dp)
        ,
        contentScale = ContentScale.Crop,
        painter = painterResource(id = R.drawable.placeholder_book_img),
        contentDescription = ""
    )
}

@Composable
fun SmallBookImage() {
    Image(
        modifier = Modifier
            .clip(RoundedCornerShape(5.dp))
            .size(80.dp, 120.dp)
        ,
        contentScale = ContentScale.Crop,
        painter = painterResource(id = R.drawable.placeholder_book_img),
        contentDescription = ""
    )
}

@Preview
@Composable
fun PreviewBookImages() {
    Surface {
        Column {
            SmallBookImage()
            StandardBookImage()
        }
    }
}