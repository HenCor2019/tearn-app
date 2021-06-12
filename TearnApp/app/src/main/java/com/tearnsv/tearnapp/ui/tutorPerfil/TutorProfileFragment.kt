package com.tearnsv.tearnapp.ui.tutorPerfil

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.tearnsv.tearnapp.R
import com.tearnsv.tearnapp.TearnApplication
import com.tearnsv.tearnapp.databinding.FragmentTutorProfileBinding

/**
 * A simple [Fragment] subclass.
 * Use the [TutorProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TutorProfileFragment : Fragment() {

    private var _binding : FragmentTutorProfileBinding? = null
    private val binding get() = _binding!!
    private var idTutor : String? = null

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            idTutor = it.getString(TUTOR_ID).toString()
        }
        tutorProfileViewModel.setId(idTutor?:tutorProfileViewModel.ID_TUTOR.value!!)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTutorProfileBinding.inflate(inflater,container,false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = tutorProfileViewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var valorationAdapter = TutorCommentariesAdapter()

        binding.recycleViewCommentaries.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = valorationAdapter
        }

        tutorProfileViewModel.tutor.observe(viewLifecycleOwner){
            Glide.with(view)
                .load(it.imgUrl)
                .centerCrop()
                .placeholder(R.drawable.default_photo)
                .into(binding.imageTutorPerfil)

            binding.labelTutorPerfilName.text = it.fullName
            binding.labelTutorPerfilDescription.text = it.description

            var languages = ""
            it.languages?.forEach {
                languages+= "${it}, "
            }
            binding.labelTutorPerfilLanguages.text = languages

            if(it.active!!){
                binding.iconActive.setColorFilter(R.color.green_selection)
            } else binding.iconActive.setColorFilter(R.color.red_fav)

            binding.labelTutorPerfilTimeRequest.text = it.responseTime

            //TODO(Funcionalidad de la disponibilidad)

            binding.labelTutorPerfilPuntuation.text = it.puntuation.toString()

            when(it.puntuation){
                0 -> {
                    binding.iconStar1.setImageResource(R.drawable.ic_baseline_star_outline_24)
                    binding.iconStar2.setImageResource(R.drawable.ic_baseline_star_outline_24)
                    binding.iconStar3.setImageResource(R.drawable.ic_baseline_star_outline_24)
                    binding.iconStar4.setImageResource(R.drawable.ic_baseline_star_outline_24)
                    binding.iconStar5.setImageResource(R.drawable.ic_baseline_star_outline_24)
                }
                1 -> {
                    binding.iconStar1.setImageResource(R.drawable.ic_baseline_star_rate_24)
                    binding.iconStar2.setImageResource(R.drawable.ic_baseline_star_outline_24)
                    binding.iconStar3.setImageResource(R.drawable.ic_baseline_star_outline_24)
                    binding.iconStar4.setImageResource(R.drawable.ic_baseline_star_outline_24)
                    binding.iconStar5.setImageResource(R.drawable.ic_baseline_star_outline_24)
                }
                2 -> {
                    binding.iconStar1.setImageResource(R.drawable.ic_baseline_star_rate_24)
                    binding.iconStar2.setImageResource(R.drawable.ic_baseline_star_rate_24)
                    binding.iconStar3.setImageResource(R.drawable.ic_baseline_star_outline_24)
                    binding.iconStar4.setImageResource(R.drawable.ic_baseline_star_outline_24)
                    binding.iconStar5.setImageResource(R.drawable.ic_baseline_star_outline_24)
                }
                3 -> {
                    binding.iconStar1.setImageResource(R.drawable.ic_baseline_star_rate_24)
                    binding.iconStar2.setImageResource(R.drawable.ic_baseline_star_rate_24)
                    binding.iconStar3.setImageResource(R.drawable.ic_baseline_star_rate_24)
                    binding.iconStar4.setImageResource(R.drawable.ic_baseline_star_outline_24)
                    binding.iconStar5.setImageResource(R.drawable.ic_baseline_star_outline_24)
                }
                4 -> {
                    binding.iconStar1.setImageResource(R.drawable.ic_baseline_star_rate_24)
                    binding.iconStar2.setImageResource(R.drawable.ic_baseline_star_rate_24)
                    binding.iconStar3.setImageResource(R.drawable.ic_baseline_star_rate_24)
                    binding.iconStar4.setImageResource(R.drawable.ic_baseline_star_rate_24)
                    binding.iconStar5.setImageResource(R.drawable.ic_baseline_star_outline_24)
                }
                5 -> {
                    binding.iconStar1.setImageResource(R.drawable.ic_baseline_star_rate_24)
                    binding.iconStar2.setImageResource(R.drawable.ic_baseline_star_rate_24)
                    binding.iconStar3.setImageResource(R.drawable.ic_baseline_star_rate_24)
                    binding.iconStar4.setImageResource(R.drawable.ic_baseline_star_rate_24)
                    binding.iconStar5.setImageResource(R.drawable.ic_baseline_star_rate_24)
                }
            }

            valorationAdapter.setData(it.commentaries!!)
        }

        val navHostFragment =
            requireActivity()
                .supportFragmentManager
                .findFragmentById(R.id.nav_host_controller_fragment)
                    as NavHostFragment
        val navController = navHostFragment.navController

        binding.actionViewAllValorations.setOnClickListener {
            navController.navigate(R.id.tutorValorationsFragment)
        }

        binding.floatingActionReportBtn.setOnClickListener {
            navController.navigate(R.id.tutorReportFragment)
        }

        binding.valorationStar1.setOnClickListener{
            binding.valorationStar1.setImageResource(R.drawable.ic_baseline_star_rate_24)
            binding.valorationStar2.setImageResource(R.drawable.ic_baseline_star_outline_24)
            binding.valorationStar3.setImageResource(R.drawable.ic_baseline_star_outline_24)
            binding.valorationStar4.setImageResource(R.drawable.ic_baseline_star_outline_24)
            binding.valorationStar5.setImageResource(R.drawable.ic_baseline_star_outline_24)
            tutorProfileViewModel.addPuntuation(1)
        }

        binding.valorationStar2.setOnClickListener{
            binding.valorationStar1.setImageResource(R.drawable.ic_baseline_star_rate_24)
            binding.valorationStar2.setImageResource(R.drawable.ic_baseline_star_rate_24)
            binding.valorationStar3.setImageResource(R.drawable.ic_baseline_star_outline_24)
            binding.valorationStar4.setImageResource(R.drawable.ic_baseline_star_outline_24)
            binding.valorationStar5.setImageResource(R.drawable.ic_baseline_star_outline_24)
            tutorProfileViewModel.addPuntuation(2)
        }

        binding.valorationStar3.setOnClickListener{
            binding.valorationStar1.setImageResource(R.drawable.ic_baseline_star_rate_24)
            binding.valorationStar2.setImageResource(R.drawable.ic_baseline_star_rate_24)
            binding.valorationStar3.setImageResource(R.drawable.ic_baseline_star_rate_24)
            binding.valorationStar4.setImageResource(R.drawable.ic_baseline_star_outline_24)
            binding.valorationStar5.setImageResource(R.drawable.ic_baseline_star_outline_24)
            tutorProfileViewModel.addPuntuation(3)
        }

        binding.valorationStar4.setOnClickListener{
            binding.valorationStar1.setImageResource(R.drawable.ic_baseline_star_rate_24)
            binding.valorationStar2.setImageResource(R.drawable.ic_baseline_star_rate_24)
            binding.valorationStar3.setImageResource(R.drawable.ic_baseline_star_rate_24)
            binding.valorationStar4.setImageResource(R.drawable.ic_baseline_star_rate_24)
            binding.valorationStar5.setImageResource(R.drawable.ic_baseline_star_outline_24)
            tutorProfileViewModel.addPuntuation(4)
        }

        binding.valorationStar5.setOnClickListener{
            binding.valorationStar1.setImageResource(R.drawable.ic_baseline_star_rate_24)
            binding.valorationStar2.setImageResource(R.drawable.ic_baseline_star_rate_24)
            binding.valorationStar3.setImageResource(R.drawable.ic_baseline_star_rate_24)
            binding.valorationStar4.setImageResource(R.drawable.ic_baseline_star_rate_24)
            binding.valorationStar5.setImageResource(R.drawable.ic_baseline_star_rate_24)
            tutorProfileViewModel.addPuntuation(5)
        }

        tutorProfileViewModel.statusCreateCommentary.observe(viewLifecycleOwner){
            if(it){
                Toast
                    .makeText(requireContext(),"Comentario creado!",Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

    companion object {
        const val TUTOR_ID = "TUTOR_ID"
    }

}