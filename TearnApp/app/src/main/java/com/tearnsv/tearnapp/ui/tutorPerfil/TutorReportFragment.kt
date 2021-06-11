package com.tearnsv.tearnapp.ui.tutorPerfil

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tearnsv.tearnapp.R
/**
 * A simple [Fragment] subclass.
 * Use the [TutorReportFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TutorReportFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tutor_report, container, false)
    }

}