package com.rishi.newsapp.ui.HomePage

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.rishi.newsapp.R
import com.rishi.newsapp.data.model.Article
import com.rishi.newsapp.data.model.SourcesList
import com.rishi.newsapp.openCustomChromeTab
import com.rishi.newsapp.ui.Screens.LoadingDialog
import com.rishi.newsapp.ui.base.UiState
import kotlinx.coroutines.delay

@Composable
fun HomeFragmentUI(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavController,
    countryId: String?,
    languageId: String?,
    sourceId: String?
) {
    val uiStateSource by viewModel.uistate_sources_.collectAsStateWithLifecycle()
    val newsList by viewModel.news_list.collectAsStateWithLifecycle()

    LaunchedEffect(countryId, languageId, sourceId) {
        if (!countryId.isNullOrEmpty() && countryId != "null") {
            viewModel.fetchNewsbyCountry(countryId)
        }
        if (!languageId.isNullOrEmpty() && languageId != "null") {
            viewModel.fetchNewsbyLanguage(languageId)
        }
        if (!sourceId.isNullOrEmpty() && sourceId != "null") {
            viewModel.fetchNewsbySources(sourceId)
        }
    }
    var showDialog by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.TopStart)
                .background(Color(0xFFF7F7FF))
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.splash_logo),
                    contentDescription = "top_logo",
                    modifier = Modifier
                        .padding(start = 20.dp, top = 20.dp)
                        .size(width = 101.dp, height = 25.dp)
                )
                when (uiStateSource) {
                    is UiState.Success -> {
                        val source =
                            (uiStateSource as UiState.Success<List<SourcesList>>).data.take(4)
                        SourceRow(source, viewModel, navController, modifier = Modifier)
                        showDialog = false
                    }

                    is UiState.Loading -> {
                        showDialog = true
                    }

                    is UiState.Error -> {
                        showDialog = false
                    }
                }

                LoadingDialog(isShowing = showDialog)

                when (val state = newsList) {
                    is UiState.Success -> {
                        showDialog = false
                        CountTitle(state.data.totalResults.toString(), modifier = Modifier)
                        if (state.data.articles.isNotEmpty()) {
                            NestedScrollPagerandList(
                                state.data.articles,
                                modifier = Modifier
                            )
                        } else {
                            Nodataview()
                        }
                    }

                    is UiState.Loading -> {
                        showDialog = true
                    }

                    is UiState.Error -> {
                        Nodataview()
                        showDialog = false
                    }
                }
//            Nodataview()
            }
        }
    }
}

@Composable
fun CountTitle(count: String, modifier: Modifier = Modifier) {
    val countText = buildAnnotatedString {
        append("There are ")
        withStyle(SpanStyle(color = Color.Red)) {
            append("$count newest")
        }
        append(" article for you ")
    }
    Text(
        countText,
        fontWeight = FontWeight.Medium,
        modifier = Modifier.padding(20.dp, 10.dp),
        textAlign = TextAlign.Start,
        fontSize = 18.sp,
        color = Color.Black,
    )
}

@Composable
fun SourceRow(
    sourcestate: List<SourcesList>,
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavController,
    modifier: Modifier.Companion
) {
    Row(
        modifier = modifier.padding(20.dp, 4.dp, 20.dp, 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        sourcestate.forEach { sourcedata ->
            SourceItem(
                imageRes = R.drawable.source_logo,
                text = sourcedata.name,
                id = sourcedata.id,
                viewModel,
                navController,
                modifier = Modifier.weight(1f)
            )
        }
        SourceItem(
            imageRes = R.drawable.icon_other,
            text = "More",
            id = "",
            viewModel,
            navController,
            modifier = Modifier
                .weight(1f)
        )
    }
}

@Composable
fun SourceItem(
    @DrawableRes imageRes: Int,
    text: String, id: String,
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .wrapContentHeight()
            .clickable {
                if (id.isNotEmpty()) {
                    viewModel.fetchNewsbySources(id)
                } else {
                    navController.navigate("source_fragment")
                }
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
        )
        Text(
            text = text,
            modifier = Modifier.padding(top = 16.dp),
            color = Color.Black,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun Nodataview(modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.no_news),
            contentDescription = null,
            modifier = Modifier.size(50.dp)
        )
        Text(
            text = "No news found!",
            modifier = Modifier.padding(top = 2.dp),
            color = Color.Black,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun NewsViewPager(
    articlelist: List<Article>,
    modifier: Modifier = Modifier
) {
    val pagerState =
        rememberPagerState(initialPage = 0, pageCount = { articlelist.size.coerceAtMost(2) })
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        while (true) {
            delay(3000) // 3 seconds delay
            val nextPage = (pagerState.currentPage + 1) % articlelist.size
            pagerState.animateScrollToPage(nextPage)
        }
    }
    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxWidth()
            .height(420.dp)
    ) { page ->
        NewsViewPagerCardItem(article = articlelist[page], modifier = Modifier)
    }
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        repeat(2) { index ->
            val selected = pagerState.currentPage == index
            Box(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .size(if (selected) 10.dp else 8.dp)
                    .clip(CircleShape)
                    .background(if (selected) Color.Red else Color.LightGray)
            )
        }
    }
}

@Composable
fun NewsViewPagerCardItem(article: Article, modifier: Modifier) {
    val context = LocalContext.current
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(420.dp)
            .padding(16.dp)
            .clickable {
                openCustomChromeTab(context, article.url)
            },
        shape = RoundedCornerShape(14.dp)
    ) {
        Box(
            modifier = modifier.fillMaxWidth()
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(article.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = article.description,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .align(Alignment.BottomStart)
                    .background(Color.Black.copy(alpha = 0.3f))
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .align(Alignment.BottomStart)
                    .padding(start = 20.dp, end = 10.dp, top = 12.dp, bottom = 10.dp)
            ) {
                Text(
                    text = article.description,
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier.padding(bottom = 10.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.source_logo),
                        contentDescription = null,
                        modifier = Modifier
                            .size(44.dp),
                        tint = Color.Unspecified // To preserve original icon color
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = article.source.name,
                        color = Color.White,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun NestedScrollPagerandList(
    articlelist: List<Article>,
    modifier: Modifier = Modifier
) {
    LazyColumn() {

        item {
            NewsViewPager(articlelist, modifier = Modifier)
        }
        item {
            Spacer(modifier = Modifier.height(4.dp))
        }
        items(articlelist) { data ->
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

@Composable
fun NewsListItemCard(
    title: String,
    source_name: String,
    image_url: String?,
    url: String,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(start = 8.dp, end = 8.dp, bottom = 14.dp)
            .clickable {
                openCustomChromeTab(context, url)
            },
        shape = RoundedCornerShape(10.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (!image_url.isNullOrEmpty()) {
                    AsyncImage(
                        model = image_url ?: R.drawable.source_logo,
                        contentDescription = source_name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .padding(start = 12.dp)
                            .size(64.dp)
                    )
                }

                Column(
                    modifier = Modifier
                        .padding(start = 15.dp, top = 12.dp, end = 10.dp)
                        .weight(1f)
                        .align(Alignment.Top)
                ) {
                    Text(
                        text = title,
                        fontSize = 12.sp,
                        color = Color.Black,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Text(
                text = source_name,
                fontSize = 11.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 10.dp, bottom = 10.dp)
            )
        }
    }
}
