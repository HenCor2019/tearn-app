package com.tearnsv.tearnapp.ui.course.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.tearnsv.tearnapp.repository.TearnRepository
import kotlinx.coroutines.Dispatchers

class CourseViewModel(private val repository: TearnRepository) : ViewModel() {
    var idCourse = MutableLiveData("")

    val fetchCourseResponse = liveData(Dispatchers.IO) {
        try {
            emit(repository.getOneCourse(idCourse.value!!))
        } catch (error: Exception) {
            Log.e("COURSE_ERROR", error.message.toString())
        }
    }

    fun setId(id: String) {
        idCourse.value = id
    }
}