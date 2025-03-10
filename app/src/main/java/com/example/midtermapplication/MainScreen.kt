package com.example.midtermapplication

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.unit.dp


@Composable
fun     MainScreen(modifier: Modifier = Modifier){

    var screens = listOf(
        NavItem("Music",  Icons.Default.Favorite),
        NavItem("Coding", Icons.Default.Edit)
        )

    var  selectedItem by remember { mutableIntStateOf(0) }



    Scaffold(bottomBar = {
        NavigationBar {
                screens.forEachIndexed{index, item-> NavigationBarItem(
                    icon = {Icon(item.icon, item.name)},
                    label = { Text( item.name)},
                    selected = index == selectedItem,
                    onClick = {selectedItem = index}
                )
                }
        }
    }) { innerPadding ->
        Column (Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
            Content(Modifier.padding(30.dp), selectedItem)
        }
    }
}




@Composable
fun  Content(modifier: Modifier = Modifier, selectedItem: Int){
        when(selectedItem){
            0 -> MusicScreen()
            1 -> CodingScreen()

        }
}