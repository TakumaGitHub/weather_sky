package com.production.takuma.weathersky.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.production.takuma.weathersky.data.Weather

@Dao
interface WeatherDao {
    @Insert
    suspend fun insert(weather: Weather)

    @Query("DELETE FROM weather_table")
    suspend fun clear()

    @Transaction
    suspend fun clearAndInsert(weather: Weather) {
        clear()
        insert(weather)
    }

    /** 天気一覧を全て取得 */
    @Query("SELECT * FROM weather_table")
    fun findAll(): List<Weather>

    /** 検索ワードから天気一覧を取得 */
    @Query("SELECT * FROM weather_table WHERE publishing_office LIKE '%' || :search || '%' OR target_area LIKE '%' || :search || '%' ")
    fun findAllFromSearch(search: String): List<Weather>

    /** 対象地域から天気情報を取得 */
    @Query("SELECT * FROM weather_table WHERE target_area == :targetArea")
    fun findWeatherFromTargetArea(targetArea: String): Weather

    /** 「メモ」の情報を更新 */
    @Query("UPDATE weather_table SET note = :note WHERE target_area = :targetArea")
    fun updateNoteInfo(targetArea: String, note: String)

    /** 「ブックマーク」の情報を更新 */
    @Query("UPDATE weather_table SET is_bookmark = :isBookmark WHERE target_area = :targetArea")
    fun updateBookmarkStateFromTargetArea(targetArea: String, isBookmark: Boolean)

    /** ノートのある天気リストを取得 */
    @Query("SELECT * FROM weather_table WHERE note IS NOT NULL AND note != ''")
    fun findNoteExistenceList(): List<Weather>

    /** 引数で渡された対象地域のブックマーク状態を登録 */
    @Query("UPDATE weather_table SET is_bookmark = 1 WHERE target_area in (:isBookmarkList)")
    fun updateBookmarkStateFromList(isBookmarkList: List<String>)

    /** 引数で渡された対象地域のブックマーク状態を解除 */
    @Query("UPDATE weather_table SET is_bookmark = 0 WHERE target_area in (:nonBookmarkList)")
    fun updateNonBookmarkStateFromList(nonBookmarkList: List<String>)

    /** ブックマークリストを取得 */
    @Query("SELECT * FROM weather_table WHERE is_bookmark == 1")
    fun findBookmarkList(): List<Weather>
}