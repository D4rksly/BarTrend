package com.example.bartrend.ui.cocktail.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bartrend.R
import com.example.bartrend.databinding.FragmentCocktailListBinding
import com.example.bartrend.domain.datasource.CocktailDataSource
import com.example.bartrend.domain.repository.CocktailRepository
import com.example.bartrend.ui.cocktail.CocktailViewModel
import com.example.bartrend.ui.cocktail.CocktailViewModel.State
import com.example.bartrend.ui.cocktail.adapter.CocktailsAdapter
import com.example.bartrend.utils.ViewModelFactory
import com.example.bartrend.utils.extensions.viewBinding

class CocktailListFragment: Fragment(R.layout.fragment_cocktail_list) {

    private lateinit var viewModel: CocktailViewModel
    private val binding by viewBinding<FragmentCocktailListBinding>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelFactory.bind<CocktailViewModel, CocktailRepository, CocktailDataSource>(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
        setAdapters()
        setListeners()
    }

    private fun setObservers() {
        viewModel.getCocktails().observe(viewLifecycleOwner, Observer(::observeCocktails))
    }

    private fun setAdapters() {
        with(binding) {
            cocktailsList.layoutManager = GridLayoutManager(context, 2)
        }
    }

    private fun setListeners() {
        with(binding) {
            cocktailsListRefresh.setOnRefreshListener {
                viewModel.getCocktails().observe(viewLifecycleOwner) {
                    observeCocktails(it)
                    cocktailsListRefresh.isRefreshing = false
                }
            }
        }
    }

    private fun observeCocktails(state: State) {
        when (state) {
            is State.Success -> {
                with(binding) {
                    cocktailsList.adapter = CocktailsAdapter(state.cocktails)
                }
            }
            is State.Error -> Toast.makeText(context, state.error, Toast.LENGTH_SHORT).show()
            is State.Loading -> Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
        }
    }
}