package com.example.searchfromdatabase.fragment

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.searchfromdatabase.R
import com.example.searchfromdatabase.adapter.ResultAdapter
import com.example.searchfromdatabase.databinding.FragmentSearchBinding
import com.example.searchfromdatabase.model.VideoInfoData
import com.example.searchfromdatabase.viewModel.SearchViewModel
import kotlinx.coroutines.*
import kotlin.math.log


class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: SearchViewModel
    private lateinit var adapter: ResultAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ResultAdapter()
        viewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        addDataInDatabase()
        initAdapter()
        initListener()

    }

    private fun initListener() {
        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    searchDataBase(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    searchDataBase(newText)
                } else {
                    searchDataBase("")
                }
                return true
            }
        })
    }

    private fun addDataInDatabase() {
        viewModel.addVideoInfo(VideoInfoData("1", "suraj"))
        viewModel.addVideoInfo(VideoInfoData("2", "rahul"))
        viewModel.addVideoInfo(VideoInfoData("3", "vivek"))
        viewModel.addVideoInfo(VideoInfoData("4", "davendra"))
    }

    private fun initAdapter() {
        binding.resultRv.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.resultRv.adapter = adapter
    }

    private fun searchDataBase(searchItem: String) {
        val searchQuery = "%${searchItem.trim()}%"
        Log.e("searchItem", "${searchItem.trim()}")
        if (!searchItem.isNullOrEmpty()) {
            viewModel.getVideoInfo(searchQuery).observe(this) { list ->
                list.let {
                    Log.e("list", "$it")
                    if (it.isNotEmpty()) {
                        adapter.setData(it)
                    }else{
                        adapter.setData(ArrayList<VideoInfoData>().apply {
                            add(VideoInfoData("001","No search result found"))
                        })
                    }

                }
            }
        } else {
            adapter.setData(ArrayList<VideoInfoData>().apply {
                add(VideoInfoData("001", "No search result found"))
            })
        }

    }


}