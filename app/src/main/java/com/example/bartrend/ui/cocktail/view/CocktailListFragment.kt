package com.example.bartrend.ui.cocktail.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.bartrend.R
import com.example.bartrend.databinding.FragmentCocktailListBinding
import com.example.bartrend.domain.datasource.CocktailDataSource
import com.example.bartrend.domain.repository.CocktailRepository
import com.example.bartrend.ui.cocktail.CocktailViewModel
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



        with(binding) {

        }
    }
}