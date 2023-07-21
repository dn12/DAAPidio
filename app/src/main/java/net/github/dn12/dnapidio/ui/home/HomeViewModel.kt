package net.github.dn12.dnapidio.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import net.github.dn12.network.api.response.YoutubeListResponse
import net.github.dn12.network.repositories.YoutubeRepository

class HomeViewModel(
    private val youtubeRepository: YoutubeRepository,
) : ViewModel() {

    val searchResultLiveData: MutableLiveData<List<YoutubeListResponse.Item>> = MutableLiveData()
    val populartLiveData: MutableLiveData<List<YoutubeListResponse.Item>> = MutableLiveData()
    val loadingStateLiveData : MutableLiveData<Boolean> = MutableLiveData(false)

    fun search(q : String){
            loadingStateLiveData.postValue(true)
            viewModelScope.launch {
                val result = youtubeRepository.search(q)
                result.data?.let {
                    val result =
                        it.body()?.items?.filterNotNull()?: listOf()
                    searchResultLiveData.postValue(result)

                }
                loadingStateLiveData.postValue(false)
            }

    }
    fun popularList(){
        loadingStateLiveData.postValue(true)
        viewModelScope.launch {
            val result = youtubeRepository.popularlist()
            result.data?.let {
                val result =
                    it.body()?.items?.filterNotNull()?: listOf()
                populartLiveData.postValue(result)
            }
            loadingStateLiveData.postValue(false)
        }

    }

}
