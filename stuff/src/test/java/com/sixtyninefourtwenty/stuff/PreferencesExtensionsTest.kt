package com.sixtyninefourtwenty.stuff

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sixtyninefourtwenty.stuff.preferences.FloatPreferenceValue
import com.sixtyninefourtwenty.stuff.preferences.IntPreferenceValue
import com.sixtyninefourtwenty.stuff.preferences.LongPreferenceValue
import com.sixtyninefourtwenty.stuff.preferences.StringPreferenceValue
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertIterableEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import java.util.EnumSet

private const val KEY = "foo"

@RunWith(AndroidJUnit4::class)
@Config(manifest = Config.NONE)
class PreferencesExtensionsTest {

    private enum class MyIntValue(override val value: Int) : IntPreferenceValue {
        FIRST(0), SECOND(1)
    }

    private enum class MyLongValue(override val value: Long) : LongPreferenceValue {
        FIRST(0), SECOND(1)
    }

    private enum class MyFloatValue(override val value: Float) : FloatPreferenceValue {
        FIRST(1.5F), SECOND(2.5F)
    }

    private enum class MyStringValue(override val value: String) : StringPreferenceValue {
        FIRST("first"), SECOND("second")
    }

    private lateinit var prefs: SharedPreferences

    @Before
    fun setup() {
        val context: Application = ApplicationProvider.getApplicationContext()
        prefs = context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
    }

    @Test
    fun intTest() {
        fun getDefaultIfNotPresent(defValue: MyIntValue) {
            assertEquals(defValue, prefs.getIntValue(KEY, MyIntValue.entries, defValue))
        }

        fun putAndGet(value: MyIntValue, defValue: MyIntValue) {
            prefs.editCommitting { putIntValue(KEY, value) }
            assertEquals(value, prefs.getIntValue(KEY, MyIntValue.entries, defValue))
        }

        getDefaultIfNotPresent(MyIntValue.FIRST)
        getDefaultIfNotPresent(MyIntValue.SECOND)
        putAndGet(MyIntValue.FIRST, MyIntValue.SECOND)
        putAndGet(MyIntValue.SECOND, MyIntValue.FIRST)
    }

    @Test
    fun longTest() {
        fun getDefaultIfNotPresent(defValue: MyLongValue) {
            assertEquals(defValue, prefs.getLongValue(KEY, MyLongValue.entries, defValue))
        }

        fun putAndGet(value: MyLongValue, defValue: MyLongValue) {
            prefs.editCommitting { putLongValue(KEY, value) }
            assertEquals(value, prefs.getLongValue(KEY, MyLongValue.entries, defValue))
        }

        getDefaultIfNotPresent(MyLongValue.FIRST)
        getDefaultIfNotPresent(MyLongValue.SECOND)
        putAndGet(MyLongValue.FIRST, MyLongValue.SECOND)
        putAndGet(MyLongValue.SECOND, MyLongValue.FIRST)
    }

    @Test
    fun floatTest() {
        fun getDefaultIfNotPresent(defValue: MyFloatValue) {
            assertEquals(defValue, prefs.getFloatValue(KEY, MyFloatValue.entries, defValue))
        }

        fun putAndGet(value: MyFloatValue, defValue: MyFloatValue) {
            prefs.editCommitting { putFloatValue(KEY, value) }
            assertEquals(value, prefs.getFloatValue(KEY, MyFloatValue.entries, defValue))
        }

        getDefaultIfNotPresent(MyFloatValue.FIRST)
        getDefaultIfNotPresent(MyFloatValue.SECOND)
        putAndGet(MyFloatValue.FIRST, MyFloatValue.SECOND)
        putAndGet(MyFloatValue.SECOND, MyFloatValue.FIRST)
    }

    @Test
    fun stringTest() {
        assertNull(prefs.getStringOrNull(KEY))
        assertEquals("foo", prefs.getNonNullString(KEY, "foo"))

        fun getDefaultIfNotPresent(defValue: MyStringValue) {
            assertEquals(defValue, prefs.getStringValue(KEY, MyStringValue.entries, defValue))
        }

        fun putAndGet(value: MyStringValue, defValue: MyStringValue) {
            prefs.editCommitting { putStringValue(KEY, value) }
            assertEquals(value, prefs.getStringValue(KEY, MyStringValue.entries, defValue))
        }

        getDefaultIfNotPresent(MyStringValue.FIRST)
        getDefaultIfNotPresent(MyStringValue.SECOND)
        putAndGet(MyStringValue.FIRST, MyStringValue.SECOND)
        putAndGet(MyStringValue.SECOND, MyStringValue.FIRST)
    }

    @Test
    fun stringsTest() {
        assertNull(prefs.getStringSetOrNull(KEY))
        assertIterableEquals(setOf<String>(), prefs.getNonNullStringSet(KEY, setOf()))

        fun getDefaultIfNotPresent(defValue: Iterable<MyStringValue>) {
            assertIterableEquals(defValue, prefs.getStringValues(KEY, MyStringValue.entries, defValue))
        }

        fun putAndGet(values: Iterable<MyStringValue>, defValue: Iterable<MyStringValue>) {
            prefs.editCommitting { putStringValues(KEY, values) }
            assertIterableEquals(values, prefs.getStringValues(KEY, values, defValue))
        }

        getDefaultIfNotPresent(setOf())
        getDefaultIfNotPresent(EnumSet.of(MyStringValue.FIRST))
        getDefaultIfNotPresent(EnumSet.of(MyStringValue.SECOND))
        getDefaultIfNotPresent(EnumSet.allOf(MyStringValue::class.java))
        putAndGet(setOf(), setOf(MyStringValue.FIRST))
        putAndGet(EnumSet.of(MyStringValue.FIRST), setOf())
        putAndGet(EnumSet.of(MyStringValue.FIRST, MyStringValue.SECOND), setOf())
    }

    private inline fun SharedPreferences.editCommitting(block: SharedPreferences.Editor.() -> Unit) =
        edit(commit = true, block)

}