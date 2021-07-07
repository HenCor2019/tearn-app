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
import com.tearnsv.tearnapp.databinding.FragmentSearchTutorBinding
import com.tearnsv.tearnapp.ui.home.HomeFragment.Companion.TUTOR_ID
import com.tearnsv.tearnapp.ui.search.adapter.SearchTutorRecyclerViewAdapter
import com.tearnsv.tearnapp.ui.search.viewmodel.SearchVMFactory
import com.tearnsv.tearnapp.ui.search.viewmodel.SearchViewModel

class SearchTutorFragment : Fragment() , SearchTutorRecyclerViewAdapter.ItemClickListener{

    private var _binding: FragmentSearchTutorBinding? = null
    private val binding get() = _binding!!

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
        _binding = FragmentSearchTutorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchTutorViewModel = searchViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val rvAdapter = SearchTutorRecyclerViewAdapter(this)

        binding.recyclerViewSearchCourses.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = rvAdapter
        }

        searchViewModel.fetchSearchResponse.observe(viewLifecycleOwner){ response ->
            binding.progressBar.visibility = View.GONE
            if(response.tutorsCount == 0){
                binding.recyclerViewSearchCourses.visibility = View.GONE
                return@observe
            }
            binding.recyclerViewSearchCourses.visibility = View.VISIBLE
            rvAdapter.setData(response.tutors)
        }

    }

    override fun onClickListener(id: String) {
        val bundle = Bundle()
        bundle.putString(TUTOR_ID,id)
        findNavController().navigate(R.id.tutorPerfilFragment,bundle)
    }

}
