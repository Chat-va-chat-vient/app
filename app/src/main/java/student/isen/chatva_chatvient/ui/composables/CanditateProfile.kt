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
import student.isen.chatva_chatvient.data.model.Cat

@Composable
fun CandidateProfile(
    cat: Cat,
    modifier: Modifier = Modifier,
    width: Dp = 335.dp,
) {
    // Vérification si le champ "id" est null ou vide
    if (cat.id.isNullOrEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .wrapContentSize(Alignment.Center)
        ) {
            Text(
                text = "Y'a plus un chat...\n(ㅠ﹏ㅠ)",
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    } else {
        val height = (width * 16 / 9)

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .wrapContentSize()
        ) {
            Box(
                modifier = modifier
                    .size(width, height)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.LightGray)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(cat.photo)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(16.dp)
                    .padding(top = 0.dp, bottom = 50.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = cat.name,
                        color = Color.White,
                        textAlign = TextAlign.Start,
                        fontFamily = FontFamily(Font(R.font.inter)),
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 40.sp,
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = cat.age.toString(),
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
                        text = "Purrs at " + cat.city,
                        color = Color.White,
                        textAlign = TextAlign.Start,
                        fontSize = 20.sp,
                    )
                }
            }
        }
    }
}
