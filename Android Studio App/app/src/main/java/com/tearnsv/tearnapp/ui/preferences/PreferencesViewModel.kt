package com.tearnsv.tearnapp.ui.preferences

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tearnsv.tearnapp.data.CategoriesResponse
import com.tearnsv.tearnapp.data.Category
import com.tearnsv.tearnapp.data.Preference
import com.tearnsv.tearnapp.repository.TearnRepository
import kotlinx.coroutines.launch

class PreferencesViewModel(private val repository: TearnRepository) : ViewModel() {

    var categories = MutableLiveData<CategoriesResponse>()
    var loading = MutableLiveData(View.GONE)
    var preferences = mutableListOf<Preference>()
    var preferencesLiveData = MutableLiveData<MutableList<Preference>>(mutableListOf())

    init {
        viewModelScope.launch {
            loading.value = View.VISIBLE
            try {
                categories.value = repository.findAll()
            } catch (e: Exception) {
                Log.e("error", e.toString())
            } finally {
                loading.value = View.GONE
            }
        }
    }

    fun addOrRemovePreferences(category: Category): Boolean {

        val preference = Preference(category.id, category.name)
        var exists = false

        preferencesLiveData.value?.forEach {
            if (it.id == preference.id) exists = true
        }

        if (preferencesLiveData.value!!.size >= 3 && !exists)
            return false

        if (exists) preferences.remove(preference)
        else preferences.add(preference)

        preferencesLiveData.value = preferences
        return true
    }
}