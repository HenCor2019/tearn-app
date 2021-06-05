package com.tearnsv.tearnapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.tearnsv.tearnapp.R
import com.tearnsv.tearnapp.TearnApplication
import com.tearnsv.tearnapp.databinding.FragmentHomeBinding

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() =_binding!!

    private val application by lazy{
        requireActivity().application as TearnApplication
    }

    private val homeViewModelFactory: HomeViewModelFactory by lazy{
        val repository = application.tearnRepository
        HomeViewModelFactory(repository)
    }

    private  val homeViewModel: HomeViewModel by viewModels{
        homeViewModelFactory
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
            .apply{
                lifecycleOwner = viewLifecycleOwner
                viewModel = homeViewModel
            }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var topicsRVAdapter = TopicsRVAdapter()
        var tutorsRVAdapter = TutorsRVAdapter()

        binding.topicsRecycleView.apply{
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = topicsRVAdapter
        }

        binding.tutorsRecycleView.apply{
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = tutorsRVAdapter
        }

        homeViewModel.recommendations.observe(viewLifecycleOwner){
            topicsRVAdapter.setData(it.subjects)
            tutorsRVAdapter.setData(it.tutors)
        }

        val navHostFragment =
            requireActivity()
                .supportFragmentManager
                .findFragmentById(R.id.nav_host_controller_fragment)
                    as NavHostFragment
        val navController = navHostFragment.navController

        binding.actionCategories.setOnClickListener {
            navController.navigate(R.id.categoriesFragment)
        }

        binding.actionSearchTopics.setOnClickListener{
            navController.navigate(R.id.searchFragment)
        }

        binding.actionSearchTutors.setOnClickListener{
            navController.navigate(R.id.searchFragment)
        }

    }
}