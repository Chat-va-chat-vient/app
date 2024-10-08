package student.isen.chatva_chatvient.ui.screens

import CandidateProfile
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import student.isen.chatva_chatvient.ui.composables.CustomAppBar
import student.isen.chatva_chatvient.ui.composables.FloatingBottomNavBar
import student.isen.chatva_chatvient.ui.composables.PassSmashButtons

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = { CustomAppBar() },
        bottomBar = { FloatingBottomNavBar(navController) },
        content = { padding ->
            Surface(
                modifier = Modifier.padding(padding),
            ) {
                CandidateProfile(
                    imageUrl = "https://i.imgur.com/c2tVnva.png", "2"
                )
                PassSmashButtons(navController)
            }

        }

    )
}










