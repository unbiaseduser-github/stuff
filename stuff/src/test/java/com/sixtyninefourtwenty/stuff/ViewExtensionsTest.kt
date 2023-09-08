@file:Suppress("unused")

package com.sixtyninefourtwenty.stuff

import android.app.Application
import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.GridLayout
import android.widget.LinearLayout
import androidx.drawerlayout.widget.DrawerLayout
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@Config(manifest = Config.NONE)
@RunWith(AndroidJUnit4::class)
class ViewExtensionsTest {

    private lateinit var view: View

    @Before
    fun init() {
        val context: Application = ApplicationProvider.getApplicationContext()
        view = View(context)
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

}