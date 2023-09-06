@file:JvmName("ListAdapters")
@file:Suppress("unused")

package com.sixtyninefourtwenty.stuff

import androidx.recyclerview.widget.ListAdapter
import java.util.function.Consumer

fun <T> ListAdapter<T, *>.copyOfCurrentList(): MutableList<T> = ArrayList(currentList)

@JvmOverloads
fun <T> ListAdapter<T, *>.modifyList(block: Consumer<in MutableList<T>>, callback: Runnable? = null) =
    submitList(copyOfCurrentList().apply(block::accept), callback)

@JvmOverloads
fun <T> ListAdapter<T, *>.addElement(element: T, callback: Runnable? = null) =
    modifyList({ it.add(element) }, callback)

@JvmOverloads
fun <T> ListAdapter<T, *>.replaceElement(existingElement: T, newElement: T, callback: Runnable? = null) =
    modifyList({ it.replaceAll { elem -> if (elem == existingElement) newElement else elem } }, callback)

@JvmOverloads
fun <T> ListAdapter<T, *>.removeElement(element: T, callback: Runnable? = null) =
    modifyList({ it.remove(element) }, callback)
