package com.tearnsv.tearnapp.ui.account

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.tearnsv.tearnapp.MainActivity
import com.tearnsv.tearnapp.R
import com.tearnsv.tearnapp.TearnApplication
import com.tearnsv.tearnapp.databinding.FragmentAccountBinding
import com.tearnsv.tearnapp.ui.account.viewmodel.AccountVMFactory
import com.tearnsv.tearnapp.ui.account.viewmodel.AccountViewModel


class AccountFragment : Fragment() {

  private var _binding: FragmentAccountBinding? = null
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
  ): View? {
    _binding = FragmentAccountBinding.inflate(inflater, container, false)
    accountViewModel.setIdUser(TearnApplication.prefs.getId()!!)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding.accountViewModel = accountViewModel
    binding.lifecycleOwner = viewLifecycleOwner
    observer(view)
    handlerEvents()
  }

  private fun observer(view: View) {
    accountViewModel.fetchUserAccountResponse.observe(viewLifecycleOwner) {
      if (it.error || accountViewModel.error) {
        binding.progressCircular.visibility = View.GONE
        Toast.makeText(requireContext(), "No se logro obtener el usuario", Toast.LENGTH_SHORT)
          .show()
        return@observe
      }
      Glide.with(view).load(it.imgUrl).placeholder(R.drawable.default_photo)
        .into(binding.usernameImage)

      makeItemsVisible()
      verifyLayoutTutor(it.isTutor)

    }
  }

  private fun verifyLayoutTutor(isTutor: Boolean) {
    if (isTutor) {
      binding.viewTutor.visibility = View.GONE
      binding.layoutIsTutor.visibility = View.VISIBLE
      binding.layoutNoTutor.visibility = View.GONE
      return
    }

    binding.viewTutor.visibility = View.VISIBLE
    binding.viewTutorInfo.visibility = View.GONE
    binding.layoutIsTutor.visibility = View.VISIBLE
    binding.layoutIsTutor.visibility = View.GONE
    binding.layoutNoTutor.visibility = View.VISIBLE

  }

  private fun makeItemsVisible() {
    binding.progressCircular.visibility = View.GONE
    binding.username.visibility = View.VISIBLE
    binding.usernameEmail.visibility = View.VISIBLE
    binding.linearContainer.visibility = View.VISIBLE
  }

  private fun handlerEvents() {
    binding.actionFavoriteTutors.setOnClickListener {
      findNavController().navigate(R.id.favoriteFragment)
    }
    binding.actionConvertToTutor.setOnClickListener {
      findNavController().navigate(R.id.landingFormFragment)

    }
    binding.actionLogout.setOnClickListener {
      val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestEmail()
        .build()

      val mGoogleSignInClient = GoogleSignIn.getClient(requireActivity(), gso);
      mGoogleSignInClient.signOut().addOnCompleteListener {
        val intent = Intent(requireContext(), MainActivity::class.java)
        requireActivity().startActivity(intent)
        requireActivity().finish()
        TearnApplication.prefs.wipe()
      }

    }
    binding.actionInformation.setOnClickListener {
      findNavController().navigate(R.id.informationFragment)
    }


  }


}