package com.example.bartrend.ui.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bartrend.ui.cocktail.CocktailViewModel
import com.example.bartrend.ui.cocktail.view.CocktailListFragment
import com.example.bartrend.ui.login.LoginViewModel
import com.example.bartrend.ui.login.view.LoginFragment
import com.example.bartrend.ui.login.view.RegisterFragment
import com.example.bartrend.utils.ViewModelFactory
import com.example.bartrend.utils.ViewModelKey
import dagger.*
import dagger.multibindings.IntoMap
import javax.inject.Provider
import javax.inject.Singleton

@Module
abstract class MainModule {
    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    internal abstract fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CocktailViewModel::class)
    internal abstract fun bindCocktailViewModel(viewModel: CocktailViewModel): ViewModel

}

@Singleton
@Component(modules = [ApplicationComponent.Module::class, MainModule::class])
interface ApplicationComponent {
    fun inject(activity: MainActivity)

    fun inject(fragment: LoginFragment)
    fun inject(fragment: RegisterFragment)

    fun inject(fragment: CocktailListFragment)

    @Component.Builder
    interface Builder {
        fun module(module: Module): Builder
        fun build(): ApplicationComponent
    }

    @dagger.Module
    class Module(private val activity: MainActivity) {
        @Singleton
        @Provides
        fun provideViewModelFactory(
            viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>
        ): ViewModelProvider.Factory = ViewModelFactory(viewModels)
    }
}