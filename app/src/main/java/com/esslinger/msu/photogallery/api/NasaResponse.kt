package com.esslinger.msu.photogallery.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NasaResponse(
    @Json(name = "hdurl") val url: String?,
    val title: String?
    // Add other necessary fields from NASA APOD API response
)