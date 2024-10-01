package student.isen.chatva_chatvient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import student.isen.chatva_chatvient.ui.screens.HomeScreen
import student.isen.chatva_chatvient.ui.screens.MessagesProfile
import student.isen.chatva_chatvient.ui.screens.PersonalProfileScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            NavHost(navController, startDestination = "home"){
                composable("home") { HomeScreen(navController) }
                composable("profile") { PersonalProfileScreen(navController) }
                composable("messages"){ MessagesProfile(navController) }
            }
        }
    }
}

