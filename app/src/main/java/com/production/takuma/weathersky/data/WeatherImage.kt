package com.production.takuma.weathersky.data

import com.production.takuma.weathersky.R

enum class WeatherImage(val targetArea: String, val iconImage: Int, val detailImage: Int) {
    // 北海道
    HOKKAIDO(
        "石狩・空知・後志地方",
        R.drawable.hokkaido_image,
        R.drawable.hokkaido_detail_image
    ),
    // 青森県
    AOMORI(
        "青森県",
        R.drawable.aomori_image,
        R.drawable.aomori_detail_image
    ),
    // 岩手県
    IWATE(
        "岩手県",
        R.drawable.iwate_image,
        R.drawable.iwate_detail_image
    ),
    // 宮城県
    MIYAGI(
        "宮城県",
        R.drawable.miyagi_image,
        R.drawable.miyagi_detail_image
    ),
    // 秋田県
    AKITA(
        "秋田県",
        R.drawable.akita_image,
        R.drawable.akita_detail_image
    ),
    // 山形県
    YAMAGATA(
        "山形県",
        R.drawable.yamagata_image,
        R.drawable.yamagata_detail_image
    ),
    // 福島県
    FUKUSHIMA(
        "福島県",
        R.drawable.fukushima_image,
        R.drawable.fukushima_detail_image
    ),
    // 茨城県
    IBARAKI(
        "茨城県",
        R.drawable.ibaraki_image,
        R.drawable.ibaraki_detail_image
    ),
    // 栃木県
    TOCHIGI(
        "栃木県",
        R.drawable.tochigi_image,
        R.drawable.tochigi_detail_image
    ),
    // 群馬県
    GUNMA(
        "群馬県",
        R.drawable.gunma_image,
        R.drawable.gunma_detail_image
    ),
    // 埼玉県
    SAITAMA(
        "埼玉県",
        R.drawable.saitama_image,
        R.drawable.saitama_detail_image
    ),
    // 千葉県
    CHIBA(
        "千葉県",
        R.drawable.chiba_image,
        R.drawable.chiba_detail_image
    ),
    // 東京都
    TOKYO(
        "東京都",
        R.drawable.tokyo_image,
        R.drawable.tokyo_detail_image
    ),
    // 神奈川県
    KANAGAWA(
        "神奈川県",
        R.drawable.kanagawa_image,
        R.drawable.kanagawa_detail_image
    ),
    // 新潟県
    NIIGATA(
        "新潟県",
        R.drawable.niigata_image,
        R.drawable.niigata_detail_image
    ),
    // 富山県
    TOYAMA(
        "富山県",
        R.drawable.toyama_image,
        R.drawable.toyama_detail_image
    ),
    // 石川県
    ISHIKAWA(
        "石川県",
        R.drawable.ishikawa_image,
        R.drawable.ishikawa_detail_image
    ),
    // 福井県
    FUKUI(
        "福井県",
        R.drawable.fukui_image,
        R.drawable.fukui_detail_image
    ),
    // 山梨県
    YAMANASHI(
        "山梨県",
        R.drawable.yamanashi_image,
        R.drawable.yamanashi_detail_image
    ),
    // 長野県
    NAGANO(
        "長野県",
        R.drawable.nagano_image,
        R.drawable.nagano_detail_image
    ),
    // 岐阜県
    GIFU(
        "岐阜県",
        R.drawable.gifu_image,
        R.drawable.gifu_detail_image
    ),
    // 静岡県
    SHIZUOKA(
        "静岡県",
        R.drawable.shizuoka_image,
        R.drawable.shizuoka_detail_image
    ),
    // 愛知県
    AICHI(
        "愛知県",
        R.drawable.aichi_image,
        R.drawable.aichi_detail_image
    ),
    // 三重県
    MIE(
        "三重県",
        R.drawable.mie_image,
        R.drawable.mie_detail_image
    ),
    // 滋賀県
    SHIGA(
        "滋賀県",
        R.drawable.shiga_image,
        R.drawable.shiga_detail_image
    ),
    // 京都府
    KYOTO(
        "京都府",
        R.drawable.kyoto_image,
        R.drawable.kyoto_detail_image
    ),
    // 大阪府
    OSAKA(
        "大阪府",
        R.drawable.osaka_image,
        R.drawable.osaka_detail_image
    ),
    // 兵庫県
    HYOGO(
        "兵庫県",
        R.drawable.hyogo_image,
        R.drawable.hyogo_detail_image
    ),
    // 奈良県
    NARA(
        "奈良県",
        R.drawable.nara_image,
        R.drawable.nara_detail_image
    ),
    // 和歌山県
    WAKAYAMA(
        "和歌山県",
        R.drawable.wakayama_image,
        R.drawable.wakayama_detail_image
    ),
    // 鳥取県
    TOTTORI(
        "鳥取県",
        R.drawable.tottori_image,
        R.drawable.tottori_detail_image
    ),
    // 島根県
    SHIMANE(
        "島根県",
        R.drawable.shimane_image,
        R.drawable.shimane_detail_image
    ),
    // 岡山県
    OKAYAMA(
        "岡山県",
        R.drawable.okayama_image,
        R.drawable.okayama_detail_image
    ),
    // 広島県
    HIROSHIMA(
        "広島県",
        R.drawable.hiroshima_image,
        R.drawable.hiroshima_detail_image
    ),
    // 山口県
    YAMAGUCHI(
        "山口県",
        R.drawable.yamaguchi_image,
        R.drawable.yamaguchi_detail_image
    ),
    // 徳島県
    TOKUSHIMA(
        "徳島県",
        R.drawable.tokushima_image,
        R.drawable.tokushima_detail_image
    ),
    // 香川県
    KAGAWA(
        "香川県",
        R.drawable.kagawa_image,
        R.drawable.kagawa_detail_image
    ),
    // 愛媛県
    EHIME(
        "愛媛県",
        R.drawable.ehime_image,
        R.drawable.ehime_detail_image
    ),
    // 高知県
    KOCHI(
        "高知県",
        R.drawable.kochi_image,
        R.drawable.kochi_detail_image
    ),
    // 福岡県
    FUKUOKA(
        "福岡県",
        R.drawable.fukuoka_image,
        R.drawable.fukuoka_detail_image
    ),
    // 佐賀県
    SAGA(
        "佐賀県",
        R.drawable.saga_image,
        R.drawable.saga_detail_image
    ),
    // 長崎県
    NAGASAKI(
        "長崎県",
        R.drawable.nagasaki_image,
        R.drawable.nagasaki_detail_image
    ),
    // 熊本県
    KUMAMOTO(
        "熊本県",
        R.drawable.kumamoto_image,
        R.drawable.kumamoto_detail_image
    ),
    // 大分県
    OITA(
        "大分県",
        R.drawable.oita_image,
        R.drawable.oita_detail_image
    ),
    // 宮崎県
    MIYAZAKI(
        "宮崎県",
        R.drawable.miyazaki_image,
        R.drawable.miyazaki_detail_image
    ),
    // 鹿児島県
    KAGOSHIMA(
        "鹿児島県",
        R.drawable.kagoshima_image,
        R.drawable.kagoshima_detail_image
    ),
    // 沖縄
    OKINAWA(
        "沖縄本島地方",
        R.drawable.okinawa_image,
        R.drawable.okinawa_detail_image
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