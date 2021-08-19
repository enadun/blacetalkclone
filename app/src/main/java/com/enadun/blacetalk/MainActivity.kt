package com.enadun.blacetalk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import com.enadun.blacetalk.fragments.EventFragment
import com.enadun.blacetalk.fragments.HomeFragment

class MainActivity : AppCompatActivity() {
    private lateinit var homeButton: AppCompatButton
    private lateinit var eventButton: AppCompatButton
    private var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        homeButton = findViewById(R.id.home_button)
        homeButton.setOnClickListener {
            index = 0
            buttonPressed()
        }
        eventButton = findViewById(R.id.event_button)
        eventButton.setOnClickListener {
            index = 1
            buttonPressed()
        }
        buttonPressed()
    }

    private fun buttonPressed() {
        homeButton.isSelected = false
        eventButton.isSelected = false
        val fragment = if (index == 0) {
            homeButton.isSelected = true
            HomeFragment()
        } else {
            eventButton.isSelected = true
            EventFragment()
        }
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()
    }

}