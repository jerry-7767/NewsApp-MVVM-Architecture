package com.rishi.newsapp.ui.SearchPage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rishi.newsapp.R
import com.rishi.newsapp.ui.HomePage.NewsListItemCard
import com.rishi.newsapp.ui.base.UiState

@Composable
fun SearchFragmentUI(searchviewmodel: SearchViewModel = hiltViewModel()) {
    val uiState by searchviewmodel.uistate_search_news.collectAsStateWithLifecycle()
    var query by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .background(Color(0xFFF7F7FF))
    ) {
        Image(
            painter = painterResource(id = R.drawable.splash_logo),
            contentDescription = "top_logo",
            modifier = Modifier
                .padding(start = 20.dp, top = 20.dp)
                .size(width = 101.dp, height = 25.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .height(50.dp)
                .padding(start = 8.dp, end = 8.dp, top = 20.dp)
        ) {
            Card(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            ) {

                TextField(
                    value = query,
                    onValueChange = {
                        query = it
                        if (it.isNotEmpty()) {
                            searchviewmodel.fetchNewsbysearch(it)
                        }
                    },
                    placeholder = { Text("Search News") },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                    trailingIcon = {
                        if (query.isNotEmpty()) {
                            IconButton(onClick = {
                                query = ""
                            }) {
                                Icon(Icons.Default.Close, contentDescription = "Clear")
                            }
                        }
                    },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

            }

            Spacer(modifier = Modifier.height(16.dp))

            // List or UI State
            when (val state = uiState) {
                is UiState.Loading -> {
//                    LoadingDialog()
                }

                is UiState.Success -> {
                    val articles = state.data.articles

                    if (articles.isEmpty()) {
                        Text(
                            "No results found",
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    } else {
                        LazyColumn {
                            items(articles) { data ->
                                NewsListItemCard(
                                    data.title,
                                    data.source.name,
                                    data.imageUrl,
                                    data.url,
                                    modifier = Modifier
                                )
                            }
                        }
                    }
                }

                is UiState.Error -> {
                    /* val msg = (uiState as UiState.Error).message
                Text("Error: $msg", color = Color.Red)*/
                }

                is UiState.Initial -> {

                }
            }
        }
    }
}