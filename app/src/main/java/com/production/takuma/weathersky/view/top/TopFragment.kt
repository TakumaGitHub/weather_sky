package com.production.takuma.weathersky.view.top

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.production.takuma.weathersky.R
import com.production.takuma.weathersky.data.Weather
import com.production.takuma.weathersky.databinding.FragmentTopBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class TopFragment : Fragment() {

    private val viewModel: TopViewModel by viewModels()

    /** ViewBinding用のbindingを定義する */
    private var _binding: FragmentTopBinding? = null
    private val binding get() = _binding!!

    // アダプターのインスタンス生成
    private val weatherListAdapter = TopWeatherListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTopBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // ローディングGIFのセット
        Glide.with(this).load(R.drawable.loading).into(binding.loadingImage)

        // RecyclerViewのセット
        binding.recyclerView.apply {
            setHasFixedSize(true)
            adapter = weatherListAdapter
            layoutManager = GridLayoutManager(requireContext(), 4, RecyclerView.VERTICAL, false)
        }
        // RecyclerViewのクリックリスナー設定
        weatherListAdapter.setOnItemClickListener(object : TopWeatherListAdapter.OnItemClickListener {
            override fun onItemClickListener(targetArea: String) {
                val action = TopFragmentDirections.actionTopFragmentToWeatherDetailFragment(targetArea)
                findNavController().navigate(action)
            }
        })

        /** viewModel.connectionErrorのオブザーバーを定義 */
        val connectionErrorObserver = Observer<Boolean> {
            // connectionErrorの値がtrueに変わったときに通信エラーダイアログを表示
            if (it) {
                val action = TopFragmentDirections.actionTopItemToConnectionErrorDialogFragment()
                findNavController().navigate(action)
            }
        }

        /** viewModel.weatherListのオブザーバーを定義 */
        val weatherListObserver = Observer<List<Weather>> {
            // 取得したリストをアダプターにセット
            weatherListAdapter.setWeatherList(it)
            // 取得したリストが空のときは「結果なし」のレイアウトを表示。空でない時は、非表示にする
            if (it.isEmpty()) {
                binding.noResultsLayout.visibility = View.VISIBLE
            } else {
                binding.noResultsLayout.visibility = View.GONE
            }
        }

        /** viewModel.isLoadingのオブザーバーを定義 */
        val isLoadingObserver = Observer<Boolean> {
            // trueのときにローディングを表示し、falseのときには非表示にする
            if (it) {
                binding.loadingLayout.visibility = View.VISIBLE
            } else {
                binding.loadingLayout.visibility = View.GONE
            }
        }

        // オブザーバーをセット
        viewModel.connectionError.observe(viewLifecycleOwner, connectionErrorObserver)
        viewModel.weatherList.observe(viewLifecycleOwner, weatherListObserver)
        viewModel.isLoading.observe(viewLifecycleOwner, isLoadingObserver)

        // 天気一覧を取得
        viewModel.getWeathers()
    }

    override fun onResume() {
        super.onResume()

        // 前回の画面離脱時に通信がキャンセルされている＆viewModelに天気一覧を保持していない場合は再度通信を行う
        if (viewModel.isJobCancelled() && viewModel.weatherList.value.isNullOrEmpty()) {
            viewModel.getWeathers()
        }

        // SearchViewのリスナーをセット
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            // 検索ボタンを押したタイミングで発火
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { viewModel.findAllFromSearch(it) }
                return false
            }

            // SearchViewの入力状態が変化する度に発火
            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null && newText.isEmpty()) {
                    viewModel.findAllFromSearch(newText)
                }
                return false
            }
        })
    }

    override fun onPause() {
        super.onPause()

        // 通信をキャンセル
        viewModel.cancelCoroutineJob()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}