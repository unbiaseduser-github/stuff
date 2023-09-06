@file:JvmName("SavedStateHandles")

package com.sixtyninefourtwenty.stuff

import android.os.Bundle
import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import java.io.Serializable

fun SavedStateHandle.getBoolean(key: String) = get<Boolean>(key)
fun SavedStateHandle.getBooleanOrDefault(key: String, defValue: Boolean) = getBoolean(key) ?: defValue
fun SavedStateHandle.setBoolean(key: String, value: Boolean) = set(key, value)
inline fun SavedStateHandle.getOrSetBoolean(key: String, block: () -> Boolean) = getBoolean(key) ?: run {
    block().also { setBoolean(key, it) }
}

fun SavedStateHandle.getByte(key: String) = get<Byte>(key)
fun SavedStateHandle.getByteOrDefault(key: String, defValue: Byte) = getByte(key) ?: defValue
fun SavedStateHandle.setByte(key: String, value: Byte) = set(key, value)
inline fun SavedStateHandle.getOrSetByte(key: String, block: () -> Byte) = getByte(key) ?: run {
    block().also { setByte(key, it) }
}

fun SavedStateHandle.getChar(key: String) = get<Char>(key)
fun SavedStateHandle.getCharOrDefault(key: String, defValue: Char) = getChar(key) ?: defValue
fun SavedStateHandle.setChar(key: String, value: Char) = set(key, value)
inline fun SavedStateHandle.getOrSetChar(key: String, block: () -> Char) = getChar(key) ?: run {
    block().also { setChar(key, it) }
}

fun SavedStateHandle.getDouble(key: String) = get<Double>(key)
fun SavedStateHandle.getDoubleOrDefault(key: String, defValue: Double) = getDouble(key) ?: defValue
fun SavedStateHandle.setDouble(key: String, value: Double) = set(key, value)
inline fun SavedStateHandle.getOrSetDouble(key: String, block: () -> Double) = getDouble(key) ?: run {
    block().also { setDouble(key, it) }
}

fun SavedStateHandle.getFloat(key: String) = get<Float>(key)
fun SavedStateHandle.getFloatOrDefault(key: String, defValue: Float) = getFloat(key) ?: defValue
fun SavedStateHandle.setFloat(key: String, value: Float) = set(key, value)
inline fun SavedStateHandle.getOrSetFloat(key: String, block: () -> Float) = getFloat(key) ?: kotlin.run {
    block().also { setFloat(key, it) }
}

fun SavedStateHandle.getInt(key: String) = get<Int>(key)
fun SavedStateHandle.getIntOrDefault(key: String, defValue: Int) = getInt(key) ?: defValue
fun SavedStateHandle.setInt(key: String, value: Int) = set(key, value)
inline fun SavedStateHandle.getOrSetInt(key: String, block: () -> Int) = getInt(key) ?: run {
    block().also { setInt(key, it) }
}

fun SavedStateHandle.getLong(key: String) = get<Long>(key)
fun SavedStateHandle.getLongOrDefault(key: String, defValue: Long) = getLong(key) ?: defValue
fun SavedStateHandle.setLong(key: String, value: Long) = set(key, value)
inline fun SavedStateHandle.getOrSetLong(key: String, block: () -> Long) = getLong(key) ?: run {
    block().also { setLong(key, it) }
}

fun SavedStateHandle.getShort(key: String) = get<Short>(key)
fun SavedStateHandle.getShortOrDefault(key: String, defValue: Short) = getShort(key) ?: defValue
fun SavedStateHandle.setShort(key: String, value: Short) = set(key, value)
inline fun SavedStateHandle.getOrSetShort(key: String, block: () -> Short) = getShort(key) ?: run {
    block().also { setShort(key, it) }
}

fun SavedStateHandle.getBundle(key: String) = get<Bundle>(key)
fun SavedStateHandle.setBundle(key: String, value: Bundle) = set(key, value)
inline fun SavedStateHandle.getOrSetBundle(key: String, block: () -> Bundle) = getBundle(key) ?: run {
    block().also { setBundle(key, it) }
}

fun SavedStateHandle.getCharSequence(key: String) = get<CharSequence>(key)
fun SavedStateHandle.setCharSequence(key: String, value: CharSequence) = set(key, value)
inline fun SavedStateHandle.getOrSetCharSequence(key: String, block: () -> CharSequence) = getCharSequence(key) ?: run {
    block().also { setCharSequence(key, it) }
}

fun SavedStateHandle.getString(key: String) = get<String>(key)
fun SavedStateHandle.getStringOrDefault(key: String, defValue: String) = getString(key) ?: defValue
fun SavedStateHandle.setString(key: String, value: String) = set(key, value)
inline fun SavedStateHandle.getOrSetString(key: String, block: () -> String) = getString(key) ?: run {
    block().also { setString(key, it) }
}

fun <T : Parcelable> SavedStateHandle.getParcelable(key: String) = get<T>(key)
fun SavedStateHandle.setParcelable(key: String, value: Parcelable) = set(key, value)
inline fun <T : Parcelable> SavedStateHandle.getOrSetParcelable(key: String, block: () -> T) = getParcelable(key) ?: run {
    block().also { setParcelable(key, it) }
}

fun SavedStateHandle.getBooleanArray(key: String) = get<BooleanArray>(key)
fun SavedStateHandle.setBooleanArray(key: String, value: BooleanArray) = set(key, value)
inline fun SavedStateHandle.getOrSetBooleanArray(key: String, block: () -> BooleanArray) = getBooleanArray(key) ?: run {
    block().also { setBooleanArray(key, it) }
}

fun SavedStateHandle.getByteArray(key: String) = get<ByteArray>(key)
fun SavedStateHandle.setByteArray(key: String, value: ByteArray) = set(key, value)
inline fun SavedStateHandle.getOrSetByteArray(key: String, block: () -> ByteArray) = getByteArray(key) ?: run {
    block().also { setByteArray(key, it) }
}

fun SavedStateHandle.getCharArray(key: String) = get<CharArray>(key)
fun SavedStateHandle.setCharArray(key: String, value: CharArray) = set(key, value)
inline fun SavedStateHandle.getOrSetCharArray(key: String, block: () -> CharArray) = getCharArray(key) ?: run {
    block().also { setCharArray(key, it) }
}

fun SavedStateHandle.getDoubleArray(key: String) = get<DoubleArray>(key)
fun SavedStateHandle.setDoubleArray(key: String, value: DoubleArray) = set(key, value)
inline fun SavedStateHandle.getOrSetDoubleArray(key: String, block: () -> DoubleArray) = getDoubleArray(key) ?: run {
    block().also { setDoubleArray(key, it) }
}

fun SavedStateHandle.getFloatArray(key: String) = get<FloatArray>(key)
fun SavedStateHandle.setFloatArray(key: String, value: FloatArray) = set(key, value)
inline fun SavedStateHandle.getOrSetFloatArray(key: String, block: () -> FloatArray) = getFloatArray(key) ?: run {
    block().also { setFloatArray(key, it) }
}

fun SavedStateHandle.getIntArray(key: String) = get<IntArray>(key)
fun SavedStateHandle.setIntArray(key: String, value: IntArray) = set(key, value)
inline fun SavedStateHandle.getOrSetIntArray(key: String, block: () -> IntArray) = getIntArray(key) ?: run {
    block().also { setIntArray(key, it) }
}

fun SavedStateHandle.getLongArray(key: String) = get<LongArray>(key)
fun SavedStateHandle.setLongArray(key: String, value: LongArray) = set(key, value)
inline fun SavedStateHandle.getOrSetLongArray(key: String, block: () -> LongArray) = getLongArray(key) ?: run {
    block().also { setLongArray(key, it) }
}

fun SavedStateHandle.getShortArray(key: String) = get<ShortArray>(key)
fun SavedStateHandle.setShortArray(key: String, value: ShortArray) = set(key, value)
inline fun SavedStateHandle.getOrSetShortArray(key: String, block: () -> ShortArray) = getShortArray(key) ?: run {
    block().also { setShortArray(key, it) }
}

fun SavedStateHandle.getParcelableArray(key: String) = get<Array<Parcelable>>(key)
fun SavedStateHandle.setParcelableArray(key: String, value: Array<Parcelable>) = set(key, value)
inline fun SavedStateHandle.getOrSetParcelableArray(key: String, block: () -> Array<Parcelable>) = getParcelableArray(key) ?: run {
    block().also { setParcelableArray(key, it) }
}

fun SavedStateHandle.getStringArray(key: String) = get<Array<String>>(key)
fun SavedStateHandle.setStringArray(key: String, value: Array<String>) = set(key, value)
inline fun SavedStateHandle.getOrSetStringArray(key: String, block: () -> Array<String>) = getStringArray(key) ?: run {
    block().also { setStringArray(key, it) }
}

fun SavedStateHandle.getCharSequenceArray(key: String) = get<Array<CharSequence>>(key)
fun SavedStateHandle.setCharSequenceArray(key: String, value: Array<CharSequence>) = set(key, value)
inline fun SavedStateHandle.getOrSetCharSequenceArray(key: String, block: () -> Array<CharSequence>) = getCharSequenceArray(key) ?: run {
    block().also { setCharSequenceArray(key, it) }
}

fun <T : Serializable> SavedStateHandle.getSerializable(key: String) = get<T>(key)
fun SavedStateHandle.setSerializable(key: String, value: Serializable) = set(key, value)
inline fun <T : Serializable> SavedStateHandle.getOrSetSerializable(key: String, block: () -> T) = getSerializable(key) ?: run {
    block().also { setSerializable(key, it) }
}
