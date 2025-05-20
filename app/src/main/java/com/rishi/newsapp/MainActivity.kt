package com.rishi.newsapp

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.rishi.newsapp.ui.Screens.HomeScreen
import com.rishi.newsapp.ui.Screens.SplashScreen
import com.rishi.newsapp.ui.theme.NewsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppTheme {
                NewsAppNavHost()
            }
        }
    }
}

@Composable
fun NewsAppNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            SplashScreen(navController)
        }
        composable("home") {
            HomeScreen()
        }
    }
}

@Composable
fun BottomNavBar(navController: NavController) {
    val items = listOf(
        BottomNavItem("Home", "home_fragment", R.drawable.icon_home),
        BottomNavItem("Search", "search_fragment", R.drawable.icon_search),
        BottomNavItem("Country", "country_fragment", R.drawable.icon_country),
        BottomNavItem("Source", "source_fragment", R.drawable.icon_source),
        BottomNavItem("Language", "language_fragment", R.drawable.icon_language)
    )

    BottomNavigation(backgroundColor = Color.White) {
        val currentBackStackEntry = navController.currentBackStackEntryAsState()
        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = item.label,
                        modifier = Modifier
                            .width(36.dp)
                            .height(36.dp)
                            .padding(4.dp)
                    )
                },
                label = { Text(item.label, fontSize = 14.sp) },
                selected = currentBackStackEntry.value?.destination?.route == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo("home_fragment")
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}

data class BottomNavItem(val label: String, val route: String, @DrawableRes val icon: Int)

fun openCustomChromeTab(context: Context, url: String) {
    val builder = CustomTabsIntent.Builder()
    val customTabsIntent = builder.build()
    customTabsIntent.launchUrl(context, Uri.parse(url))
}