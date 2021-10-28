package com.example.bartrend.ui.core

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.example.bartrend.utils.ViewModelFactory
import java.lang.Exception

abstract class ViewFragment<VM: ViewModel>(@LayoutRes private val contentLayoutId: Int, val viewModelClass: Class<VM>): Fragment(contentLayoutId) {

    internal lateinit var viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = createViewModel()
    }

    private fun createViewModel(): VM {
        for (vm in viewModels) {
            if(vm.viewModel.name.equals(viewModelClass.name)) {
                return ViewModelFactory.bind(this, vm)
            }
        }
        throw Exception("View Model Structure is not defined in Navigation")
    }
}