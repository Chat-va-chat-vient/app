package student.isen.chatva_chatvient.viewmodel.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import student.isen.chatva_chatvient.data.repositories.CatRepository
import student.isen.chatva_chatvient.viewmodel.MessagingViewModel

class MessagingViewModelFactory(
    private val catRepository: CatRepository,
    private val catId: String
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MessagingViewModel::class.java)) {
            return MessagingViewModel(catRepository, catId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}