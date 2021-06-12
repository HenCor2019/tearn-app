package com.tearnsv.tearnapp.ui.account.formtutor

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.tearnsv.tearnapp.R
import com.tearnsv.tearnapp.TearnApplication
import com.tearnsv.tearnapp.databinding.FragmentAccountBinding
import com.tearnsv.tearnapp.ui.account.viewmodel.AccountVMFactory
import com.tearnsv.tearnapp.ui.account.viewmodel.AccountViewModel

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
                    Toast.makeText(
                        requireContext(),
                        "Espera un momento...",
                        Toast.LENGTH_LONG
                    ).show()
                    Toast.makeText(
                        requireContext(),
                        "¡Felicidades, ahora eres un tutor!",
                        Toast.LENGTH_SHORT
                    ).show()
                })
                .setNegativeButton("NO", DialogInterface.OnClickListener { dialog, id ->
                    dismiss()
                })
            builder.create()
        } ?: throw IllegalStateException("Fragment cannot be null")


    }
}