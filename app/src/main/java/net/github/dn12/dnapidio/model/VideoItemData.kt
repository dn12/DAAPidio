package net.github.dn12.dnapidio.model

import net.github.dn12.network.api.response.YoutubeListResponse
import net.github.dn12.network.api.response.YoutubeSearchResponse

data class VideoItemData(val imageUrl: String, val videoId:String,val Title: String,val Desc: String){

    companion object {
        fun parseFromYoutubeData(yt : YoutubeListResponse.Item):VideoItemData{
            return VideoItemData(yt.snippet?.thumbnails?.default?.url?:"",yt.id?:"",yt.snippet?.title?:"", yt.snippet?.description?:"")
        }
        fun parseFromYoutubeData(yt : YoutubeSearchResponse.Item):VideoItemData{
            return VideoItemData(yt.snippet?.thumbnails?.default?.url?:"",yt.id?.videoId?:"",yt.snippet?.title?:"", yt.snippet?.description?:"")
        }
    }
}

