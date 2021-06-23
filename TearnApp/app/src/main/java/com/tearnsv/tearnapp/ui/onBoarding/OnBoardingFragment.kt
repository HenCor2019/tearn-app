package com.tearnsv.tearnapp.ui.onBoarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.tearnsv.tearnapp.Prefs
import com.tearnsv.tearnapp.R
import com.tearnsv.tearnapp.TearnApplication
import com.tearnsv.tearnapp.databinding.FragmentOnBoardingBinding

/**
 * A simple [Fragment] subclass.
 * Use the [OnBoardingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OnBoardingFragment : Fragment() {

  private var _binding: FragmentOnBoardingBinding? = null
  private val binding get() = _binding!!
  private val viewModelOnBoarding: OnBoardingViewModel by viewModels()

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {

    if (!TearnApplication.prefs.getId().isNullOrEmpty())
      findNavController().navigate(R.id.preferencesFragment)

    _binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
      .apply {
        lifecycleOwner = viewLifecycleOwner
        viewModel = viewModelOnBoarding
      }


    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    viewModelOnBoarding.position.observe(viewLifecycleOwner) {
      binding.imgOnBoarding.setImageResource(viewModelOnBoarding.imagesOnBardiing[it])
      when (it) {
        0 -> {
          binding.onBoardingTitle.text = getString(R.string.label_on_boarding_title1)
          binding.onBoardingDescription.setText(R.string.label_on_boarding_description)
          binding.onBoarding1.setImageResource(R.drawable.ic_baseline_lens_24)
          binding.onBoarding2.setImageResource(R.drawable.ic_baseline_panorama_fish_eye_24)
          binding.onBoarding3.setImageResource(R.drawable.ic_baseline_panorama_fish_eye_24)
        }
        1 -> {
          binding.onBoardingTitle.text = getString(R.string.label_on_boarding_title2)
          binding.onBoardingDescription.setText(R.string.label_on_boarding_description)
          binding.onBoarding2.setImageResource(R.drawable.ic_baseline_lens_24)
          binding.onBoarding1.setImageResource(R.drawable.ic_baseline_panorama_fish_eye_24)
          binding.onBoarding3.setImageResource(R.drawable.ic_baseline_panorama_fish_eye_24)
        }
        2 -> {
          binding.onBoardingTitle.text = getString(R.string.label_on_boarding_title3)
          binding.onBoardingDescription.setText(R.string.label_on_boarding_description)
          binding.onBoarding3.setImageResource(R.drawable.ic_baseline_lens_24)
          binding.onBoarding1.setImageResource(R.drawable.ic_baseline_panorama_fish_eye_24)
          binding.onBoarding2.setImageResource(R.drawable.ic_baseline_panorama_fish_eye_24)
        }
        else -> {
          Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
        }
      }
    }

    val navHostFragment =
      requireActivity()
        .supportFragmentManager
        .findFragmentById(R.id.nav_host_fragment)
          as NavHostFragment
    val navController = navHostFragment.navController

    binding.actionOnBoardingButton.setOnClickListener {
      if (viewModelOnBoarding.position.value == 2) {
        navController.navigate(R.id.loginFragment)
      } else viewModelOnBoarding.onButtonChangeView()
    }

    binding.onBoarding1.setOnClickListener {
      viewModelOnBoarding.onClickPostions(0)
    }

    binding.onBoarding2.setOnClickListener {
      viewModelOnBoarding.onClickPostions(1)
    }

    binding.onBoarding3.setOnClickListener {
      viewModelOnBoarding.onClickPostions(2)
    }
  }

}