package com.production.takuma.weathersky.view.top

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.production.takuma.weathersky.data.Weather
import com.production.takuma.weathersky.data.WeatherImage
import com.production.takuma.weathersky.databinding.WeatherListItemBinding
import com.production.takuma.weathersky.utils.SingleClick

class TopWeatherListAdapter :
    RecyclerView.Adapter<TopWeatherListAdapter.ViewHolder>(), SingleClick {

    /** 天気一覧 */
    private val weatherList = mutableListOf<Weather>()

    /**
     * 天気一覧をアダプターにセットする
     *
     * @param item 天気一覧
     */
    fun setWeatherList(item: List<Weather>) {
        weatherList.clear()
        weatherList.addAll(item)
        notifyDataSetChanged()
    }

    /** クリックリスナー格納変数 */
    lateinit var listener: OnItemClickListener

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = WeatherListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(weatherList[position])

        // アイテムをクリック
        holder.binding.parentLayout.setOnSingleClickListener {
            listener.onItemClickListener(weatherList[position].targetArea)
        }
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class ViewHolder(val binding: WeatherListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(weather: Weather) {
            binding.apply {
                // 対象地域から該当画像を取得し、レイアウトに挿入
                val image = WeatherImage.getIconImageFromTargetArea(weather.targetArea)
                weatherImage.setImageResource(image)
                // 取得した各テキストをレイアウトに挿入
                publishingOfficeText.text = weather.publishingOffice
                reportDatetimeText.text = weather.reportDatetime

                // 「ブックマーク済」「ノート済」を判断し、Visibility制御
                if (weather.isBookmark == null || weather.isBookmark == false)
                    bookmarkIconImage.visibility = View.GONE
                if (weather.note.isNullOrEmpty()) noteIconImage.visibility = View.GONE
            }
        }
    }

    /**
     * クリックイベントインターフェース
     */
    interface OnItemClickListener {
        fun onItemClickListener(targetArea: String)
    }

    /**
     * クリックリスナーをセット
     */
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}