package com.tearnsv.tearnapp.ui.categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tearnsv.tearnapp.R
import com.tearnsv.tearnapp.TearnApplication
import com.tearnsv.tearnapp.databinding.FragmentCategoryBinding
import com.tearnsv.tearnapp.ui.categories.adapter.CategoryRecyclerViewAdapter
import com.tearnsv.tearnapp.ui.categories.viewmodel.CategoryVMFactory
import com.tearnsv.tearnapp.ui.categories.viewmodel.CategoryViewModel
import com.tearnsv.tearnapp.ui.category.CategoryFragment

class CategoryFragment : Fragment(), CategoryRecyclerViewAdapter.ItemClickListener {


    private lateinit var rvAdapter: CategoryRecyclerViewAdapter

    private var _binding: FragmentCategoryBinding? = null
    private val binding get() = _binding!!
    private val application by lazy { requireActivity().application as TearnApplication }
    private val categoryVMFactory: CategoryVMFactory by lazy {
        val repository = application.tearnRepository
        CategoryVMFactory(repository)
    }

    private val categoryViewModel: CategoryViewModel by viewModels { categoryVMFactory }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.categoryViewModel = categoryViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        setupRecyclerView()

    }

    private fun setupRecyclerView(){
        rvAdapter = CategoryRecyclerViewAdapter(this)
        binding.recycleViewCategories.apply{
            layoutManager = LinearLayoutManager(requireContext())
            adapter = rvAdapter
            observers()

        }

    }

    private fun observers(){
        categoryViewModel.fetchCategoryResponse.observe(viewLifecycleOwner){
            rvAdapter.setData(it.results)
        }

    }

    override fun onClickListener(id: String) {
        val bundle = Bundle()
        bundle.putString(CategoryFragment.ID_COURSE, id)
        findNavController().navigate(R.id.categoryFragment, bundle)
    }

}