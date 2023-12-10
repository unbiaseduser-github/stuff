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
import android.widget.RadioGroup
import androidx.annotation.GravityInt
import androidx.annotation.IdRes
import androidx.core.view.get
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.button.MaterialButton
import com.google.android.material.button.MaterialButtonToggleGroup
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

@IdRes
fun ViewGroup.getViewIdAt(index: Int): Int = get(index).id

fun ViewGroup.childrenIterable() = object : Iterable<View> {
    override fun iterator(): Iterator<View> = ViewGroupIterator(this@childrenIterable)
}

fun ViewGroup.mutableChildrenIterable() = object : MutableIterable<View> {
    override fun iterator(): MutableIterator<View> = MutableViewGroupIterator(this@mutableChildrenIterable)
}

private class MutableViewGroupIterator(viewGroup: ViewGroup) : ViewGroupIterator(viewGroup), MutableIterator<View> {

    override fun remove() {
        check(index >= 0)
        viewGroup.removeViewAt(index)
        index--
    }

}

private open class ViewGroupIterator(protected val viewGroup: ViewGroup) : Iterator<View> {

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

/**
 * The index of the selected radio button in this group, or -1 if selection is empty.
 */
val RadioGroup.checkedIndex: Int
    get() {
        val checkedId = checkedRadioButtonId
        if (checkedId == -1) {
            return -1
        }
        for (i in 0 ..< childCount) {
            if (getViewIdAt(i) == checkedId) {
                return i
            }
        }
        throw AssertionError("unreachable, there must be a button whose id is $checkedId")
    }

fun RadioGroup.checkIndex(index: Int) {
    check(getViewIdAt(index))
}

/**
 * The index of the selected button in this group, or -1 if selection is empty or not in
 * [single selection mode][MaterialButtonToggleGroup.isSingleSelection]
 */
val MaterialButtonToggleGroup.checkedIndex: Int
    get() {
        val checkedId = checkedButtonId
        if (checkedId == View.NO_ID) {
            return -1
        }
        for (i in 0 ..< childCount) {
            if (getViewIdAt(i) == checkedId) {
                return i
            }
        }
        throw AssertionError("unreachable, there must be a button whose id is $checkedId")
    }

fun MaterialButtonToggleGroup.checkIndex(index: Int) {
    check(getViewIdAt(index))
}

fun MaterialButtonToggleGroup.uncheckIndex(index: Int) {
    uncheck(getViewIdAt(index))
}

/**
 * The indices of the selected buttons in this group.
 */
val MaterialButtonToggleGroup.checkedIndices: List<Int>
    get() {
        return checkedButtonIds.map { checkedId ->
            for (i in 0 ..< childCount) {
                if (getViewIdAt(i) == checkedId) {
                    return@map i
                }
            }
            throw AssertionError("unreachable, there must be a button whose id is $checkedId")
        }
    }

/**
 * Sets the [MaterialButton]s with the specified [indices] to the checked state. If in
 * [single selection mode][MaterialButtonToggleGroup.isSingleSelection], the button in the last
 * index will be checked.
 */
fun MaterialButtonToggleGroup.checkIndices(indices: Iterable<Int>) {
    if (indices.none()) {
        return
    }
    if (isSingleSelection) {
        // Check last index to preserve behavior from earlier library versions
        check(getViewIdAt(indices.last()))
        return
    }
    indices.forEach { index ->
        check(getViewIdAt(index))
    }
}

/**
 * Sets the [MaterialButton]s with the specified [indices] to the checked state. If in
 * [single selection mode][MaterialButtonToggleGroup.isSingleSelection], the button in the last
 * index will be checked.
 */
fun MaterialButtonToggleGroup.checkIndices(indices: IntArray) {
    if (indices.none()) {
        return
    }
    if (isSingleSelection) {
        // Check last index to preserve behavior from earlier library versions
        check(getViewIdAt(indices.last()))
        return
    }
    indices.forEach { index ->
        check(getViewIdAt(index))
    }
}

/**
 * Sets the [MaterialButton]s with the specified [indices] to the unchecked state.
 */
fun MaterialButtonToggleGroup.uncheckIndices(indices: Iterable<Int>) {
    indices.forEach { index ->
        uncheck(getViewIdAt(index))
    }
}

/**
 * Sets the [MaterialButton]s with the specified [indices] to the unchecked state.
 */
fun MaterialButtonToggleGroup.uncheckIndices(indices: IntArray) {
    indices.forEach { index ->
        uncheck(getViewIdAt(index))
    }
}
