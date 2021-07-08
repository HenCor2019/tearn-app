package com.tearnsv.tearnapp.ui.account.formtutor

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.tearnsv.tearnapp.R
import com.tearnsv.tearnapp.TearnApplication
import com.tearnsv.tearnapp.databinding.FragmentPersonalInformationBinding
import com.tearnsv.tearnapp.ui.account.viewmodel.AccountVMFactory
import com.tearnsv.tearnapp.ui.account.viewmodel.AccountViewModel

class PersonalInformationFragment : Fragment() {

  private var _binding: FragmentPersonalInformationBinding? = null
  private val binding get() = _binding!!

  private val application by lazy { requireActivity().application as TearnApplication }
  private val accountVMFactory: AccountVMFactory by lazy {
    val repository = application.tearnRepository
    AccountVMFactory(repository)
  }

  private val accountViewModel: AccountViewModel by activityViewModels { accountVMFactory }


  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _binding = FragmentPersonalInformationBinding.inflate(inflater, container, false)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding.principalViewModel = accountViewModel
    binding.lifecycleOwner = viewLifecycleOwner
    handlerEvents()
  }

  private fun handlerEvents() {
    binding.actionNextForm.setOnClickListener {
      if (!accountViewModel.verifyInputsPrincipalFragment()) {
        Toast.makeText(requireContext(), "Entradas invalidas", Toast.LENGTH_SHORT).show()
        return@setOnClickListener
      }
      findNavController().navigate(R.id.personalInformationTwoFragment)
    }
  }

}