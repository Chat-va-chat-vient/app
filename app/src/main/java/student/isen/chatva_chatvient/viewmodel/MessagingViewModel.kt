package student.isen.chatva_chatvient.viewmodel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import student.isen.chatva_chatvient.data.model.Message
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import student.isen.chatva_chatvient.data.model.Cat
import student.isen.chatva_chatvient.data.repositories.CatRepository

class MessagingViewModel(private val catRepository: CatRepository, catId : String) : ViewModel() {

    // StateFlow for holding contact information
    private val _contactInfo = MutableStateFlow<Cat?>(null)
    val contactInfo: StateFlow<Cat?> = _contactInfo

    init {
        // Fetch contacts or initialize data
        getContact(catId)
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
}