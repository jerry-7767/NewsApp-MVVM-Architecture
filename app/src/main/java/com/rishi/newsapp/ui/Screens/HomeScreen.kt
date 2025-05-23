package com.rishi.newsapp.ui.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.rishi.newsapp.BottomNavBar
import com.rishi.newsapp.R
import com.rishi.newsapp.ui.CountryPage.CountryFragmentUI
import com.rishi.newsapp.ui.CountryPage.CountryViewModel
import com.rishi.newsapp.ui.HomePage.HomeFragmentUI
import com.rishi.newsapp.ui.HomePage.HomeViewModel
import com.rishi.newsapp.ui.LanguagePage.LanguageFragmentUi
import com.rishi.newsapp.ui.LanguagePage.LanguageViewModel
import com.rishi.newsapp.ui.SearchPage.SearchFragmentUI
import com.rishi.newsapp.ui.SearchPage.SearchViewModel
import com.rishi.newsapp.ui.SourcePage.SourceFragmentUI
import com.rishi.newsapp.ui.SourcePage.SourceViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    searchviewmodel: SearchViewModel = hiltViewModel(),
    countryViewModel: CountryViewModel = hiltViewModel(),
    sourceViewModel: SourceViewModel = hiltViewModel(),
    languageViewModel: LanguageViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    Scaffold(bottomBar = { BottomNavBar(navController) }) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home_fragment",
            modifier = Modifier.padding(innerPadding)
        ) {
//            composable("home_fragment") { HomeFragmentUI(viewModel, navController, "", "") }
            composable(
                route = "home_fragment?countryId={countryId}&languageId={languageId}&sourceId={sourceId}",
                arguments = listOf(
                    navArgument("countryId") {
                        type = NavType.StringType
                        nullable = true
                        defaultValue = null
                    },
                    navArgument("languageId") {
                        type = NavType.StringType
                        nullable = true
                        defaultValue = null
                    },
                    navArgument("sourceId") {
                        type = NavType.StringType
                        nullable = true
                        defaultValue = null
                    })
            ) { backStackEntry ->
                val countryId = backStackEntry.arguments?.getString("countryId")
                val languageId = backStackEntry.arguments?.getString("languageId")
                val sourceId = backStackEntry.arguments?.getString("sourceId")
                HomeFragmentUI(viewModel, navController, countryId, languageId, sourceId)
            }
            composable("search_fragment") { SearchFragmentUI(searchviewmodel) }
            composable("country_fragment") { CountryFragmentUI(countryViewModel, navController) }
            composable("source_fragment") { SourceFragmentUI(sourceViewModel, navController) }
            composable("language_fragment") { LanguageFragmentUi(languageViewModel, navController) }
        }
    }
}

@Composable
fun LoadingDialog() {
        Dialog(onDismissRequest = { /* Do nothing to disable dismiss */ }) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(Color.Transparent), // mimic transparent dialog
                contentAlignment = Alignment.Center
            ) {
                Card(
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White // replaces backgroundColor
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(75.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        LottieAnimationLoader()
                    }
                }
        }
    }
}

@Composable
fun LottieAnimationLoader() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loader))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        isPlaying = true
    )

    LottieAnimation(
        composition = composition,
        progress = { progress },
        modifier = Modifier.size(75.dp)
    )
}