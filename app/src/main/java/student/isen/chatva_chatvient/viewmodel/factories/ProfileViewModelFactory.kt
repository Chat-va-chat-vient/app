package student.isen.chatva_chatvient.viewmodel.factories

import ProfileViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import student.isen.chatva_chatvient.data.repositories.CatRepository

class ProfileViewModelFactory(
    private val catRepository: CatRepository,
    private val catId: String
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return ProfileViewModel(catRepository, catId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}