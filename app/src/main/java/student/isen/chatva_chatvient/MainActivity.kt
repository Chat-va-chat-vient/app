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
import student.isen.chatva_chatvient.ui.screens.ProfileScreen

class MainActivity : ComponentActivity() {
    private lateinit var catRepository: CatRepository
    private var userId = "0881fc7c-3f06-4e1f-bf8c-21597eff596e"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Initialize the repository with the apiService from ApiClient
        catRepository = CatRepository(ApiClient.apiService)

        setContent {
            val navController = rememberNavController()
            NavHost(navController, startDestination = "home"){
                composable("home") { HomeScreen(navController, catRepository, userId) }
                composable("profile") { ProfileScreen(navController, catRepository, userId) }
                composable("messagelist"){ ContactListScreen(navController, catRepository) }
                composable("message/{catId}") { backStackEntry ->
                    val catId = backStackEntry.arguments?.getString("catId") ?: ""
                    MessagingScreen(catId, navController, catRepository)
                }
            }
        }
    }
}

