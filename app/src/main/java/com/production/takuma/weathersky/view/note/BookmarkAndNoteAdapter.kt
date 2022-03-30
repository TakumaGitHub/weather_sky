package com.production.takuma.weathersky.view.note

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.production.takuma.weathersky.R
import com.production.takuma.weathersky.data.Weather
import com.production.takuma.weathersky.data.WeatherImage
import com.production.takuma.weathersky.databinding.BookmarkNoteItemBinding
import com.production.takuma.weathersky.utils.SingleClick

class BookmarkAndNoteAdapter: RecyclerView.Adapter<BookmarkAndNoteAdapter.ViewHolder>(), SingleClick {

    /** ノートあり天気一覧 */
    private val weatherList = mutableListOf<Weather>()

    /** クリックリスナー格納変数 */
    lateinit var listener: OnItemClickListener

    /**
     * ノートあり天気一覧をアダプターにセットする
     *
     * @param item 天気一覧
     */
    fun setWeatherList(item: List<Weather>) {
        weatherList.clear()
        weatherList.addAll(item)
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: BookmarkNoteItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(weather: Weather) {
            binding.apply {
                weatherImage.setImageResource(WeatherImage.getIconImageFromTargetArea(weather.targetArea))
                targetAreaText.text = weather.targetArea
                publishingOfficeText.text = weather.publishingOffice
                reportDatetimeText.text = weather.reportDatetime
                if (weather.isBookmark != null && weather.isBookmark == true) {
                    bookmarkIconImage.setImageResource(R.drawable.bookmark_note_list_bookmark_active_icon)
                } else {
                    bookmarkIconImage.setImageResource(R.drawable.bookmark_note_list_bookmark_default_icon)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = BookmarkNoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(weatherList[position])

        // カセットをクリック
        holder.binding.parentLayout.setOnSingleClickListener {
            listener.onItemClickListener(weatherList[position].targetArea)
        }

        // アイコン（ブックマーク）をクリック
        holder.binding.bookmarkIconImage.setOnSingleClickListener {
            if (weatherList[position].isBookmark == true) {
                holder.binding.bookmarkIconImage.setImageResource(R.drawable.bookmark_note_list_bookmark_default_icon)
                weatherList[position].isBookmark = false
            } else {
                holder.binding.bookmarkIconImage.setImageResource(R.drawable.bookmark_note_list_bookmark_active_icon)
                weatherList[position].isBookmark = true
            }
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

    /**
     * ブックマーク登録されている対象地域のリストを取得
     *
     * @return ブックマーク登録されている対象地域のリスト
     */
    fun getBookmarkUpdateList(): List<String> {
        return weatherList.filter { it.isBookmark == true }.map { it.targetArea }
    }

    /**
     * ブックマーク解除されている対象地域のリストを取得
     *
     * @return ブックマーク解除されている対象地域のリスト
     */
    fun getNonBookmarkUpdateList(): List<String> {
        return weatherList.filter { it.isBookmark == false }.map { it.targetArea }
    }

    /**
     * クリックイベントインターフェース
     */
    interface OnItemClickListener {
        /**
         * カセットクリックリスナー
         *
         * @param targetArea クリックされた対象地域
         */
        fun onItemClickListener(targetArea: String)
    }

    /**
     * クリックリスナーをセット
     */
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }
}