package com.tearnsv.tearnapp.ui.tutorPerfil

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.tearnsv.tearnapp.R
import com.tearnsv.tearnapp.TearnApplication
import com.tearnsv.tearnapp.databinding.FragmentTutorValorationsBinding
import com.tearnsv.tearnapp.ui.home.HomeFragment

/**
 * A simple [Fragment] subclass.
 * Use the [TutorValorationsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TutorValorationsFragment : Fragment() {

    private var _binding : FragmentTutorValorationsBinding? = null
    private val binding get() = _binding!!
    private lateinit var idTutor : String
    private lateinit var idAuthor : String

    private val application by lazy {
        requireActivity().application as TearnApplication
    }

    private val tutorProfileFactory: TutorProfileViewModelFactory by lazy {
        val repository = application.tearnRepository
        TutorProfileViewModelFactory(repository)
    }

    private val tutorProfileViewModel: TutorProfileViewModel by activityViewModels {
        tutorProfileFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTutorValorationsBinding.inflate(inflater,container,false)
            .apply {
                lifecycleOwner = viewLifecycleOwner
                viewModel = tutorProfileViewModel
            }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var valorationAdapter = TutorCommentariesAdapter()

        binding.recycleViewAllValorations.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = valorationAdapter
        }

        val navHostFragment =
            requireActivity()
                .supportFragmentManager
                .findFragmentById(R.id.nav_host_controller_fragment)
                    as NavHostFragment
        val navController = navHostFragment.navController

        binding.actionBackIcon.setOnClickListener{
            navController.navigate(R.id.tutorPerfilFragment)
        }

        tutorProfileViewModel.tutor.observe(viewLifecycleOwner){
            Glide.with(view)
                .load(tutorProfileViewModel.tutor.value?.imgUrl)
                .centerCrop()
                .placeholder(R.drawable.default_photo)
                .into(binding.imageTutorValoration)

            binding.nameTutor.text = tutorProfileViewModel.tutor.value?.fullName

            valorationAdapter.setData(it.commentaries!!)
        }
    }
}