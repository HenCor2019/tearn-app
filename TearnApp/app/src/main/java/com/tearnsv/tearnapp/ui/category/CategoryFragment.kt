package com.tearnsv.tearnapp.ui.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.tearnsv.tearnapp.R
import com.tearnsv.tearnapp.TearnApplication
import com.tearnsv.tearnapp.databinding.FragmentCategoryOneBinding
import com.tearnsv.tearnapp.ui.category.viewmodel.CategoryVMFactory
import com.tearnsv.tearnapp.ui.category.viewmodel.CategoryViewModel
import com.tearnsv.tearnapp.ui.category.adapter.PrincipalRecyclerViewAdapter
import com.tearnsv.tearnapp.ui.course.CourseFragment

class CategoryFragment : Fragment(), PrincipalRecyclerViewAdapter.ItemClickListener {

    private lateinit var idCategory: String
    private lateinit var rvAdapter: PrincipalRecyclerViewAdapter

    private var _binding: FragmentCategoryOneBinding? = null
    private val binding get() = _binding!!

    private val application by lazy { requireActivity().application as TearnApplication }
    private val categoryVMFactory: CategoryVMFactory by lazy {
        val repository = application.tearnRepository
        CategoryVMFactory(repository)
    }

    private val categoryViewModel: CategoryViewModel by viewModels { categoryVMFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            idCategory = it.getString(ID_COURSE).toString()
        }
        categoryViewModel.setIdCategory(idCategory)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryOneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupBinding()
        setupRecyclerView()
        observers(view)

    }

    private fun setupBinding() {
        binding.categoryViewModel = categoryViewModel
        binding.lifecycleOwner = viewLifecycleOwner

    }

    private fun setupRecyclerView() {
        rvAdapter = PrincipalRecyclerViewAdapter(this)
        binding.recycleViewSubjects.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = rvAdapter
        }
    }

    private fun observers(view: View) {
        categoryViewModel.categoryResponse.observe(viewLifecycleOwner) {
            if (it.error)
                return@observe

            binding.categoryName.text = it.name
            Glide.with(view).load(it.imgUrl).placeholder(R.drawable.ic_launcher_background)
                .into(binding.categoryImage)
            binding.categoryDescription.text = it.description

            if (it.subjectCount == 0)
                return@observe

            rvAdapter.setData(it.subjects)
        }

    }


    companion object {
        val ID_COURSE = "ID_COURSE"
    }

    override fun onClickListener(id: String) {
        val bundle = Bundle()
        bundle.putString(CourseFragment.COURSE_ID, id)
        findNavController().navigate(R.id.courseFragment, bundle)

    }
}