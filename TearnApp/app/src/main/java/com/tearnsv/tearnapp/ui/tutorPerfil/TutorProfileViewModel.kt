package com.tearnsv.tearnapp.ui.tutorPerfil

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tearnsv.tearnapp.data.Commentary
import com.tearnsv.tearnapp.data.Report
import com.tearnsv.tearnapp.data.Tutor
import com.tearnsv.tearnapp.repository.TearnRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class TutorProfileViewModel(private val repository: TearnRepository) : ViewModel() {
    var tutor = MutableLiveData<Tutor>()
    var ID_TUTOR = MutableLiveData("")
    var ID_AUTHOR = MutableLiveData("60c2ea9d171ce20439e6c108")
    var loading = MutableLiveData(View.GONE)
    var data = MutableLiveData(View.GONE)
    var puntuationStatus = MutableLiveData(View.VISIBLE)
    var valorationStatus = MutableLiveData(View.GONE)
    var puntuation = MutableLiveData(0)
    var valoration = MutableLiveData("")
    var statusCreateCommentary = MutableLiveData(false)
    var statusCreateReport = MutableLiveData(false)
    var option = MutableLiveData(false)
    val reports = MutableLiveData(
        listOf(
            "Incita la violencia",
            "Bullying o acoso",
            "Desnudos o actividad sexual",
            "Lenguaje Inapropiado",
            "Contenido Explícito",
            "Spam",
            "Falsificación de identidad"
        )
    )

    fun createReport(msg : String, dateTime : String){
        viewModelScope.launch {
            statusCreateReport.value = false
            var report = Report(ID_AUTHOR.value!!,dateTime, msg, ID_TUTOR.value!!)
            try {
                var response = repository.createReport(report)
                Log.e("error", response.error.toString())
                Log.e("message", response.message)

                if (response.error) throw Exception()
                else{
                    statusCreateReport.value = true
                    option.value = false
                }
            }catch (e: Exception){
                Log.e("error", e.toString())
            }
        }

    }

    fun setOption(option: Boolean){
        this.option.value = option
    }

    fun openValoration() {
        puntuationStatus.value = View.GONE
        valorationStatus.value = View.VISIBLE
    }

    fun closeValoration() {
        puntuationStatus.value = View.VISIBLE
        valorationStatus.value = View.GONE
    }

    fun addPuntuation(value: Int) {
        puntuation.value = value
    }

    fun addValoration() {
        viewModelScope.launch {
            statusCreateCommentary.value = false
            try {
                val commentary = Commentary(
                    ID_AUTHOR.value!!,
                    valoration.value!!,
                    puntuation.value!!,
                    ID_TUTOR.value!!
                )
                val response = repository.createCommentary(commentary)
                Log.e("error", response.error.toString())
                Log.e("message", response.message)

                if (response.error) throw Exception(response.message)
                else statusCreateCommentary.value = true
            } catch (e: Exception) {
                Log.e("error", e.toString())
            }
        }
    }

    fun setId(idTutor: String) {
        ID_TUTOR.value = idTutor
        getTutorData()
    }

    private fun getTutorData(){
        viewModelScope.launch {
            data.value = View.GONE
            try {
                tutor.value = repository.getTutor(ID_TUTOR.value!!)
                Log.e("tutorReciview", tutor.value.toString())
            } catch (e: Exception) {
                Log.e("error", e.toString())
            } finally {
                loading.value = View.GONE
                data.value = View.VISIBLE
            }
        }
    }
}