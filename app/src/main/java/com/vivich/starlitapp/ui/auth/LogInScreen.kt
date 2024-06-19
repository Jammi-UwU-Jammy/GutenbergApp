package com.vivich.starlitapp.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vivich.starlitapp.R
import com.vivich.starlitapp.ui.theme.Black
import com.vivich.starlitapp.ui.theme.BlueGray
import com.vivich.starlitapp.ui.theme.LightBlueWhite

@Composable
fun LoginScreen(
    onFacebookLogInClick: () -> Unit  = {},
    onGoogleLogInClick: () -> Unit  = {},
    onGuestLogInClick: () -> Unit = {}
) {
    Surface{
        Column(
            modifier = Modifier.fillMaxSize()
        ){
            TopSection()
            Spacer(modifier = Modifier.height(36.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 30.dp)
            ){
                Text(text = "Sign in with social media")
                Spacer(modifier = Modifier.height(36.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                ){
                    SignInMedia(
                        onClick = onGoogleLogInClick,
                        rIcon = R.drawable.google,
                        label = "Google",
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    SignInMedia(
                        onClick = onFacebookLogInClick,
                        rIcon = R.drawable.facebook,
                        label = "Facebook",
                        modifier = Modifier.weight(1f)
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                SignInMedia(
                    onClick = onGuestLogInClick,
                    rIcon = R.drawable.ic_launcher_foreground,
                    label = "Guest",
                    modifier = Modifier.fillMaxWidth(.4f)
                )
            }
        }
    }
}

@Composable
private fun TopSection() {
    val uiColor: Color = if (isSystemInDarkTheme()) Color.White else Black
    Box(
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f),
            contentScale = ContentScale.FillBounds,
            painter = painterResource(id = R.drawable.shape),
            contentDescription = ""
        )
        Row(
            modifier = Modifier.padding(top = 80.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.flower_fill),
                contentDescription = "",
                tint = uiColor
            )

            Spacer(modifier = Modifier.width(15.dp))
            Column {
                Text(
                    text = "Starlit",
                    style = MaterialTheme.typography.headlineMedium,
                    color = uiColor
                )
                Text(
                    text = "Find your books",
                    style = MaterialTheme.typography.titleMedium,
                    color = uiColor
                )
            }
        }
        Text(
            modifier = Modifier
                .padding(10.dp)
                .align(alignment = Alignment.BottomCenter),
            text = "Welcome",
            style = MaterialTheme.typography.headlineLarge,
            color = uiColor
        )
    }
}


@Composable
fun SignInMedia(
    modifier: Modifier = Modifier,
    rIcon: Int,
    label: String = "",
    onClick: () -> Unit = {}
){
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .height(40.dp)
            .socialMediaLogIn()
            .clickable { onClick() }
    ){
        Image(
            modifier = Modifier.size(16.dp),
            painter = painterResource(rIcon),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium
        )
    }
}

@Composable
fun Modifier.socialMediaLogIn() : Modifier {
    return if (isSystemInDarkTheme()){
        this
            .background(Color.Transparent)
            .border(
                width = 1.dp,
                color = BlueGray,
                shape = RoundedCornerShape(4.dp)
            )
    }else{
        this.background(LightBlueWhite)
    }
}


@Preview
@Composable
fun LogInScreenPreview() {
    LoginScreen()
}