import androidx.compose.foundation.layout.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import student.isen.chatva_chatvient.R

@Composable
fun CandidateProfile(
    imageUrl: String, // URL de l'image
    km: String, // Localisation à afficher
    modifier: Modifier = Modifier,
    width: Dp = 335.dp, // Largeur par défaut
) {
    val height = (width * 16 / 9) // Calcul de la hauteur pour un ratio d'aspect 9:16

    // Box parent pour centrer le contenu
    Box(
        modifier = Modifier
            .fillMaxSize() // Remplit tout l'écran
            .background(Color.White) // Couleur de fond
            .wrapContentSize() // Centre le contenu
    ) {
        // Box pour afficher l'image avec un ratio d'aspect
        Box(
            modifier = modifier
                .size(width, height) // Définit la largeur et la hauteur
                .clip(RoundedCornerShape(8.dp)) // Coins arrondis
                .background(Color.LightGray) // Couleur de fond
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true) // Animation de transition
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop, // Ajuste l'image
                modifier = Modifier.fillMaxSize() // Remplit le Box
            )
        }

        // Affichage de la localisation avec une icône
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart) // Aligne en bas à gauche
                .padding(16.dp)
                .padding(top = 0.dp, bottom = 50.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Milo",
                    color = Color.White,
                    textAlign = TextAlign.Start,
                    fontFamily = FontFamily(Font(R.font.inter)),
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 40.sp,
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "5",
                    color = Color.White,
                    textAlign = TextAlign.Start,
                    fontFamily = FontFamily(Font(R.font.inter)),
                    fontWeight = FontWeight.Normal,
                    fontSize = 35.sp,
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(26.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Ronronne à " + km + " km",
                    color = Color.White,
                    textAlign = TextAlign.Start,
                    fontSize = 20.sp,
                )
            }
        }
    }
}
