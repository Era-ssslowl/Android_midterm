package com.example.midtermapplication.screens

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.midtermapplication.ViewModels.MusicCardViewModel
import com.example.midtermapplication.entities.MusicCard


@Composable
fun MusicScreen(modifier: Modifier = Modifier, viewModel: MusicCardViewModel = hiltViewModel()){

        val musicCards by viewModel.musicCards.collectAsState(initial = emptyList())



        LaunchedEffect(Unit) {
            viewModel.insertDefaultAlbums()
        }


        Column (Modifier.width(350.dp).height(600.dp)){
            Text("My favorite Albums", Modifier.padding(5.dp) , fontSize = 25.sp, fontWeight = FontWeight.Bold,)
            AlbumCarousel(musicCards, Modifier.padding(16.dp))
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




