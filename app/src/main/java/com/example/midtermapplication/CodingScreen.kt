package com.example.midtermapplication

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import coil.compose.AsyncImage


@Composable
fun CodingScreen() {

    var myLanguages = listOf(
        CodingLanguage(
            "java",
            "Java — строго типизированный объектно-ориентированный язык программирования общего назначения",
            "https://www.java.com/ru/",
            "https://aety.io/wp-content/uploads/2016/11/java-logo-vector.png"
        ),
        CodingLanguage(
            "Spring",
            "Spring Framework (или коротко Spring) — универсальный фреймворк с открытым исходным кодом для Java-платформы. ",
            "https://spring.io/",
            "https://miro.medium.com/v2/resize:fit:400/1*BfOUBD1I2fUhsXDcCB7jXQ.png"
        ),
        CodingLanguage(
            "javaScript",
            "JavaScript – это язык программирования, который используют разработчики для создания интерактивных веб-страниц",
            "https://learn.javascript.ru/",
            "https://i.pinimg.com/736x/19/ae/5b/19ae5b59a81e3d31806513cd5afaa385.jpg"
        ),
    )

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