package student.isen.chatva_chatvient.viewmodel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import student.isen.chatva_chatvient.data.model.Message
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import student.isen.chatva_chatvient.data.model.Cat
import student.isen.chatva_chatvient.data.repositories.CatRepository

class MessagingViewModel(private val catRepository: CatRepository) : ViewModel() {
    // StateFlow for holding the chat messages for a contact
    private val _chatMessages = MutableStateFlow<List<Message>>(emptyList())
    val chatMessages: StateFlow<List<Message>> = _chatMessages

    // StateFlow for holding contact information
    private val _contactInfo = MutableStateFlow<Cat?>(null)
    val contactInfo: StateFlow<Cat?> = _contactInfo

    // Load the chat messages based on contactId
    fun loadMessages(contactId: String) {
        viewModelScope.launch {
            // Load messages (not implemented here)
            // _chatMessages.value = loadChatMessages(contactId) // Implement this based on your logic

            // Load contact information
            _contactInfo.value = catRepository.getCatById(contactId)
        }
    }
}