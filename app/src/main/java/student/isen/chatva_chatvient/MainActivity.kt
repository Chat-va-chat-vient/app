package student.isen.chatva_chatvient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import student.isen.chatva_chatvient.data.api.ApiClient
import student.isen.chatva_chatvient.data.repositories.CatRepository
import student.isen.chatva_chatvient.ui.screens.HomeScreen
import student.isen.chatva_chatvient.ui.screens.ContactListScreen
import student.isen.chatva_chatvient.ui.screens.MessagingScreen
import student.isen.chatva_chatvient.ui.screens.PersonalProfileScreen

class MainActivity : ComponentActivity() {
    private lateinit var catRepository: CatRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialize the repository with the apiService from ApiClient
        catRepository = CatRepository(ApiClient.apiService)

        setContent {
            val navController = rememberNavController()
            NavHost(navController, startDestination = "home"){
                composable("home") { HomeScreen(navController) }
                composable("profile") { PersonalProfileScreen(navController) }
                composable("messagelist"){ ContactListScreen(navController, catRepository) }
                composable("message/{catId}") { backStackEntry ->
                    val catId = backStackEntry.arguments?.getString("catId") ?: ""
                    MessagingScreen(catId, navController, catRepository)
                }
            }
        }
    }
}

