@file:JvmName("SavedStateHandles")
@file:Suppress("unused")

package com.sixtyninefourtwenty.stuff

import android.os.Bundle
import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.map
import com.sixtyninefourtwenty.stuff.interfaces.JsonSerializable
import com.sixtyninefourtwenty.stuff.interfaces.JsonSerializer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.io.Serializable

fun SavedStateHandle.getBoolean(key: String) = get<Boolean>(key)
fun SavedStateHandle.getBooleanOrDefault(key: String, defValue: Boolean) = getBoolean(key) ?: defValue
fun SavedStateHandle.setBoolean(key: String, value: Boolean) = set(key, value)
inline fun SavedStateHandle.getOrSetBoolean(key: String, block: () -> Boolean) = getBoolean(key) ?: run {
    block().also { setBoolean(key, it) }
}
fun SavedStateHandle.getBooleanLiveData(key: String) = getLiveData<Boolean>(key)
fun SavedStateHandle.getBooleanLiveData(key: String, initialValue: Boolean) = getLiveData(key, initialValue)
fun SavedStateHandle.getNullableBooleanLiveData(key: String) = getLiveData<Boolean?>(key)
fun SavedStateHandle.getNullableBooleanLiveData(key: String, initialValue: Boolean?) = getLiveData(key, initialValue)
fun SavedStateHandle.getBooleanStateFlow(key: String, initialValue: Boolean) = getStateFlow(key, initialValue)
fun SavedStateHandle.getNullableBooleanStateFlow(key: String, initialValue: Boolean?) = getStateFlow(key, initialValue)

fun SavedStateHandle.getByte(key: String) = get<Byte>(key)
fun SavedStateHandle.getByteOrDefault(key: String, defValue: Byte) = getByte(key) ?: defValue
fun SavedStateHandle.setByte(key: String, value: Byte) = set(key, value)
inline fun SavedStateHandle.getOrSetByte(key: String, block: () -> Byte) = getByte(key) ?: run {
    block().also { setByte(key, it) }
}
fun SavedStateHandle.getByteLiveData(key: String) = getLiveData<Byte>(key)
fun SavedStateHandle.getByteLiveData(key: String, initialValue: Byte) = getLiveData(key, initialValue)
fun SavedStateHandle.getNullableByteLiveData(key: String) = getLiveData<Byte?>(key)
fun SavedStateHandle.getNullableByteLiveData(key: String, initialValue: Byte?) = getLiveData(key, initialValue)
fun SavedStateHandle.getByteStateFlow(key: String, initialValue: Byte) = getStateFlow(key, initialValue)
fun SavedStateHandle.getNullableByteStateFlow(key: String, initialValue: Byte?) = getStateFlow(key, initialValue)

fun SavedStateHandle.getChar(key: String) = get<Char>(key)
fun SavedStateHandle.getCharOrDefault(key: String, defValue: Char) = getChar(key) ?: defValue
fun SavedStateHandle.setChar(key: String, value: Char) = set(key, value)
inline fun SavedStateHandle.getOrSetChar(key: String, block: () -> Char) = getChar(key) ?: run {
    block().also { setChar(key, it) }
}
fun SavedStateHandle.getCharLiveData(key: String) = getLiveData<Char>(key)
fun SavedStateHandle.getCharLiveData(key: String, initialValue: Char) = getLiveData(key, initialValue)
fun SavedStateHandle.getNullableCharLiveData(key: String) = getLiveData<Char?>(key)
fun SavedStateHandle.getNullableCharLiveData(key: String, initialValue: Char?) = getLiveData(key, initialValue)
fun SavedStateHandle.getCharStateFlow(key: String, initialValue: Char) = getStateFlow(key, initialValue)
fun SavedStateHandle.getNullableCharStateFlow(key: String, initialValue: Char?) = getStateFlow(key, initialValue)

fun SavedStateHandle.getDouble(key: String) = get<Double>(key)
fun SavedStateHandle.getDoubleOrDefault(key: String, defValue: Double) = getDouble(key) ?: defValue
fun SavedStateHandle.setDouble(key: String, value: Double) = set(key, value)
inline fun SavedStateHandle.getOrSetDouble(key: String, block: () -> Double) = getDouble(key) ?: run {
    block().also { setDouble(key, it) }
}
fun SavedStateHandle.getDoubleLiveData(key: String) = getLiveData<Double>(key)
fun SavedStateHandle.getDoubleLiveData(key: String, initialValue: Double) = getLiveData(key, initialValue)
fun SavedStateHandle.getNullableDoubleLiveData(key: String) = getLiveData<Double?>(key)
fun SavedStateHandle.getNullableDoubleLiveData(key: String, initialValue: Double?) = getLiveData(key, initialValue)
fun SavedStateHandle.getDoubleStateFlow(key: String, initialValue: Double) = getStateFlow(key, initialValue)
fun SavedStateHandle.getNullableDoubleStateFlow(key: String, initialValue: Double?) = getStateFlow(key, initialValue)

fun SavedStateHandle.getFloat(key: String) = get<Float>(key)
fun SavedStateHandle.getFloatOrDefault(key: String, defValue: Float) = getFloat(key) ?: defValue
fun SavedStateHandle.setFloat(key: String, value: Float) = set(key, value)
inline fun SavedStateHandle.getOrSetFloat(key: String, block: () -> Float) = getFloat(key) ?: kotlin.run {
    block().also { setFloat(key, it) }
}
fun SavedStateHandle.getFloatLiveData(key: String) = getLiveData<Float>(key)
fun SavedStateHandle.getFloatLiveData(key: String, initialValue: Float) = getLiveData(key, initialValue)
fun SavedStateHandle.getNullableFloatLiveData(key: String) = getLiveData<Float?>(key)
fun SavedStateHandle.getNullableFloatLiveData(key: String, initialValue: Float?) = getLiveData(key, initialValue)
fun SavedStateHandle.getFloatStateFlow(key: String, initialValue: Float) = getStateFlow(key, initialValue)
fun SavedStateHandle.getNullableFloatStateFlow(key: String, initialValue: Float?) = getStateFlow(key, initialValue)

fun SavedStateHandle.getInt(key: String) = get<Int>(key)
fun SavedStateHandle.getIntOrDefault(key: String, defValue: Int) = getInt(key) ?: defValue
fun SavedStateHandle.setInt(key: String, value: Int) = set(key, value)
inline fun SavedStateHandle.getOrSetInt(key: String, block: () -> Int) = getInt(key) ?: run {
    block().also { setInt(key, it) }
}
fun SavedStateHandle.getIntLiveData(key: String) = getLiveData<Int>(key)
fun SavedStateHandle.getIntLiveData(key: String, initialValue: Int) = getLiveData(key, initialValue)
fun SavedStateHandle.getNullableIntLiveData(key: String) = getLiveData<Int?>(key)
fun SavedStateHandle.getNullableIntLiveData(key: String, initialValue: Int?) = getLiveData(key, initialValue)
fun SavedStateHandle.getIntStateFlow(key: String, initialValue: Int) = getStateFlow(key, initialValue)
fun SavedStateHandle.getNullableIntStateFlow(key: String, initialValue: Int?) = getStateFlow(key, initialValue)

fun SavedStateHandle.getLong(key: String) = get<Long>(key)
fun SavedStateHandle.getLongOrDefault(key: String, defValue: Long) = getLong(key) ?: defValue
fun SavedStateHandle.setLong(key: String, value: Long) = set(key, value)
inline fun SavedStateHandle.getOrSetLong(key: String, block: () -> Long) = getLong(key) ?: run {
    block().also { setLong(key, it) }
}
fun SavedStateHandle.getLongLiveData(key: String) = getLiveData<Long>(key)
fun SavedStateHandle.getLongLiveData(key: String, initialValue: Long) = getLiveData(key, initialValue)
fun SavedStateHandle.getNullableLongLiveData(key: String) = getLiveData<Long?>(key)
fun SavedStateHandle.getNullableLongLiveData(key: String, initialValue: Long?) = getLiveData(key, initialValue)
fun SavedStateHandle.getLongStateFlow(key: String, initialValue: Long) = getStateFlow(key, initialValue)
fun SavedStateHandle.getNullableLongStateFlow(key: String, initialValue: Long?) = getStateFlow(key, initialValue)

fun SavedStateHandle.getShort(key: String) = get<Short>(key)
fun SavedStateHandle.getShortOrDefault(key: String, defValue: Short) = getShort(key) ?: defValue
fun SavedStateHandle.setShort(key: String, value: Short) = set(key, value)
inline fun SavedStateHandle.getOrSetShort(key: String, block: () -> Short) = getShort(key) ?: run {
    block().also { setShort(key, it) }
}
fun SavedStateHandle.getShortLiveData(key: String) = getLiveData<Short>(key)
fun SavedStateHandle.getShortLiveData(key: String, initialValue: Short) = getLiveData(key, initialValue)
fun SavedStateHandle.getNullableShortLiveData(key: String) = getLiveData<Short?>(key)
fun SavedStateHandle.getNullableShortLiveData(key: String, initialValue: Short?) = getLiveData(key, initialValue)
fun SavedStateHandle.getShortStateFlow(key: String, initialValue: Short) = getStateFlow(key, initialValue)
fun SavedStateHandle.getNullableShortStateFlow(key: String, initialValue: Short?) = getStateFlow(key, initialValue)

fun SavedStateHandle.getBundle(key: String) = get<Bundle>(key)
fun SavedStateHandle.setBundle(key: String, value: Bundle) = set(key, value)
inline fun SavedStateHandle.getOrSetBundle(key: String, block: () -> Bundle) = getBundle(key) ?: run {
    block().also { setBundle(key, it) }
}
fun SavedStateHandle.getBundleLiveData(key: String) = getLiveData<Bundle>(key)
fun SavedStateHandle.getBundleLiveData(key: String, initialValue: Bundle) = getLiveData(key, initialValue)
fun SavedStateHandle.getNullableBundleLiveData(key: String) = getLiveData<Bundle?>(key)
fun SavedStateHandle.getNullableBundleLiveData(key: String, initialValue: Bundle?) = getLiveData(key, initialValue)
fun SavedStateHandle.getBundleStateFlow(key: String, initialValue: Bundle) = getStateFlow(key, initialValue)
fun SavedStateHandle.getNullableBundleStateFlow(key: String, initialValue: Bundle?) = getStateFlow(key, initialValue)

fun SavedStateHandle.getCharSequence(key: String) = get<CharSequence>(key)
fun SavedStateHandle.setCharSequence(key: String, value: CharSequence) = set(key, value)
inline fun SavedStateHandle.getOrSetCharSequence(key: String, block: () -> CharSequence) = getCharSequence(key) ?: run {
    block().also { setCharSequence(key, it) }
}
fun SavedStateHandle.getCharSequenceLiveData(key: String) = getLiveData<CharSequence>(key)
fun SavedStateHandle.getCharSequenceLiveData(key: String, initialValue: CharSequence) = getLiveData(key, initialValue)
fun SavedStateHandle.getNullableCharSequenceLiveData(key: String) = getLiveData<CharSequence?>(key)
fun SavedStateHandle.getNullableCharSequenceLiveData(key: String, initialValue: CharSequence?) = getLiveData(key, initialValue)
fun SavedStateHandle.getCharSequenceStateFlow(key: String, initialValue: CharSequence) = getStateFlow(key, initialValue)
fun SavedStateHandle.getNullableCharSequenceStateFlow(key: String, initialValue: CharSequence?) = getStateFlow(key, initialValue)

fun SavedStateHandle.getString(key: String) = get<String>(key)
fun SavedStateHandle.getStringOrDefault(key: String, defValue: String) = getString(key) ?: defValue
fun SavedStateHandle.setString(key: String, value: String) = set(key, value)
inline fun SavedStateHandle.getOrSetString(key: String, block: () -> String) = getString(key) ?: run {
    block().also { setString(key, it) }
}
fun SavedStateHandle.getStringLiveData(key: String) = getLiveData<String>(key)
fun SavedStateHandle.getStringLiveData(key: String, initialValue: String) = getLiveData(key, initialValue)
fun SavedStateHandle.getNullableStringLiveData(key: String) = getLiveData<String?>(key)
fun SavedStateHandle.getNullableStringLiveData(key: String, initialValue: String?) = getLiveData(key, initialValue)
fun SavedStateHandle.getStringStateFlow(key: String, initialValue: String) = getStateFlow(key, initialValue)
fun SavedStateHandle.getNullableStringStateFlow(key: String, initialValue: String?) = getStateFlow(key, initialValue)

fun <T : Parcelable> SavedStateHandle.getParcelable(key: String) = get<T>(key)
fun SavedStateHandle.setParcelable(key: String, value: Parcelable) = set(key, value)
inline fun <T : Parcelable> SavedStateHandle.getOrSetParcelable(key: String, block: () -> T) = getParcelable(key) ?: run {
    block().also { setParcelable(key, it) }
}
fun <T : Parcelable> SavedStateHandle.getParcelableLiveData(key: String) = getLiveData<T>(key)
fun <T : Parcelable> SavedStateHandle.getParcelableLiveData(key: String, initialValue: T) = getLiveData(key, initialValue)
fun <T : Parcelable?> SavedStateHandle.getNullableParcelableLiveData(key: String) = getLiveData<T>(key)
fun <T : Parcelable?> SavedStateHandle.getNullableParcelableLiveData(key: String, initialValue: T) = getLiveData(key, initialValue)
fun <T : Parcelable> SavedStateHandle.getParcelableStateFlow(key: String, initialValue: T) = getStateFlow(key, initialValue)
fun <T : Parcelable?> SavedStateHandle.getNullableParcelableStateFlow(key: String, initialValue: T) = getStateFlow(key, initialValue)

fun SavedStateHandle.getBooleanArray(key: String) = get<BooleanArray>(key)
fun SavedStateHandle.setBooleanArray(key: String, value: BooleanArray) = set(key, value)
inline fun SavedStateHandle.getOrSetBooleanArray(key: String, block: () -> BooleanArray) = getBooleanArray(key) ?: run {
    block().also { setBooleanArray(key, it) }
}
fun SavedStateHandle.getBooleanArrayLiveData(key: String) = getLiveData<BooleanArray>(key)
fun SavedStateHandle.getBooleanArrayLiveData(key: String, initialValue: BooleanArray) = getLiveData(key, initialValue)
fun SavedStateHandle.getNullableBooleanArrayLiveData(key: String) = getLiveData<BooleanArray?>(key)
fun SavedStateHandle.getNullableBooleanArrayLiveData(key: String, initialValue: BooleanArray?) = getLiveData(key, initialValue)
fun SavedStateHandle.getBooleanArrayStateFlow(key: String, initialValue: BooleanArray) = getStateFlow(key, initialValue)
fun SavedStateHandle.getNullableBooleanArrayStateFlow(key: String, initialValue: BooleanArray?) = getStateFlow(key, initialValue)

fun SavedStateHandle.getByteArray(key: String) = get<ByteArray>(key)
fun SavedStateHandle.setByteArray(key: String, value: ByteArray) = set(key, value)
inline fun SavedStateHandle.getOrSetByteArray(key: String, block: () -> ByteArray) = getByteArray(key) ?: run {
    block().also { setByteArray(key, it) }
}
fun SavedStateHandle.getByteArrayLiveData(key: String) = getLiveData<ByteArray>(key)
fun SavedStateHandle.getByteArrayLiveData(key: String, initialValue: ByteArray) = getLiveData(key, initialValue)
fun SavedStateHandle.getNullableByteArrayLiveData(key: String) = getLiveData<ByteArray?>(key)
fun SavedStateHandle.getNullableByteArrayLiveData(key: String, initialValue: ByteArray?) = getLiveData(key, initialValue)
fun SavedStateHandle.getByteArrayStateFlow(key: String, initialValue: ByteArray) = getStateFlow(key, initialValue)
fun SavedStateHandle.getNullableByteArrayStateFlow(key: String, initialValue: ByteArray?) = getStateFlow(key, initialValue)

fun SavedStateHandle.getCharArray(key: String) = get<CharArray>(key)
fun SavedStateHandle.setCharArray(key: String, value: CharArray) = set(key, value)
inline fun SavedStateHandle.getOrSetCharArray(key: String, block: () -> CharArray) = getCharArray(key) ?: run {
    block().also { setCharArray(key, it) }
}
fun SavedStateHandle.getCharArrayLiveData(key: String) = getLiveData<CharArray>(key)
fun SavedStateHandle.getCharArrayLiveData(key: String, initialValue: CharArray) = getLiveData(key, initialValue)
fun SavedStateHandle.getNullableCharArrayLiveData(key: String) = getLiveData<CharArray?>(key)
fun SavedStateHandle.getNullableCharArrayLiveData(key: String, initialValue: CharArray?) = getLiveData(key, initialValue)
fun SavedStateHandle.getCharArrayStateFlow(key: String, initialValue: CharArray) = getStateFlow(key, initialValue)
fun SavedStateHandle.getNullableCharArrayStateFlow(key: String, initialValue: CharArray?) = getStateFlow(key, initialValue)

fun SavedStateHandle.getDoubleArray(key: String) = get<DoubleArray>(key)
fun SavedStateHandle.setDoubleArray(key: String, value: DoubleArray) = set(key, value)
inline fun SavedStateHandle.getOrSetDoubleArray(key: String, block: () -> DoubleArray) = getDoubleArray(key) ?: run {
    block().also { setDoubleArray(key, it) }
}
fun SavedStateHandle.getDoubleArrayLiveData(key: String) = getLiveData<DoubleArray>(key)
fun SavedStateHandle.getDoubleArrayLiveData(key: String, initialValue: DoubleArray) = getLiveData(key, initialValue)
fun SavedStateHandle.getNullableDoubleArrayLiveData(key: String) = getLiveData<DoubleArray?>(key)
fun SavedStateHandle.getNullableDoubleArrayLiveData(key: String, initialValue: DoubleArray?) = getLiveData(key, initialValue)
fun SavedStateHandle.getDoubleArrayStateFlow(key: String, initialValue: DoubleArray) = getStateFlow(key, initialValue)
fun SavedStateHandle.getNullableDoubleArrayStateFlow(key: String, initialValue: DoubleArray?) = getStateFlow(key, initialValue)

fun SavedStateHandle.getFloatArray(key: String) = get<FloatArray>(key)
fun SavedStateHandle.setFloatArray(key: String, value: FloatArray) = set(key, value)
inline fun SavedStateHandle.getOrSetFloatArray(key: String, block: () -> FloatArray) = getFloatArray(key) ?: run {
    block().also { setFloatArray(key, it) }
}
fun SavedStateHandle.getFloatArrayLiveData(key: String) = getLiveData<FloatArray>(key)
fun SavedStateHandle.getFloatArrayLiveData(key: String, initialValue: FloatArray) = getLiveData(key, initialValue)
fun SavedStateHandle.getNullableFloatArrayLiveData(key: String) = getLiveData<FloatArray?>(key)
fun SavedStateHandle.getNullableFloatArrayLiveData(key: String, initialValue: FloatArray?) = getLiveData(key, initialValue)
fun SavedStateHandle.getFloatArrayStateFlow(key: String, initialValue: FloatArray) = getStateFlow(key, initialValue)
fun SavedStateHandle.getNullableFloatArrayStateFlow(key: String, initialValue: FloatArray?) = getStateFlow(key, initialValue)

fun SavedStateHandle.getIntArray(key: String) = get<IntArray>(key)
fun SavedStateHandle.setIntArray(key: String, value: IntArray) = set(key, value)
inline fun SavedStateHandle.getOrSetIntArray(key: String, block: () -> IntArray) = getIntArray(key) ?: run {
    block().also { setIntArray(key, it) }
}
fun SavedStateHandle.getIntArrayLiveData(key: String) = getLiveData<IntArray>(key)
fun SavedStateHandle.getIntArrayLiveData(key: String, initialValue: IntArray) = getLiveData(key, initialValue)
fun SavedStateHandle.getNullableIntArrayLiveData(key: String) = getLiveData<IntArray?>(key)
fun SavedStateHandle.getNullableIntArrayLiveData(key: String, initialValue: IntArray?) = getLiveData(key, initialValue)
fun SavedStateHandle.getIntArrayStateFlow(key: String, initialValue: IntArray) = getStateFlow(key, initialValue)
fun SavedStateHandle.getNullableIntArrayStateFlow(key: String, initialValue: IntArray?) = getStateFlow(key, initialValue)

fun SavedStateHandle.getLongArray(key: String) = get<LongArray>(key)
fun SavedStateHandle.setLongArray(key: String, value: LongArray) = set(key, value)
inline fun SavedStateHandle.getOrSetLongArray(key: String, block: () -> LongArray) = getLongArray(key) ?: run {
    block().also { setLongArray(key, it) }
}
fun SavedStateHandle.getLongArrayLiveData(key: String) = getLiveData<LongArray>(key)
fun SavedStateHandle.getLongArrayLiveData(key: String, initialValue: LongArray) = getLiveData(key, initialValue)
fun SavedStateHandle.getNullableLongArrayLiveData(key: String) = getLiveData<LongArray?>(key)
fun SavedStateHandle.getNullableLongArrayLiveData(key: String, initialValue: LongArray?) = getLiveData(key, initialValue)
fun SavedStateHandle.getLongArrayStateFlow(key: String, initialValue: LongArray) = getStateFlow(key, initialValue)
fun SavedStateHandle.getNullableLongArrayStateFlow(key: String, initialValue: LongArray?) = getStateFlow(key, initialValue)

fun SavedStateHandle.getShortArray(key: String) = get<ShortArray>(key)
fun SavedStateHandle.setShortArray(key: String, value: ShortArray) = set(key, value)
inline fun SavedStateHandle.getOrSetShortArray(key: String, block: () -> ShortArray) = getShortArray(key) ?: run {
    block().also { setShortArray(key, it) }
}
fun SavedStateHandle.getShortArrayLiveData(key: String) = getLiveData<ShortArray>(key)
fun SavedStateHandle.getShortArrayLiveData(key: String, initialValue: ShortArray) = getLiveData(key, initialValue)
fun SavedStateHandle.getNullableShortArrayLiveData(key: String) = getLiveData<ShortArray?>(key)
fun SavedStateHandle.getNullableShortArrayLiveData(key: String, initialValue: ShortArray?) = getLiveData(key, initialValue)
fun SavedStateHandle.getShortArrayStateFlow(key: String, initialValue: ShortArray) = getStateFlow(key, initialValue)
fun SavedStateHandle.getNullableShortArrayStateFlow(key: String, initialValue: ShortArray?) = getStateFlow(key, initialValue)

fun SavedStateHandle.getParcelableArray(key: String) = get<Array<Parcelable>>(key)
fun SavedStateHandle.setParcelableArray(key: String, value: Array<Parcelable>) = set(key, value)
inline fun SavedStateHandle.getOrSetParcelableArray(key: String, block: () -> Array<Parcelable>) = getParcelableArray(key) ?: run {
    block().also { setParcelableArray(key, it) }
}
fun SavedStateHandle.getParcelableArrayLiveData(key: String) = getLiveData<Array<Parcelable>>(key)
fun SavedStateHandle.getParcelableArrayLiveData(key: String, initialValue: Array<Parcelable>) = getLiveData(key, initialValue)
fun SavedStateHandle.getNullableParcelableArrayLiveData(key: String) = getLiveData<Array<Parcelable>?>(key)
fun SavedStateHandle.getNullableParcelableArrayLiveData(key: String, initialValue: Array<Parcelable>?) = getLiveData(key, initialValue)
fun SavedStateHandle.getParcelableArrayStateFlow(key: String, initialValue: Array<Parcelable>) = getStateFlow(key, initialValue)
fun SavedStateHandle.getNullableParcelableArrayStateFlow(key: String, initialValue: Array<Parcelable>?) = getStateFlow(key, initialValue)

fun SavedStateHandle.getStringArray(key: String) = get<Array<String>>(key)
fun SavedStateHandle.setStringArray(key: String, value: Array<String>) = set(key, value)
inline fun SavedStateHandle.getOrSetStringArray(key: String, block: () -> Array<String>) = getStringArray(key) ?: run {
    block().also { setStringArray(key, it) }
}
fun SavedStateHandle.getStringArrayLiveData(key: String) = getLiveData<Array<String>>(key)
fun SavedStateHandle.getStringArrayLiveData(key: String, initialValue: Array<String>) = getLiveData(key, initialValue)
fun SavedStateHandle.getNullableStringArrayLiveData(key: String) = getLiveData<Array<String>?>(key)
fun SavedStateHandle.getNullableStringArrayLiveData(key: String, initialValue: Array<String>?) = getLiveData(key, initialValue)
fun SavedStateHandle.getStringArrayStateFlow(key: String, initialValue: Array<String>) = getStateFlow(key, initialValue)
fun SavedStateHandle.getNullableStringArrayStateFlow(key: String, initialValue: Array<String>?) = getStateFlow(key, initialValue)

fun SavedStateHandle.getCharSequenceArray(key: String) = get<Array<CharSequence>>(key)
fun SavedStateHandle.setCharSequenceArray(key: String, value: Array<CharSequence>) = set(key, value)
inline fun SavedStateHandle.getOrSetCharSequenceArray(key: String, block: () -> Array<CharSequence>) = getCharSequenceArray(key) ?: run {
    block().also { setCharSequenceArray(key, it) }
}
fun SavedStateHandle.getCharSequenceArrayLiveData(key: String) = getLiveData<Array<CharSequence>>(key)
fun SavedStateHandle.getCharSequenceArrayLiveData(key: String, initialValue: Array<CharSequence>) = getLiveData(key, initialValue)
fun SavedStateHandle.getNullableCharSequenceArrayLiveData(key: String) = getLiveData<Array<CharSequence>?>(key)
fun SavedStateHandle.getNullableCharSequenceArrayLiveData(key: String, initialValue: Array<CharSequence>?) = getLiveData(key, initialValue)
fun SavedStateHandle.getCharSequenceArrayStateFlow(key: String, initialValue: Array<CharSequence>) = getStateFlow(key, initialValue)
fun SavedStateHandle.getNullableCharSequenceArrayStateFlow(key: String, initialValue: Array<CharSequence>?) = getStateFlow(key, initialValue)

fun <T : Serializable> SavedStateHandle.getSerializable(key: String) = get<T>(key)
fun SavedStateHandle.setSerializable(key: String, value: Serializable) = set(key, value)
inline fun <T : Serializable> SavedStateHandle.getOrSetSerializable(key: String, block: () -> T) = getSerializable(key) ?: run {
    block().also { setSerializable(key, it) }
}
fun <T : Serializable> SavedStateHandle.getSerializableLiveData(key: String) = getLiveData<T>(key)
fun <T : Serializable> SavedStateHandle.getSerializableLiveData(key: String, initialValue: T) = getLiveData(key, initialValue)
fun <T : Serializable?> SavedStateHandle.getNullableSerializableLiveData(key: String) = getLiveData<T>(key)
fun <T : Serializable?> SavedStateHandle.getNullableSerializableLiveData(key: String, initialValue: T) = getLiveData(key, initialValue)
fun <T : Serializable> SavedStateHandle.getSerializableStateFlow(key: String, initialValue: T) = getStateFlow(key, initialValue)
fun <T : Serializable?> SavedStateHandle.getNullableSerializableStateFlow(key: String, initialValue: T) = getStateFlow(key, initialValue)

fun <T> SavedStateHandle.getJsonSerializable(key: String, jsonSerializer: JsonSerializer<T>) = getString(key)?.let { jsonSerializer.fromJsonString(it) }
fun <T : JsonSerializable<T>> SavedStateHandle.setJsonSerializable(key: String, obj: T) = setString(key, obj.jsonSerializer.toJsonString(obj))
inline fun <T : JsonSerializable<T>> SavedStateHandle.getOrSetJsonSerializable(
    key: String,
    jsonSerializer: JsonSerializer<T>,
    block: () -> T
) = getJsonSerializable(key, jsonSerializer) ?: run {
    block().also { setJsonSerializable(key, it) }
}
fun <T> SavedStateHandle.getJsonSerializableLiveData(key: String, jsonSerializer: JsonSerializer<T>) =
    getStringLiveData(key).map { jsonSerializer.fromJsonString(it) }
fun <T : JsonSerializable<T>> SavedStateHandle.getJsonSerializableLiveData(key: String, initialValue: T): LiveData<T> {
    val jsonSerializer = initialValue.jsonSerializer
    return getStringLiveData(key, jsonSerializer.toJsonString(initialValue)).map {
        jsonSerializer.fromJsonString(it)
    }
}
fun <T> SavedStateHandle.getNullableJsonSerializableLiveData(key: String, jsonSerializer: JsonSerializer<T>) =
    getNullableStringLiveData(key).map { nullableValue -> nullableValue?.let { jsonSerializer.fromJsonString(it) } }
fun <T : JsonSerializable<T>> SavedStateHandle.getNullableJsonSerializableLiveData(
    key: String,
    initialValue: T?
): LiveData<T?> {
    val jsonSerializer = initialValue?.jsonSerializer
    return getNullableStringLiveData(key, jsonSerializer?.toJsonString(initialValue)).map { nullableValue ->
        nullableValue?.let { jsonSerializer?.fromJsonString(it) }
    }
}
fun <T : JsonSerializable<T>> SavedStateHandle.getJsonSerializableFlow(
    key: String,
    initialValue: T
): Flow<T> {
    val jsonSerializer = initialValue.jsonSerializer
    return getStringStateFlow(key, jsonSerializer.toJsonString(initialValue)).map {
        jsonSerializer.fromJsonString(it)
    }
}
fun <T : JsonSerializable<T>> SavedStateHandle.getNullableJsonSerializableFlow(
    key: String,
    initialValue: T?
): Flow<T?> {
    val jsonSerializer = initialValue?.jsonSerializer
    return getNullableStringStateFlow(key, jsonSerializer?.toJsonString(initialValue)).map { nullableValue ->
        nullableValue?.let { jsonSerializer?.fromJsonString(it) }
    }
}
