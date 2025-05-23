package com.rishi.newsapp.ui.CountryPage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rishi.newsapp.R
import com.rishi.newsapp.data.model.Country
import com.rishi.newsapp.ui.base.UiState

@Composable
fun CountryFragmentUI(
    countryViewModel: CountryViewModel = hiltViewModel(),
    navController: NavController
) {
    val country_list by countryViewModel.country_list.collectAsState()
    countryViewModel.getCountrylist()
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

                val state = country_list
                Countrylist(country_list, navController, modifier = Modifier)
//            Nodataview()
            }

        }
    }
}

@Composable
fun Countrylist(
    country_list: UiState<List<Country>>,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    when (country_list) {
        is UiState.Success -> {
            val list = country_list.data
            LazyColumn() {
                items(list) { data ->
                    CommonListItem(
                        data.name,
                        data.id,
                        navController,
                        "country",
                        modifier = modifier
                    )
                }
            }
        }

        is UiState.Error -> {

        }

        is UiState.Initial -> {

        }

        UiState.Loading -> {}
    }
}

@Composable
fun CommonListItem(
    country_name: String,
    country_id: String,
    navController: NavController,
    category: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(65.dp)
            .background(Color.White)
            .clickable {
                if (category == "language") {
                    navController.navigate("home_fragment?languageId=$country_id")
                } else if (category == "country") {
                    navController.navigate("home_fragment?countryId=$country_id")
                } else {
                    navController.navigate("home_fragment?sourceId=$country_id")
                }
            }
    ) {
        Row {
            Image(
                painter = painterResource(id = R.drawable.app_logo),
                contentDescription = "logo",
                modifier = modifier
                    .padding(start = 8.dp, top = 6.dp, bottom = 6.dp)
                    .size(width = 34.dp, height = 34.dp)
                    .align(Alignment.CenterVertically)
            )
            Text(
                text = country_name,
                color = Color.Black,
                fontSize = 12.sp,
                modifier = modifier
                    .padding(start = 16.dp)
                    .align(Alignment.CenterVertically)
            )
        }
    }
}