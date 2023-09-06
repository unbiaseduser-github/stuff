@file:JvmName("Fragments")
@file:Suppress("unused")

package com.sixtyninefourtwenty.stuff

import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner

@JvmOverloads
fun Fragment.addMenuProvider(provider: MenuProvider, lifecycleOwner: LifecycleOwner? = viewLifecycleOwner) =
    if (lifecycleOwner != null) {
        requireActivity().addMenuProvider(provider, lifecycleOwner)
    } else {
        requireActivity().addMenuProvider(provider)
    }
