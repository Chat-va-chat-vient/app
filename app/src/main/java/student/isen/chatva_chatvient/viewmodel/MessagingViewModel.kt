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

    private val _contactInfo = MutableStateFlow<Cat?>(null)
    val contactInfo: StateFlow<Cat?> = _contactInfo

    init {
        getContact(catId)
    }

    private fun getContact(contactId: String) {
        viewModelScope.launch {
            println(contactId)
            _contactInfo.value = catRepository.getCatById(contactId)
        }
    }
}