@file:JvmName("Views")
@file:Suppress("unused")

package com.sixtyninefourtwenty.stuff

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import com.google.android.material.textfield.TextInputLayout
import com.sixtyninefourtwenty.stuff.annotations.NotSuitableForJava
import com.sixtyninefourtwenty.stuff.listeners.TextWatcherAdapter

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
