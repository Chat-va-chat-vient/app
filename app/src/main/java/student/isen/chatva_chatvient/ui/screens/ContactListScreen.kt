package student.isen.chatva_chatvient.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import student.isen.chatva_chatvient.data.model.Cat
import student.isen.chatva_chatvient.ui.composables.CustomAppBar
import student.isen.chatva_chatvient.ui.composables.FloatingBottomNavBar
import student.isen.chatva_chatvient.viewmodel.ContactListViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import student.isen.chatva_chatvient.data.repositories.CatRepository
import student.isen.chatva_chatvient.viewmodel.factories.ContactListViewModelFactory


@Composable
fun ContactListScreen(
    navController: NavController,
    catRepository: CatRepository,
    userId: String
) {

    val viewModel: ContactListViewModel = viewModel(
        factory = ContactListViewModelFactory(catRepository,userId)
    )

    val contacts by viewModel.contacts.collectAsState()

    Scaffold(
        topBar = { CustomAppBar() },
        bottomBar = { FloatingBottomNavBar(navController) },
        content = { padding ->
            Surface(
                modifier = Modifier.padding(padding),
            ) {
                LazyColumn(modifier = Modifier.padding(16.dp)) {
                    items(contacts) { contact ->
                        ContactItem(contact, navController)
                    }
                }
            }
        }
    )
}

@Composable
fun ContactItem(contact: Cat, navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(onClick = {

                navController.navigate("message/${contact.id}")}
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = contact.photo,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(40.dp)
                .clip(CircleShape)
                .border(2.dp, Color.Gray, CircleShape)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            BasicText(text = contact.name, style = MaterialTheme.typography.bodyLarge)
        }
    }
}
