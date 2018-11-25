package com.foodever.foodever.activities

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.foodever.foodever.R
import com.foodever.foodever.activities.`class`.RestaurantCategoryAdapter
import com.foodever.foodever.activities.`class`.RestaurantCategoryData
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
//                message.setText(R.string.title_home)
//                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
//                message.setText(R.string.title_notifications)
//                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_map -> {
//                message.setText(R.string.title_map)
//                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_me -> {
                val intent = Intent(this@MainActivity, UserActivity::class.java)
                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("LOGIN", "CURRENT USER: ${FirebaseAuth.getInstance().currentUser?.email}")

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)


//        svSearch.setOnQueryChangeListener {
        //get suggestions based on newQuery

        //pass them on to the search view

//            svSearch.swapSuggestions(newSuggestions)
//        }

        /*Listen to hamburger button clicks:*/
//        svSearch.setOnLeftMenuClickListener {
//
//        }

        /* quickly connect your NavigationDrawer to the hamburger button:*/
//        svSearch.attachNavigationDrawerToMenuButton(mDrawerLayout)

        /* Listen for item selections */
        svSearch.setOnMenuItemClickListener {

        }

//        val pager =
        rvRestaurantCategory.layoutManager = LinearLayoutManager(this)
//        rvRestaurantCategory.adapter = RestaurantCategoryAdapter()
        fetchRestaurantData()
    }

    private fun fetchRestaurantData() {
        val url = "http://192.168.1.2:81/Laravel/foodever/public/restaurant"

        val request = Request.Builder().url(url).build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response?.body()?.string()
                println(body)

                val gson = GsonBuilder().create()
                val restaurantData = gson.fromJson(body, RestaurantCategoryData::class.java)
                runOnUiThread {
                    rvRestaurantCategory.adapter = RestaurantCategoryAdapter(restaurantData)
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                println("Failed request")
            }
        })

    }
}

