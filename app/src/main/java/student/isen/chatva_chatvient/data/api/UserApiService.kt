package student.isen.chatva_chatvient.data.api

import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import student.isen.chatva_chatvient.data.model.Cat
import student.isen.chatva_chatvient.data.model.LikeRequest

interface UserApiService {



    // Fetch all cats
    @GET("users")
    suspend fun getCats(): List<Cat>

    // Fetch a specific cat by ID
    @GET("users/{id}")
    suspend fun getCatById(@Path("id") id: String): Cat

    @GET("users/{id}/photo")
    suspend fun getCatPictureById(@Path("id") id: String): ResponseBody

    @GET("users/{id}/likes")
    suspend fun getLikes(@Path("id") id: String, @Query("liked") liked: Int): List<Cat>

    // Get the 10 next profiles for the user "id"
    @GET("users/{id}/smashorpass")
    suspend fun getNextProfiles(@Path("id") id: String): List<Cat>

    @POST("users/{userId}/like")
    suspend fun likeCat(
        @Path("userId") userId: String,
        @Body likeRequest: LikeRequest
    )

    @PUT("users/{id}")
    suspend fun updateCat(@Path("id") id: String, @Body cat: Cat)

}