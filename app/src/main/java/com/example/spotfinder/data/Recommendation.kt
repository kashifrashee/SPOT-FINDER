package com.example.spotfinder.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Recommendation(
    @DrawableRes val imageResId : Int,
    val title: String,
    val description: String
)

data class Category(
    @DrawableRes val imageResId: Int,
    @StringRes val categoryTitle: Int,
)

data class RecommendationDetail(
    @DrawableRes val imageResId : Int,
    val title: String,
    val description: String,
    val details: String
)

data class selectedCategory(
    val name: String,
    val recommendations: List<RecommendationDetail>
)