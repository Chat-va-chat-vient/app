package student.isen.chatva_chatvient.viewmodel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import student.isen.chatva_chatvient.data.model.Cat
import student.isen.chatva_chatvient.data.repositories.CatRepository

class ContactListViewModel(private val catRepository: CatRepository) : ViewModel() {
    // StateFlow to hold the list of contacts
    private val _contacts = MutableStateFlow<List<Cat>>(emptyList())
    val contacts: StateFlow<List<Cat>> = _contacts

    init {
        // Fetch contacts or initialize data
        loadContacts()
    }

    private fun loadContacts() {
        viewModelScope.launch {
            // Simulate fetching contacts from a data source
            _contacts.value = catRepository.getNextProfiles("0881fc7c-3f06-4e1f-bf8c-21597eff596e")
        }
    }
}