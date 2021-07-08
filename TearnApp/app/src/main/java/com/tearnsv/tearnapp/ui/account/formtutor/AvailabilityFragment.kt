package com.tearnsv.tearnapp.ui.account.formtutor

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.tearnsv.tearnapp.R
import com.tearnsv.tearnapp.TearnApplication
import com.tearnsv.tearnapp.data.CourseResponse
import com.tearnsv.tearnapp.databinding.FragmentAvailabityBinding
import com.tearnsv.tearnapp.databinding.FragmentPersonalInformationBinding
import com.tearnsv.tearnapp.ui.account.viewmodel.AccountVMFactory
import com.tearnsv.tearnapp.ui.account.viewmodel.AccountViewModel
import java.util.*
import kotlin.concurrent.schedule

class AvailabilityFragment : Fragment() {

  private var _binding: FragmentAvailabityBinding? = null
  private val binding get() = _binding!!


  private val application by lazy { requireActivity().application as TearnApplication }
  private val accountVMFactory: AccountVMFactory by lazy {
    val repository = application.tearnRepository
    AccountVMFactory(repository)
  }

  private val accountViewModel: AccountViewModel by activityViewModels { accountVMFactory }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _binding = FragmentAvailabityBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding.availabilityViewModel = accountViewModel
    binding.lifecycleOwner = viewLifecycleOwner
    setupObservers()
    handleEvents()
  }

  private fun setupObservers() {
    accountViewModel.fetchSubjectsResponse.observe(viewLifecycleOwner) {
      val subjects = it.results.map { subject -> SimpleSubject(subject.id, subject.name) }

      setupMaps(subjects)
      setupDropDown()
    }
  }

  private fun setupMaps(subjects: List<SimpleSubject>) {
    for (subject in subjects)
      accountViewModel.mapOfSubjects.value!![subject.id] = subject.name

  }

  private fun setupDropDown() {
    val subjectAdapter = ArrayAdapter(
      requireActivity(),
      android.R.layout.select_dialog_item,
      accountViewModel.mapOfSubjects.value!!.values.toList()
    )
    val responseTimeAdapter = ArrayAdapter(
      requireActivity(),
      android.R.layout.select_dialog_item,
      listOf("30min", "1h", "1h 30min", "2h")
    )

    binding.userSubject.threshold = 1
    binding.userSubject.setAdapter(subjectAdapter)
    binding.userTime.threshold = 1
    binding.userTime.setAdapter(responseTimeAdapter)
  }

  private fun handleEvents() {
    binding.userSubject.setOnItemClickListener { parent, view, position, id ->
      accountViewModel.mapOfCourses.value!!.clear()
      val courses = accountViewModel.fetchSubjectsResponse.value!!.results[position].courses
      for (course in courses)
        accountViewModel.mapOfCourses.value!![course._id] = course.name
      val courseAdapter = ArrayAdapter(
        requireActivity(),
        android.R.layout.select_dialog_item,
        accountViewModel.mapOfCourses.value!!.values.toList()
      )
      binding.userCourse.threshold = 1
      binding.userCourse.setAdapter(courseAdapter)
    }

    binding.virtualCheckbox.setOnCheckedChangeListener { buttonView, isChecked ->
      if (isChecked) {
        accountViewModel.availabilityList.add("Virtual")
        return@setOnCheckedChangeListener

      }
      accountViewModel.availabilityList =
        accountViewModel.availabilityList.filter { it != "Virtual" }.toMutableList()
    }

    binding.onsiteCheckbox.setOnCheckedChangeListener { buttonView, isChecked ->
      if (isChecked) {
        accountViewModel.availabilityList.add("Presencial")
        return@setOnCheckedChangeListener

      }
      accountViewModel.availabilityList =
        accountViewModel.availabilityList.filter { it != "Presencial" }.toMutableList()
    }

    binding.actionConvertForm.setOnClickListener {
      if (!accountViewModel.verifyDropdownInputs()) {
        Toast.makeText(
          requireContext(),
          "Selecciona una materia y curso",
          Toast.LENGTH_SHORT
        ).show()
        return@setOnClickListener
      }

      if(accountViewModel.verifyAvailability()){
        Toast.makeText(
          requireContext(),
          "Selecciona una disponibilidad",
          Toast.LENGTH_SHORT
        ).show()
        return@setOnClickListener

      }

      val idSubject = getKeyValue(accountViewModel.mapOfSubjects.value!!, accountViewModel.subject.value!!)
      val idCourse = getKeyValue(accountViewModel.mapOfCourses.value!!, accountViewModel.course.value!!)

      accountViewModel.idCourse.value = mutableListOf(idCourse)
      accountViewModel.idSubject.value = mutableListOf(idSubject)

      val dialog = DialogConfirmFragment()
      dialog.show(requireActivity().supportFragmentManager, "ConvertToTutor")

    }
  }

  private fun getKeyValue(hashMap: MutableMap<String, String>, value: String) =
    hashMap.filterValues { it == value }.keys.first()

  data class SimpleSubject(val id: String, val name: String)

}