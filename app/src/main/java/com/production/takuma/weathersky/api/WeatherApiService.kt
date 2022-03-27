package com.production.takuma.weathersky.api

import com.production.takuma.weathersky.data.Weather
import retrofit2.Response
import retrofit2.http.GET

interface WeatherApiService {
    /**
     * 天気情報取得
     *
     * @return 天気情報
     */

    // 北海道
    @GET("016000.json")
    suspend fun getWeatherHokkaido(): Response<Weather>
    // 東京都
    @GET("130000.json")
    suspend fun getWeatherTokyo(): Response<Weather>
    // 京都府
    @GET("260000.json")
    suspend fun getWeatherKyoto(): Response<Weather>
    // 大阪府
    @GET("270000.json")
    suspend fun getWeatherOsaka(): Response<Weather>
    // 福岡県
    @GET("400000.json")
    suspend fun getWeatherFukuoka(): Response<Weather>
}