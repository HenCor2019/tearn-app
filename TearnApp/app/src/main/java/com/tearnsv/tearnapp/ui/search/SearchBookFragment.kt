package com.tearnsv.tearnapp.ui.search

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.tearnsv.tearnapp.R
import com.tearnsv.tearnapp.TearnApplication
import com.tearnsv.tearnapp.databinding.FragmentSearchBookBinding
import com.tearnsv.tearnapp.ui.bookadapter.BooksRVAdapter
import com.tearnsv.tearnapp.ui.search.viewmodel.SearchVMFactory
import com.tearnsv.tearnapp.ui.search.viewmodel.SearchViewModel

class SearchBookFragment : Fragment(), BooksRVAdapter.ItemClickListener {


    private lateinit var rvAdapter: BooksRVAdapter
    private var _binding: FragmentSearchBookBinding? = null
    private val binding get() = _binding!!

    private val application by lazy { requireActivity().application as TearnApplication }
    private val searchVMFactory: SearchVMFactory by lazy {
        val repository = application.tearnRepository
        SearchVMFactory(repository)
    }

    private val searchViewModel: SearchViewModel by activityViewModels { searchVMFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.bookViewModel = searchViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        setupRecyclerView()
        observers()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBookBinding.inflate(inflater, container, false)
        return binding.root
    }


    private fun setupRecyclerView() {
        rvAdapter = BooksRVAdapter(this)
        binding.recyclerViewBooksTutors.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = rvAdapter
        }
    }

    private fun observers() {
        searchViewModel.fetchBooksResponse.observe(viewLifecycleOwner) {
            rvAdapter.setData(it.items)
        }
    }

    override fun onClickItem(url: String) {
        val uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }

}