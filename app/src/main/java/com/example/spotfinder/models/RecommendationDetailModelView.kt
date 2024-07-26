package com.example.spotfinder.models

import androidx.lifecycle.ViewModel
import com.example.spotfinder.R
import com.example.spotfinder.data.RecommendationDetail
import com.example.spotfinder.data.selectedCategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RecommendationDetailModelView : ViewModel() {
    private val _recommendationDetails = MutableStateFlow<RecommendationDetail?>(null)
    val recommendationDetail : StateFlow<RecommendationDetail?> get() = _recommendationDetails

    private val recommendationDetailsList = listOf(
        selectedCategory(
            name = "Coffee Shops",
            recommendations = listOf(
                RecommendationDetail(
                    title = "Gloria Jeans",
                    description = "Amazing pastries.",
                    imageResId = R.drawable.gloria_jeans,
                    details = "Offers a variety of coffee and delightful pastries."
                ),
                RecommendationDetail(
                    title = "New York Coffee",
                    description = "Perfect for meetings.",
                    imageResId = R.drawable.new_york_coffee,
                    details = "A quiet place, ideal for business meetings and casual gatherings."
                ),
                RecommendationDetail(
                    title = "FLOC - For The Love Of Coffee",
                    description = "Friendly staff and delicious drinks.",
                    imageResId = R.drawable.floc___for_the_love_of_coffee_jpg,
                    details = "Popular for its friendly service and great variety of coffee."
                )
            )
        ),
        selectedCategory(
            name = "Restaurants",
            recommendations = listOf(
                RecommendationDetail(
                    title = "Kolachi Restaurant",
                    description = "I believe Kolachi has get itself as one of the must to visit and dine place in Amazing food",
                    imageResId = R.drawable.kolachi_restaurant,
                    details = "Located at the seaside, offering a variety of delicious dishes."
                ),
                RecommendationDetail(
                    title = "BBQ Tonight",
                    description = "This was my first visit to BBQ tonight but I understand it's a long time Good",
                    imageResId = R.drawable.bbq_tonight,
                    details = "Famous for its BBQ dishes and great service."
                ),
                RecommendationDetail(
                    title = "Chef's Table",
                    description = "my experience was amazing. The food is delicious and the atmosphere was Great Place.",
                    imageResId = R.drawable.chef_s_table,
                    details = "Offers an exquisite dining experience with a variety of cuisines."
                ),
                RecommendationDetail(
                    title = "LalQila",
                    description = "BBQ was delicious. Waiters service was great. Seating was very good. I will Friends visit to Lal Qila",
                    imageResId = R.drawable.lalqila,
                    details = "A themed restaurant offering a wide variety of traditional dishes."
                )
            )
        ),
        selectedCategory(
            name = "Kid-friendly Places",
            recommendations = listOf(
                RecommendationDetail(
                    title = "PAF Museum",
                    description = "Fun and educational activities.",
                    imageResId = R.drawable.paf_museum,
                    details = "Features aircraft displays and fun activities for kids."
                ),
                RecommendationDetail(
                    title = "Mazar-E-Quaid",
                    description = "Safe and enjoyable play area.",
                    imageResId = R.drawable.mazar_e_quaid,
                    details = "Historical landmark with ample space for children to play."
                ),
                RecommendationDetail(
                    title = "Tooba Mosque",
                    description = "Look into the history and how it was built, very good collaboration with the Turks and the Pakistanis",
                    imageResId = R.drawable.masjid_tooba,
                    details = "An architectural marvel with a rich history."
                ),
                RecommendationDetail(
                    title = "Clifton Beach",
                    description = "Clifton beach Karachi is one of the beautiful place in Karachi.",
                    imageResId = R.drawable.sea_view,
                    details = "A popular spot for families to enjoy the seaside."
                )
            )
        ),
        selectedCategory(
            name = "Parks",
            recommendations = listOf(
                RecommendationDetail(
                    title = "Bin Qasim Park",
                    description = "Bin Qasim Park is located in Clifton Karachi. Bin Qasim park is biggest park of Asia. Its area are too long, must visit..",
                    imageResId = R.drawable.bin_qasim_park,
                    details = "A vast park with beautiful gardens and walking paths."
                ),
                RecommendationDetail(
                    title = "Zamzama Park",
                    description = "The central area is dedicated to child-friendly attractions such as a skating rink for roller-blades, rides, jumping...",
                    imageResId = R.drawable.zamzama_park,
                    details = "A well-maintained park with various attractions for kids."
                ),
                RecommendationDetail(
                    title = "Bin Qasim Park Clifton",
                    description = "Serene lakes and bird watching.",
                    imageResId = R.drawable.bin_qasim_park_clifton,
                    details = "A beautiful park known for its serene lakes and bird-watching opportunities."
                ),
                RecommendationDetail(
                    title = "Rahat Park",
                    description = "Well-maintained playgrounds.",
                    imageResId = R.drawable.rahat_park,
                    details = "Features well-maintained playgrounds and picnic areas."
                ),

                )
        ),
        selectedCategory(
            name = "Shopping Centers",
            recommendations = listOf(
                RecommendationDetail(
                    title = "LuckyOne Mall",
                    description = "Wide variety of stores.",
                    imageResId = R.drawable.luckyone_mall,
                    details = "One of the largest malls with a wide variety of stores and dining options."
                ),
                RecommendationDetail(
                    title = "Dolmen Mall Clifton",
                    description = "Great food court and entertainment.",
                    imageResId = R.drawable.dolmen_mall_clifton,
                    details = "Features a great food court and various entertainment options."
                ),
                RecommendationDetail(
                    title = "Atrium Mall and Cinema",
                    description = "Luxury brands and boutiques.",
                    imageResId = R.drawable.atrium_mall_and_cinema,
                    details = "Known for its luxury brands and an in-house cinema."
                ),
                RecommendationDetail(
                    title = "Millennium Mall",
                    description = "Family-friendly shopping experience.",
                    imageResId = R.drawable.millennium_mall,
                    details = "Offers a family-friendly shopping experience with a variety of stores."
                ),
                RecommendationDetail(
                    title = "Port Grand Pakistan",
                    description = "Excellent deals and discounts.",
                    imageResId = R.drawable.port_grand_pakistan,
                    details = "A unique shopping and entertainment complex with great deals."
                )
            )
        )
    )

    fun loadRecommendationDetails(recommendationTitle: String){
        for (selectedCategory in recommendationDetailsList){
            for (recommendation in selectedCategory.recommendations){
                if (recommendation.title == recommendationTitle){
                    _recommendationDetails.value = recommendation
                    return
                }
            }
        }
        _recommendationDetails.value = null
    }
}