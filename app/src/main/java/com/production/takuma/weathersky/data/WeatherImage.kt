package com.production.takuma.weathersky.data

import com.production.takuma.weathersky.R

enum class WeatherImage(val targetArea: String, val iconImage: Int, val detailImage: Int) {
    // 北海道
    HOKKAIDO(
        "石狩・空知・後志地方",
        R.drawable.japan_image,
        R.drawable.japan_tokyo_image
    ),
    // 東京都
    TOKYO(
        "東京都",
        R.drawable.japan_image,
        R.drawable.japan_tokyo_image
    ),
    // 京都府
    KYOTO(
        "京都府",
        R.drawable.japan_image,
        R.drawable.japan_tokyo_image
    ),
    // 大阪府
    OSAKA(
        "大阪府",
        R.drawable.japan_image,
        R.drawable.japan_tokyo_image
    ),
    // 福岡県
    FUKUOKA(
        "福岡県",
        R.drawable.japan_image,
        R.drawable.japan_tokyo_image
    );

    companion object {
        /**
         * 天気一覧に表示する画像を対象地域から取得
         *
         * @param targetArea 対象地域
         * @return 天気一覧に表示する画像リソースID
         */
        fun getIconImageFromTargetArea(targetArea: String): Int {
            return values().first { it.targetArea == targetArea }.iconImage
        }

        /**
         * 詳細画面に表示する画像を対象地域から取得
         *
         * @param targetArea 対象地域
         * @return 詳細画面に表示する画像リソースID
         */
        fun getDetailImageFromTargetArea(targetArea: String): Int {
            return values().first { it.targetArea == targetArea }.detailImage
        }
    }
}