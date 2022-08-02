package com.example.weather.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.example.weather.databinding.FragmentSearchBinding
import com.example.weather.model.repository.remote.LocationResponse
import com.example.weather.utils.KeyboardManager
import com.example.weather.utils.TAG
import com.example.weather.utils.WrapContentLinearLayoutManager
import com.example.weather.viewmodel.SearchViewModel

class SearchFragment : Fragment(), SearchFragmentListener
{
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SearchViewModel by viewModels()
    private val _adapter = SearchAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View
    {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        init()
    }

    private fun init()
    {
        setActionUp()
        addImeOptionsListener()
        setDataBindingModels()
        setupRecyclerView()
        observeViewModels()
    }

    private fun setActionUp()
    {
        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbarSearch.toolbar.setupWithNavController(navController, appBarConfiguration)
    }

    private fun addImeOptionsListener()
    {
        binding.toolbarSearch.editTextSearchToolbarQuery.setOnEditorActionListener { v, actionId, event ->
            return@setOnEditorActionListener when (actionId)
            {
                EditorInfo.IME_ACTION_SEARCH ->
                {
                    search()
                    KeyboardManager.hideKeyboard(context)
                    true
                }
                else -> false
            }
        }
    }

    private fun search()
    {
        val input = binding.toolbarSearch.editTextSearchToolbarQuery.text.toString()
        fetchWeather(input)
    }

    private fun fetchWeather(city: String)
    {
        viewModel.fetchLocations(city)
    }

    private fun setDataBindingModels()
    {
        binding.toolbarSearch.listener = this
    }

    private fun setupRecyclerView()
    {
        if (context == null) return
        binding.recyclerViewSearch.apply {
            layoutManager = WrapContentLinearLayoutManager(context)
            adapter = _adapter
            setHasFixedSize(true)
            setItemViewCacheSize(20)
        }
    }

    private fun observeViewModels()
    {
        viewModel.locationResponses.observe(viewLifecycleOwner) {
            _adapter.update(it)
            updateNoResultVisibility(it)
        }
        viewModel.loading.observe(viewLifecycleOwner) {
            binding.progressBarSearch.visibility = if (it) View.VISIBLE else View.GONE
        }
        viewModel.loadingError.observe(viewLifecycleOwner) {
            binding.textViewSearchError.visibility = if (it) View.VISIBLE else View.GONE
            if(it)
                _adapter.update(arrayListOf())
        }
    }

    private fun updateNoResultVisibility(it: List<LocationResponse>)
    {
        if (it.isEmpty())
            binding.textViewSearchNoResult.visibility = View.VISIBLE
        else
            binding.textViewSearchNoResult.visibility = View.GONE
    }

    override fun onClearEditText()
    {
        binding.toolbarSearch.editTextSearchToolbarQuery.setText("")
    }

    override fun onDestroyView()
    {
        super.onDestroyView()
        _binding = null
    }
}

interface SearchFragmentListener
{
    fun onClearEditText()
}