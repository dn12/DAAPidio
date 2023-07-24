package net.github.dn12.dnapidio.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import net.github.dn12.dnapidio.databinding.FragmentSearchBinding
import net.github.dn12.dnapidio.model.VideoItemData
import net.github.dn12.dnapidio.ui.home.HomeViewModel
import net.github.dn12.dnapidio.ui.home.VideoListAdapter
import org.koin.android.ext.android.inject

class SearchFragment : Fragment() {

    private val viewModel: HomeViewModel by inject()

    private lateinit var binding: FragmentSearchBinding

    var lsSearchResult = ArrayList<VideoItemData>()
    lateinit var videoAdapter: VideoListAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater)

        with(binding){
            ivSearch.setOnClickListener {
                if(etSearch.text.toString().isNotEmpty()){
                    viewModel.search(etSearch.text.toString())
                }
            }

        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        setupAdapter()

        viewModel.searchResultLiveData.observeForever {
            lsSearchResult.addAll(it.map { VideoItemData.parseFromYoutubeData(it) })
            videoAdapter.notifyDataSetChanged()
        }
        viewModel.loadingStateLiveData.observeForever {
            binding.progressBar.isVisible = it
        }
    }

    fun setupAdapter() {

        with(binding) {

            videoAdapter = VideoListAdapter(
                requireContext(),
                lsSearchResult,
                object : VideoListAdapter.VideoListListener {
                    override fun onVideoItemClicked(vid: VideoItemData) {
                        navigateToDetail(vid)
                    }

                })
            rvVideo.adapter = videoAdapter
            rvVideo.layoutManager = LinearLayoutManager(requireContext())

        }
    }


    fun navigateToDetail(vid : VideoItemData){
        findNavController().navigate(
            SearchFragmentDirections.navigateToDetail(
                vid.videoId,
                vid.Title,
                vid.Desc
            )
        )
    }

}