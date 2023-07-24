package net.github.dn12.dnapidio.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.request.RequestOptions
import com.glide.slider.library.SliderLayout
import com.glide.slider.library.slidertypes.TextSliderView
import net.github.dn12.dnapidio.databinding.FragmentHomeBinding
import net.github.dn12.dnapidio.model.VideoItemData
import org.koin.android.ext.android.inject


private const val ANDROID_R_REQUIRED_EXTENSION_VERSION = 2

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by inject()

    private lateinit var binding: FragmentHomeBinding

    var lsSlider = ArrayList<VideoItemData>()
    var lsPopular = ArrayList<VideoItemData>()
    lateinit var videoAdapter: VideoListAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater)

        binding.ivSearch.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.navigateToSearch())
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.getPopularList()

        setupAdapter()

        viewModel.popularLiveData.observeForever {
            var index = 0
            for (v in it) {
                if (index < 3)
                    lsSlider.add(VideoItemData.parseFromYoutubeData(v))
                else
                    lsPopular.add(VideoItemData.parseFromYoutubeData(v))

                index++
            }

            renderData()
        }
        viewModel.loadingStateLiveData.observeForever {
            binding.progressBar.isVisible = it
        }
    }

    fun setupAdapter() {

        with(binding) {
            slider.setPresetTransformer(SliderLayout.Transformer.Default)
            slider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom)
            slider.setDuration(4000)

            videoAdapter = VideoListAdapter(requireContext(), lsPopular,object : VideoListAdapter.VideoListListener{
                override fun onVideoItemClicked(vid: VideoItemData) {
                    navigateToDetail(vid)
                }

            })
            rvVideo.adapter = videoAdapter
            rvVideo.layoutManager = LinearLayoutManager(requireContext())

        }
    }

    fun renderData() {
        val act = requireActivity()
        val requestOptions = RequestOptions().centerCrop()
        binding.slider.removeAllSliders()
        for (i in 0 until lsSlider.size) {
            val sliderView = TextSliderView(act)
            sliderView
                .image(lsSlider.get(i).imageUrl)
                .description(lsSlider.get(i).Title)
                .setRequestOption(requestOptions)
                .setProgressBarVisible(true)
                .setOnSliderClickListener {
                    navigateToDetail(lsSlider.get(i))
                }

            binding.slider.addSlider(sliderView)

        }



        videoAdapter.notifyDataSetChanged()


    }

    fun navigateToDetail(vid : VideoItemData){
        findNavController().navigate(
            HomeFragmentDirections.navigateToDetail(
                vid.videoId,
                vid.Title,
                vid.Desc
            )
        )
    }

}
