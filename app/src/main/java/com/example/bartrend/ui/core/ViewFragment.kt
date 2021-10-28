package com.example.bartrend.ui.core

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class ViewFragment(@LayoutRes private val contentLayoutId: Int): Fragment(contentLayoutId) {

}