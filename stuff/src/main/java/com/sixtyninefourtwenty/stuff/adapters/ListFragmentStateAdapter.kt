package com.sixtyninefourtwenty.stuff.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

@Suppress("unused")
class ListFragmentStateAdapter(
    fragmentActivity: FragmentActivity,
    private val fragmentSuppliers: List<() -> Fragment>
) : FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment = fragmentSuppliers[position]()

    override fun getItemCount(): Int = fragmentSuppliers.size

}
