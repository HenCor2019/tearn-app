package com.tearnsv.tearnapp.ui.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tearnsv.tearnapp.R
import com.tearnsv.tearnapp.TearnApplication
import com.tearnsv.tearnapp.databinding.FragmentFavoriteBinding
import com.tearnsv.tearnapp.ui.account.adapter.AccountRVAdapter
import com.tearnsv.tearnapp.ui.account.viewmodel.AccountVMFactory
import com.tearnsv.tearnapp.ui.account.viewmodel.AccountViewModel

class FavoriteFragment : Fragment() {

    private lateinit var rvAdapter: AccountRVAdapter

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private val application by lazy { requireActivity().application as TearnApplication }
    private val accountVMFactory: AccountVMFactory by lazy {
        val repository = application.tearnRepository
        AccountVMFactory(repository)
    }

    private val accountViewModel: AccountViewModel by activityViewModels { accountVMFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.accountViewModel = accountViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        setupRecyclerView()
        observers()
    }

    private fun observers() {
        accountViewModel.fetchFavoriteUsers.observe(viewLifecycleOwner) {
            rvAdapter.setData(it.favTutors)
        }

    }

    private fun setupRecyclerView() {
        rvAdapter = AccountRVAdapter()
        binding.recyclerViewFavorites.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = rvAdapter
        }

    }


}