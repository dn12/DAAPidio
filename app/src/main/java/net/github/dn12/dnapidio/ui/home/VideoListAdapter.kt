

package net.github.dn12.dnapidio.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.Glide
import net.github.dn12.dnapidio.databinding.ItemVideoBinding
import net.github.dn12.dnapidio.model.VideoItemData

class VideoListAdapter(val ctx: Context,val dataSet: MutableList<VideoItemData>,val lst:VideoListListener) : RecyclerView.Adapter<VideoListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    private lateinit var binding: ItemVideoBinding

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        binding = ItemVideoBinding.inflate(inflater, viewGroup, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.setIsRecyclable(false)
        val vid = dataSet[position]
        binding.tvTitle.text =vid.Title
        Glide.with(ctx).load(vid.imageUrl).into(binding.ivThumb)
        binding.root.setOnClickListener {
            lst.onVideoItemClicked(vid)
        }
    }

    override fun getItemCount() = dataSet.size

    interface VideoListListener{
        fun onVideoItemClicked(vid:VideoItemData)
    }
}
