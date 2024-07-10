package com.vivich.starlitapp.ui.lobby.gutendex

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.vivich.starlitapp.R
import com.vivich.starlitapp.globalhandler.UpdateBrightness
import com.vivich.starlitapp.globalhandler.setBrightness
import com.vivich.starlitapp.ui.theme.contentGrayMedium

@Composable
fun GBookContentBottomBar(
    modifier: Modifier = Modifier,
    fontSize: MutableIntState = remember { mutableIntStateOf(16) }
) {
    var selectedItem by remember { mutableIntStateOf(-1) }
    var brightness = remember { mutableFloatStateOf(.5f)}

    Column {
        Spacer(modifier = Modifier.weight(1f))
        if (selectedItem != -1) {
            when (selectedItem){
                0 -> {
                    Brightness()
                }
                1 -> {
                    FontSettings(fontSize=fontSize)
                }
                2 -> {Text(text = "Settings for Settings", fontSize = 18.sp)}
            }
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
                icon = { Text(text = "Aa", style = contentGrayMedium, fontSize = 25.sp) }
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
private fun Brightness(
){
    var sliderValue by remember { mutableFloatStateOf(.5f) }
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "Brightness", fontSize = 18.sp)
        Slider(
            modifier = Modifier.fillMaxWidth(.7f),
            value = sliderValue,
            onValueChange = {
                sliderValue = it
                setBrightness(context, sliderValue)
            },
            valueRange = 0f..1f,
        )
    }
}

@Composable
fun FontSettings(
    modifier: Modifier = Modifier,
    fontSize: MutableIntState
) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "Font settings", fontSize = fontSize.intValue.sp)
        Slider(
            modifier = Modifier.fillMaxWidth(.7f),
            value =fontSize.intValue.toFloat(),
            onValueChange = {fontSize.intValue = it.toInt()},
            valueRange = 14f..36f,
        )
    }
}

@Preview
@Composable
fun BottomBarPreview(modifier: Modifier = Modifier) {
    GBookContentBottomBar()
}