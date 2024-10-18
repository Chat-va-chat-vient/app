package student.isen.chatva_chatvient.ui.screens

import ProfileViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import student.isen.chatva_chatvient.R
import student.isen.chatva_chatvient.data.repositories.CatRepository
import student.isen.chatva_chatvient.ui.composables.CustomAppBar
import student.isen.chatva_chatvient.ui.composables.FloatingBottomNavBar
import student.isen.chatva_chatvient.viewmodel.factories.ProfileViewModelFactory

@Composable
fun ProfileScreen(
    navController: NavController,
    catRepository: CatRepository,
    catId: String
) {
    // Create the ViewModel using the factory
    val viewModel: ProfileViewModel = viewModel(
        factory = ProfileViewModelFactory(catRepository, catId)
    )

    // État pour le dialogue de confirmation
    val showDialog = remember { mutableStateOf(false) }

    val userName by viewModel.userName
    val userDescription by viewModel.userDescription
    val userCity by viewModel.userCity
    val userPhoto by viewModel.userPhoto

    // Ajout du Scaffold pour afficher le dialog
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
                    // Image de profil
                    Box(
                        modifier = Modifier
                            .width(125.dp)
                            .height((125 * 16 / 9).dp)
                            .clip(RoundedCornerShape(8.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        val painter = rememberAsyncImagePainter(userPhoto)
                        Image(
                            painter = painter,
                            contentDescription = "Profile Photo",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    Spacer(modifier = Modifier.height(50.dp))

                    // Modifier le nom d'utilisateur
                    OutlinedTextField(
                        value = userName,
                        onValueChange = { viewModel.onUserNameChange(it) },
                        label = { Text("Cat Name") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Modifier la ville
                    OutlinedTextField(
                        value = userCity,
                        onValueChange = { viewModel.onUserCityChange(it) },
                        label = { Text("City") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    // Modifier la description
                    OutlinedTextField(
                        value = userDescription,
                        onValueChange = { viewModel.onUserDescriptionChange(it) },
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
                        onClick = {
                            viewModel.saveProfile {showDialog.value=true}
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "Save")
                    }
                }

                // Affichage du dialogue de confirmation
                if (showDialog.value) {
                    AlertDialog(
                        onDismissRequest = { showDialog.value = false },
                        title = { Text("Success") },
                        text = { Text("Your profile has been successfully saved.") },
                        confirmButton = {
                            TextButton(
                                onClick = { showDialog.value = false }
                            ) {
                                Text("OK")
                            }
                        }
                    )
                }
            }
        }
    )
}
