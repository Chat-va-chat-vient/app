package student.isen.chatva_chatvient.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import student.isen.chatva_chatvient.data.model.Cat
import student.isen.chatva_chatvient.data.repositories.CatRepository

class HomeViewModel(
    private val candidateRepository: CatRepository,
    private val catId: String
) : ViewModel() {

    private val _currentCat = MutableStateFlow(Cat("","",0,"","","",""))
    val currentCat = _currentCat

    private var candidateList: List<Cat> = emptyList()
    private var currentIndex: Int = 0

    init {
        // Load initial candidate profiles
        loadCandidates()
    }

    private fun loadCandidates() {
        viewModelScope.launch {
            // Fetch candidate list from the repository
            candidateList = candidateRepository.getNextProfiles(catId)

            // Set the first candidate if available
            if (candidateList.isNotEmpty()) {
                setCurrentCandidate()
            }
        }
    }

    private fun setCurrentCandidate() {
        val candidate = candidateList[currentIndex]
        _currentCat.value = candidate
    }

    fun onPassClick() {
        nextCandidate()
    }

    fun onSmashClick() {
        nextCandidate()
    }

    private fun nextCandidate() {
        // Move to the next candidate
        if (currentIndex < candidateList.size - 1) {
            currentIndex++
            setCurrentCandidate()
        } else {
            // Optionally handle the end of the candidate list
            // For now, just loop back to the beginning
            currentIndex = 0
            setCurrentCandidate()
        }
    }
}
