package com.tearnsv.tearnapp.ui.preferences

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
import com.tearnsv.tearnapp.data.Category
import com.tearnsv.tearnapp.databinding.FragmentPreferencesBinding

class PreferencesFragment : Fragment(), PreferencesAdapter.OnClickSuccess {

    private var _binding: FragmentPreferencesBinding? = null
    private val binding get() = _binding!!

    private val application by lazy {
        requireActivity().application as TearnApplication
    }

    private val preferencesFactory: PreferencesViewModelFactory by lazy {
        val repository = application.tearnRepository
        PreferencesViewModelFactory(repository)
    }

    private val preferencesViewModel: PreferencesViewModel by viewModels {
        preferencesFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPreferencesBinding.inflate(inflater, container, false)
            .apply {
                lifecycleOwner = viewLifecycleOwner
                viewModel = preferencesViewModel
            }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var preferencesAdapter = PreferencesAdapter(this)
        var preferencesSelectedAdapter = PreferencesSelectedAdapter()

        val navHostFragment =
            requireActivity()
                .supportFragmentManager
                .findFragmentById(R.id.nav_host_fragment)
                    as NavHostFragment
        val navController = navHostFragment.navController

        binding.button.setOnClickListener {
            navController.navigate(R.id.navControllerActivity)
        }

        binding.recycleViewPreferences.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = preferencesSelectedAdapter
        }

        binding.recycleViewCategories.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = preferencesAdapter
        }

        preferencesViewModel.categories.observe(viewLifecycleOwner) {
            preferencesAdapter.setData(it.results)
        }

        preferencesViewModel.preferencesLiveData.observe(viewLifecycleOwner) {
            preferencesSelectedAdapter.setData(it)
        }
    }

    override fun onClickSuccess(category: Category): Boolean {
        return preferencesViewModel.addOrRemovePreferences(category)
    }

}