package com.example.midtermapplication

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import java.net.URL


@Composable
fun MusicScreen(modifier: Modifier = Modifier){
        var myAlbums = listOf(
            MusicCard(
                "Vessel",
                "Alternative-rock",
                "Twenty-one pilots",
                "https://is1-ssl.mzstatic.com/image/thumb/Music211/v4/73/a7/23/73a7230c-19df-02a4-ff4e-53944024f63d/075679957924.jpg/600x600bb.jpg",
                "https://open.spotify.com/album/2r2r78NE05YjyHyVbVgqFn"
                ),
            MusicCard(
                "Ok computer",
                "Rock",
                "Radiohead",
                "https://is1-ssl.mzstatic.com/image/thumb/Music116/v4/07/60/ba/0760ba0f-148c-b18f-d0ff-169ee96f3af5/634904078164.png/600x600bb.jpg",
                "https://open.spotify.com/album/6dVIqQ8qmQ5GBnJ9shOYGE"
            ),
            MusicCard(
                "Часть чего-то большего",
                "Rock",
                "Валентин Стрыкало",
                "https://is1-ssl.mzstatic.com/image/thumb/Music62/v4/18/08/7c/18087c3e-2899-70ba-f3fc-4cfba1924985/191061114437.jpg/600x600bb.jpg",
                "https://open.spotify.com/album/0x4WtpS8RL49nXoHlMgQsW"
            ),
        )


        Column (Modifier.width(350.dp).height(600.dp)){
            Text("My favorite Albums", Modifier.padding(5.dp) , fontSize = 25.sp, fontWeight = FontWeight.Bold,)
            AlbumCarousel(myAlbums, Modifier.padding(16.dp))
        }

}



@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AlbumCarousel(albums: List<MusicCard>, modifier: Modifier = Modifier){
    val pagerState = rememberPagerState (pageCount = {albums.size})

    HorizontalPager(state = pagerState) { page ->
        CardItem(album = albums[page], Modifier.padding(32.dp))
    }
}

@Composable
fun CardItem(album: MusicCard, modifier: Modifier) {
    val uriHandler = LocalUriHandler.current


    Card(Modifier.fillMaxSize()) {
        AsyncImage(
            model = album.cover,
            contentDescription = album.name,
            modifier = Modifier
                .size(350.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = album.name, style = MaterialTheme.typography.titleLarge)
            Text(text = album.artist )
            Text(text = album.genre)
            HorizontalDivider(Modifier.padding(10.dp), thickness = 5.dp)
            Button(onClick = {uriHandler.openUri(album.url)}) {
                Text("Open on Spotify")
            }
        }
    }

}




