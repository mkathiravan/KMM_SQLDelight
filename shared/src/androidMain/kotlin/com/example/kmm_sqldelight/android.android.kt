package com.example.kmm_sqldelight

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.example.kmm_sqldelight.datasource.DriverFactory
import com.example.kmm_sqldelight.taskApp.ui.App

@Composable
fun MainView() = App(
    DriverFactory(LocalContext.current.applicationContext).createDriver(), true
)