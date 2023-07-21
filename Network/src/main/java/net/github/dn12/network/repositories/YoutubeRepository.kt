package net.github.dn12.network.repositories


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.github.dn12.network.api.YoutubeService
import net.github.dn12.network.api.response.YoutubeListResponse
import net.github.dn12.network.util.MyNetworkCallResult
import net.github.dn12.network.util.MyNetworkRequestResult
import retrofit2.Response

class YoutubeRepository(
    private val onlineServices: YoutubeService
) {


    suspend fun search(q: String): MyNetworkRequestResult<Response<YoutubeListResponse>> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val resp = onlineServices.search(q = q)
                MyNetworkRequestResult.success(resp)
            } catch (e: Exception) {
                e.printStackTrace()
                MyNetworkRequestResult.error(
                    MyNetworkCallResult.ERROR
                )
            }
        }

    suspend fun popularlist(): MyNetworkRequestResult<Response<YoutubeListResponse>> =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val resp = onlineServices.popularlist()
                MyNetworkRequestResult.success(resp)
            } catch (e: Exception) {
                e.printStackTrace()
                MyNetworkRequestResult.error(
                    MyNetworkCallResult.ERROR
                )
            }
        }

}
