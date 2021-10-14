package com.example.bartrend.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

class ViewModelFactory<V : ViewModel, R, D>(
    private val viewModel: Class<V>,
    private val repository: Class<R>,
    private val dataSource: Class<D>
): ViewModelProvider.Factory {

    companion object {
        inline fun <reified V : ViewModel, reified R, reified D> bind(owner: ViewModelStoreOwner): V {
            return ViewModelProvider(owner, ViewModelFactory(V::class.java, R::class.java, D::class.java)).get(V::class.java)
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val dataSourceInstance = dataSource.newInstance()
        val repositoryInstance = repository.getConstructor(dataSource).newInstance(dataSourceInstance)
        return viewModel.getConstructor(repository).newInstance(repositoryInstance) as T
    }

}
