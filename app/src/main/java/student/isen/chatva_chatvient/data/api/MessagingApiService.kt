package student.isen.chatva_chatvient.data.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import student.isen.chatva_chatvient.data.model.MessageRequest
import student.isen.chatva_chatvient.data.model.MessageResponse

interface MessagingApiService {

    @POST("messages/send")
    suspend fun sendMessage(@Body messageRequest: MessageRequest): Response<MessageResponse>

}