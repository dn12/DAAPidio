package net.github.dn12.network.api

import net.github.dn12.network.api.response.YoutubeListResponse
import net.github.dn12.network.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeService {


    //https://www.googleapis.com/youtube/v3/search?key=AIzaSyDE83rdHuCpTnaWbAAQyqMK-Nm_fCyQogk&chart=mostPopular&q=android&type=video&part=snippet
    @GET("/v3/search")
    suspend fun search(
        @Query("key") key: String = Constants.API_KEY,
        @Query("chart") chart: String = Constants.MOSTPOPULAR,
        @Query("type") type: String = Constants.TYPE_VIDEO,
        @Query("part") part: String = Constants.PART_SNIPPET,
        @Query("q") q: String
    ): Response<YoutubeListResponse>


    //https://www.googleapis.com/youtube/v3/videos?key=AIzaSyDE83rdHuCpTnaWbAAQyqMK-Nm_fCyQogk&chart=mostPopular&part=snippet
    @GET("/v3/videos")
    suspend fun popularlist(
        @Query("key") key: String = Constants.API_KEY,
        @Query("chart") chart: String = Constants.MOSTPOPULAR,
        @Query("type") type: String = Constants.TYPE_VIDEO,
        @Query("part") part: String = Constants.PART_SNIPPET,
    ): Response<YoutubeListResponse>
}
