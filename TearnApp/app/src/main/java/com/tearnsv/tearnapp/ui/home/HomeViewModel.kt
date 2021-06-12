package com.tearnsv.tearnapp.ui.home

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tearnsv.tearnapp.data.Recommendations
import com.tearnsv.tearnapp.repository.TearnRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: TearnRepository): ViewModel() {

    var recommendations = MutableLiveData<Recommendations>()
    var loading = MutableLiveData(View.GONE)
    val ID = MutableLiveData("60b0a724b4099b13d593ce39")

    init {
        loading.value = View.VISIBLE

        viewModelScope.launch {
            try {
                recommendations.value = repository.findAllRecommendations(ID.value!!)
            }catch (e: Exception){
                Log.e("error", e.toString())
            }finally {
                loading.value = View.GONE
            }
        }
    }
}