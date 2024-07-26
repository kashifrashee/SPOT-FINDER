package com.example.spotfinder.models

import androidx.lifecycle.ViewModel
import com.example.spotfinder.R
import com.example.spotfinder.data.Recommendation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RecommendationModelView : ViewModel() {
    private val _recommendations = MutableStateFlow<List<Recommendation>>(emptyList())
    val recommendation : StateFlow<List<Recommendation>> get() = _recommendations

    fun loadRecommendations(category: String){
        _recommendations.value = when(category){
            R.string.coffee_shops.toString() -> listOf(
                Recommendation(R.drawable.gloria_jeans, "Gloria Jeans", "Amazing pastries."),
                Recommendation(
                    R.drawable.new_york_coffee,
                    "New York Coffee",
                    "Perfect for meetings."
                ),
                Recommendation(
                    R.drawable.floc___for_the_love_of_coffee_jpg,
                    "FLOC - For The Love Of Coffee",
                    "Friendly staff and delicious drinks."
                )
            )

            R.string.restaurants.toString() -> listOf(
                Recommendation(
                    R.drawable.kolachi_restaurant,
                    "Kolachi Restaurant",
                    "I believe Kolachi has get itself as one of the must to visit and dine place in Amazing food"
                ),
                Recommendation(
                    R.drawable.bbq_tonight, "BBQ Tonight",
                    "This was my first visit to BBQ tonight but I understand it's a long time Good"
                ),
                Recommendation(
                    R.drawable.chef_s_table,
                    "Chef's Table",
                    "my experience was amazing. The food is delicious and the atmosphere was Great Place."
                ),
                Recommendation(
                    R.drawable.lalqila,
                    "LalQila",
                    "BBQ was delicious. Waiters service was great. Seating was very good. I will Friends visit to Lal Qila"
                )
            )

            R.string.kid_friendly_places.toString() -> listOf(
                Recommendation(
                    R.drawable.paf_museum,
                    "PAF Museum",
                    "Fun and educational activities."
                ),
                Recommendation(
                    R.drawable.mazar_e_quaid,
                    "Mazar-E-Quaid",
                    "Safe and enjoyable play area."
                ),
                Recommendation(
                    R.drawable.masjid_tooba, "Tooba Mosque",
                    "Look into the history and how it was built, very good collaboration with the Turks and the Pakistanis"
                ),
                Recommendation(
                    R.drawable.sea_view,
                    "Clifton Beach",
                    "Clifton beach Karachi is one of the beautiful place in Karachi."
                )
            )

            R.string.parks.toString() -> listOf(
                Recommendation(
                    R.drawable.bin_qasim_park,
                    "Bin Qasim Park",
                    "Bin Qasim Park is located in Clifton Karachi. Bin Qasim park is biggest park of Asia. Its area are too long, must visit.."
                ),
                Recommendation(
                    R.drawable.zamzama_park,
                    "Zamzama Park",
                    "The central area is dedicated to child-friendly attractions such as a skating rink for roller-blades, rides, jumping..."
                ),
                Recommendation(
                    R.drawable.bin_qasim_park_clifton,
                    "Bin Qasim Park Clifton",
                    "Serene lakes and bird watching."
                ),
                Recommendation(R.drawable.rahat_park, "Rahat Park", "Well-maintained playgrounds."),
            )

            R.string.shopping_centers.toString() -> listOf(
                Recommendation(
                    R.drawable.luckyone_mall,
                    "LuckyOne Mall",
                    "Wide variety of stores."
                ),
                Recommendation(
                    R.drawable.dolmen_mall_clifton,
                    "Dolmen Mall Clifton",
                    "Great food court and entertainment."
                ),
                Recommendation(
                    R.drawable.atrium_mall_and_cinema,
                    "Atrium Mall and Cinema",
                    "Luxury brands and boutiques."
                ),
                Recommendation(
                    R.drawable.millennium_mall,
                    "Millennium Mall",
                    "Family-friendly shopping experience."
                ),
                Recommendation(
                    R.drawable.port_grand_pakistan,
                    "Port Grand Pakistan",
                    "Excellent deals and discounts."
                )
            )

            else -> emptyList()
        }
    }
}