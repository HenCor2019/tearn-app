package com.tearnsv.tearnapp.ui.alertDialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.tearnsv.tearnapp.R
import com.tearnsv.tearnapp.TearnApplication
import com.tearnsv.tearnapp.ui.tutorPerfil.TutorProfileViewModel
import com.tearnsv.tearnapp.ui.tutorPerfil.TutorProfileViewModelFactory

/**
 * A simple [Fragment] subclass.
 * Use the [DialogConfirmFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DialogConfirmFragment : DialogFragment() {

    private val application by lazy {
        requireActivity().application as TearnApplication
    }

    private val tutorProfileFactory: TutorProfileViewModelFactory by lazy {
        val repository = application.tearnRepository
        TutorProfileViewModelFactory(repository)
    }

    private val tutorProfileViewModel: TutorProfileViewModel by
    activityViewModels {
        tutorProfileFactory
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return requireActivity().let {
            val builder =  AlertDialog.Builder(it)
            builder.setMessage("¿Reportar tutor?")
                .setPositiveButton("SI",DialogInterface.OnClickListener{
                        dialog, id ->
                    tutorProfileViewModel.setOption(true)
                })
                .setNegativeButton("NO",DialogInterface.OnClickListener{
                        dialog, id ->
                    tutorProfileViewModel.setOption(false)
                })
            builder.create()
        } ?: throw IllegalStateException("Fragment cannot be null")
    }
}