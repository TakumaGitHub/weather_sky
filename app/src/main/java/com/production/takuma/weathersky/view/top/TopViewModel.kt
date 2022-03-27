package com.production.takuma.weathersky.view.top

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.production.takuma.weathersky.data.Weather
import com.production.takuma.weathersky.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TopViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {

    /** 天気一覧 */
    val weatherList = MutableLiveData<List<Weather>>()

    /**
     * 通信エラーについて、LiveDataのBooleanで管理
     * true = エラー発生, false = 通信成功
     */
    val connectionError = repository.connectionError

    /**
     * 天気一覧取得のコルーチン
     */
    private var accessWeatherJob: Job? = null

    /**
     * 通信中の管理（読み込み画像管理）
     */
    val isLoading = MutableLiveData<Boolean>()

    /**
     * 天気一覧をAPI取得
     */
    fun getWeathers() {
        // 通信中に切り替え
        isLoading.value = true
        accessWeatherJob = viewModelScope.launch {
            withContext(Dispatchers.IO) {
                // データベースに天気一覧がない場合のみAPI通信し、一覧取得する
                repository.findAllWeathers().apply {
                    if (isNullOrEmpty()) {
                        repository.accessWeathers()
                        // 通信がキャンセルされている場合はここで処理から抜ける
                        if (accessWeatherJob?.isActive == false) return@withContext
                        weatherList.postValue(repository.findAllWeathers())
                    } else {
                        weatherList.postValue(this)
                    }
                }
            }
            isLoading.value = false
        }
    }

    /**
     * 検索ワードから該当する天気一覧を取得
     *
     * @param searchWord SearchViewに入力した検索ワード
     */
    fun findAllFromSearch(searchWord: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                weatherList.postValue(repository.findAllWeathersFromSearch(searchWord))
            }
        }
    }

    /**
     * 通信をキャンセルする
     */
    fun cancelCoroutineJob() {
        accessWeatherJob?.cancel()
    }

    /**
     * 前回の通信がキャンセルされて終わったかどうかをチェックする
     *
     * @return true：通信がキャンセルされた, false：通信成功 or 通信中 or null
     */
    fun isJobCancelled(): Boolean {
        return accessWeatherJob?.let {
            it.isCompleted && it.isCancelled
        } ?: false
    }
}