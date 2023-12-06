package com.esslinger.msu.photogallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.esslinger.msu.photogallery.api.GalleryItem
import com.esslinger.msu.photogallery.PhotoRepository
import kotlinx.coroutines.flow.Flow

private const val PAGE_SIZE = 100
private const val API_KEY = "VuPthmRCoJx9xFfgnJ9TyIW3E87QgZulPTKdDfsP"

class PhotoGalleryViewModel : ViewModel() {
    private val photoRepository = PhotoRepository(apiKey = API_KEY)

    val photoList: Flow<PagingData<GalleryItem>> =
        photoRepository.fetchPhotosPaging()
            .cachedIn(viewModelScope)
}