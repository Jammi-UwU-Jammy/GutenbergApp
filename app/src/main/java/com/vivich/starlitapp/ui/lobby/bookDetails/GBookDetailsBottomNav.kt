package com.vivich.starlitapp.ui.lobby.bookDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.vivich.starlitapp.BookScreens
import com.vivich.starlitapp.BookScreens.Details.withId

@Composable
fun GBookDetailsBottomBar(
    bookId: Int,
    navHostController: NavHostController = rememberNavController(),
    buttonOnClick: () -> Unit = {}
) {
    NavigationBar(
        modifier = Modifier
            .fillMaxWidth(),
        containerColor = Color.Transparent
    ){
        NavigationBarItem(selected = false,
            modifier = Modifier
                .padding(20.dp, 10.dp)
                .background(Color.Black),
            onClick = {
                buttonOnClick()
//                navHostController.navigate(BookScreens.Content.withId("$bookId"))
            },
            icon = { Text(text = "Start Reading", color = Color.White) }
        )
    }
}