package com.esslinger.msu.photogallery

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.esslinger.msu.photogallery.api.GalleryItem
import com.esslinger.msu.photogallery.api.NasaApi
import kotlinx.coroutines.flow.Flow
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

private const val PAGE_SIZE = 100

class PhotoRepository(private val apiKey: String) {
    private val nasaApi: NasaApi

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.nasa.gov/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        nasaApi = retrofit.create()
    }

    fun fetchPhotosPaging(): Flow<PagingData<GalleryItem>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = { PhotoPagingSource(nasaApi, apiKey) }
        ).flow
    }
}