package student.isen.chatva_chatvient.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import student.isen.chatva_chatvient.data.model.Cat
import student.isen.chatva_chatvient.data.model.Message
import student.isen.chatva_chatvient.data.repositories.CatRepository
import student.isen.chatva_chatvient.viewmodel.MessagingViewModel
import student.isen.chatva_chatvient.viewmodel.factories.MessagingViewModelFactory


@Composable
fun MessagingScreen(catId: String, navController: NavController, catRepository: CatRepository) {

    // Create the ViewModel using the factory
    val viewModel: MessagingViewModel = viewModel(
        factory = MessagingViewModelFactory(catRepository, catId)
    )

    val contact by viewModel.contactInfo.collectAsState()

    // Display contact information
    contact?.let { cat ->
        Scaffold(
            topBar = { CustomTopAppBar(cat, navController) },
            content = { padding ->
                Surface(
                    modifier = Modifier.padding(padding),
                ) {
                    MessagingPage(cat)
                }
            }
        )
    }



}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(cat: Cat, navController: NavController) {
    TopAppBar(
        title = {
            Row(modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically,) {
                //Add the profile picture here
                AsyncImage(
                    model = cat.photo,
                    contentDescription = null,
                    contentScale = ContentScale.Crop, // Ajuste l'image
                    modifier = Modifier.size(40.dp)
                        .clip(CircleShape)                       // clip to the circle shape
                        .border(2.dp, Color.Gray, CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(cat.name)
            }
        },
        navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Localized description"
                )
            }
        },
        actions = {
            IconButton(onClick = { /* do something */ }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Localized description"
                )
            }
        },
    )
}

@Composable
fun MessagingPage(cat: Cat) {
    var messages by remember { mutableStateOf(listOf<Message>()) }
    var newMessage by remember { mutableStateOf(TextFieldValue()) }

    Column(modifier = Modifier.fillMaxSize()) {
        // Message List
        LazyColumn(
            modifier = Modifier.weight(1F).padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(messages) { message ->
                MessageItem(message)
            }
        }

        // Bottom Bar with rounded text field
        BottomBar(
            textValue = newMessage,
            onTextChange = { newMessage = it },
            onSendClick = {
                if (newMessage.text.isNotBlank()) {
                    messages = messages + Message("You", newMessage.text)
                    newMessage = TextFieldValue("") // Clear input field
                }
            }
        )
    }
}

@Composable
fun BottomBar(
    textValue: TextFieldValue,
    onTextChange: (TextFieldValue) -> Unit,
    onSendClick: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 8.dp,
        shadowElevation = 4.dp,

        //rounded top corner
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Rounded TextField
            BasicTextField(
                value = textValue,
                onValueChange = onTextChange,
                modifier = Modifier
                    .weight(1f)
                    .background(Color.Gray.copy(alpha = 0.2f), shape = MaterialTheme.shapes.medium)
                    .padding(12.dp),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Send,
                    keyboardType = KeyboardType.Text
                ),
                textStyle = LocalTextStyle.current.copy(
                    color = Color.Black,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start
                ),
                decorationBox = { innerTextField ->
                    if (textValue.text.isEmpty()) {
                        Text(
                            text = "Type a message...",
                            style = LocalTextStyle.current.copy(
                                color = Color.Gray,
                                fontSize = 16.sp
                            )
                        )
                    }
                    innerTextField()  // Render the input field
                }
            )

            Spacer(modifier = Modifier.width(8.dp))

            // Send Button

            IconButton(
                onClick = onSendClick,
                modifier = Modifier
                    .size(48.dp)
                    .background(MaterialTheme.colorScheme.primary , shape = CircleShape)
            ) { //Add send icon
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Send,
                    contentDescription = "Send",
                    tint = Color.White
                )
                              }

        }
    }
}


@Composable
fun MessageItem(message: Message) {
    val isUserMessage = message.sender == "You"

    // Bubble style
    val bubbleColor = if (isUserMessage) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
    val alignment = if (isUserMessage) Alignment.CenterEnd else Alignment.CenterStart

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(alignment)
            .padding(8.dp)
    ) {
        Text(
            text = message.content,
            color = Color.White,
            fontSize = 16.sp,
            modifier = Modifier
                .background(bubbleColor, shape = MaterialTheme.shapes.medium)
                .padding(12.dp)
        )
    }
}