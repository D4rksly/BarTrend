package com.example.bartrend.ui.cocktail.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.bartrend.R
import com.example.bartrend.databinding.FragmentCocktailListBinding
import com.example.bartrend.domain.datasource.CocktailDataSource
import com.example.bartrend.domain.repository.CocktailRepository
import com.example.bartrend.ui.cocktail.CocktailViewModel
import com.example.bartrend.ui.cocktail.CocktailViewModel.State
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
        setListeners()
    }

    private fun setListeners() {
        viewModel.getCocktails().observe(viewLifecycleOwner, Observer(::observeCocktails))
    }

    private fun observeCocktails(state: State) {
        when (state) {
            is State.Success -> TODO("Add this to the list")
            is State.Error -> Toast.makeText(context, state.error, Toast.LENGTH_SHORT).show()
            is State.Loading -> Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
        }
    }
}