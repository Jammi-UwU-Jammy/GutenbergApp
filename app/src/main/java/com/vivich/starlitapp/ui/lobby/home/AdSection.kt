package com.vivich.starlitapp.ui.lobby.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vivich.starlitapp.R

@Composable
fun AdSlideSection(
    modifier: Modifier = Modifier
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
            .fillMaxHeight(.3f)
    ){
        ElevatedCard(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .size(300.dp, 200.dp)
        ){
            Column(
                modifier = Modifier.padding(20.dp)
            ){
                Text(text = "Book Name")
                Text(text = "Author: Name")
                Spacer(modifier = Modifier.height(20.dp))
                Row{
                    Text(text = "Time: 30 min")
                    Spacer(modifier = Modifier.width(20.dp))
                    Text(text = "Rating: 3.7")
                }

            }
        }
        Image(
            modifier = Modifier
                .fillMaxHeight(.7f)
                .padding(horizontal = 20.dp)
                .clip(RoundedCornerShape(5.dp))
                .align(Alignment.BottomEnd)
            ,
            painter = painterResource(id = R.drawable.placeholder_book_img),
            contentDescription =""
        )
    }
}


@Preview
@Composable
fun AdSectionPreview() {
    Surface {
        AdSlideSection()
    }
}