package student.isen.chatva_chatvient.viewmodel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import student.isen.chatva_chatvient.data.model.Cat

class ConstactListViewModel : ViewModel() {
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
            _contacts.value = listOf(
                Cat("Alice", 4, "https://www.nj.com/resizer/mg42jsVYwvbHKUUFQzpw6gyKmBg=/1280x0/smart/advancelocal-adapter-image-uploads.s3.amazonaws.com/image.nj.com/home/njo-media/width2048/img/somerset_impact/photo/sm0212petjpg-7a377c1c93f64d37.jpg", null),
                Cat("Bob", 8, "https://www.nj.com/resizer/mg42jsVYwvbHKUUFQzpw6gyKmBg=/1280x0/smart/advancelocal-adapter-image-uploads.s3.amazonaws.com/image.nj.com/home/njo-media/width2048/img/somerset_impact/photo/sm0212petjpg-7a377c1c93f64d37.jpg", null),
                Cat("Charlie", 10, "https://www.nj.com/resizer/mg42jsVYwvbHKUUFQzpw6gyKmBg=/1280x0/smart/advancelocal-adapter-image-uploads.s3.amazonaws.com/image.nj.com/home/njo-media/width2048/img/somerset_impact/photo/sm0212petjpg-7a377c1c93f64d37.jpg", null)
            )
        }
    }
}