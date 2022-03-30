package com.production.takuma.weathersky.view.bookmark

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
class BookmarkViewModel @Inject constructor(
    private val repository: WeatherRepository
): ViewModel() {

    /** ブックマーク天気一覧 */
    val bookmarkWeatherList = MutableLiveData<List<Weather>>()

    /**
     * ブックマーク天気一覧をDBから取得
     */
    fun getBookmarkWeatherList() {
        viewModelScope.launch {
            bookmarkWeatherList.value = withContext(Dispatchers.IO) {
                repository.getBookmarkWeatherList()
            }
        }
    }

    /**
     * ブックマーク状態を更新
     */
    fun updateBookmarkStateFromList(bookmarkList: List<String>, nonBookmarkList: List<String>) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.updateBookmarkStateFromList(bookmarkList)
                repository.updateNonBookmarkStateFromList(nonBookmarkList)
            }
        }
    }
}