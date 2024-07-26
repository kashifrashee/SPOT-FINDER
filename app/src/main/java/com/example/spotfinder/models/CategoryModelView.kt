package com.example.spotfinder.models

import androidx.lifecycle.ViewModel
import com.example.spotfinder.R
import com.example.spotfinder.data.Category
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CategoryModelView : ViewModel() {
    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    val category : StateFlow<List<Category>> get() = _categories

    fun loadCategories(){
        _categories.value = listOf(
                Category(R.drawable.coffee_shops_image,R.string.coffee_shops,),
                Category(R.drawable.restaurants_image,R.string.restaurants),
                Category(R.drawable.kid_friendly_places_image,R.string.kid_friendly_places),
                Category(R.drawable.parks_image,R.string.parks),
                Category(R.drawable.shopping_centers_image1,R.string.shopping_centers)
            )
    }
}