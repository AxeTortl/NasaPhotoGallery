package com.esslinger.msu.photogallery.api

import android.nfc.tech.MifareUltralight.PAGE_SIZE
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Response

private const val API_KEY = "VuPthmRCoJx9xFfgnJ9TyIW3E87QgZulPTKdDfsP"

interface NasaApi {
    @GET("planetary/apod")
    suspend fun getAstronomyPictureOfTheDay(
        @Query("api_key") apiKey: String,
        @Query("count") count: Int
    ): List<NasaResponse>
}