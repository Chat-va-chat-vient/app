package student.isen.chatva_chatvient.viewmodel.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import student.isen.chatva_chatvient.data.repositories.CatRepository
import student.isen.chatva_chatvient.viewmodel.ContactListViewModel

class ContactListViewModelFactory(
    private val catRepository: CatRepository,
    private val userId: String
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ContactListViewModel::class.java)) {
            return ContactListViewModel(catRepository, userId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}