package com.production.takuma.weathersky

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// Hiltのコード生成をトリガーするアノテーション
@HiltAndroidApp
class WeatherSkyApplication : Application()