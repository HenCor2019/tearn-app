package com.tearnsv.tearnapp.ui.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tearnsv.tearnapp.R
import com.tearnsv.tearnapp.TearnApplication
import com.tearnsv.tearnapp.databinding.FragmentCoursesSearchBinding
import com.tearnsv.tearnapp.ui.course.CourseFragment
import com.tearnsv.tearnapp.ui.search.adapter.SearchCourseRecyclerViewAdapter
import com.tearnsv.tearnapp.ui.search.viewmodel.SearchVMFactory
import com.tearnsv.tearnapp.ui.search.viewmodel.SearchViewModel

class SearchCourseFragment : Fragment(), SearchCourseRecyclerViewAdapter.ItemClickListener {

    private var _binding: FragmentCoursesSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    private val application by lazy { requireActivity().application as TearnApplication }
    private val searchVMFactory: SearchVMFactory by lazy {
        val repository = application.tearnRepository
        SearchVMFactory(repository)
    }

    private val searchViewModel: SearchViewModel by activityViewModels { searchVMFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoursesSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchCourseViewModel = searchViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val rvAdapter = SearchCourseRecyclerViewAdapter(this)

        binding.recyclerViewSearchTutors.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = rvAdapter
        }

        searchViewModel.fetchSearchResponse.observe(viewLifecycleOwner) { response ->
            binding.progressCircular.visibility = View.GONE
            if (response.coursesCount == 0) {
                binding.emptyContainer.visibility = View.VISIBLE
                binding.recyclerViewSearchTutors.visibility = View.GONE
                return@observe
            }
            binding.recyclerViewSearchTutors.visibility = View.VISIBLE
            binding.emptyContainer.visibility = View.GONE
            rvAdapter.setData(response.courses)
        }


    }

    override fun onClickListener(id: String) {
        val bundle = Bundle()
        bundle.putString(CourseFragment.COURSE_ID, id)
        findNavController().navigate(R.id.courseFragment, bundle)

    }

}
