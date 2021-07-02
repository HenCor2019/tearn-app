package com.tearnsv.tearnapp.ui.account.information

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.tearnsv.tearnapp.R
import com.tearnsv.tearnapp.TearnApplication
import com.tearnsv.tearnapp.data.NewTutor
import com.tearnsv.tearnapp.data.Tutor
import com.tearnsv.tearnapp.databinding.FragmentInformationBinding
import com.tearnsv.tearnapp.ui.account.adapter.SubjectRVAdapter
import com.tearnsv.tearnapp.ui.account.formtutor.AvailabilityFragment
import com.tearnsv.tearnapp.ui.account.formtutor.DialogConfirmFragment
import com.tearnsv.tearnapp.ui.account.viewmodel.AccountVMFactory
import com.tearnsv.tearnapp.ui.account.viewmodel.AccountViewModel
import com.tearnsv.tearnapp.ui.home.NavControllerActivity
import java.util.*

class InformationFragment : Fragment(), SubjectRVAdapter.ItemClickListener {


  private var _binding: FragmentInformationBinding? = null
  private val binding get() = _binding!!

  private var MAP_OF_SUBJECT: MutableMap<String, String> = mutableMapOf()
  private var MAP_OF_COURSES: MutableMap<String, String> = mutableMapOf()
  private lateinit var rvSubjectsAdapter: SubjectRVAdapter
  private lateinit var rvCoursesAdapter: SubjectRVAdapter
  private lateinit var tutorSubjects: MutableList<String>
  private lateinit var tutorCourses: MutableList<String>


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
    _binding = FragmentInformationBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding.informationViewModel = accountViewModel
    binding.lifecycleOwner = viewLifecycleOwner
    setupRecyclerViews()
    setupObservers()
    handleEvents()
  }

  private fun handleEvents() {
    binding.editSwitch.setOnCheckedChangeListener { _, isChecked ->
      if (isChecked) {
        makeInputAccessible()
        return@setOnCheckedChangeListener
      }
      makeInputNotAccessible()
    }

    binding.userSubject.setOnItemClickListener { _, _, position, _ ->
      if (!binding.editSwitch.isChecked)
        return@setOnItemClickListener

      val selectedSubject = MAP_OF_SUBJECT.values.toList()[position]
      if (tutorSubjects.contains(selectedSubject))
        return@setOnItemClickListener
      tutorSubjects.add(selectedSubject)
      rvSubjectsAdapter.setData(tutorSubjects)
    }

    binding.spanishCheckbox.setOnCheckedChangeListener { _, isChecked ->
      if (isChecked) {
        accountViewModel.languageInformation.add("Español")
        return@setOnCheckedChangeListener
      }

      accountViewModel.languageInformation =
        accountViewModel.languageInformation.filter { it != "Español" }.toMutableList()
    }
    binding.englishCheckbox.setOnCheckedChangeListener { _, isChecked ->
      if (isChecked) {
        accountViewModel.languageInformation.add("Ingles")
        return@setOnCheckedChangeListener
      }

      accountViewModel.languageInformation =
        accountViewModel.languageInformation.filter { it != "Ingles" }.toMutableList()

    }
    binding.virtualCheckbox.setOnCheckedChangeListener { _, isChecked ->

      if (isChecked) {
        accountViewModel.availabilityInformation.add("Virtual")
        return@setOnCheckedChangeListener
      }

      accountViewModel.availabilityInformation =
        accountViewModel.availabilityInformation.filter { it != "Virtual" }.toMutableList()

    }
    binding.onsiteCheckbox.setOnCheckedChangeListener { _, isChecked ->
      if (isChecked) {
        accountViewModel.availabilityInformation.add("Presencial")
        return@setOnCheckedChangeListener
      }

      accountViewModel.availabilityInformation =
        accountViewModel.availabilityInformation.filter { it != "Presencial" }.toMutableList()
    }

    binding.userCourse.setOnItemClickListener { _, _, position, _ ->
      if (!binding.editSwitch.isChecked)
        return@setOnItemClickListener

      if (!accountViewModel.areValidUpdated()) {
        Toast.makeText(requireContext(), "Modificaciones invalidas", Toast.LENGTH_SHORT).show()
        return@setOnItemClickListener
      }

      val selectedCourse = MAP_OF_COURSES.values.toList()[position]
      if (tutorSubjects.contains(selectedCourse))
        return@setOnItemClickListener
      tutorCourses.add(selectedCourse)
      rvCoursesAdapter.setData(tutorCourses)
    }

    binding.actionUpdate.setOnClickListener {
      val coursesId = MAP_OF_COURSES.filterValues { value ->
        tutorCourses.contains(value)
      }
      val subjects = MAP_OF_SUBJECT.filterValues { value ->
        tutorSubjects.contains(value)
      }
      val fullName =
        "${accountViewModel.namesInformation.value} ${accountViewModel.lastnamesInformation.value} "

      val updateTutor =
        NewTutor(
          accountViewModel.idUser.value!!,
          coursesId.keys.toList(),
          subjects.keys.toList(),
          fullName,
          accountViewModel.dobInformation.value!!,
          accountViewModel.descriptionInformation.value!!,
          accountViewModel.responseInformation.value!!,
          accountViewModel.availabilityInformation.map {
            it.toLowerCase(Locale.ROOT).capitalize(Locale.ROOT)
          }.toSet().toList(),
          accountViewModel.languageInformation.map {
            it.toLowerCase(Locale.ROOT).capitalize(Locale.ROOT)
          }.toSet().toList()
        )

      accountViewModel.updateTutor(updateTutor)
      if (accountViewModel.error) {
        Toast.makeText(requireContext(), "No se logro actualizar", Toast.LENGTH_LONG).show()
        return@setOnClickListener

      }

      Toast.makeText(requireContext(), "Datos actualizados", Toast.LENGTH_SHORT).show()
      val intent = Intent(requireContext(), NavControllerActivity::class.java)
      requireActivity().startActivity(intent)
      requireActivity().finish()
    }
  }

  private fun makeInputNotAccessible() {
    binding.inputTextNames.inputType = View.AUTOFILL_TYPE_NONE
    binding.inputTextDob.inputType = View.AUTOFILL_TYPE_NONE
    binding.inputTextLastnames.inputType = View.AUTOFILL_TYPE_NONE
    binding.inputTextAbout.inputType = View.AUTOFILL_TYPE_NONE
    binding.inputResponseTime.inputType = View.AUTOFILL_TYPE_NONE
    binding.actionUpdate.visibility = View.GONE

  }

  private fun makeInputAccessible() {
    binding.inputTextNames.inputType = View.AUTOFILL_TYPE_TEXT
    binding.inputTextDob.inputType = View.AUTOFILL_TYPE_DATE
    binding.inputTextLastnames.inputType = View.AUTOFILL_TYPE_TEXT
    binding.inputTextAbout.inputType = View.AUTOFILL_TYPE_TEXT
    binding.inputResponseTime.inputType = View.AUTOFILL_TYPE_TEXT
    binding.actionUpdate.visibility = View.VISIBLE
  }

  private fun setupRecyclerViews() {
    rvSubjectsAdapter = SubjectRVAdapter(this)
    rvCoursesAdapter = SubjectRVAdapter(this)
    binding.subjectsNameRecyclerView.apply {
      layoutManager = GridLayoutManager(requireContext(), 2)
      adapter = rvSubjectsAdapter
    }
    binding.coursesNameRecyclerView.apply {
      layoutManager = GridLayoutManager(requireContext(), 2)
      adapter = rvCoursesAdapter
    }
  }

  private fun setupObservers() {
    accountViewModel.fetchSubjectsResponse.observe(viewLifecycleOwner) {
      val subjects = it.results.map { AvailabilityFragment.SimpleSubject(it.id, it.name) }

      val courses = it.results
        .map { it.courses.map { SimpleCourse(it._id, it.name) } }
        .flatten()

      setupMaps(subjects, courses)
      setupDropDown()
    }

    accountViewModel.fetchTutorInformation.observe(viewLifecycleOwner) { tutor ->
      binding.progressCircular.visibility = View.VISIBLE
      if (tutor.error || accountViewModel.error) {
        Toast.makeText(requireContext(), "Algo malo sucedio", Toast.LENGTH_SHORT).show()
        return@observe
      }
      setupTutorInformation(tutor)
      tutorSubjects = MAP_OF_SUBJECT.filterKeys { key ->
        tutor.subjects?.contains(key) ?: false
      }.values.toMutableList()
      tutorCourses = MAP_OF_COURSES.filterKeys { key ->
        tutor.courses?.contains(key) ?: false
      }.values.toMutableList()

      rvSubjectsAdapter.setData(tutorSubjects)
      rvCoursesAdapter.setData(tutorCourses)
    }
  }

  private fun setupTutorInformation(tutor: Tutor?) {
    binding.progressCircular.visibility = View.GONE
    binding.scrollInformation.visibility = View.VISIBLE

    val fullname = tutor?.fullName?.split(" ")
    accountViewModel.namesInformation.value = fullname?.slice(0..1)?.joinToString(" ")
    accountViewModel.lastnamesInformation.value =
      fullname?.slice(2 until fullname.size)?.joinToString(" ")
    accountViewModel.descriptionInformation.value = tutor?.description
    accountViewModel.dobInformation.value = tutor?.dod
    accountViewModel.responseInformation.value = tutor?.responseTime
    accountViewModel.languageInformation = tutor!!.languages!!.toMutableList()
    accountViewModel.availabilityInformation = tutor.availability!!.toMutableList()

    binding.spanishCheckbox.isChecked =
      tutor.languages?.map { it.toLowerCase(Locale.ROOT) }?.contains("español") ?: false

    binding.englishCheckbox.isChecked =
      tutor.languages?.map { it.toLowerCase(Locale.ROOT) }?.contains("ingles") ?: false

    binding.virtualCheckbox.isChecked =
      tutor.availability?.map { it.toLowerCase(Locale.ROOT) }?.contains("virtual") ?: false

    binding.onsiteCheckbox.isChecked =
      tutor.availability?.map { it.toLowerCase(Locale.ROOT) }?.contains("presencial") ?: false

  }


  private fun setupMaps(
    subjects: List<AvailabilityFragment.SimpleSubject>,
    courses: List<SimpleCourse>
  ) {
    for (subject in subjects)
      MAP_OF_SUBJECT[subject.id] = subject.name
    for (course in courses)
      MAP_OF_COURSES[course.id] = course.name

  }

  private fun setupDropDown() {
    val subjectAdapter = ArrayAdapter(
      requireActivity(), android.R.layout.select_dialog_item, MAP_OF_SUBJECT.values.toList()
    )

    val courseAdapter = ArrayAdapter(
      requireActivity(), android.R.layout.select_dialog_item, MAP_OF_COURSES.values.toList()
    )

    binding.userSubject.threshold = 1
    binding.userSubject.setAdapter(subjectAdapter)
    binding.userCourse.threshold = 1
    binding.userCourse.setAdapter(courseAdapter)
  }

  override fun onIconDeleteListener(name: String) {
    tutorSubjects = tutorSubjects.filter { it != name }.toMutableList()
    tutorCourses = tutorCourses.filter { it != name }.toMutableList()
    rvSubjectsAdapter.setData(tutorSubjects)
    rvCoursesAdapter.setData(tutorCourses)
  }

  data class SimpleCourse(val id: String, val name: String)

}