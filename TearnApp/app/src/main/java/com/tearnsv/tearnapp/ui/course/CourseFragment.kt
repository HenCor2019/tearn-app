package com.tearnsv.tearnapp.ui.course

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tearnsv.tearnapp.R
import com.tearnsv.tearnapp.TearnApplication
import com.tearnsv.tearnapp.databinding.FragmentCourseBinding
import com.tearnsv.tearnapp.ui.course.adapter.CourseRecyclerViewAdapter
import com.tearnsv.tearnapp.ui.course.viewmodel.CourseVMFactory
import com.tearnsv.tearnapp.ui.course.viewmodel.CourseViewModel

class CourseFragment : Fragment() {

    private lateinit var idCourse: String

    private var _binding: FragmentCourseBinding? = null
    private val binding get() = _binding!!

    private val application by lazy { requireActivity().application as TearnApplication }

    private val courseVMFactory: CourseVMFactory by lazy {
        val repository = application.tearnRepository
        CourseVMFactory(repository)
    }

    private val courseViewModel: CourseViewModel by viewModels { courseVMFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            idCourse = it.getString(COURSE_ID).toString()
        }
        courseViewModel.setId(idCourse)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCourseBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.courseViewModel = courseViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val rvAdapter = CourseRecyclerViewAdapter()
        view.findViewById<RecyclerView>(R.id.recycler_view_principal_tutor).apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = rvAdapter
        }

        courseViewModel.fetchCourseResponse.observe(viewLifecycleOwner) {

            Glide.with(view).load(it.imgUrl).placeholder(R.drawable.ic_launcher_foreground)
                .into(binding.imageCourse)

            if (it.error)
                return@observe

            if (it.tutorsCount == 0) {
                binding.emptyContainer.visibility = View.VISIBLE
                binding.recyclerViewPrincipalTutor.visibility = View.GONE
                return@observe

            }

            rvAdapter.setData(it.tutors)
            binding.emptyContainer.visibility = View.GONE
            binding.recyclerViewPrincipalTutor.visibility = View.VISIBLE
        }
    }
    companion object {
        const val COURSE_ID = "COURSE_ID"
    }
}