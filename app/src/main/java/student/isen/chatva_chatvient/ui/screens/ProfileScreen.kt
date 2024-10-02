package student.isen.chatva_chatvient.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import student.isen.chatva_chatvient.R
import student.isen.chatva_chatvient.ui.composables.CustomAppBar
import student.isen.chatva_chatvient.ui.composables.FloatingBottomNavBar

@Composable
fun PersonalProfileScreen(navController: NavController) {
    var userName by remember { mutableStateOf("Mimi le Chat") }
    var userDescription by remember { mutableStateOf("Adore chasser des souris en peluche et grimper aux arbres.") }
    var userCity by remember { mutableStateOf("Paris") } // Input de la ville

    Scaffold(
        topBar = { CustomAppBar() },
        bottomBar = { FloatingBottomNavBar(navController) },
        content = { padding ->
            Surface(
                modifier = Modifier.padding(padding),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    // Image de profil avec option de modification
                    Box(
                        modifier = Modifier
                            .size(125.dp)
                            .aspectRatio(9f/16f)
                            .clickable { /* Ajouter logique pour changer la photo de profil */ },
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_launcher_background),
                            contentDescription = "Profile Photo",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                        Text(
                            text = "Change",
                            color = Color.White,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }

                    Spacer(modifier = Modifier.height(50.dp))

                    // Modifier le nom d'utilisateur
                    OutlinedTextField(
                        value = userName,
                        onValueChange = { userName = it },
                        label = { Text("Cat Name") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Modifier la ville (Input de ville)
                    OutlinedTextField(
                        value = userCity,
                        onValueChange = { userCity = it },
                        label = { Text("City") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Modifier la description
                    OutlinedTextField(
                        value = userDescription,
                        onValueChange = { userDescription = it },
                        label = { Text("Description") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = false,
                        maxLines = 4
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Abonnement MeowMax
                    Button(
                        onClick = { /* Naviguer vers la page MeowMax ou gérer l'abonnement */ },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE91E63)),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "Subscribe to MeowMax", color = Color.White)
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Bouton pour enregistrer les modifications
                    Button(
                        onClick = { /* Logique pour sauvegarder les modifications */ },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "Save")
                    }
                }
            }
        }
    )
}
