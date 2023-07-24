package net.github.dn12.dnapidio.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.request.RequestOptions
import com.glide.slider.library.SliderLayout
import com.glide.slider.library.slidertypes.TextSliderView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import net.github.dn12.dnapidio.databinding.FragmentHomeBinding
import net.github.dn12.dnapidio.databinding.FragmentVideoDetailBinding
import net.github.dn12.dnapidio.model.VideoItemData
import net.github.dn12.dnapidio.ui.home.HomeViewModel
import net.github.dn12.dnapidio.ui.home.VideoListAdapter
import org.koin.android.ext.android.inject

class VideoDetailFragment : Fragment() {

    private val viewModel: HomeViewModel by inject()

    private lateinit var binding: FragmentVideoDetailBinding

    var lsSlider = ArrayList<VideoItemData>()
    var lsPopular = ArrayList<VideoItemData>()
    lateinit var videoAdapter : VideoListAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVideoDetailBinding.inflate(inflater)
        with(binding) {
            arguments?.let {

                val args= VideoDetailFragmentArgs.fromBundle(it)
                val videoId = args.videoid
                tvTitle.text=args.title
                tvDesc.text=args.desc
                yt.enableAutomaticInitialization = false
                requireActivity().lifecycle.addObserver(yt);

                yt.initialize(object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        super.onReady(youTubePlayer)
                        youTubePlayer.cueVideo(videoId, 0f)
                    }
                }, true)
            }
        }
        return binding.root
    }





}