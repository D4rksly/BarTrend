package com.example.bartrend.ui.cocktail.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bartrend.R
import com.example.bartrend.databinding.FragmentCocktailListBinding
import com.example.bartrend.domain.datasource.CocktailDataSource
import com.example.bartrend.domain.repository.CocktailRepository
import com.example.bartrend.ui.cocktail.CocktailViewModel
import com.example.bartrend.utils.ViewModelFactory

class CocktailListFragment: Fragment(R.layout.fragment_cocktail_list) {

    private lateinit var viewModel: CocktailViewModel
    private lateinit var binding: FragmentCocktailListBinding
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewModel = ViewModelFactory.bind<CocktailViewModel, CocktailRepository, CocktailDataSource>(this)
        binding = FragmentCocktailListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        with(binding) {

        }
    }
}