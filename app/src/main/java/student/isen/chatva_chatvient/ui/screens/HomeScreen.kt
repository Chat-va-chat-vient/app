package student.isen.chatva_chatvient.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import student.isen.chatva_chatvient.ui.composables.PassSmashButtons
import student.isen.chatva_chatvient.ui.theme.PurpleGrey40
import student.isen.chatva_chatvient.ui.theme.PurpleGrey80

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = { AddAppBar() },
        bottomBar = { FloatingBottomNavBar(navController) },
        content = { padding ->
            Surface(
                modifier = Modifier.padding(padding),
            ) {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = "Lorem ipsum dolor sit amet...")
                PassSmashButtons(navController)
            }

        }

    )
}

@Composable
fun FloatingBottomNavBar(navController: NavController) {
    val insets = WindowInsets.navigationBars
    val bottomPadding = with(LocalDensity.current) { insets.getBottom(LocalDensity.current).toDp() }

    val bottomNavItems = listOf("Profile", "Home", "Messages")
    val selectedItem = remember { mutableStateOf("Home") }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = bottomPadding + 16.dp, start = 16.dp, end = 16.dp)
            .height(60.dp)
            .clip(RoundedCornerShape(32.dp))
            .background(PurpleGrey80)
    ) {
        BottomNavigation(
            backgroundColor = Color.Transparent,
            elevation = 0.dp,
            modifier = Modifier.fillMaxSize()
        ) {
            bottomNavItems.forEach { item ->
                BottomNavigationItem(
                    icon = {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally, // Centre l'icône et le texte
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(
                                imageVector = when (item) {
                                    "Profile" -> Icons.Filled.Person
                                    "Home" -> Icons.Filled.Home
                                    "Messages" -> Icons.Filled.MailOutline
                                    else -> Icons.Filled.Home
                                },
                                contentDescription = null,
                                modifier = Modifier.size(24.dp)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(item)
                        }
                    },
                    selected = selectedItem.value == item,
                    onClick = {
                        selectedItem.value = item
                        navController.navigate(item.lowercase())
                    },
                    selectedContentColor = Color.Blue,
                    unselectedContentColor = Color.Gray,
                    alwaysShowLabel = true,
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}





@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAppBar() {
    TopAppBar(
        title = { Text("My App") },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        actions = {
            IconButton(onClick = { /* Do something */ }) {
                Icon(Icons.Default.Search, contentDescription = "Search")
            }
        }
    )
}