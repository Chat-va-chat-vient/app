package student.isen.chatva_chatvient.data.repositories

import student.isen.chatva_chatvient.data.api.ApiClient
import student.isen.chatva_chatvient.data.model.MessageRequest
import student.isen.chatva_chatvient.data.model.MessageResponse

class MessagingRepository {
    private val apiService = ApiClient.messagingApiService

    suspend fun sendMessage(messageRequest: MessageRequest): MessageResponse {
        return apiService.sendMessage(messageRequest)
    }

}