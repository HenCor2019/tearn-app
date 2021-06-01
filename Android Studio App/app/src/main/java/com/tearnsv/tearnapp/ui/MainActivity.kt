package com.tearnsv.tearnapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.tearnsv.tearnapp.R
import com.tearnsv.tearnapp.data.OnboardingItem

class MainActivity : AppCompatActivity() {

    private lateinit var onboardingItemAdapter: OnboardingItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setOnboardingItems()
    }

    private fun setOnboardingItems(){
        onboardingItemAdapter = OnboardingItemAdapter(
            listOf(
                OnboardingItem(
                    onboardinImage = R.drawable.maestros,
                    title ="Enseñanza profesional",
                    description = "hola 123"
                ),
                OnboardingItem(
                    onboardinImage = R.drawable.libros,
                    title = "Libros para ti",
                    description = "hola 456"
                ),
                OnboardingItem(
                    onboardinImage = R.drawable.novedades,
                    title = "Recomendaciones exclusivas",
                    description = "hola 789"
                )

            )
        )
        val onboardingViewPager = findViewById<ViewPager2>(R.id.onboardingviewpager)
        onboardingViewPager.adapter = onboardingItemAdapter

    }
}