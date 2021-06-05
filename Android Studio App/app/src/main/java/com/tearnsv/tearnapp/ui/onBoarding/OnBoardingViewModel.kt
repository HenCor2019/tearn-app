package com.tearnsv.tearnapp.ui.onBoarding

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tearnsv.tearnapp.R

class OnBoardingViewModel : ViewModel(){
    var position = MutableLiveData(0)
    var imagesOnBardiing =
        listOf(R.drawable.teachers,R.drawable.books,R.drawable.news_recommendations)

    fun onButtonChangeView(){
        position.value = position.value?.plus(1)
    }

    fun onClickPostions(value: Int){
        position.value = value
    }
}