package student.isen.chatva_chatvient.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import student.isen.chatva_chatvient.data.api.UserApiService
import student.isen.chatva_chatvient.data.model.Cat
import student.isen.chatva_chatvient.data.model.LikeRequest
import student.isen.chatva_chatvient.data.repositories.CatRepository

class HomeViewModel(
    private val candidateRepository: CatRepository,
    private val catId: String
) : ViewModel() {

    private val _currentCat = MutableStateFlow(Cat("","",0,"","","",""))
    val currentCat = _currentCat

    private var candidateList: List<Cat> = emptyList()
    private var currentIndex: Int = 0

    private val _isEndOfList = MutableStateFlow(false)
    val isEndOfList: StateFlow<Boolean> = _isEndOfList

    init {
        loadCandidates()
    }

    private fun loadCandidates() {
        viewModelScope.launch {
            candidateList = candidateRepository.getNextProfiles(catId)

            if (candidateList.isNotEmpty()) {
                setCurrentCandidate()
            }
            else {
                _isEndOfList.value = true
            }
        }
    }

    private fun setCurrentCandidate() {
        val candidate = candidateList[currentIndex]
        _currentCat.value = candidate
    }

    fun onPassClick() {
        if (!_isEndOfList.value) {
            likeOrDislike(liked = 0)
            nextCandidate()
        }
    }

    fun onSmashClick() {
        if (!_isEndOfList.value) {
            likeOrDislike(liked = 1)
            nextCandidate()
        }
    }

    private fun nextCandidate() {
        if (currentIndex < candidateList.size - 1) {
            currentIndex++
            setCurrentCandidate()
        } else {
            _isEndOfList.value = true
        }
    }

    private fun likeOrDislike(liked: Int) {
        viewModelScope.launch {
            val currentCatId = _currentCat.value.id
            try {
                candidateRepository.likeCat(
                    userId = catId,
                    LikeRequest(userIdLiked = currentCatId, liked = liked)
                )
            } catch (_: Exception) {
            }
        }
    }
}
