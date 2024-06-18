package com.vivich.starlitapp.ui.shared

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vivich.starlitapp.R

@Composable
fun StandardBookImage(
    modifier: Modifier = Modifier,
    painter: Painter = painterResource(id = R.drawable.placeholder_book_img)
){
    Image(
        modifier = Modifier
            .clip(RoundedCornerShape(5.dp))
            .size(120.dp, 200.dp)
        ,
        contentScale = ContentScale.Crop,
        painter = painter,
        contentDescription = ""
    )
}

@Composable
fun SmallBookImage(
    modifier: Modifier = Modifier,
    painter: Painter = painterResource(id = R.drawable.placeholder_book_img)
){
    Image(
        modifier = Modifier
            .clip(RoundedCornerShape(5.dp))
            .size(80.dp, 120.dp)
        ,
        contentScale = ContentScale.Crop,
        painter = painter,
        contentDescription = ""
    )
}

@Composable
fun BlurredImage(
    painter: Painter = painterResource(id = R.drawable.placeholder_book_img),
    modifier: Modifier = Modifier
){
    Image(
        colorFilter = ColorFilter.tint(Color.hsl(.7f, 1F, 1f, 0.5f), BlendMode.SrcAtop),
        contentScale = ContentScale.Crop,
        modifier = modifier.blur(radius= 20.dp),
        painter = painter,
        contentDescription = "blurred image"
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