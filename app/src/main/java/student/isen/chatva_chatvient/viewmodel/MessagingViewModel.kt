package student.isen.chatva_chatvient.viewmodel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import student.isen.chatva_chatvient.data.model.Cat
import student.isen.chatva_chatvient.data.model.MessageRequest
import student.isen.chatva_chatvient.data.repositories.CatRepository
import student.isen.chatva_chatvient.data.repositories.MessagingRepository

class MessagingViewModel(
    private val catRepository: CatRepository,
    private val messagingRepository: MessagingRepository,
    private val catId : String,
    private val selfId: String
) : ViewModel() {

    // StateFlow for holding contact information
    private val _contactInfo = MutableStateFlow<Cat?>(null)
    val contactInfo: StateFlow<Cat?> = _contactInfo

    private val _selfUser = MutableStateFlow<Cat?>(null)
    val selfUser: StateFlow<Cat?> = _selfUser

    private val _messages = MutableStateFlow<List<MessageRequest>>(emptyList())
    val messages: StateFlow<List<MessageRequest>> = _messages

    init {
        getSelf(selfId)
        // Fetch contacts or initialize data
        getContact(catId)
    }

    // Load the chat messages based on contactId
    private fun getSelf(contactId: String) {
        viewModelScope.launch {
            // Load messages (not implemented here)
            // _chatMessages.value = loadChatMessages(contactId) // Implement this based on your logic

            // Load contact information
            println(contactId)
            _selfUser.value = catRepository.getCatById(contactId)
        }
    }

    // Load the chat messages based on contactId
    private fun getContact(contactId: String) {
        viewModelScope.launch {
            // Load messages (not implemented here)
            // _chatMessages.value = loadChatMessages(contactId) // Implement this based on your logic

            // Load contact information
            println(contactId)
            _contactInfo.value = catRepository.getCatById(contactId)
        }
    }

    // Send a message through the API
    fun sendMessage(message: MessageRequest) {
        viewModelScope.launch {
            val result = messagingRepository.sendMessage(message)
            val reply = MessageRequest(message.recipient, message.sender, "Hello")

            _messages.value = _messages.value + message
            _messages.value = _messages.value + reply
        }
    }
}