package com.vivich.starlitapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.vivich.starlitapp.featureTesting.MainViewModel
import com.vivich.starlitapp.ui.theme.StarlitAppTheme
import com.vivich.starlitapp.ui.lobby.gutendex.GutendexScreen


class MainActivity : ComponentActivity() {

//    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            StarlitAppTheme {
                val navController = rememberNavController()
                RootGraph(context = this, navController=navController)
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    StarlitAppTheme {

    }
}