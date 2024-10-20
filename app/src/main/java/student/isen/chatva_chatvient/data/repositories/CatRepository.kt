package student.isen.chatva_chatvient.data.repositories

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import student.isen.chatva_chatvient.data.api.UserApiService
import student.isen.chatva_chatvient.data.model.Cat
import student.isen.chatva_chatvient.data.model.LikeRequest

class CatRepository(private val userApiService: UserApiService) {
    suspend fun getCats(): List<Cat> {
        return userApiService.getCats()
    }

    suspend fun getCatById(id: String): Cat {
        return userApiService.getCatById(id)
    }

    suspend fun getCatPictureById(id: String): Bitmap? {
        val responseBody = userApiService.getCatPictureById(id)
        return responseBody.byteStream().use { inputStream ->
            BitmapFactory.decodeStream(inputStream)
        }
    }

    suspend fun getNextProfiles(id: String): List<Cat> {
        return userApiService.getNextProfiles(id)
    }

    suspend fun updateCat(catId: String, cat: Cat) {
        userApiService.updateCat(catId, cat)
    }

    suspend fun getLiked(userId: String, liked: Int): List<Cat> {
        return userApiService.getLikes(userId, liked)
    }

    suspend fun likeCat(userId: String, likeRequest: LikeRequest) {
        userApiService.likeCat(userId, likeRequest)
    }

}