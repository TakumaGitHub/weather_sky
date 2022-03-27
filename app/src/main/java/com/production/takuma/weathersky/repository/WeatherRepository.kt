package com.production.takuma.weathersky.repository

import androidx.lifecycle.MutableLiveData
import com.production.takuma.weathersky.api.WeatherApiService
import com.production.takuma.weathersky.data.Weather
import com.production.takuma.weathersky.db.WeatherDao
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
        // 東京都
        runCatching { weatherApiService.getWeatherTokyo() }
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
        // 福岡県
        runCatching { weatherApiService.getWeatherFukuoka() }
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
        connectionError.postValue(true)
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
     * データベースから、天気ロケーションIDをもとに天気情報を取得
     *
     * @param id 天気ロケーションID
     * @return [Weather] 天気情報
     */
    fun findWeatherFromId(id: String) = weatherDao.findWeatherFromId(id)

    /**
     * 「メモ」の情報を更新
     *
     * @param id 天気ロケーションID
     * @param note ノート
     */
    fun updateNoteInfo(id: String, note: String) =
        weatherDao.updateNoteInfo(id, note)

    /**
     * 「ブックマーク」の情報を更新
     *
     * @param id 天気ロケーションID
     * @param isBookmark ブックマーク
     */
    fun updateBookmarkStateFromId(id: String, isBookmark: Boolean) =
        weatherDao.updateBookmarkStateFromId(id, isBookmark)

    /**
     * ノートのある天気一覧を取得
     */
    fun getNoteWeatherList() = weatherDao.findNoteExistenceList()

    /**
     * 指定した天気ロケーションIDのブックマーク状態を、DB上で登録する
     *
     * @param bookmarkList ブックマーク登録対象の天気ロケーションIDリスト
     */
    fun updateBookmarkStateFromList(bookmarkList: List<String>) =
        weatherDao.updateBookmarkStateFromList(bookmarkList)

    /**
     * 指定した天気ロケーションIDのブックマーク状態を、DB上で解除する
     *
     * @param nonBookmarkList ブックマーク解除対象の天気ロケーションIDリスト
     */
    fun updateNonBookmarkStateFromList(nonBookmarkList: List<String>) =
        weatherDao.updateNonBookmarkStateFromList(nonBookmarkList)

    /**
     * ブックマークした都市の天気を一覧で取得
     *
     * @return ブックマークした都市の天気一覧
     */
    fun getBookmarkWeatherList(): List<Weather> = weatherDao.findBookmarkList()
}