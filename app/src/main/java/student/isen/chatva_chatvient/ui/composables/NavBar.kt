package student.isen.chatva_chatvient.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import student.isen.chatva_chatvient.R
import student.isen.chatva_chatvient.ui.theme.PurpleGrey80

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
//            .background(PurpleGrey80)
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
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(
                                imageVector = when (item) {
                                    "Profile" -> Icons.Outlined.Person
                                    "Home" -> ImageVector.vectorResource(R.drawable.home)
                                    "Messages" -> Icons.Filled.MailOutline
                                    else -> Icons.Outlined.Person // Valeur par défaut
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
                        when (item) {
                            "Profile" -> navController.navigate("profile") // Remplace "profile" par le nom de la route de ta page Profile
                            "Home" -> navController.navigate("home") // Remplace "home" par le nom de la route de ta page Home
                            "Messages" -> navController.navigate("messagelist") // Remplace "messages" par le nom de la route de ta page Messages
                        }
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
