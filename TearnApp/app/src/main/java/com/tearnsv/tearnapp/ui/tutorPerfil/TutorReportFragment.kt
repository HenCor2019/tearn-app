package com.tearnsv.tearnapp.ui.tutorPerfil

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.tearnsv.tearnapp.R
import com.tearnsv.tearnapp.TearnApplication
import com.tearnsv.tearnapp.databinding.FragmentTutorReportBinding
import com.tearnsv.tearnapp.ui.alertDialog.DialogConfirmFragment

/**
 * A simple [Fragment] subclass.
 * Use the [TutorReportFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TutorReportFragment : Fragment() {

    private var _binding : FragmentTutorReportBinding? = null
    private val binding get() = _binding!!

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
        _binding = FragmentTutorReportBinding.inflate(inflater, container, false)
            .apply {
                lifecycleOwner = viewLifecycleOwner
                viewModel = tutorProfileViewModel
            }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var reportAdapter = TutorReportsAdapter{
            var dialogConfirm = DialogConfirmFragment()
            dialogConfirm.show(requireActivity().supportFragmentManager,"prueba")

            tutorProfileViewModel.option.observe(viewLifecycleOwner){ option->
                if(option){
                    tutorProfileViewModel.createReport(it[0],it[1])
                }
            }
        }

        binding.recycleViewReports.apply{
            layoutManager = LinearLayoutManager(requireContext())
            adapter = reportAdapter
        }

        tutorProfileViewModel.reports.observe(viewLifecycleOwner){
            reportAdapter.setData(it)
        }

        val navHostFragment =
            requireActivity()
                .supportFragmentManager
                .findFragmentById(R.id.nav_host_controller_fragment)
                    as NavHostFragment
        val navController = navHostFragment.navController

        tutorProfileViewModel.statusCreateReport.observe(viewLifecycleOwner){
            if(it){
                Toast
                    .makeText(requireContext(),"Reporte enviado!", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        binding.actionBackIcon.setOnClickListener{
            navController.navigate(R.id.tutorPerfilFragment)
        }
    }
}