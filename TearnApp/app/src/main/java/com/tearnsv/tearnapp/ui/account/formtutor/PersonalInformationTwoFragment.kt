package com.tearnsv.tearnapp.ui.account.formtutor

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.tearnsv.tearnapp.R
import com.tearnsv.tearnapp.TearnApplication
import com.tearnsv.tearnapp.databinding.FragmentPersonalInformationTwoBinding
import com.tearnsv.tearnapp.ui.account.viewmodel.AccountVMFactory
import com.tearnsv.tearnapp.ui.account.viewmodel.AccountViewModel

class PersonalInformationTwoFragment : Fragment() {

    private var _binding: FragmentPersonalInformationTwoBinding? = null
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
        _binding = FragmentPersonalInformationTwoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.principalInformationViewModel = accountViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        handlerEvents()
    }

    private fun handlerEvents() {
        binding.actionNextForm.setOnClickListener {
            if (!accountViewModel.verifyInputsPrincipalFragmentTwo()) {
                Toast.makeText(requireContext(), "Entradas invalidas", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (accountViewModel.verifyLanguages()) {
                Toast.makeText(requireContext(), "Selecciona un idioma", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }
            findNavController().navigate(R.id.availabityFragment)
        }

        binding.spanishCheckbox.setOnCheckedChangeListener { buttonView, isChecked ->
            accountViewModel.languages[0] = if(isChecked) "Español" else ""
        }
        binding.englishCheckbox.setOnCheckedChangeListener { buttonView, isChecked ->
            accountViewModel.languages[1] = if (isChecked) "Ingles" else ""
        }

    }
}