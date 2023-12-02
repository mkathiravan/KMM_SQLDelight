package com.example.kmm_sqldelight

import androidx.compose.ui.window.ComposeUIViewController
import com.example.kmm_sqldelight.datasource.DriverFactory
import com.example.kmm_sqldelight.taskApp.ui.App

fun MainViewController() = ComposeUIViewController {
    App(
        DriverFactory().createDriver(),
        true
    )
}