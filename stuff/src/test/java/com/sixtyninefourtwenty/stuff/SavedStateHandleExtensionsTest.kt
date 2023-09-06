package com.sixtyninefourtwenty.stuff

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDate

/**
 * Tests for type-safe savedstatehandle extensions to make sure the underlying savedstatehandle actually accepts those types
 */
class SavedStateHandleExtensionsTest {

    companion object {
        const val KEY = "foo"
    }

    private lateinit var savedStateHandle: SavedStateHandle

    @BeforeEach
    fun init() {
        savedStateHandle = SavedStateHandle()
    }

    @Test
    fun booleanTest() {
        assertNull(savedStateHandle.getBoolean(KEY))
        assertFalse(savedStateHandle.getBooleanOrDefault(KEY, false))
        assertTrue(savedStateHandle.getOrSetBoolean(KEY) { true })
        assertTrue(savedStateHandle.getBoolean(KEY)!!)
        savedStateHandle.setBoolean(KEY, false)
        assertFalse(savedStateHandle.getBoolean(KEY)!!)
    }

    @Test
    fun byteTest() {
        assertNull(savedStateHandle.getByte(KEY))
        assertEquals(-1, savedStateHandle.getByteOrDefault(KEY, -1))
        assertEquals(2, savedStateHandle.getOrSetByte(KEY) { 2 })
        assertEquals(2, savedStateHandle.getByte(KEY))
        savedStateHandle.setByte(KEY, 1)
        assertEquals(1, savedStateHandle.getByte(KEY))
    }

    @Test
    fun charTest() {
        assertNull(savedStateHandle.getChar(KEY))
        assertEquals('a', savedStateHandle.getCharOrDefault(KEY, 'a'))
        assertEquals('c', savedStateHandle.getOrSetChar(KEY) { 'c' })
        assertEquals('c', savedStateHandle.getChar(KEY))
        savedStateHandle.setChar(KEY, 'b')
        assertEquals('b', savedStateHandle.getChar(KEY))
    }

    @Test
    fun doubleTest() {
        assertNull(savedStateHandle.getDouble(KEY))
        assertEquals(Double.MIN_VALUE, savedStateHandle.getDoubleOrDefault(KEY, Double.MIN_VALUE))
        assertEquals(2.1, savedStateHandle.getOrSetDouble(KEY) { 2.1 })
        assertEquals(2.1, savedStateHandle.getDouble(KEY))
        savedStateHandle.setDouble(KEY, 1.0)
        assertEquals(1.0, savedStateHandle.getDouble(KEY))
    }

    @Test
    fun floatTest() {
        assertNull(savedStateHandle.getFloat(KEY))
        assertEquals(Float.MIN_VALUE, savedStateHandle.getFloatOrDefault(KEY, Float.MIN_VALUE))
        assertEquals(3.1F, savedStateHandle.getOrSetFloat(KEY) { 3.1F })
        assertEquals(3.1F, savedStateHandle.getFloat(KEY))
        savedStateHandle.setFloat(KEY, 1.0F)
        assertEquals(1.0F, savedStateHandle.getFloat(KEY))
    }

    @Test
    fun intTest() {
        assertNull(savedStateHandle.getInt(KEY))
        assertEquals(Int.MIN_VALUE, savedStateHandle.getIntOrDefault(KEY, Int.MIN_VALUE))
        assertEquals(2, savedStateHandle.getOrSetInt(KEY) { 2 })
        assertEquals(2, savedStateHandle.getInt(KEY))
        savedStateHandle.setInt(KEY, 1)
        assertEquals(1, savedStateHandle.getInt(KEY))
    }

    @Test
    fun longTest() {
        assertNull(savedStateHandle.getLong(KEY))
        assertEquals(Long.MIN_VALUE, savedStateHandle.getLongOrDefault(KEY, Long.MIN_VALUE))
        assertEquals(3, savedStateHandle.getOrSetLong(KEY) { 3 })
        assertEquals(3, savedStateHandle.getLong(KEY))
        savedStateHandle.setLong(KEY, 1)
        assertEquals(1, savedStateHandle.getLong(KEY))
    }

    @Test
    fun shortTest() {
        assertNull(savedStateHandle.getShort(KEY))
        assertEquals(Short.MIN_VALUE, savedStateHandle.getShortOrDefault(KEY, Short.MIN_VALUE))
        assertEquals(4, savedStateHandle.getOrSetShort(KEY) { 4 })
        assertEquals(4, savedStateHandle.getShort(KEY))
        savedStateHandle.setShort(KEY, 1)
        assertEquals(1, savedStateHandle.getShort(KEY))
    }

    @Test
    fun bundleTest() {
        assertNull(savedStateHandle.getBundle(KEY))
        val b1 = Bundle()
        assertEquals(b1, savedStateHandle.getOrSetBundle(KEY) { b1 })
        assertEquals(b1, savedStateHandle.getBundle(KEY))
        val b2 = Bundle()
        savedStateHandle.setBundle(KEY, b2)
        assertEquals(b2, savedStateHandle.getBundle(KEY))
    }

    @Test
    fun charSequenceTest() {
        assertNull(savedStateHandle.getCharSequence(KEY))
        val sb1 = StringBuilder("foo")
        assertEquals(sb1, savedStateHandle.getOrSetCharSequence(KEY) { sb1 })
        assertEquals(sb1, savedStateHandle.getCharSequence(KEY))
        val sb2 = StringBuilder()
        savedStateHandle.setCharSequence(KEY, sb2)
        assertEquals(sb2, savedStateHandle.getCharSequence(KEY))
    }

    @Test
    fun stringTest() {
        assertNull(savedStateHandle.getString(KEY))
        assertEquals("bar", savedStateHandle.getStringOrDefault(KEY, "bar"))
        assertEquals("foo", savedStateHandle.getOrSetString(KEY) { "foo" })
        assertEquals("foo", savedStateHandle.getString(KEY))
        savedStateHandle.setString(KEY, "e")
        assertEquals("e", savedStateHandle.getString(KEY))
    }

    @Test
    fun parcelableTest() {
        assertNull(savedStateHandle.getParcelable(KEY))
        val obj1 = MyParcelable()
        assertEquals(obj1, savedStateHandle.getOrSetParcelable(KEY) { obj1 })
        assertEquals(obj1, savedStateHandle.getParcelable(KEY))
        val obj2 = MyParcelable()
        savedStateHandle.setParcelable(KEY, obj2)
        assertEquals(obj2, savedStateHandle.getParcelable(KEY))
    }

    @Test
    fun serializableTest() {
        assertNull(savedStateHandle.getSerializable(KEY))
        assertEquals(LocalDate.now().plusDays(1), savedStateHandle.getOrSetSerializable(KEY) { LocalDate.now().plusDays(1) })
        assertEquals(LocalDate.now().plusDays(1), savedStateHandle.getSerializable(KEY))
        savedStateHandle.setSerializable(KEY, LocalDate.now())
        assertEquals(LocalDate.now(), savedStateHandle.getSerializable(KEY))
    }

    @Test
    fun booleanArrayTest() {
        assertNull(savedStateHandle.getBooleanArray(KEY))
        assertArrayEquals(booleanArrayOf(true, false), savedStateHandle.getOrSetBooleanArray(KEY) { booleanArrayOf(true, false) })
        assertArrayEquals(booleanArrayOf(true, false), savedStateHandle.getBooleanArray(KEY))
        savedStateHandle.setBooleanArray(KEY, BooleanArray(1))
        assertArrayEquals(BooleanArray(1), savedStateHandle.getBooleanArray(KEY))
    }

    @Test
    fun byteArrayTest() {
        assertNull(savedStateHandle.getByteArray(KEY))
        assertArrayEquals(byteArrayOf(0, 1), savedStateHandle.getOrSetByteArray(KEY) { byteArrayOf(0, 1) })
        assertArrayEquals(byteArrayOf(0, 1), savedStateHandle.getByteArray(KEY))
        savedStateHandle.setByteArray(KEY, ByteArray(1))
        assertArrayEquals(ByteArray(1), savedStateHandle.getByteArray(KEY))
    }

    @Test
    fun charArrayTest() {
        assertNull(savedStateHandle.getCharArray(KEY))
        assertArrayEquals(charArrayOf('a', 'b'), savedStateHandle.getOrSetCharArray(KEY) { charArrayOf('a', 'b') })
        assertArrayEquals(charArrayOf('a', 'b'), savedStateHandle.getCharArray(KEY))
        savedStateHandle.setCharArray(KEY, CharArray(1))
        assertArrayEquals(CharArray(1), savedStateHandle.getCharArray(KEY))
    }

    @Test
    fun doubleArrayTest() {
        assertNull(savedStateHandle.getDoubleArray(KEY))
        assertArrayEquals(doubleArrayOf(1.0, 3.1), savedStateHandle.getOrSetDoubleArray(KEY) { doubleArrayOf(1.0, 3.1) })
        assertArrayEquals(doubleArrayOf(1.0, 3.1), savedStateHandle.getDoubleArray(KEY))
        savedStateHandle.setDoubleArray(KEY, DoubleArray(1))
        assertArrayEquals(DoubleArray(1), savedStateHandle.getDoubleArray(KEY))
    }

    @Test
    fun floatArrayTest() {
        assertNull(savedStateHandle.getFloatArray(KEY))
        assertArrayEquals(floatArrayOf(1.0F, 3.1F), savedStateHandle.getOrSetFloatArray(KEY) { floatArrayOf(1.0F, 3.1F) })
        assertArrayEquals(floatArrayOf(1.0F, 3.1F), savedStateHandle.getFloatArray(KEY))
        savedStateHandle.setFloatArray(KEY, FloatArray(1))
        assertArrayEquals(FloatArray(1), savedStateHandle.getFloatArray(KEY))
    }

    @Test
    fun intArrayTest() {
        assertNull(savedStateHandle.getIntArray(KEY))
        assertArrayEquals(intArrayOf(6, 9), savedStateHandle.getOrSetIntArray(KEY) { intArrayOf(6, 9) })
        assertArrayEquals(intArrayOf(6, 9), savedStateHandle.getIntArray(KEY))
        savedStateHandle.setIntArray(KEY, IntArray(1))
        assertArrayEquals(IntArray(1), savedStateHandle.getIntArray(KEY))
    }

    @Test
    fun longArrayTest() {
        assertNull(savedStateHandle.getLongArray(KEY))
        assertArrayEquals(longArrayOf(6, 9), savedStateHandle.getOrSetLongArray(KEY) { longArrayOf(6, 9) })
        assertArrayEquals(longArrayOf(6, 9), savedStateHandle.getLongArray(KEY))
        savedStateHandle.setLongArray(KEY, LongArray(1))
        assertArrayEquals(LongArray(1), savedStateHandle.getLongArray(KEY))
    }

    @Test
    fun shortArrayTest() {
        assertNull(savedStateHandle.getShortArray(KEY))
        assertArrayEquals(shortArrayOf(2, 3), savedStateHandle.getOrSetShortArray(KEY) { shortArrayOf(2, 3) })
        assertArrayEquals(shortArrayOf(2, 3), savedStateHandle.getShortArray(KEY))
        savedStateHandle.setShortArray(KEY, ShortArray(1))
        assertArrayEquals(ShortArray(1), savedStateHandle.getShortArray(KEY))
    }

    @Test
    fun parcelableArrayTest() {
        assertNull(savedStateHandle.getParcelableArray(KEY))
        assertArrayEquals(arrayOf(MyParcelable()), savedStateHandle.getOrSetParcelableArray(KEY) { arrayOf(MyParcelable()) })
        assertArrayEquals(arrayOf(MyParcelable()), savedStateHandle.getParcelableArray(KEY))
        savedStateHandle.setParcelableArray(KEY, arrayOf())
        assertArrayEquals(arrayOf<Parcelable>(), savedStateHandle.getParcelableArray(KEY))
    }

    @Test
    fun stringArrayTest() {
        assertNull(savedStateHandle.getStringArray(KEY))
        assertArrayEquals(arrayOf("foo"), savedStateHandle.getOrSetStringArray(KEY) { arrayOf("foo") })
        assertArrayEquals(arrayOf("foo"), savedStateHandle.getStringArray(KEY))
        savedStateHandle.setStringArray(KEY, arrayOf())
        assertArrayEquals(arrayOf(), savedStateHandle.getStringArray(KEY))
    }

    @Test
    fun charSequenceArrayTest() {
        assertNull(savedStateHandle.getCharSequenceArray(KEY))
        val arr1 = arrayOf<CharSequence>(StringBuilder("foo"), StringBuffer("bar"))
        assertEquals(arr1, savedStateHandle.getOrSetCharSequenceArray(KEY) { arr1 })
        assertEquals(arr1, savedStateHandle.getCharSequenceArray(KEY))
        val arr2 = arrayOf<CharSequence>()
        savedStateHandle.setCharSequenceArray(KEY, arr2)
        assertEquals(arr2, savedStateHandle.getCharSequenceArray(KEY))
    }

    private data class MyParcelable(val something: String = "something") : Parcelable {
        override fun describeContents(): Int = 0

        override fun writeToParcel(dest: Parcel, flags: Int) {

        }
    }

}