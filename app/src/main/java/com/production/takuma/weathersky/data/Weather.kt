package com.production.takuma.weathersky.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather_table")
data class Weather(
    // 発表者
    @ColumnInfo(name = "publishing_office") val publishingOffice: String,
    // 報告日時
    @ColumnInfo(name = "report_datetime") val reportDatetime: String,
    // 対象地域
    @ColumnInfo(name = "target_area") val targetArea: String,
    // ヘッドライン
    @ColumnInfo(name = "headline_text") val headlineText: String,
    // 詳細
    val text: String,

    // ブックマーク
    @ColumnInfo(name = "is_bookmark") var isBookmark: Boolean? = null,
    // ノート
    val note: String? = null
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "db_id") var dbId: Int = 0
}
