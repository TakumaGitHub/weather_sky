package com.production.takuma.weathersky.view.note

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
class NoteViewModel @Inject constructor(
    private val repository: WeatherRepository
): ViewModel() {

    /**
     * ノートあり天気一覧
     */
    val weatherList = MutableLiveData<List<Weather>>()

    /**
     * ノートあり天気一覧を取得
     */
    fun getNoteList() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                weatherList.postValue(repository.getNoteWeatherList())
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