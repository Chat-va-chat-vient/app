package student.isen.chatva_chatvient.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import student.isen.chatva_chatvient.R
import student.isen.chatva_chatvient.ui.theme.PassButtonColor
import student.isen.chatva_chatvient.ui.theme.SmashButtonColor

@Composable
fun SmashButton(onClick: () -> Unit, function: () -> Unit) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .size(56.dp) // Make the button circular
            .clip(CircleShape) // Apply the circle shape
            .background(SmashButtonColor) // Optional: Set background color
    ) {
        // Replace with the VectorDrawable from your drawable resource
        Image(
            painter = painterResource(id = R.drawable.heart), // Replace with your drawable resource ID
            contentDescription = "Heart",
            modifier = Modifier.size(40.dp) // Adjust the size of the image

        )
    }
}

@Composable
fun PassButton(onClick: () -> Unit, function: () -> Unit) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .size(56.dp) // Make the button circular
            .clip(CircleShape) // Apply the circle shape
            .background(PassButtonColor) // Optional: Set background color
    ) {
        // Replace with the VectorDrawable from your drawable resource
        Image(
            painter = painterResource(id = R.drawable.griffre), // Replace with your drawable resource ID
            contentDescription = "claw",
            modifier = Modifier.size(40.dp) // Adjust the size of the image
        )
    }
}

@Composable
fun PassSmashButtons(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 30.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            PassButton(
                onClick = { navController.navigate("details") }) {}

            Spacer(modifier = Modifier.width(10.dp)) // Spacer to add 10.dp space between buttons

            SmashButton(
                onClick = { navController.navigate("details") }) {}
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPassSmashButtons() {
    val navController = rememberNavController() // Simulating the NavController
    PassSmashButtons(navController)
}