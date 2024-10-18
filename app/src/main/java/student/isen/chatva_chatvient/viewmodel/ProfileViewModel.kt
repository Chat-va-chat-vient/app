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

    var userPhoto = mutableStateOf("")
        private set

    private var userAge: Int? = null
    private var userGender: String? = null

    init {
        loadCatProfile(catId)
    }

    private fun loadCatProfile(catId: String) {
        viewModelScope.launch {
            try {
                val cat: Cat = catRepository.getCatById(catId)
                userName.value = cat.name
                userDescription.value = cat.description
                userCity.value = cat.city
                userPhoto.value = cat.photo
                userAge = cat.age
                userGender = cat.gender

            } catch (e: Exception) {
                userName.value = "Erreur de chargement"
                userDescription.value = "Impossible de charger la description."
            }
        }
    }

    fun onUserNameChange(newName: String) {
        userName.value = newName
    }

    fun onUserDescriptionChange(newDescription: String) {
        userDescription.value = newDescription
    }

    fun onUserCityChange(newCity: String) {
        userCity.value = newCity
    }

    fun onUserPhotoChange(newPhoto: String) {
        userPhoto.value = newPhoto
    }

    fun saveProfile(onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                val updatedCat = Cat(
                    id = catId,
                    name = userName.value,
                    description = userDescription.value,
                    city = userCity.value,
                    age = userAge ?: 0,
                    gender = userGender ?: "",
                    photo = userPhoto.value
                )

                catRepository.updateCat(catId, updatedCat)

                onSuccess()
            } catch (e: Exception) {
            }
        }
    }
}
