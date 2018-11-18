package com.foodever.foodever.activities

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.foodever.foodever.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                message.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                message.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_map -> {
                message.setText(R.string.title_map)
                return@OnNavigationItemSelectedListener true
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

        val pager =
    }
}
