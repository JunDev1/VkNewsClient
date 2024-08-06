@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.vknewsclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.vknewsclient.ui.theme.VkNewsClientTheme
import com.example.vknewsclient.ui.theme.MainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VkNewsClientTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun CustomScaffold() {
    val listItem = listOf("Like", "Profile", "Additional")
    var selectedItem by remember {
        mutableStateOf(0)
    }
    val selectedIcon = listOf(Icons.Filled.Favorite, Icons.Filled.Person, Icons.Filled.Add)
    val unSelectedIcon = listOf(Icons.Outlined.Favorite, Icons.Outlined.Person, Icons.Outlined.Add)
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(text = "TopAppBarTitle")
            },
            navigationIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(Icons.Filled.AccountBox, contentDescription = null)
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Black,
                titleContentColor = Color.White
            )
        )
    },
        bottomBar = {
            NavigationBar {
                listItem.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedItem == index,
                        onClick = { selectedItem = index },
                        icon = {
                            Icon(
                                if (selectedItem == index) selectedIcon[index] else unSelectedIcon[index],
                                contentDescription = item
                            )
                        })
                }
            }
        }
    ) {
        Text(
            modifier = Modifier.padding(it),
            text = "This is Scaffold content"
        )
    }
}