@file:Suppress("unused")

package com.sixtyninefourtwenty.stuff

import android.app.Application
import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.core.view.get
import androidx.drawerlayout.widget.DrawerLayout
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.android.material.button.MaterialButton
import com.google.android.material.button.MaterialButtonToggleGroup
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertThrows
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@Config(manifest = Config.NONE)
@RunWith(AndroidJUnit4::class)
class ViewExtensionsTest {

    private lateinit var view: View
    private lateinit var viewGroup: ViewGroup
    private lateinit var radioGroup: RadioGroup
    private lateinit var materialButtonToggleGroup: MaterialButtonToggleGroup

    @Before
    fun init() {
        val context = ApplicationProvider.getApplicationContext<Application>().apply {
            setTheme(com.google.android.material.R.style.Theme_Material3_DynamicColors_DayNight)
        }
        view = View(context)
        viewGroup = LinearLayout(context).apply {
            addView(TextView(context))
            addView(ImageView(context))
            addView(Button(context))
        }
        /* Robolectric doesn't support View.generateViewId() so i have to create a dummy layout
        (test_layout.xml) to generate ID resources. Fun.
        */
        radioGroup = RadioGroup(context).apply {
            val resIds = intArrayOf(R.id.rb1, R.id.rb2, R.id.rb3)
            repeat(3) { index ->
                addView(RadioButton(context).apply {
                    id = resIds[index]
                })
            }
        }
        materialButtonToggleGroup = MaterialButtonToggleGroup(context).apply {
            val resIds = intArrayOf(R.id.mb1, R.id.mb2, R.id.mb3)
            repeat(3) { index ->
                addView(MaterialButton(context).apply {
                    id = resIds[index]
                })
            }
        }
    }

    @Test
    fun updateGravity() {
        view.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        assertFalse(view.updateGravity(Gravity.BOTTOM))

        view.layoutParams = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        assertTrue(view.updateGravity(Gravity.BOTTOM))
        view.layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        assertTrue(view.updateGravity(Gravity.BOTTOM))
        view.layoutParams = DrawerLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        assertTrue(view.updateGravity(Gravity.BOTTOM))
        view.layoutParams = GridLayout.LayoutParams(GridLayout.spec(0), GridLayout.spec(0))
        assertTrue(view.updateGravity(Gravity.BOTTOM))

        view.layoutParams = LayoutParamsWithGravityField(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        assertFalse(view.updateGravity(Gravity.BOTTOM))
        view.layoutParams = LayoutParamsWithSetGravityMethod(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        assertFalse(view.updateGravity(Gravity.BOTTOM))
    }

    @Test
    fun forceUpdateGravity() {
        view.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        assertFalse(view.forceUpdateGravity(Gravity.BOTTOM))

        view.layoutParams = DrawerLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        assertTrue(view.forceUpdateGravity(Gravity.BOTTOM))
        view.layoutParams = GridLayout.LayoutParams(GridLayout.spec(0), GridLayout.spec(0))
        assertTrue(view.forceUpdateGravity(Gravity.BOTTOM))

        view.layoutParams = LayoutParamsWithGravityField(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        assertTrue(view.forceUpdateGravity(Gravity.BOTTOM))
        view.layoutParams = LayoutParamsWithSetGravityMethod(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        assertTrue(view.forceUpdateGravity(Gravity.BOTTOM))
    }

    private class LayoutParamsWithGravityField : ViewGroup.LayoutParams {

        @JvmField
        var gravity: Int = Gravity.NO_GRAVITY

        constructor(c: Context, attrs: AttributeSet?): super(c, attrs)

        constructor(width: Int, height: Int): super(width, height)

        constructor(source: ViewGroup.LayoutParams): super(source)

    }

    private class LayoutParamsWithSetGravityMethod : ViewGroup.LayoutParams {

        fun setGravity(gravity: Int) {
            //empty
        }

        constructor(c: Context, attrs: AttributeSet?): super(c, attrs)

        constructor(width: Int, height: Int): super(width, height)

        constructor(source: ViewGroup.LayoutParams): super(source)

    }

    @Test
    fun childrenIterable() {
        val it = viewGroup.childrenIterable().iterator()
        assertTrue(it.hasNext())
        assertTrue(it.next() is TextView)
        assertTrue(it.hasNext())
        assertTrue(it.next() is ImageView)
        assertTrue(it.hasNext())
        assertTrue(it.next() is Button)
        assertFalse(it.hasNext())
    }

    @Test
    fun mutableChildrenIterableRemoveSequentially() {
        assertEquals(3, viewGroup.childCount)
        val it = viewGroup.mutableChildrenIterable().iterator()
        assertThrows(IllegalStateException::class.java) { it.remove() }
        val first = it.next()
        assertTrue(first is TextView)
        it.remove()
        assertEquals(2, viewGroup.childCount)
        val second = it.next()
        assertTrue(second is ImageView)
        it.remove()
        assertEquals(1, viewGroup.childCount)
        val third = it.next()
        assertTrue(third is Button)
        it.remove()
        assertEquals(0, viewGroup.childCount)
    }

    @Test
    fun mutableChildrenIterableRemoveMiddle() {
        assertEquals(3, viewGroup.childCount)
        val it = viewGroup.mutableChildrenIterable().iterator()
        it.next()
        it.next()
        it.remove()
        assertEquals(2, viewGroup.childCount)
        assertTrue(viewGroup.getChildAt(0) is TextView)
        assertTrue(viewGroup.getChildAt(1) is Button)
    }

    @Test
    fun radioGroupUncheckedThenGetCheckedIndex() {
        radioGroup.clearCheck()
        assertEquals(-1, radioGroup.checkedIndex)
    }

    @Test
    fun radioGroupCheckManuallyThenGetCheckedIndex() {
        radioGroup.check(radioGroup[0].id)
        assertEquals(0, radioGroup.checkedIndex)
    }

    @Test
    fun radioGroupCheckIndexThenGetCheckedIndex() {
        radioGroup.checkIndex(1)
        assertEquals(1, radioGroup.checkedIndex)
    }

    @Test
    fun radioGroupCheckIndexThenGetCheckedIndexManually() {
        radioGroup.checkIndex(2)
        assertEquals(radioGroup[2].id, radioGroup.checkedRadioButtonId)
    }

    @Test
    fun mbtgUncheckedThenGetCheckedIndex() {
        materialButtonToggleGroup.isSingleSelection = true
        materialButtonToggleGroup.clearChecked()
        assertEquals(-1, materialButtonToggleGroup.checkedIndex)
    }

    @Test
    fun mbtgCheckManuallyThenGetCheckedIndex() {
        materialButtonToggleGroup.isSingleSelection = true
        materialButtonToggleGroup.check(materialButtonToggleGroup[0].id)
        assertEquals(0, materialButtonToggleGroup.checkedIndex)
    }

    @Test
    fun mbtgCheckIndexThenGetCheckedIndex() {
        materialButtonToggleGroup.isSingleSelection = true
        materialButtonToggleGroup.checkIndex(1)
        assertEquals(1, materialButtonToggleGroup.checkedIndex)
    }

    @Test
    fun mbtgCheckIndexThenGetCheckedIndexManually() {
        materialButtonToggleGroup.isSingleSelection = true
        materialButtonToggleGroup.checkIndex(2)
        assertEquals(materialButtonToggleGroup[2].id, materialButtonToggleGroup.checkedButtonId)
    }

    @Test
    fun mbtgUncheckedThenGetCheckedIndices() {
        materialButtonToggleGroup.clearChecked()
        assertTrue(materialButtonToggleGroup.checkedIndices.isEmpty())
    }

    @Test
    fun mbtgCheckManuallyThenGetCheckedIndices() {
        materialButtonToggleGroup.check(materialButtonToggleGroup[0].id)
        materialButtonToggleGroup.check(materialButtonToggleGroup[2].id)
        assertEquals(listOf(0, 2), materialButtonToggleGroup.checkedIndices)
    }

    @Test
    fun mbtgCheckIndicesThenGetCheckedIndices() {
        materialButtonToggleGroup.checkIndices(intArrayOf(1, 2))
        assertEquals(listOf(1, 2), materialButtonToggleGroup.checkedIndices)
    }

    @Test
    fun mbtgCheckIndicesThenGetCheckedIndicesManually() {
        materialButtonToggleGroup.checkIndices(intArrayOf(0, 1))
        assertEquals(
            listOf(
                materialButtonToggleGroup[0].id,
                materialButtonToggleGroup[1].id
            ),
            materialButtonToggleGroup.checkedButtonIds
        )
    }

}