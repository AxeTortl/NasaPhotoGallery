package com.esslinger.msu.photogallery

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.esslinger.msu.photogallery.api.GalleryItem
import com.esslinger.msu.photogallery.api.NasaApi
import com.esslinger.msu.photogallery.api.NasaResponse
import retrofit2.HttpException
import java.io.IOException

private const val INITIAL_PAGE = 1
private const val PAGE_SIZE = 100

class PhotoPagingSource(private val nasaApi: NasaApi, private val apiKey: String) :
    PagingSource<Int, GalleryItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GalleryItem> {
        try {
            val nextPage = params.key ?: INITIAL_PAGE
            val response = nasaApi.getAstronomyPictureOfTheDay(apiKey, PAGE_SIZE)
            val photos = response.mapNotNull { it.toGalleryItem() }

            return LoadResult.Page(
                data = photos,
                prevKey = if (nextPage == INITIAL_PAGE) null else nextPage - 1,
                nextKey = if (photos.isEmpty()) null else nextPage + 1
            )
        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, GalleryItem>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    private fun NasaResponse.toGalleryItem(): GalleryItem? {
        return if (!url.isNullOrBlank() && !title.isNullOrBlank()) {
            GalleryItem(title = title, id = "", url = url)
        } else {
            null
        }
    }
}