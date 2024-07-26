package com.example.spotfinder.ui.theme

import androidx.activity.compose.BackHandler
import androidx.lifecycle.ViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.spotfinder.R
import com.example.spotfinder.data.Category
import com.example.spotfinder.models.CategoryModelView
import com.example.spotfinder.models.RecommendationDetailModelView
import com.example.spotfinder.models.RecommendationModelView

@Composable
fun SpotFinderApp(){
    val navController = rememberNavController()

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination

    Scaffold(
        topBar = {
            SpotFinderAppBar(
                title = getAppBarTitle(destination = currentDestination),
                showBackButton = navController.previousBackStackEntry != null,
                onBackButtonClick = { navController.popBackStack() }
            )
        }
    ) {innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "categories",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("categories"){
                CategoriesScreen(navController = navController)
            }
            composable("recommendations/{category}"){backStackEntry ->
                RecommendationScreen(
                    category = backStackEntry.arguments?.getString("category"),
                    navController = navController
                )
            }
            composable("recommendationDetail/{title}"){backStackEntry ->
                RecommendationDetailScreen(
                    recommendationTitle = backStackEntry.arguments?.getString("title") ?: "",
                    onBackButtonClick = { navController.popBackStack() }
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SpotFinderAppBar(
    title: String,
    showBackButton: Boolean,
    onBackButtonClick:() -> Unit,
    modifier: Modifier = Modifier
){
    TopAppBar(
        title = {
            Text(
                text = title,
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = (if (showBackButton){
            {
                IconButton(onClick = onBackButtonClick) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.back_button)
                    )
                }
            }
        }else{
            { Box {}}
        }),
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = modifier,
    )
}

@Composable
fun CategoriesScreen(
    navController:NavController
){
    val viewModel : CategoryModelView = viewModel()
    LaunchedEffect(Unit) {
        viewModel.loadCategories()
    }
    val categories by viewModel.category.collectAsState()
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
        items(categories){ category ->
            CategoryListImageItem(
                category = category,
                modifier = Modifier
                    .clickable {
                        navController.navigate("recommendations/${category.categoryTitle}")
                    }
            )
        }
    }
}

@Composable
fun CategoryListImageItem(
    category: Category,
    modifier: Modifier = Modifier
){
    Card (
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 15.dp
        ),
        shape = RoundedCornerShape(8.dp)
    ){
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(8.dp)
        ){
            Image(
                painter = painterResource(id = category.imageResId),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(id = category.categoryTitle),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.Cursive,
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            )
        }
    }
}

@Composable
fun RecommendationScreen(
    category: String?,
    navController: NavController
){
    val viewModel : RecommendationModelView = viewModel()
    val recommendations = viewModel.recommendation.collectAsState(initial = emptyList())

    LaunchedEffect(category) {
        category?.let { viewModel.loadRecommendations(it) }
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(recommendations.value) { recommendation ->
            Card(modifier = Modifier
                .padding(top =8.dp)
                .clickable { navController.navigate("recommendationDetail/${recommendation.title}") }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                ) {
                    Image(
                        painter = painterResource(id = recommendation.imageResId),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                    Text(
                        text = recommendation.title,
                        //style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(vertical = 8.dp),
                        //fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                    Text(
                        text = recommendation.description,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(horizontal = 16.dp),
                        fontSize = 18.sp,
                        fontFamily = FontFamily.Serif
                    )
                }
            }
        }
    }
}

@Composable
fun RecommendationDetailScreen(
    recommendationTitle: String,
    onBackButtonClick: () -> Unit,
    viewModel: RecommendationDetailModelView = viewModel()
){
    BackHandler {
        onBackButtonClick()
    }

    val recommendationDetail = viewModel.recommendationDetail.collectAsState().value

    // Load the recommendation detail
    LaunchedEffect(recommendationTitle) {
        viewModel.loadRecommendationDetails(recommendationTitle)
    }

    recommendationDetail?.let { detail ->
        Column(
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Image(
                painter = painterResource(id = detail.imageResId),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = detail.description,
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = detail.details,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}


@Composable
fun getAppBarTitle(destination: NavDestination?): String {
    return when (destination?.route) {
        "categories" -> stringResource(R.string.categories)
        "recommendations/{category}" -> stringResource(R.string.recommendations)
        "recommendationDetail/{title}" -> stringResource(id = R.string.details)
        else -> stringResource(R.string.app_name)
    }
}