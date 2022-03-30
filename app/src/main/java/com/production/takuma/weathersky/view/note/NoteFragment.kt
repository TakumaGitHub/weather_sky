package com.production.takuma.weathersky.view.note

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
import com.production.takuma.weathersky.databinding.FragmentNoteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NoteFragment : Fragment() {

    private val viewModel: NoteViewModel by viewModels()

    /** ViewBinding用のbindingを定義する */
    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!

    private val noteListAdapter = BookmarkAndNoteAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // RecyclerViewをセット
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = noteListAdapter
        }
        // RecyclerViewのクリックリスナー設定
        noteListAdapter.setOnItemClickListener(object :
            BookmarkAndNoteAdapter.OnItemClickListener {
            override fun onItemClickListener(targetArea: String) {
                val action = NoteFragmentDirections.actionNoteFragmentToWeatherDetailFragment(targetArea)
                findNavController().navigate(action)
            }
        })

        /** viewModel.noteListのオブザーバーを定義 */
        val weatherListObserver = Observer<List<Weather>> {
            // 取得したリストをアダプターにセット
            noteListAdapter.setWeatherList(it)
            // 取得したリストが空のときは「結果なし」のレイアウトを表示。空でない時は、非表示にする
            if (it.isEmpty()) {
                binding.noResultsLayout.visibility = View.VISIBLE
            } else {
                binding.noResultsLayout.visibility = View.GONE
            }
        }
        // オブザーバーをセット
        viewModel.weatherList.observe(viewLifecycleOwner, weatherListObserver)

        // ノートあり天気一覧をDBから取得
        viewModel.getNoteList()
    }

    override fun onStop() {
        super.onStop()

        // 画面離脱時にブックマーク状態を更新
        viewModel.updateBookmarkStateFromList(
            noteListAdapter.getBookmarkUpdateList(),
            noteListAdapter.getNonBookmarkUpdateList()
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}