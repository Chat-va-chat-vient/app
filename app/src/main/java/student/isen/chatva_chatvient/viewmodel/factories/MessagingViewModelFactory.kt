package student.isen.chatva_chatvient.viewmodel.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import student.isen.chatva_chatvient.data.repositories.CatRepository
import student.isen.chatva_chatvient.viewmodel.ContactListViewModel

class MessagingViewModelFactory( private val catRepository: CatRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ContactListViewModel::class.java)) {
            return ContactListViewModel(catRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}