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
        loadCandidates()
    }

    private fun loadCandidates() {
        viewModelScope.launch {
            candidateList = candidateRepository.getNextProfiles(catId)

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
        if (currentIndex < candidateList.size - 1) {
            currentIndex++
            setCurrentCandidate()
        } else {
            currentIndex = 0
            setCurrentCandidate()
        }
    }
}
