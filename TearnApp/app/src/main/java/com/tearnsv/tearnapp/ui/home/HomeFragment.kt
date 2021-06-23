package com.tearnsv.tearnapp.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.tearnsv.tearnapp.R
import com.tearnsv.tearnapp.TearnApplication
import com.tearnsv.tearnapp.databinding.FragmentHomeBinding
import com.tearnsv.tearnapp.ui.bookadapter.BooksRVAdapter

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment(), BooksRVAdapter.ItemClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val application by lazy {
        requireActivity().application as TearnApplication
    }

    private val homeViewModelFactory: HomeViewModelFactory by lazy {
        val repository = application.tearnRepository
        HomeViewModelFactory(repository)
    }

    private val homeViewModel: HomeViewModel by viewModels {
        homeViewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
            .apply {
                lifecycleOwner = viewLifecycleOwner
                viewModel = homeViewModel
            }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        var topicsRVAdapter = TopicsRVAdapter()
        val booksRVAdapter = BooksRVAdapter(this)
        var tutorsRVAdapter = TutorsRVAdapter{
            var bundle = Bundle()
            bundle.putString(TUTOR_ID,it)
            findNavController().navigate(R.id.tutorPerfilFragment,bundle)
        }

        binding.topicsRecycleView.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = topicsRVAdapter
        }

        binding.tutorsRecycleView.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = tutorsRVAdapter
        }

        homeViewModel.recommendations.observe(viewLifecycleOwner){
            topicsRVAdapter.setData(it.subjects)
            tutorsRVAdapter.setData(it.tutors)
        }

        binding.booksRecyclerView.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = booksRVAdapter

        }

        homeViewModel.fetchBookResponse.observe(viewLifecycleOwner) {
            booksRVAdapter.setData(it.items)
        }


        var bottomNav =
            requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navigation)

        binding.actionCategories.setOnClickListener {
            findNavController().navigate(R.id.categoriesFragment)
        }

        binding.actionSearchTopics.setOnClickListener {
            findNavController().navigate(R.id.searchFragment)
            bottomNav.selectedItemId = R.id.page_1
        }

        binding.actionSearchTutors.setOnClickListener {
            findNavController().navigate(R.id.searchFragment)
            bottomNav.selectedItemId = R.id.page_1
        }
    }

    companion object {
        const val TUTOR_ID = "TUTOR_ID"
        const val AUTHOR_ID = "AUTHOR_ID"
    }

    override fun onClickItem(url: String) {
        val uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }
}