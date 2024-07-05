package com.vivich.starlitapp.ui.lobby.gutendex

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.vivich.starlitapp.R
import com.vivich.starlitapp.ui.theme.contentGrayMedium

@Composable
fun BookContentBottomBar(modifier: Modifier = Modifier) {
    BottomAppBar {
        NavigationBarItem(
            selected = false,
            onClick = { /*TODO*/ },
            icon = { Icon(painter = painterResource(id = R.drawable.sun_line), contentDescription = "") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { /*TODO*/ },
            icon = { Text(text = "A", style = contentGrayMedium, fontSize = 25.sp) }
        )
        NavigationBarItem(
            selected = false,
            onClick = { /*TODO*/ },
            icon = { Icon(painter = painterResource(id = R.drawable.construction_24dp_fill0_wght400_grad0_opsz24), contentDescription = "") }
        )
    }
}

@Composable
fun CustomiseTab(modifier: Modifier = Modifier) {

}

@Preview
@Composable
fun BottomBarPreview(modifier: Modifier = Modifier) {
    BookContentBottomBar()
}