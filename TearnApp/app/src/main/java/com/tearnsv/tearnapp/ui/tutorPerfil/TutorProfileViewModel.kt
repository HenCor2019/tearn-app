package com.tearnsv.tearnapp.ui.tutorPerfil

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tearnsv.tearnapp.TearnApplication
import com.tearnsv.tearnapp.data.Commentaries
import com.tearnsv.tearnapp.data.Commentary
import com.tearnsv.tearnapp.data.Report
import com.tearnsv.tearnapp.data.Tutor
import com.tearnsv.tearnapp.repository.TearnRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class TutorProfileViewModel(private val repository: TearnRepository) : ViewModel() {
  var userCommentary = MutableLiveData<Commentaries>()
  var tutor = MutableLiveData<Tutor>()
  var ID_TUTOR = MutableLiveData("")
  var ID_AUTHOR = MutableLiveData("")
  var loading = MutableLiveData(View.GONE)
  var data = MutableLiveData(View.GONE)
  var puntuationStatus = MutableLiveData(View.VISIBLE)
  var valorationStatus = MutableLiveData(View.GONE)
  var puntuation = MutableLiveData(0)
  var valoration = MutableLiveData("")
  var statusCreateReport = MutableLiveData(false)
  var toastMessage = MutableLiveData("")
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

  init {
    ID_AUTHOR.value = TearnApplication.prefs.getId()
  }

  fun createReport(msg: String, dateTime: String) {
    viewModelScope.launch {
      statusCreateReport.value = false
      var report = Report(ID_AUTHOR.value!!, dateTime, msg, ID_TUTOR.value!!)
      try {
        val response = repository.createReport(report)
        Log.e("error", response.error.toString())
        Log.e("message", response.message)

        if (response.error) throw Exception()
        else {
          statusCreateReport.value = true
          option.value = false
        }
      } catch (e: Exception) {
        Log.e("error", e.toString())
      }
    }

  }

  fun setOption(option: Boolean) {
    this.option.value = option
  }

  fun openValoration() {
    puntuationStatus.value = View.GONE
    valorationStatus.value = View.VISIBLE
    puntuation.value = userCommentary.value?.puntuation ?: 0
    valoration.value = userCommentary.value?.description ?: ""
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
      try {
        if (userCommentary.value != null) {
          val updatedCommentary =
            Commentary(
              id = ID_AUTHOR.value!!,
              description = valoration.value!!,
              puntuation = puntuation.value!!
            )
          repository.updateUserCommentary(updatedCommentary)
          userCommentary.value =
            Commentaries(valoration.value!!, puntuation.value!!, userCommentary.value!!.author)
          closeValoration()
          toastMessage.value = "¡Comentario actualizado! Recarga la pagina para ver cambios"
          return@launch
        }

        val commentary = Commentary(
          author = ID_AUTHOR.value!!,
          description = valoration.value!!,
          puntuation = puntuation.value!!,
          adressedId = ID_TUTOR.value!!
        )
        val response = repository.createCommentary(commentary)
        Log.e("error", response.error.toString())
        Log.e("message", response.message)

        if (response.error) throw Exception(response.message)

        toastMessage.value = "¡Comentario creado! Recarga la pagina para ver cambios"

      } catch (e: Exception) {
        Log.e("error", e.toString())
        toastMessage.value = "No se puede agregar tu comentario"
      } finally {
        closeValoration()
      }
    }
  }

  fun setId(idTutor: String) {
    ID_TUTOR.value = idTutor
    getTutorData()
  }

  private fun getTutorData() {
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