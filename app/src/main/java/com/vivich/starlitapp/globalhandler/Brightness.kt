package com.vivich.starlitapp.globalhandler

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.WindowManager
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext


@Composable
fun UpdateBrightness(brightness:Float) {
    val context = LocalContext.current
    DisposableEffect(Unit) {
        setBrightness(context, brightness)
        onDispose {
            setBrightness(context, brightness)
        }
    }
}

fun setBrightness(context: Context, brightness:Float) {
    val activity = context as? Activity ?: return
    val layoutParams: WindowManager.LayoutParams = activity.window.attributes
    layoutParams.screenBrightness = brightness
    activity.window.attributes = layoutParams
//    Log.d("ddd", activity.window.attributes.screenBrightness.toString())
}

fun getBrightness(context: Context) {
    val activity = context as? Activity ?: return
    val layoutParams: WindowManager.LayoutParams = activity.window.attributes
}