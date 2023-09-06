package com.sixtyninefourtwenty.stuff.listeners

import androidx.appcompat.widget.SearchView

@Suppress("unused")
fun interface ChangeOnlyQueryTextListener : SearchView.OnQueryTextListener {

    override fun onQueryTextChange(newText: String): Boolean {
        onQueryTextChangeCustom(newText)
        return true
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        return false
    }

    fun onQueryTextChangeCustom(newText: String)

    companion object {
        @JvmStatic
        fun changeOnly(block: ChangeOnlyQueryTextListener): SearchView.OnQueryTextListener = block
    }

}