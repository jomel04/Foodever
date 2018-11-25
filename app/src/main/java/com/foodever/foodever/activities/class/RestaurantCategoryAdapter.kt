package com.foodever.foodever.activities.`class`

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.foodever.foodever.R
import kotlinx.android.synthetic.main.category_row.view.*

class RestaurantCategoryAdapter(val restaurantCategoryData: RestaurantCategoryData): RecyclerView.Adapter<RestaurantViewHolder>() {

    override fun getItemCount(): Int {
        return restaurantCategoryData.restaurants.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): RestaurantViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val callCategoryRow = layoutInflater.inflate(R.layout.category_row, parent, false)
        return RestaurantViewHolder(callCategoryRow)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val restaurants = restaurantCategoryData.restaurants.get(position)
        holder?.view?.tvName?.text = restaurants.name
        holder?.view?.tvAddress?.text = "${restaurants.branch.address}, ${restaurants.branch.city}"
        holder?.view?.rbRate?.rating = restaurants.branch.rate
        holder?.view?.tvReviews?.text = "${restaurants.branch.rate} - stars ${restaurants.branch.comment} - comments"

    }
}

data class RestaurantViewHolder(val view: View): RecyclerView.ViewHolder(view)