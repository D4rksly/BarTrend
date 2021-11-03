package com.example.bartrend.ui.cocktail.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bartrend.R
import com.example.bartrend.databinding.FragmentCocktailListBinding
import com.example.bartrend.ui.cocktail.CocktailViewModel
import com.example.bartrend.ui.cocktail.CocktailViewModel.State
import com.example.bartrend.ui.cocktail.adapter.CocktailsAdapter
import com.example.bartrend.ui.cocktail.adapter.CocktailsAdapterDelegate
import com.example.bartrend.ui.cocktail.model.CocktailModel
import com.example.bartrend.ui.cocktail.model.FavoriteCocktailModel
import com.example.bartrend.utils.ViewModelFactory
import com.example.bartrend.utils.extensions.getComponent
import com.example.bartrend.utils.extensions.viewBinding
import javax.inject.Inject

class CocktailListFragment: Fragment(R.layout.fragment_cocktail_list), CocktailsAdapterDelegate {

    @Inject
    internal lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: CocktailViewModel by viewModels { viewModelFactory }
    private val binding by viewBinding<FragmentCocktailListBinding>()

    private var cocktails: List<CocktailModel>? = null
    private var favoriteCocktails: List<FavoriteCocktailModel>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getComponent().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
        setAdapters()
        setListeners()
    }

    private fun setObservers() {
        viewModel.getCocktails().observe(viewLifecycleOwner, ::observe)
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
                    observe(it)
                    cocktailsListRefresh.isRefreshing = false
                }
            }
        }
    }

    private fun observe(state: State) {
        when (state) {
            is State.CocktailSuccess -> {
                with(binding) {
                    cocktailsList.adapter = CocktailsAdapter(state.cocktails, this@CocktailListFragment)
                }
            }
            is State.FavoriteSuccess -> { /* Unreachable */ }
            is State.SetFavoriteSuccess -> Toast.makeText(context, "Saved Successfully", Toast.LENGTH_SHORT).show()
            is State.Error -> Toast.makeText(context, state.error, Toast.LENGTH_SHORT).show()
            is State.Loading -> Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onToggleFavorite(isFavorite: Boolean, cocktailId: Int) {
        viewModel.setFavorite(cocktailId, isFavorite).observe(viewLifecycleOwner, Observer(::observe))
    }
}