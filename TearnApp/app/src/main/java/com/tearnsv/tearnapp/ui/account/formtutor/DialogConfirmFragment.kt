package com.tearnsv.tearnapp.ui.account.formtutor

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.tearnsv.tearnapp.MainActivity
import com.tearnsv.tearnapp.R
import com.tearnsv.tearnapp.TearnApplication
import com.tearnsv.tearnapp.databinding.FragmentAccountBinding
import com.tearnsv.tearnapp.ui.account.viewmodel.AccountVMFactory
import com.tearnsv.tearnapp.ui.account.viewmodel.AccountViewModel
import com.tearnsv.tearnapp.ui.home.NavControllerActivity

class DialogConfirmFragment : DialogFragment() {

  private var _binding: FragmentAccountBinding? = null
  private val binding get() = _binding!!

  private val application by lazy { requireActivity().application as TearnApplication }
  private val accountVMFactory: AccountVMFactory by lazy {
    val repository = application.tearnRepository
    AccountVMFactory(repository)
  }

  private val accountViewModel: AccountViewModel by activityViewModels { accountVMFactory }

  override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
    return requireActivity().let {
      val builder = AlertDialog.Builder(it)
      builder.setMessage("¿Estas seguro?")
        .setPositiveButton("SI", DialogInterface.OnClickListener { dialog, id ->
          accountViewModel.convertToTutor()
          if (accountViewModel.error) {
            Toast.makeText(requireContext(), "Algo malo sucedio", Toast.LENGTH_LONG).show()
            return@OnClickListener
          }
          Toast.makeText(requireContext(), "Espera un momento...", Toast.LENGTH_LONG).show()
          Toast.makeText(requireContext(), "¡Felicidades, ahora eres un tutor!", Toast.LENGTH_SHORT)
            .show()
          val intent = Intent(requireContext(), NavControllerActivity::class.java)
          requireActivity().startActivity(intent)
          requireActivity().finish()
        })
        .setNegativeButton("NO", DialogInterface.OnClickListener { dialog, id ->
          dismiss()
        })
      builder.create()
    } ?: throw IllegalStateException("Fragment cannot be null")


  }
}