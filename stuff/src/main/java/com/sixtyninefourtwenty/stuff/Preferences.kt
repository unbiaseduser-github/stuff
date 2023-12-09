@file:JvmName("Preferences")
@file:Suppress("unused")

package com.sixtyninefourtwenty.stuff

import android.content.SharedPreferences
import androidx.preference.CheckBoxPreference
import androidx.preference.ListPreference
import androidx.preference.MultiSelectListPreference
import androidx.preference.SeekBarPreference
import androidx.preference.SwitchPreferenceCompat
import com.sixtyninefourtwenty.stuff.preferences.AnyPreferenceValue
import com.sixtyninefourtwenty.stuff.preferences.FloatPreferenceValue
import com.sixtyninefourtwenty.stuff.preferences.IntPreferenceValue
import com.sixtyninefourtwenty.stuff.preferences.LongPreferenceValue
import com.sixtyninefourtwenty.stuff.preferences.StringPreferenceValue

fun ListPreference.setOnPreferenceChange(block: ((String) -> Boolean)?) =
    if (block != null) {
        setOnPreferenceChangeListener { _, newValue -> block(newValue as String) }
    } else {
        onPreferenceChangeListener = null
    }

fun SwitchPreferenceCompat.setOnPreferenceChange(block: ((Boolean) -> Boolean)?) =
    if (block != null) {
        setOnPreferenceChangeListener { _, newValue -> block(newValue as Boolean) }
    } else {
        onPreferenceChangeListener = null
    }

fun MultiSelectListPreference.setOnPreferenceChange(block: ((Set<String>) -> Boolean)?) =
    if (block != null) {
        setOnPreferenceChangeListener { _, newValue -> @Suppress("UNCHECKED_CAST") block(newValue as Set<String>) }
    } else {
        onPreferenceChangeListener = null
    }

fun CheckBoxPreference.setOnPreferenceChange(block: ((Boolean) -> Boolean)?) =
    if (block != null) {
        setOnPreferenceChangeListener { _, newValue -> block(newValue as Boolean) }
    } else {
        onPreferenceChangeListener = null
    }

fun SeekBarPreference.setOnPreferenceChange(block: ((Int) -> Boolean)?) =
    if (block != null) {
        setOnPreferenceChangeListener { _, newValue -> block(newValue as Int) }
    } else {
        onPreferenceChangeListener = null
    }

fun SharedPreferences.getNonNullString(key: String, defValue: String) = getString(key, defValue)!!

fun SharedPreferences.getStringOrNull(key: String) = getString(key, null)

@JvmOverloads
fun SharedPreferences.getNonNullStringSet(key: String, defValue: Set<String> = setOf()): Set<String> = getStringSet(key, defValue)!!

fun SharedPreferences.getStringSetOrNull(key: String): Set<String>? = getStringSet(key, null)

private fun <E : AnyPreferenceValue> SharedPreferences.getAnyValue(key: String, availableValues: Iterable<E>, defValue: E): E {
    val prefValue: Any = when (defValue) {
        is IntPreferenceValue -> getInt(key, defValue.value)
        is FloatPreferenceValue -> getFloat(key, defValue.value)
        is LongPreferenceValue -> getLong(key, defValue.value)
        is StringPreferenceValue -> getNonNullString(key, defValue.value)
        else -> throw IllegalArgumentException("unknown subtype: ${defValue.javaClass.simpleName}")
    }
    if (prefValue == defValue.value) {
        return defValue
    }
    return availableValues.first { it.value == prefValue }
}

fun <E : IntPreferenceValue> SharedPreferences.getIntValue(key: String, availableValues: Iterable<E>, defValue: E): E =
    getAnyValue(key, availableValues, defValue)

fun SharedPreferences.Editor.putIntValue(key: String, value: IntPreferenceValue): SharedPreferences.Editor =
    putInt(key, value.value)

fun <E : LongPreferenceValue> SharedPreferences.getLongValue(key: String, availableValues: Iterable<E>, defValue: E): E =
    getAnyValue(key, availableValues, defValue)

fun SharedPreferences.Editor.putLongValue(key: String, value: LongPreferenceValue): SharedPreferences.Editor =
    putLong(key, value.value)

fun <E : FloatPreferenceValue> SharedPreferences.getFloatValue(key: String, availableValues: Iterable<E>, defValue: E): E =
    getAnyValue(key, availableValues, defValue)

fun SharedPreferences.Editor.putFloatValue(key: String, value: FloatPreferenceValue): SharedPreferences.Editor =
    putFloat(key, value.value)

fun <E : StringPreferenceValue> SharedPreferences.getStringValue(key: String, availableValues: Iterable<E>, defValue: E): E =
    getAnyValue(key, availableValues, defValue)

fun SharedPreferences.Editor.putStringValue(key: String, value: StringPreferenceValue): SharedPreferences.Editor =
    putString(key, value.value)

@JvmOverloads
fun <E : StringPreferenceValue> SharedPreferences.getStringValues(key: String, availableValues: Iterable<E>, defValue: Iterable<E> = setOf()): Iterable<E> =
    getNonNullStringSet(key, defValue.mapTo(mutableSetOf()) { it.value })
        .map { prefValue -> availableValues.first { prefValue == it.value } }

fun SharedPreferences.Editor.putStringValues(key: String, values: Iterable<StringPreferenceValue>): SharedPreferences.Editor =
    putStringSet(key, values.mapTo(mutableSetOf()) { it.value })
