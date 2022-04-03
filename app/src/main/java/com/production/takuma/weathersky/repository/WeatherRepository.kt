package com.production.takuma.weathersky.repository

import androidx.lifecycle.MutableLiveData
import com.production.takuma.weathersky.api.WeatherApiService
import com.production.takuma.weathersky.data.Weather
import com.production.takuma.weathersky.db.WeatherDao
import retrofit2.Response
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherApiService: WeatherApiService,
    private val weatherDao: WeatherDao
) {
    /**
     * 通信エラーについて、LiveDataのBooleanで管理
     * true = エラー発生, false = 通信成功
     */
    val connectionError = MutableLiveData(false)

    /**
     * 天気一覧の取得を行う
     * 通信成功：取得した天気一覧をデータベースに登録
     * 通信失敗：通信失敗ダイアログを表示
     */
    suspend fun accessWeathers() {
        weatherDao.clear()
        // 北海道
        runCatching { weatherApiService.getWeatherHokkaido() }
            .onSuccess { response ->
                response.body()?.let { weather ->
                    weatherDao.insert(weather)
                } ?: setConnectionError()
            }
            .onFailure { setConnectionError() }
        // 青森県
        runCatching { weatherApiService.getWeatherAomori() }
            .onSuccess { response ->
                response.body()?.let { weather ->
                    weatherDao.insert(weather)
                } ?: setConnectionError()
            }
            .onFailure { setConnectionError() }
        // 岩手県
        runCatching { weatherApiService.getWeatherIwate() }
            .onSuccess { response ->
                response.body()?.let { weather ->
                    weatherDao.insert(weather)
                } ?: setConnectionError()
            }
            .onFailure { setConnectionError() }
        // 宮城県
        runCatching { weatherApiService.getWeatherMiyagi() }
            .onSuccess { response ->
                response.body()?.let { weather ->
                    weatherDao.insert(weather)
                } ?: setConnectionError()
            }
            .onFailure { setConnectionError() }
        // 秋田県
        runCatching { weatherApiService.getWeatherAkita() }
            .onSuccess { response ->
                response.body()?.let { weather ->
                    weatherDao.insert(weather)
                } ?: setConnectionError()
            }
            .onFailure { setConnectionError() }
        // 山形県
        runCatching { weatherApiService.getWeatherYamagata() }
            .onSuccess { response ->
                response.body()?.let { weather ->
                    weatherDao.insert(weather)
                } ?: setConnectionError()
            }
            .onFailure { setConnectionError() }
        // 福島県
        runCatching { weatherApiService.getWeatherFukushima() }
            .onSuccess { response ->
                response.body()?.let { weather ->
                    weatherDao.insert(weather)
                } ?: setConnectionError()
            }
            .onFailure { setConnectionError() }
        // 茨城県
        runCatching { weatherApiService.getWeatherIbaraki() }
            .onSuccess { response ->
                response.body()?.let { weather ->
                    weatherDao.insert(weather)
                } ?: setConnectionError()
            }
            .onFailure { setConnectionError() }
        // 栃木県
        runCatching { weatherApiService.getWeatherTochigi() }
            .onSuccess { response ->
                response.body()?.let { weather ->
                    weatherDao.insert(weather)
                } ?: setConnectionError()
            }
            .onFailure { setConnectionError() }
        // 群馬県
        runCatching { weatherApiService.getWeatherGunma() }
            .onSuccess { response ->
                response.body()?.let { weather ->
                    weatherDao.insert(weather)
                } ?: setConnectionError()
            }
            .onFailure { setConnectionError() }
        // 埼玉県
        runCatching { weatherApiService.getWeatherSaitama() }
            .onSuccess { response ->
                response.body()?.let { weather ->
                    weatherDao.insert(weather)
                } ?: setConnectionError()
            }
            .onFailure { setConnectionError() }
        // 千葉県
        runCatching { weatherApiService.getWeatherChiba() }
            .onSuccess { response ->
                response.body()?.let { weather ->
                    weatherDao.insert(weather)
                } ?: setConnectionError()
            }
            .onFailure { setConnectionError() }
        // 東京都
        runCatching { weatherApiService.getWeatherTokyo() }
            .onSuccess { response ->
                response.body()?.let { weather ->
                    weatherDao.insert(weather)
                } ?: setConnectionError()
            }
            .onFailure { setConnectionError() }
        // 神奈川県
        runCatching { weatherApiService.getWeatherKanagawa() }
            .onSuccess { response ->
                response.body()?.let { weather ->
                    weatherDao.insert(weather)
                } ?: setConnectionError()
            }
            .onFailure { setConnectionError() }
        // 新潟県
        runCatching { weatherApiService.getWeatherNiigata() }
            .onSuccess { response ->
                response.body()?.let { weather ->
                    weatherDao.insert(weather)
                } ?: setConnectionError()
            }
            .onFailure { setConnectionError() }
        // 富山県
        runCatching { weatherApiService.getWeatherToyama() }
            .onSuccess { response ->
                response.body()?.let { weather ->
                    weatherDao.insert(weather)
                } ?: setConnectionError()
            }
            .onFailure { setConnectionError() }
        // 石川県
        runCatching { weatherApiService.getWeatherIshikawa() }
            .onSuccess { response ->
                response.body()?.let { weather ->
                    weatherDao.insert(weather)
                } ?: setConnectionError()
            }
            .onFailure { setConnectionError() }
        // 福井県
        runCatching { weatherApiService.getWeatherFukui() }
            .onSuccess { response ->
                response.body()?.let { weather ->
                    weatherDao.insert(weather)
                } ?: setConnectionError()
            }
            .onFailure { setConnectionError() }
        // 山梨県
        runCatching { weatherApiService.getWeatherYamanashi() }
            .onSuccess { response ->
                response.body()?.let { weather ->
                    weatherDao.insert(weather)
                } ?: setConnectionError()
            }
            .onFailure { setConnectionError() }
        // 長野県
        runCatching { weatherApiService.getWeatherNagano() }
            .onSuccess { response ->
                response.body()?.let { weather ->
                    weatherDao.insert(weather)
                } ?: setConnectionError()
            }
            .onFailure { setConnectionError() }
        // 岐阜県
        runCatching { weatherApiService.getWeatherGifu() }
            .onSuccess { response ->
                response.body()?.let { weather ->
                    weatherDao.insert(weather)
                } ?: setConnectionError()
            }
            .onFailure { setConnectionError() }
        // 静岡県
        runCatching { weatherApiService.getWeatherShizuoka() }
            .onSuccess { response ->
                response.body()?.let { weather ->
                    weatherDao.insert(weather)
                } ?: setConnectionError()
            }
            .onFailure { setConnectionError() }
        // 愛知県
        runCatching { weatherApiService.getWeatherAichi() }
            .onSuccess { response ->
                response.body()?.let { weather ->
                    weatherDao.insert(weather)
                } ?: setConnectionError()
            }
            .onFailure { setConnectionError() }
        // 三重県
        runCatching { weatherApiService.getWeatherMie() }
            .onSuccess { response ->
                response.body()?.let { weather ->
                    weatherDao.insert(weather)
                } ?: setConnectionError()
            }
            .onFailure { setConnectionError() }
        // 滋賀県
        runCatching { weatherApiService.getWeatherShiga() }
            .onSuccess { response ->
                response.body()?.let { weather ->
                    weatherDao.insert(weather)
                } ?: setConnectionError()
            }
            .onFailure { setConnectionError() }
        // 京都府
        runCatching { weatherApiService.getWeatherKyoto() }
            .onSuccess { response ->
                response.body()?.let { weather ->
                    weatherDao.insert(weather)
                } ?: setConnectionError()
            }
            .onFailure { setConnectionError() }
        // 大阪府
        runCatching { weatherApiService.getWeatherOsaka() }
            .onSuccess { response ->
                response.body()?.let { weather ->
                    weatherDao.insert(weather)
                } ?: setConnectionError()
            }
            .onFailure { setConnectionError() }
        // 兵庫県
        runCatching { weatherApiService.getWeatherHyogo() }
            .onSuccess { response ->
                response.body()?.let { weather ->
                    weatherDao.insert(weather)
                } ?: setConnectionError()
            }
            .onFailure { setConnectionError() }
        // 奈良県
        runCatching { weatherApiService.getWeatherNara() }
            .onSuccess { response ->
                response.body()?.let { weather ->
                    weatherDao.insert(weather)
                } ?: setConnectionError()
            }
            .onFailure { setConnectionError() }
        // 和歌山県
        runCatching { weatherApiService.getWeatherWakayama() }
            .onSuccess { response ->
                response.body()?.let { weather ->
                    weatherDao.insert(weather)
                } ?: setConnectionError()
            }
            .onFailure { setConnectionError() }
        // 鳥取県
        runCatching { weatherApiService.getWeatherTottori() }
            .onSuccess { response ->
                response.body()?.let { weather ->
                    weatherDao.insert(weather)
                } ?: setConnectionError()
            }
            .onFailure { setConnectionError() }
        // 島根県
        runCatching { weatherApiService.getWeatherShimane() }
            .onSuccess { response ->
                response.body()?.let { weather ->
                    weatherDao.insert(weather)
                } ?: setConnectionError()
            }
            .onFailure { setConnectionError() }
        // 岡山県
        runCatching { weatherApiService.getWeatherOkayama() }
            .onSuccess { response ->
                response.body()?.let { weather ->
                    weatherDao.insert(weather)
                } ?: setConnectionError()
            }
            .onFailure { setConnectionError() }
        // 広島県
        runCatching { weatherApiService.getWeatherHiroshima() }
            .onSuccess { response ->
                response.body()?.let { weather ->
                    weatherDao.insert(weather)
                } ?: setConnectionError()
            }
            .onFailure { setConnectionError() }
        // 山口県
        runCatching { weatherApiService.getWeatherYamaguchi() }
            .onSuccess { response ->
                response.body()?.let { weather ->
                    weatherDao.insert(weather)
                } ?: setConnectionError()
            }
            .onFailure { setConnectionError() }
        // 徳島県
        runCatching { weatherApiService.getWeatherTokushima() }
            .onSuccess { response ->
                response.body()?.let { weather ->
                    weatherDao.insert(weather)
                } ?: setConnectionError()
            }
            .onFailure { setConnectionError() }
        // 香川県
        runCatching { weatherApiService.getWeatherKagawa() }
            .onSuccess { response ->
                response.body()?.let { weather ->
                    weatherDao.insert(weather)
                } ?: setConnectionError()
            }
            .onFailure { setConnectionError() }
        // 愛媛県
        runCatching { weatherApiService.getWeatherEhime() }
            .onSuccess { response ->
                response.body()?.let { weather ->
                    weatherDao.insert(weather)
                } ?: setConnectionError()
            }
            .onFailure { setConnectionError() }
        // 高知県
        runCatching { weatherApiService.getWeatherKochi() }
            .onSuccess { response ->
                response.body()?.let { weather ->
                    weatherDao.insert(weather)
                } ?: setConnectionError()
            }
            .onFailure { setConnectionError() }
        // 福岡県
        runCatching { weatherApiService.getWeatherFukuoka() }
            .onSuccess { response ->
                response.body()?.let { weather ->
                    weatherDao.insert(weather)
                } ?: setConnectionError()
            }
            .onFailure { setConnectionError() }
        // 佐賀県
        runCatching { weatherApiService.getWeatherSaga() }
            .onSuccess { response ->
                response.body()?.let { weather ->
                    weatherDao.insert(weather)
                } ?: setConnectionError()
            }
            .onFailure { setConnectionError() }
        // 長崎県
        runCatching { weatherApiService.getWeatherNagasaki() }
            .onSuccess { response ->
                response.body()?.let { weather ->
                    weatherDao.insert(weather)
                } ?: setConnectionError()
            }
            .onFailure { setConnectionError() }
        // 熊本県
        runCatching { weatherApiService.getWeatherKumamoto() }
            .onSuccess { response ->
                response.body()?.let { weather ->
                    weatherDao.insert(weather)
                } ?: setConnectionError()
            }
            .onFailure { setConnectionError() }
        // 大分県
        runCatching { weatherApiService.getWeatherOita() }
            .onSuccess { response ->
                response.body()?.let { weather ->
                    weatherDao.insert(weather)
                } ?: setConnectionError()
            }
            .onFailure { setConnectionError() }
        // 宮崎県
        runCatching { weatherApiService.getWeatherMiyazaki() }
            .onSuccess { response ->
                response.body()?.let { weather ->
                    weatherDao.insert(weather)
                } ?: setConnectionError()
            }
            .onFailure { setConnectionError() }
        // 鹿児島県
        runCatching { weatherApiService.getWeatherKagoshima() }
            .onSuccess { response ->
                response.body()?.let { weather ->
                    weatherDao.insert(weather)
                } ?: setConnectionError()
            }
            .onFailure { setConnectionError() }
        // 沖縄
        runCatching { weatherApiService.getWeatherOkinawa() }
            .onSuccess { response ->
                response.body()?.let { weather ->
                    weatherDao.insert(weather)
                } ?: setConnectionError()
            }
            .onFailure { setConnectionError() }
    }

    /**
     * 通信エラーが発生した場合に、[connectionError]にtrueをセットする
     */
    private fun setConnectionError() {
        if (connectionError.value == false) {
            connectionError.postValue(true)
        }
    }

    /**
     * データベースから、天気一覧を全て取得
     * @return API通信後にデータベースへ保存した天気一覧
     */
    fun findAllWeathers() = weatherDao.findAll()

    /**
     * データベースから、検索ワードをもとに天気一覧を取得
     *
     * @param searchWord SearchViewに入力した検索ワード
     * @return 検索ワードを元に、データベースから取得した天気一覧
     */
    fun findAllWeathersFromSearch(searchWord: String) = weatherDao.findAllFromSearch(searchWord)

    /**
     * データベースから、対象地域をもとに天気情報を取得
     *
     * @param targetArea 対象地域
     * @return [Weather] 天気情報
     */
    fun findWeatherFromTargetArea(targetArea: String) = weatherDao.findWeatherFromTargetArea(targetArea)

    /**
     * 「ノート」の情報を更新
     *
     * @param targetArea 対象地域
     * @param note ノート
     */
    fun updateNoteInfo(targetArea: String, note: String) =
        weatherDao.updateNoteInfo(targetArea, note)

    /**
     * 「ブックマーク」の情報を更新
     *
     * @param targetArea 対象地域
     * @param isBookmark ブックマーク
     */
    fun updateBookmarkStateFromTargetArea(targetArea: String, isBookmark: Boolean) =
        weatherDao.updateBookmarkStateFromTargetArea(targetArea, isBookmark)

    /**
     * ノートのある天気一覧を取得
     */
    fun getNoteWeatherList() = weatherDao.findNoteExistenceList()

    /**
     * 指定した対象地域のブックマーク状態を、DB上で登録する
     *
     * @param bookmarkList ブックマーク登録対象の対象地域リスト
     */
    fun updateBookmarkStateFromList(bookmarkList: List<String>) =
        weatherDao.updateBookmarkStateFromList(bookmarkList)

    /**
     * 指定した対象地域のブックマーク状態を、DB上で解除する
     *
     * @param nonBookmarkList ブックマーク解除対象の対象地域リスト
     */
    fun updateNonBookmarkStateFromList(nonBookmarkList: List<String>) =
        weatherDao.updateNonBookmarkStateFromList(nonBookmarkList)

    /**
     * ブックマークした対象地域の天気を一覧で取得
     *
     * @return ブックマークした対象地域の天気一覧
     */
    fun getBookmarkWeatherList(): List<Weather> = weatherDao.findBookmarkList()
}