package student.isen.chatva_chatvient.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

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
                LikeButton(
                    onClick = {navController.navigate("details")}
                ){}
            }

        }

    )
}

@Composable
fun FloatingBottomNavBar(navController: NavController) {
    // Get the height of the navigation bar
    val insets = WindowInsets.navigationBars
    val bottomPadding = with(LocalDensity.current) { insets.getBottom(LocalDensity.current).toDp() }

    val bottomNavItems = listOf("Profile", "Home", "Messages")
    val selectedItem = remember { mutableStateOf("Home") }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = bottomPadding + 16.dp, start = 16.dp, end = 16.dp) // Apply padding to float above system navigation
            .height(70.dp)
            .clip(RoundedCornerShape(32.dp)) // Rounded corners
            .background(Color.Green)
    ) {
        BottomNavigation(
            backgroundColor = Color.Transparent,
            elevation = 0.dp,
            modifier = Modifier.fillMaxSize()
        ) {
            bottomNavItems.forEach { item ->
                BottomNavigationItem(
                    icon = {
                        when (item) {
                            "Profil" -> Icon(Icons.Filled.Person, contentDescription = null)
                            "Home" -> Icon(Icons.Filled.Home, contentDescription = null)
                            "Messages" -> Icon(Icons.Filled.MailOutline, contentDescription = null)
                            else -> Icon(Icons.Filled.Home, contentDescription = null)
                        }
                    },
                    label = { Text(item) },
                    selected = selectedItem.value == item,
                    onClick = {
                        selectedItem.value = item
                        navController.navigate(item.lowercase())
                    },
                    selectedContentColor = Color.Blue,
                    unselectedContentColor = Color.Gray
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


@Composable
fun LikeButton(onClick: () -> Unit, function: () -> Unit) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .size(56.dp) // Make the button circular
            .clip(CircleShape) // Apply the circle shape
            .background(Color.Red) // Optional: Set background color
    ) {
        Icon(
            imageVector = Icons.Default.Favorite, // Use a built-in heart icon
            contentDescription = "Heart",
            tint = Color.White // Set icon color
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

}