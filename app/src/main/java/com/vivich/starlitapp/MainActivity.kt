package com.vivich.starlitapp

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController

import com.vivich.starlitapp.globalhandler.getBrightness
import com.vivich.starlitapp.ui.theme.StarlitAppTheme
import com.vivich.starlitapp.ui.lobby.gutendex.GutendexScreen


class MainActivity : ComponentActivity() {

//    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            StarlitAppTheme {
                checkAndRequestWriteSettingsPermission(this)

                Log.d("ddd", getBrightness(this).toString())

                val navController = rememberNavController()
                RootGraph(context = this, navController=navController)
            }
        }
    }
}

private fun checkAndRequestWriteSettingsPermission(activity: Activity) {
    if (!Settings.System.canWrite(activity)) {
        val intent = Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS)
        intent.data = Uri.parse("package:${activity.packageName}")
        activity.startActivityForResult(intent, 200)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    StarlitAppTheme {

    }
}