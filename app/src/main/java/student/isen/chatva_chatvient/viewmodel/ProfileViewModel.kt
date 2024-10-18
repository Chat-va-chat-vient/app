import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import student.isen.chatva_chatvient.data.model.Cat
import student.isen.chatva_chatvient.data.repositories.CatRepository

class ProfileViewModel(private val catRepository: CatRepository,
                       private val catId: String) : ViewModel() {


    var userName = mutableStateOf("")
        private set

    var userDescription = mutableStateOf("")
        private set

    var userCity = mutableStateOf("")
        private set

    init {
        loadCatProfile(catId)
    }

    private fun loadCatProfile(catId: String) {
        viewModelScope.launch {
            try {
                val cat: Cat = catRepository.getCatById(catId)
                userName.value = cat.name
                userDescription.value = cat.description ?: ""
            } catch (e: Exception) {
                // Gestion d'erreur si l'API échoue
                userName.value = "Erreur de chargement"
                userDescription.value = "Impossible de charger la description."
            }
        }
    }

    // Modifier les valeurs
    fun onUserNameChange(newName: String) {
        userName.value = newName
    }

    fun onUserDescriptionChange(newDescription: String) {
        userDescription.value = newDescription
    }

    fun onUserCityChange(newCity: String) {
        userCity.value = newCity
    }

    fun saveProfile() {
        viewModelScope.launch {
            // Logique pour sauvegarder les changements
            // Par exemple, faire appel à une API ou sauvegarder localement
        }
    }
}
