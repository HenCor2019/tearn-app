package com.tearnsv.tearnapp.ui.account.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.tearnsv.tearnapp.R
import com.tearnsv.tearnapp.TearnApplication
import com.tearnsv.tearnapp.databinding.FragmentFavoriteBinding
import com.tearnsv.tearnapp.ui.account.adapter.AccountRVAdapter
import com.tearnsv.tearnapp.ui.account.viewmodel.AccountVMFactory
import com.tearnsv.tearnapp.ui.account.viewmodel.AccountViewModel
import com.tearnsv.tearnapp.ui.home.HomeFragment
import com.tearnsv.tearnapp.ui.tutorPerfil.TutorFavoriteViewModel
import com.tearnsv.tearnapp.ui.tutorPerfil.TutorFavoriteViewModelFactory

class FavoriteFragment : Fragment(), AccountRVAdapter.ItemClickListener {

    private lateinit var rvAdapter: AccountRVAdapter

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private val application by lazy { requireActivity().application as TearnApplication }

    private val tutorFavoriteViewModelFactory: TutorFavoriteViewModelFactory by lazy {
        val repository = application.tearnRepository
        TutorFavoriteViewModelFactory(repository)
    }

    private val tutorFavoriteViewModel: TutorFavoriteViewModel by activityViewModels {
        tutorFavoriteViewModelFactory
    }

    private val accountVMFactory: AccountVMFactory by lazy {
        val repository = application.tearnRepository
        AccountVMFactory(repository)
    }

    private val accountViewModel: AccountViewModel by activityViewModels { accountVMFactory }

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
        binding.actionBackIcon?.setOnClickListener {
            findNavController().navigate(R.id.accountFragment)
        }
        setupRecyclerView()
        observers()
    }

    private fun observers() {
        tutorFavoriteViewModel.favTutors.observe(viewLifecycleOwner){
            accountViewModel.getFavoriteTutors()
        }

        accountViewModel.favTutorsResponse.observe(viewLifecycleOwner){
            rvAdapter.setData(it)
        }

    }

    private fun setupRecyclerView() {
        rvAdapter = AccountRVAdapter(this)
        binding.recyclerViewFavorites.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = rvAdapter
        }
    }

    override fun onClickFavButton(id: String) {
        tutorFavoriteViewModel.addOrRemoveFav(id)
    }

    override fun onClickListener(id: String) {
        var bundle = Bundle()
        bundle.putString(HomeFragment.TUTOR_ID, id)
        findNavController().navigate(R.id.tutorPerfilFragment, bundle)
    }
}