package com.rishi.newsapp.ui.SourcePage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rishi.newsapp.R
import com.rishi.newsapp.data.model.SourcesList
import com.rishi.newsapp.ui.CountryPage.CommonListItem
import com.rishi.newsapp.ui.HomePage.Nodataview
import com.rishi.newsapp.ui.Screens.LoadingDialog
import com.rishi.newsapp.ui.base.UiState

@Composable
fun SourceFragmentUI(
    sourceViewModel: SourceViewModel = hiltViewModel(),
    navController: NavController
) {
    val source_list by sourceViewModel.uistate_source.collectAsState()
    sourceViewModel.fetchSource()
    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.TopStart)
                .background(Color(0xFFF7F7FF))
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.splash_logo),
                    contentDescription = "top_logo",
                    modifier = Modifier
                        .padding(start = 20.dp, top = 20.dp)
                        .size(width = 101.dp, height = 25.dp)
                )

                val state = source_list
                SourceList(source_list, navController, modifier = Modifier)
            }

        }
    }
}

@Composable
fun SourceList(
    country_list: UiState<List<SourcesList>>,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    when (country_list) {
        is UiState.Success -> {
            val list = country_list.data
            if (list.isNotEmpty()) {
                LazyColumn() {
                    items(list) { data ->
                        CommonListItem(
                            data.name,
                            data.id,
                            navController,
                            "source",
                            modifier = modifier
                        )
                    }
                }
            } else {
                Nodataview()
            }
        }

        is UiState.Error -> {
            Nodataview()
        }

        UiState.Loading -> {
            LoadingDialog()
        }

        is UiState.Initial -> {

        }
    }
}

