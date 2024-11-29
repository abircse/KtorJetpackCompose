package com.ktorjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import com.ktorjetpackcompose.screens.PostScreen
import com.ktorjetpackcompose.ui.theme.KtorJetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KtorJetpackComposeTheme {
                Scaffold(
                    content = {
                        PostScreen(it)
                    }
                )

            }
        }
    }
}
