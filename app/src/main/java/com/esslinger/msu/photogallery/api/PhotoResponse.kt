package com.esslinger.msu.photogallery.api

import com.esslinger.msu.photogallery.api.GalleryItem
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PhotoResponse(
    @Json(name = "photos") val galleryItems: List<NasaResponse>
)