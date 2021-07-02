package com.tearnsv.tearnapp.ui.tutorPerfil

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tearnsv.tearnapp.data.FavTutorPetition
import com.tearnsv.tearnapp.data.entity.User
import com.tearnsv.tearnapp.data.entity.UserWithFavTutor
import com.tearnsv.tearnapp.repository.TearnRepository
import kotlinx.coroutines.launch

class TutorFavoriteViewModel(private var repository: TearnRepository): ViewModel() {
    var user = MutableLiveData<User>()
    var favTutors = repository.getFavTutors()
    val ID = MutableLiveData("60b0a724b4099b13d593ce39")


    //Va a cambiar
    init {
        viewModelScope.launch {
            try {
                user.value = repository.getUser(ID.value!!)
                if(user.value!!.error) throw Exception()
            }catch (e: Exception){
                Log.e("error", e.toString())
            }
        }
    }

    fun addOrRemoveFav(idTutor:String){
        viewModelScope.launch {
            try {
                var favTutor = FavTutorPetition(ID.value!!,idTutor)
                var response = repository.addOrRemoveFavTutor(favTutor)
                Log.e("response.error", response.error.toString())
                Log.e("response.msg",response.message)
                if (response.error) throw Exception()
            }catch (e: Exception) {
                Log.e("error", e.toString())
            }
        }
    }
}