package student.isen.chatva_chatvient.data.repositories

import student.isen.chatva_chatvient.data.api.ApiClient
import student.isen.chatva_chatvient.data.model.MessageRequest
import student.isen.chatva_chatvient.data.model.MessageResponse

class MessagingRepository {
    private val apiService = ApiClient.messagingApiService

    suspend fun sendMessage(messageRequest: MessageRequest): MessageResponse? {
        var response = apiService.sendMessage(messageRequest)

        if (response.isSuccessful){
            return response.body()

        }
        return null
    }

}