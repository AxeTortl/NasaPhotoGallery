package com.esslinger.msu.photogallery.api

import com.esslinger.msu.photogallery.api.FlickrResponse
import retrofit2.http.GET

private const val API_KEY = "3b72d7c88921240c7d1b6e9c68e947b2"

interface FlickrApi {
    @GET(
        "services/rest/?method=flickr.interestingness.getList" +
            "&api_key=$API_KEY" +
            "&format=json" +
            "&nojsoncallback=1" +
            "&extras=url_s"
    )
    suspend fun fetchPhotos(): FlickrResponse
}
