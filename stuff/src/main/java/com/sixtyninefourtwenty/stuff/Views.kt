@file:JvmName("Views")
@file:Suppress("unused")

package com.sixtyninefourtwenty.stuff

import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.annotation.GravityInt
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.textfield.TextInputLayout
import com.sixtyninefourtwenty.stuff.annotations.NotSuitableForJava
import com.sixtyninefourtwenty.stuff.listeners.TextWatcherAdapter

/**
 * Sets [gravity] to a layout params that supports setting gravity:
 * - [FrameLayout.LayoutParams]
 * - [LinearLayout.LayoutParams]
 * - [DrawerLayout.LayoutParams]
 * - [GridLayout.LayoutParams]
 * @return true if the params is one of the above subclasses, false if not (the view's params is unchanged
 * if this method returns false)
 */
fun View.updateGravity(@GravityInt gravity: Int): Boolean {
    val params = layoutParams
    val success = when (params) {
        is FrameLayout.LayoutParams -> {
            params.gravity = gravity
            true
        }
        is LinearLayout.LayoutParams -> {
            params.gravity = gravity
            true
        }
        is DrawerLayout.LayoutParams -> {
            params.gravity = gravity
            true
        }
        is GridLayout.LayoutParams -> {
            params.setGravity(gravity)
            true
        }
        else -> false
    }
    if (success) {
        layoutParams = params
    }
    return success
}

/**
 * Sets [gravity] to an arbitrary layout params. The params must have either a public `int` field
 * named `gravity` or a public method named `setGravity` that takes a parameter of type `int`.
 * @return true if the params satisfies the above condition, false if not (the view's params is unchanged
 * if this method returns false)
 */
fun View.forceUpdateGravity(@GravityInt gravity: Int): Boolean {
    val params = layoutParams
    val clazz = layoutParams.javaClass
    val success = try {
        clazz.getField("gravity").setInt(params, gravity)
        true
    } catch (_: ReflectiveOperationException) {
        try {
            clazz.getMethod("setGravity", Int::class.javaPrimitiveType).invoke(params, gravity)
            true
        } catch (_: ReflectiveOperationException) {
            false
        }
    }
    if (success) {
        layoutParams = params
    }
    return success
}

fun ViewGroup.childrenIterable() = object : Iterable<View> {
    override fun iterator(): Iterator<View> = ViewGroupIterator(this@childrenIterable)
}

fun ViewGroup.mutableChildrenIterable() = object : MutableIterable<View> {
    override fun iterator(): MutableIterator<View> = MutableViewGroupIterator(this@mutableChildrenIterable)
}

private class MutableViewGroupIterator(private val viewGroup: ViewGroup) : ViewGroupIterator(viewGroup), MutableIterator<View> {

    override fun remove() {
        check(index >= 0)
        viewGroup.removeViewAt(index)
        index--
    }

}

private open class ViewGroupIterator(private val viewGroup: ViewGroup) : Iterator<View> {

    protected var index = -1

    override fun hasNext(): Boolean = viewGroup.childCount > index + 1

    override fun next(): View {
        if (!hasNext()) {
            throw NoSuchElementException()
        }
        index++
        return viewGroup.getChildAt(index)
    }

}

fun EditText.isBlank() = text == null || text.toString().isBlank()

fun EditText.getInput() = text?.toString()?.trim().orEmpty()

fun EditText.addTextWatcherSetTextInputLayoutError(
    til: TextInputLayout,
    errorTextFunction: (CharSequence) -> CharSequence?
) = addTextChangedListener(TextWatcherSetTextInputLayoutError(til, errorTextFunction))

private class TextWatcherSetTextInputLayoutError(
    private val til: TextInputLayout,
    private val errorTextFunction: (CharSequence) -> CharSequence?
) : TextWatcherAdapter {

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        til.error = errorTextFunction(s)
    }

}

@NotSuitableForJava(reason = "Property wrapper for getDrawable() and setImageDrawable().")
var ImageView.drawableContent: Drawable?
    get() = drawable
    set(value) { setImageDrawable(value) }

@JvmOverloads
fun View.toggleVisibility(visibilityToToggleTo: Int = View.GONE) {
    require(visibilityToToggleTo != View.VISIBLE) {
        "visibilityToToggleTo must be View.GONE or View.VISIBLE"
    }
    visibility = if (visibility == View.VISIBLE) {
        visibilityToToggleTo
    } else {
        View.VISIBLE
    }
}
