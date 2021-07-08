package com.tearnsv.tearnapp.ui.tutorPerfil

import android.content.res.ColorStateList
import android.graphics.Color
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
import com.tearnsv.tearnapp.data.Commentary
import com.tearnsv.tearnapp.databinding.FragmentTutorProfileBinding
import com.tearnsv.tearnapp.ui.home.HomeViewModelFactory

/**
 * A simple [Fragment] subclass.
 * Use the [TutorProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TutorProfileFragment : Fragment() {

  private var _binding: FragmentTutorProfileBinding? = null
  private val binding get() = _binding!!
  private var idTutor: String? = null

  private val application by lazy {
    requireActivity().application as TearnApplication
  }

  private val tutorFavoriteViewModelFactory: TutorFavoriteViewModelFactory by lazy {
    val repository = application.tearnRepository
    TutorFavoriteViewModelFactory(repository)
  }

  private val tutorFavoriteViewModel: TutorFavoriteViewModel by activityViewModels {
    tutorFavoriteViewModelFactory
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
    tutorProfileViewModel.setId(idTutor ?: tutorProfileViewModel.ID_TUTOR.value!!)
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _binding = FragmentTutorProfileBinding.inflate(inflater, container, false).apply {
      lifecycleOwner = viewLifecycleOwner
      viewModel = tutorProfileViewModel
    }
    wipe()
    return binding.root
  }

  private fun wipe() {
    tutorProfileViewModel.closeValoration()
    tutorProfileViewModel.toastMessage.value = ""
    tutorProfileViewModel.puntuation.value = 0
    tutorProfileViewModel.valoration.value = ""
    tutorProfileViewModel.userCommentary.value = null
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    val valorationAdapter = TutorCommentariesAdapter()

    setValorationStar()
    observers()

    binding.recycleViewCommentaries.apply {
      layoutManager = LinearLayoutManager(requireContext())
      adapter = valorationAdapter
    }

    tutorProfileViewModel.tutor.observe(viewLifecycleOwner) {

      tutorProfileViewModel.userCommentary.value =
        it.commentaries?.filter { c -> c.author.id == TearnApplication.prefs.getId() }?.getOrNull(0)

      Glide.with(view)
        .load(it.imgUrl)
        .centerCrop()
        .placeholder(R.drawable.default_photo)
        .into(binding.imageTutorPerfil)

      setColorFav()

      binding.labelTutorPerfilName.text = it.fullName
      binding.labelTutorPerfilDescription.text = it.description

      var languages = formatList(it.languages!!)
      binding.labelTutorPerfilLanguages.text = languages

      if (it.active!!) {
        binding.iconActive.setColorFilter(Color.parseColor("#67EA67"))
      } else binding.iconActive.setColorFilter(Color.parseColor("#ff0000"))

      binding.labelTutorPerfilTimeRequest.text = it.responseTime

      val availability = formatList(it.availability!!)
      binding.labelTutorPerfilDisponibility.text = availability

      binding.labelTutorPerfilPuntuation.text = it.puntuation.toString()
      puntuationStar(it.puntuation!!)

      valorationAdapter.setData(it.commentaries!!.filter { commentary ->
        commentary.author.id != TearnApplication.prefs.getId()
      }
      )
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

    binding.floatingActionFavBtn.setOnClickListener {
      tutorFavoriteViewModel.addOrRemoveFav(tutorProfileViewModel.ID_TUTOR.value!!)
    }
  }

  private fun puntuationStar(puntuation: Int) {
    when (puntuation) {
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
  }

  private fun setValorationStar() {
    binding.valorationStar1.setOnClickListener {
      binding.valorationStar1.setImageResource(R.drawable.ic_baseline_star_rate_24)
      binding.valorationStar2.setImageResource(R.drawable.ic_baseline_star_outline_24)
      binding.valorationStar3.setImageResource(R.drawable.ic_baseline_star_outline_24)
      binding.valorationStar4.setImageResource(R.drawable.ic_baseline_star_outline_24)
      binding.valorationStar5.setImageResource(R.drawable.ic_baseline_star_outline_24)
      tutorProfileViewModel.addPuntuation(1)
    }

    binding.valorationStar2.setOnClickListener {
      binding.valorationStar1.setImageResource(R.drawable.ic_baseline_star_rate_24)
      binding.valorationStar2.setImageResource(R.drawable.ic_baseline_star_rate_24)
      binding.valorationStar3.setImageResource(R.drawable.ic_baseline_star_outline_24)
      binding.valorationStar4.setImageResource(R.drawable.ic_baseline_star_outline_24)
      binding.valorationStar5.setImageResource(R.drawable.ic_baseline_star_outline_24)
      tutorProfileViewModel.addPuntuation(2)
    }

    binding.valorationStar3.setOnClickListener {
      binding.valorationStar1.setImageResource(R.drawable.ic_baseline_star_rate_24)
      binding.valorationStar2.setImageResource(R.drawable.ic_baseline_star_rate_24)
      binding.valorationStar3.setImageResource(R.drawable.ic_baseline_star_rate_24)
      binding.valorationStar4.setImageResource(R.drawable.ic_baseline_star_outline_24)
      binding.valorationStar5.setImageResource(R.drawable.ic_baseline_star_outline_24)
      tutorProfileViewModel.addPuntuation(3)
    }

    binding.valorationStar4.setOnClickListener {
      binding.valorationStar1.setImageResource(R.drawable.ic_baseline_star_rate_24)
      binding.valorationStar2.setImageResource(R.drawable.ic_baseline_star_rate_24)
      binding.valorationStar3.setImageResource(R.drawable.ic_baseline_star_rate_24)
      binding.valorationStar4.setImageResource(R.drawable.ic_baseline_star_rate_24)
      binding.valorationStar5.setImageResource(R.drawable.ic_baseline_star_outline_24)
      tutorProfileViewModel.addPuntuation(4)
    }

    binding.valorationStar5.setOnClickListener {
      binding.valorationStar1.setImageResource(R.drawable.ic_baseline_star_rate_24)
      binding.valorationStar2.setImageResource(R.drawable.ic_baseline_star_rate_24)
      binding.valorationStar3.setImageResource(R.drawable.ic_baseline_star_rate_24)
      binding.valorationStar4.setImageResource(R.drawable.ic_baseline_star_rate_24)
      binding.valorationStar5.setImageResource(R.drawable.ic_baseline_star_rate_24)
      tutorProfileViewModel.addPuntuation(5)
    }
  }

  private fun formatList(list: List<String>): String {
    var text = ""
    list.forEach {
      text += "${it.toLowerCase().capitalize()}, "
    }

    return text.dropLast(2)
  }

  private fun setColorFav() {
    var exists = false
    tutorFavoriteViewModel.favTutors.value!!.forEach {
      if (it.idTutor == tutorProfileViewModel.ID_TUTOR.value!!) exists = true
    }

    if (exists)
      binding.floatingActionFavBtn.backgroundTintList =
        ColorStateList.valueOf(Color.parseColor("#ff0000"))
    else
      binding.floatingActionFavBtn.backgroundTintList =
        ColorStateList.valueOf(Color.parseColor("#707070"))
  }

  private fun observers() {

    tutorFavoriteViewModel.favTutors.observe(viewLifecycleOwner) {
      setColorFav()
    }

    tutorProfileViewModel.puntuation.observe(viewLifecycleOwner) {
      puntuationStar(it)
      updateUserStars(it)
    }

    tutorProfileViewModel.toastMessage.observe(viewLifecycleOwner) {
      if (it.isNullOrEmpty())
        return@observe
      Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
    }
  }

  private fun updateUserStars(punctuation: Int) {
    val starList = mutableListOf<Int>()
    if (punctuation == 0) {
      setColorStar(listOf())
      return
    }
    var star: Int
    for (index in 0..punctuation) {
      star = if (punctuation >= index + 1) STAR_FILL else STAR_NOT_FILL
      starList.add(star)
    }
    setColorStar(starList)
  }

  private fun setColorStar(starList: List<Int>) {
    binding.valorationStar1.setImageResource(starList.getOrNull(0) ?: STAR_NOT_FILL)
    binding.valorationStar2.setImageResource(starList.getOrNull(1) ?: STAR_NOT_FILL)
    binding.valorationStar3.setImageResource(starList.getOrNull(2) ?: STAR_NOT_FILL)
    binding.valorationStar4.setImageResource(starList.getOrNull(3) ?: STAR_NOT_FILL)
    binding.valorationStar5.setImageResource(starList.getOrNull(4) ?: STAR_NOT_FILL)
  }

  companion object {
    const val TUTOR_ID = "TUTOR_ID"
    const val STAR_FILL = R.drawable.ic_baseline_star_rate_24
    const val STAR_NOT_FILL = R.drawable.ic_baseline_star_outline_24
  }

}