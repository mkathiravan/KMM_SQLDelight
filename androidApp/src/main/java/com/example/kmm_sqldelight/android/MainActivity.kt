package com.example.kmm_sqldelight.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.kmm_sqldelight.MainView
import com.example.kmm_sqldelight.taskApp.ui.TaskAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskAppTheme {
                Surface(modifier = Modifier.background(MaterialTheme.colors.background)) {
                    MainView()
                }
            }
        }
    }
}
