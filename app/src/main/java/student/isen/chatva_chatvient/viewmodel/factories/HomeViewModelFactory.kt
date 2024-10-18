package student.isen.chatva_chatvient.viewmodel.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import student.isen.chatva_chatvient.data.repositories.CatRepository
import student.isen.chatva_chatvient.viewmodel.HomeViewModel
class HomeViewModelFactory(
    private val catRepository: CatRepository,
    private val catId: String
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(catRepository, catId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}