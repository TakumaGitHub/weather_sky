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
    // 青森県
    @GET("020000.json")
    suspend fun getWeatherAomori(): Response<Weather>
    // 岩手県
    @GET("030000.json")
    suspend fun getWeatherIwate(): Response<Weather>
    // 宮城県
    @GET("040000.json")
    suspend fun getWeatherMiyagi(): Response<Weather>
    // 秋田県
    @GET("050000.json")
    suspend fun getWeatherAkita(): Response<Weather>
    // 山形県
    @GET("060000.json")
    suspend fun getWeatherYamagata(): Response<Weather>
    // 福島県
    @GET("070000.json")
    suspend fun getWeatherFukushima(): Response<Weather>
    // 茨城県
    @GET("080000.json")
    suspend fun getWeatherIbaraki(): Response<Weather>
    // 栃木県
    @GET("090000.json")
    suspend fun getWeatherTochigi(): Response<Weather>
    // 群馬県
    @GET("100000.json")
    suspend fun getWeatherGunma(): Response<Weather>
    // 埼玉県
    @GET("110000.json")
    suspend fun getWeatherSaitama(): Response<Weather>
    // 千葉県
    @GET("120000.json")
    suspend fun getWeatherChiba(): Response<Weather>
    // 東京都
    @GET("130000.json")
    suspend fun getWeatherTokyo(): Response<Weather>
    // 神奈川県
    @GET("140000.json")
    suspend fun getWeatherKanagawa(): Response<Weather>
    // 新潟県
    @GET("150000.json")
    suspend fun getWeatherNiigata(): Response<Weather>
    // 富山県
    @GET("160000.json")
    suspend fun getWeatherToyama(): Response<Weather>
    // 石川県
    @GET("170000.json")
    suspend fun getWeatherIshikawa(): Response<Weather>
    // 福井県
    @GET("180000.json")
    suspend fun getWeatherFukui(): Response<Weather>
    // 山梨県
    @GET("190000.json")
    suspend fun getWeatherYamanashi(): Response<Weather>
    // 長野県
    @GET("200000.json")
    suspend fun getWeatherNagano(): Response<Weather>
    // 岐阜県
    @GET("210000.json")
    suspend fun getWeatherGifu(): Response<Weather>
    // 静岡県
    @GET("220000.json")
    suspend fun getWeatherShizuoka(): Response<Weather>
    // 愛知県
    @GET("230000.json")
    suspend fun getWeatherAichi(): Response<Weather>
    // 三重県
    @GET("240000.json")
    suspend fun getWeatherMie(): Response<Weather>
    // 滋賀県
    @GET("250000.json")
    suspend fun getWeatherShiga(): Response<Weather>
    // 京都府
    @GET("260000.json")
    suspend fun getWeatherKyoto(): Response<Weather>
    // 大阪府
    @GET("270000.json")
    suspend fun getWeatherOsaka(): Response<Weather>
    // 兵庫県
    @GET("280000.json")
    suspend fun getWeatherHyogo(): Response<Weather>
    // 奈良県
    @GET("290000.json")
    suspend fun getWeatherNara(): Response<Weather>
    // 和歌山県
    @GET("300000.json")
    suspend fun getWeatherWakayama(): Response<Weather>
    // 鳥取県
    @GET("310000.json")
    suspend fun getWeatherTottori(): Response<Weather>
    // 島根県
    @GET("320000.json")
    suspend fun getWeatherShimane(): Response<Weather>
    // 岡山県
    @GET("330000.json")
    suspend fun getWeatherOkayama(): Response<Weather>
    // 広島県
    @GET("340000.json")
    suspend fun getWeatherHiroshima(): Response<Weather>
    // 山口県
    @GET("350000.json")
    suspend fun getWeatherYamaguchi(): Response<Weather>
    // 徳島県
    @GET("360000.json")
    suspend fun getWeatherTokushima(): Response<Weather>
    // 香川県
    @GET("370000.json")
    suspend fun getWeatherKagawa(): Response<Weather>
    // 愛媛県
    @GET("380000.json")
    suspend fun getWeatherEhime(): Response<Weather>
    // 高知県
    @GET("390000.json")
    suspend fun getWeatherKochi(): Response<Weather>
    // 福岡県
    @GET("400000.json")
    suspend fun getWeatherFukuoka(): Response<Weather>
    // 佐賀県
    @GET("410000.json")
    suspend fun getWeatherSaga(): Response<Weather>
    // 長崎県
    @GET("420000.json")
    suspend fun getWeatherNagasaki(): Response<Weather>
    // 熊本県
    @GET("430000.json")
    suspend fun getWeatherKumamoto(): Response<Weather>
    // 大分県
    @GET("440000.json")
    suspend fun getWeatherOita(): Response<Weather>
    // 宮崎県
    @GET("450000.json")
    suspend fun getWeatherMiyazaki(): Response<Weather>
    // 鹿児島県
    @GET("460100.json")
    suspend fun getWeatherKagoshima(): Response<Weather>
    // 沖縄
    @GET("471000.json")
    suspend fun getWeatherOkinawa(): Response<Weather>
}