package student.isen.chatva_chatvient.data.repositories

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import student.isen.chatva_chatvient.data.api.ApiService
import student.isen.chatva_chatvient.data.model.Cat

class CatRepository(private val apiService: ApiService) {
    suspend fun getCats(): List<Cat> {
        return apiService.getCats()
    }

    suspend fun getCatById(id: String): Cat {
        return apiService.getCatById(id)
    }

    suspend fun getCatPictureById(id: String): Bitmap? {
        val responseBody = apiService.getCatPictureById(id)
        return responseBody.byteStream().use { inputStream ->
            BitmapFactory.decodeStream(inputStream)
        }
    }

    suspend fun getNextProfiles(id: String): List<Cat> {

        return apiService.getNextProfiles(id)
    }


}