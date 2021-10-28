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
        fun bind(owner: ViewModelStoreOwner, viewModelContent: ViewModelContent, view): V {
            return ViewModelProvider(owner, ViewModelFactory(
                V::class.java,
                viewModelContent.repository,
                viewModelContent.dataSource
            )).get(V::class.java)
        }

        inline fun <reified V : ViewModel, reified R, reified D> bind(owner: ViewModelStoreOwner): V {
            return ViewModelProvider(owner, ViewModelFactory(
                V::class.java,
                R::class.java,
                D::class.java
            )).get(V::class.java)
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val dataSourceInstance = dataSource.newInstance()
        val repositoryInstance = repository.getConstructor(dataSource).newInstance(dataSourceInstance)
        return viewModel.getConstructor(repository).newInstance(repositoryInstance) as T
    }

}

inline fun <reified VM: ViewModel, reified R, reified DT>bindViewModel(): ViewModelContent {
    return ViewModelContent(VM::class.java, R::class.java, DT::class.java)
}

data class ViewModelContent(
    val viewModel: Class<*>,
    val repository: Class<*>,
    val dataSource: Class<*>
)