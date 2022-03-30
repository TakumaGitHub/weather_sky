package com.production.takuma.weathersky.view.bookmark

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.production.takuma.weathersky.data.Weather
import com.production.takuma.weathersky.databinding.FragmentBookmarkBinding
import com.production.takuma.weathersky.view.note.BookmarkAndNoteAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookmarkFragment : Fragment() {

    private val viewModel: BookmarkViewModel by viewModels()

    /** ViewBinding用のbindingを定義する */
    private var _binding: FragmentBookmarkBinding? = null
    private val binding get() = _binding!!

    private var bookmarkListAdapter = BookmarkAndNoteAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // RecyclerViewをセット
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = bookmarkListAdapter
        }
        // RecyclerViewのクリックリスナー設定
        bookmarkListAdapter.setOnItemClickListener(object :
            BookmarkAndNoteAdapter.OnItemClickListener {
            override fun onItemClickListener(targetArea: String) {
                val action = BookmarkFragmentDirections.actionBookmarkFragmentToWeatherDetailFragment(targetArea)
                findNavController().navigate(action)
            }
        })

        /** viewModel.bookmarkWeatherListのオブザーバーを定義 */
        val bookmarkListObserver = Observer<List<Weather>> {
            // 取得したリストをアダプターにセット
            bookmarkListAdapter.setWeatherList(it)
            // 取得したリストが空のときは「結果なし」のレイアウトを表示。空でない時は、非表示にする
            if (it.isEmpty()) {
                binding.noResultsLayout.visibility = View.VISIBLE
            } else {
                binding.noResultsLayout.visibility = View.GONE
            }
        }

        // オブザーバーをセット
        viewModel.bookmarkWeatherList.observe(viewLifecycleOwner, bookmarkListObserver)

        // ブックマーク天気一覧をDBから取得
        viewModel.getBookmarkWeatherList()
    }

    override fun onStop() {
        super.onStop()

        // 画面離脱時にブックマーク状態を更新
        viewModel.updateBookmarkStateFromList(
            bookmarkListAdapter.getBookmarkUpdateList(),
            bookmarkListAdapter.getNonBookmarkUpdateList()
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}