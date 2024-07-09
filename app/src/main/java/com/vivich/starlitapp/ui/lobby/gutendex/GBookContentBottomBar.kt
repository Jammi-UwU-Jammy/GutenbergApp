package com.vivich.starlitapp.ui.lobby.gutendex

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vivich.starlitapp.R
import com.vivich.starlitapp.ui.theme.contentGrayMedium

@Composable
fun GBookContentBottomBar(modifier: Modifier = Modifier) {
    var selectedItem by remember { mutableIntStateOf(-1) }

    Column {
        Spacer(modifier = Modifier.weight(1f))
        if (selectedItem != -1) {
            ExpandableTab(selectedItem)
        }

        BottomAppBar {
            NavigationBarItem(
                selected = false,
                onClick = { selectedItem = if (selectedItem == 0) -1 else 0 },
                icon = { Icon(painter = painterResource(id = R.drawable.sun_line), contentDescription = "") }
            )
            NavigationBarItem(
                selected = false,
                onClick = { selectedItem = if (selectedItem == 1) -1 else 1 },
                icon = { Text(text = "A", style = contentGrayMedium, fontSize = 25.sp) }
            )
            NavigationBarItem(
                selected = false,
                onClick = { selectedItem = if (selectedItem == 2) -1 else 2 },
                icon = { Icon(painter = painterResource(id = R.drawable.construction_24dp_fill0_wght400_grad0_opsz24), contentDescription = "") }
            )


        }
    }
}

@Composable
private fun ExpandableTab(selectedId: Int) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
    ){
        when (selectedId){
            0 -> {Text(text = "Settings for Light", fontSize = 18.sp)}
            1 -> {Text(text = "Settings for Font", fontSize = 18.sp)}
            2 -> {Text(text = "Settings for Settings", fontSize = 18.sp)}
        }
    }
}

@Composable
private fun DarkLightMode(modifier: Modifier = Modifier) {

}

@Preview
@Composable
fun BottomBarPreview(modifier: Modifier = Modifier) {
    GBookContentBottomBar()
}