package com.moudy.alshafie.Api
import retrofit2.http.GET
import retrofit2.http.Query

data class PixabayImage(
    val id: Int,
    val webformatURL: String,
    val tags: String
)

data class PixabayApiResponse(
    val hits: List<PixabayImage>
)

interface PixabayApiService {

    @GET("api/")

    suspend fun searchImagesrec(
        @Query("key") apiKey: String,
        @Query("q") query: String,

        @Query("image_type") imageType: String = "photo",
        @Query("per_page") perPage: Int = 3,
        @Query("page") page: Int = 1,


    ): PixabayApiResponse
}

