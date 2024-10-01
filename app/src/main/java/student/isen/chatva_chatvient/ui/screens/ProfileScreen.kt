package student.isen.chatva_chatvient.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun DetailPage(navController: NavController) {
    // Crée une colonne pour organiser les éléments
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Titre de la page d'accueil
        Text(text = "Bienvenue sur la Detail Page", style = MaterialTheme.typography.titleSmall)

        Spacer(modifier = Modifier.height(20.dp))

        // Bouton pour naviguer vers la page de détails
        Button(onClick = { navController.navigate("home") }) {
            Text(text = "Aller aux Home Page")
        }
    }
}