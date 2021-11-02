package com.example.bartrend.utils.extensions

import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.viewbinding.ViewBinding
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

inline fun <reified VB: ViewBinding> Fragment.viewBinding() = FragmentViewBindingDelegate(this, VB::class.java)

class FragmentViewBindingDelegate<T : ViewBinding>(private val fragment: Fragment, klazz: Class<T>) : ReadOnlyProperty<Fragment, T> {

    private var binding: T? = null
    private val bindMethod = klazz.getMethod("bind", View::class.java)

    init {
        fragment.lifecycle.addObserver(
            object : LifecycleEventObserver {
                override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                    if(event == Lifecycle.Event.ON_CREATE) {
                        fragment.viewLifecycleOwnerLiveData.observe(fragment, Observer { viewLifecycleOwner ->
                            viewLifecycleOwner.lifecycle.addObserver(ViewLifecycleBinding())
                        })
                    }
                }
            }
        )
    }

    @Suppress("UNCHECKED_CAST")
    override fun getValue(thisRef: Fragment, property: KProperty<*>): T =
        binding ?: (bindMethod.invoke(null, thisRef.requireView()) as T).also { binding = it }

    inner class ViewLifecycleBinding : LifecycleEventObserver {
        override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
            if(event == Lifecycle.Event.ON_DESTROY) {
                binding = null
                fragment.lifecycle.removeObserver(this)
            }
        }
    }

}