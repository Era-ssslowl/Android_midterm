package com.example.midtermapplication.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.midtermapplication.ViewModels.CodingLanguageViewModel
import com.example.midtermapplication.entities.CodingLanguage


@Composable
fun CodingScreen(viewModel: CodingLanguageViewModel = hiltViewModel()) {

    val myLanguages by viewModel.codingLanguages.collectAsState(initial = emptyList())

    LaunchedEffect(Unit) {
        viewModel.insertDefaultLanguages()
    }

    Column {
        Text("Languages that I know", Modifier.padding(5.dp) , fontSize = 25.sp, fontWeight = FontWeight.Bold,)
        myLanguages.forEachIndexed({ index, item -> CardItem(item) })
    }

}


@Composable
fun CardItem(language: CodingLanguage) {


    val uriHandler = LocalUriHandler.current
    var isVisible by remember { mutableStateOf(false) }

    Card(Modifier.width(500.dp).padding(10.dp)) {
        Column (Modifier.padding(10.dp)) {
            Row(
                Modifier.width(500.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = language.Icon,
                    contentDescription = language.name,
                    modifier = Modifier
                        .size(60.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
                Text(language.name)
                IconButton(onClick = {isVisible = !isVisible}) {
                    Icon( imageVector =  if  (isVisible) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown, "expand/Collapse")
                }
            }
            if (isVisible){
                HorizontalDivider(Modifier.padding(10.dp), thickness = 5.dp)
            }
            AnimatedVisibility(isVisible) {
                Column {
                    Text(language.briefDesc)
                    Button(onClick = { uriHandler.openUri(language.url) }) {
                        Text("Learn more")
                    }
                }

            }
        }
    }
}