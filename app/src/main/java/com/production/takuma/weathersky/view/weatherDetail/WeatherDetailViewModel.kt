package com.production.takuma.weathersky.view.weatherDetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.production.takuma.weathersky.data.Weather
import com.production.takuma.weathersky.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class WeatherDetailViewModel @Inject constructor(
    private val repository: WeatherRepository
): ViewModel() {

    /** 天気詳細 */
    val weather = MutableLiveData<Weather>()

    /** ブックマーク状態 */
    private var isBookmark = false

    /** メモ */
    private var note = ""

    /**
     * 対象地域からDBに保存してある天気情報を取得
     *
     * @param targetArea 対象地域
     */
    fun getWeather(targetArea: String) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                repository.findWeatherFromTargetArea(targetArea)
            }
            // ブックマーク状態を設定
            result.isBookmark?.let { setBookmarkState(it) }
            // メモを設定
            result.note?.let { setNote(it) }
            // フィールド変数の天気詳細に、取得した情報をセット
            weather.value = result
        }
    }

    /**
     * 入力された情報をDBに保存
     */
    fun saveInfo() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                // 対象地域がnullでなければ、DBの天気情報を更新
                weather.value?.targetArea?.let { targetArea ->
                    repository.updateNoteInfo(targetArea, getNote())
                }
            }
        }
    }

    /**
     * ブックマーク状態をDBに保存
     */
    fun saveBookmarkState() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                // 対象地域がnullでなければ、該当天気情報のお気に入りを更新
                weather.value?.targetArea?.let { targetArea ->
                    repository.updateBookmarkStateFromTargetArea(targetArea, getBookmarkState())
                }
            }
        }
    }

    /**
     * ブックマークの状態を取得
     *
     * @return true：ブックマーク状態, false：ブックマーク解除状態
     */
    fun getBookmarkState(): Boolean {
        return isBookmark
    }

    /**
     * ブックマークの状態を変更
     *
     * @param boolean true：ブックマーク状態に変更, false：ブックマーク解除状態に変更
     */
    fun setBookmarkState(boolean: Boolean) {
        isBookmark = boolean
    }

    /**
     * ノートを取得
     *
     * @return ノート
     */
    fun getNote(): String {
        return note
    }

    /**
     * ノートを変更
     *
     * @param text 入力されたノート
     */
    fun setNote(text: String) {
        note = text
    }
}