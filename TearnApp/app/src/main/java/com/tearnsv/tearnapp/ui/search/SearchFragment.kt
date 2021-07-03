package com.tearnsv.tearnapp.ui.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.tearnsv.tearnapp.R
import com.tearnsv.tearnapp.TearnApplication
import com.tearnsv.tearnapp.databinding.FragmentSearchBinding
import com.tearnsv.tearnapp.ui.search.adapter.SearchAdapter
import com.tearnsv.tearnapp.ui.search.viewmodel.SearchVMFactory
import com.tearnsv.tearnapp.ui.search.viewmodel.SearchViewModel

class SearchFragment : Fragment() {


    private var _binding: FragmentSearchBinding? = null
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

    private val searchViewModel: SearchViewModel by activityViewModels { searchVMFactory  }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchViewModel = searchViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val TABS_NAME = listOf("Cursos", "Tutores", "Libros")
        val tabLayout = view.findViewById<TabLayout>(R.id.tab_layout)
        val viewPager = view.findViewById<ViewPager2>(R.id.search_view_pager)
        viewPager.adapter = SearchAdapter(requireActivity())

        TabLayoutMediator(tabLayout,viewPager){ tab, position ->
            tab.text = TABS_NAME[position]
        }.attach()
        setupSearchView()
    }

    private fun setupSearchView(){
        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchViewModel.setPattern(query ?: "")
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }

}
