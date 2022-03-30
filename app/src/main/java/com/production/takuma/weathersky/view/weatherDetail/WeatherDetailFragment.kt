package com.production.takuma.weathersky.view.weatherDetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.production.takuma.weathersky.R
import com.production.takuma.weathersky.data.Weather
import com.production.takuma.weathersky.data.WeatherImage
import com.production.takuma.weathersky.databinding.FragmentWeatherDetailBinding
import com.production.takuma.weathersky.utils.SingleClick
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class WeatherDetailFragment : Fragment(), SingleClick {

    private val viewModel: WeatherDetailViewModel by viewModels()

    /** ViewBinding用のbindingを定義する */
    private var _binding: FragmentWeatherDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // ローディングGIFのセット
        Glide.with(this).load(R.drawable.loading).into(binding.loadingImage)

        /** viewModel.weatherのオブザーバーを定義 */
        val weatherObserver = Observer<Weather> { weather ->
            binding.apply {
                // 天気詳細画像をセット
                weatherImage.setImageResource(WeatherImage.getDetailImageFromTargetArea(weather.targetArea))
                // ブックマーク状態をセット
                if (viewModel.getBookmarkState()) {
                    bookmarkButton.setImageResource(R.drawable.bookmark_active_image)
                } else {
                    bookmarkButton.setImageResource(R.drawable.bookmark_default_image)
                }
                // 各テキストをセット
                targetAreaText.text = weather.targetArea
                descriptionText.text = weather.text
                publishingOfficeText.text = weather.publishingOffice
                reportDatetimeText.text = weather.reportDatetime
                // ノートをセット
                if (viewModel.getNote().isNotEmpty()) noteText.setText(viewModel.getNote())

                // ローディングを非表示
                loadingLayout.visibility = View.GONE
            }
        }

        // オブザーバーをセット
        viewModel.weather.observe(viewLifecycleOwner, weatherObserver)

        // TopFragmentから受け取った値がnullでなければ、天気詳細を取得する
        arguments?.getString("target_area")?.let { viewModel.getWeather(it) }

        // ブックマークボタンを押すと活性化 or 非活性化
        binding.bookmarkButton.setOnClickListener {
            when (viewModel.getBookmarkState()) {
                true -> {
                    viewModel.setBookmarkState(false)
                    binding.bookmarkButton.setImageResource(R.drawable.bookmark_default_image)
                }
                else -> {
                    viewModel.setBookmarkState(true)
                    binding.bookmarkButton.setImageResource(R.drawable.bookmark_active_image)
                }
            }
            // ブックマーク状態をDBに保存
            viewModel.saveBookmarkState()
        }

        // "Date"に現在年月日を挿入
        binding.dateText.text = getCurrentDate()

        // 保存ボタンを押下
        binding.saveButton.setOnSingleClickListener {
            viewModel.setNote(binding.noteText.text.toString())
            viewModel.saveInfo()
            findNavController().navigate(WeatherDetailFragmentDirections.actionWeatherDetailFragmentToTopFragment())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /**
     * 現在年月日取得
     *
     * @return "yyyy.MM.dd"形式での年月日
     */
    private fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault())
        val date = Date(System.currentTimeMillis())
        return dateFormat.format(date)
    }
}