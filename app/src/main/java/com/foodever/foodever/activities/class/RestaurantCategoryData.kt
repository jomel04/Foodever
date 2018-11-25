package com.foodever.foodever.activities.`class`

data class RestaurantCategoryData(val restaurants: List<Restaurant>)

data class Restaurant(val restaurantId: Int, val name: String, val branch: Branch)

data class Branch(val branchId: Int, val image: String, val address: String, val city: String, val rate: Float, val comment: String)
