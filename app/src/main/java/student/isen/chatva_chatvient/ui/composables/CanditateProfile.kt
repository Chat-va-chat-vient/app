import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
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

@Composable
fun AspectRatioImageFromUrl(
    imageUrl: String, // URL de l'image
    modifier: Modifier = Modifier,
    width: Dp = 350.dp, // Largeur par défaut
) {
    val height = (width * 16 / 9) // Calcul de la hauteur pour un ratio d'aspect 9:16

    // Box parent pour centrer l'image
    Box(
        modifier = Modifier
            .fillMaxSize() // Remplit tout l'écran
            .background(Color.White) // Optionnel : couleur de fond pour le contraste
            .wrapContentSize() // Centre le contenu
    ) {
        // Box pour afficher l'image avec un ratio d'aspect
        Box(
            modifier = modifier
                .size(width, height) // Définit la largeur et la hauteur
                .clip(RoundedCornerShape(8.dp)) // Optionnel : coins arrondis
                .background(Color.LightGray) // Optionnel : couleur de fond
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageUrl)
                    .crossfade(true) // Animation de transition
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop, // Ajuste l'image pour qu'elle s'adapte au conteneur
                modifier = Modifier.fillMaxSize() // Remplit le Box
            )
        }
    }
}
