package student.isen.chatva_chatvient.ui.screens

import CandidateProfile
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import student.isen.chatva_chatvient.data.repositories.CatRepository
import student.isen.chatva_chatvient.ui.composables.CustomAppBar
import student.isen.chatva_chatvient.ui.composables.FloatingBottomNavBar
import student.isen.chatva_chatvient.ui.composables.PassSmashButtons
import student.isen.chatva_chatvient.viewmodel.HomeViewModel
import student.isen.chatva_chatvient.viewmodel.MessagingViewModel
import student.isen.chatva_chatvient.viewmodel.factories.HomeViewModelFactory
import student.isen.chatva_chatvient.viewmodel.factories.MessagingViewModelFactory

@Composable
fun HomeScreen(navController: NavController, catRepository: CatRepository, catId: String) {

    val viewModel: HomeViewModel = viewModel(
        factory = HomeViewModelFactory(catRepository, catId)
    )

    val currentCat by viewModel.currentCat.collectAsState()

    Scaffold(
        topBar = { CustomAppBar() },
        bottomBar = { FloatingBottomNavBar(navController) },
        content = { padding ->
            Surface(
                modifier = Modifier.padding(padding),
            ) {
                CandidateProfile(
                    cat = currentCat
                )
                PassSmashButtons(
                    onSmashClick = {viewModel.onSmashClick()},
                    onPassClick = {viewModel.onPassClick()}
                )
            }

        }

    )
}










