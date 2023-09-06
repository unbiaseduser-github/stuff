package com.sixtyninefourtwenty.stuff.listeners

import android.view.View

@Suppress("unused")
fun interface ShortedOnLongClickListener : View.OnLongClickListener {

    override fun onLongClick(v: View): Boolean {
        onClickCustom(v)
        return true
    }

    fun onClickCustom(v: View)

    companion object {
        @JvmStatic
        fun shorten(block: ShortedOnLongClickListener): View.OnLongClickListener = block
    }

}