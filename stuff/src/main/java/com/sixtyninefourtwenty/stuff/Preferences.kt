@file:JvmName("Preferences")
@file:Suppress("unused")

package com.sixtyninefourtwenty.stuff

import androidx.preference.CheckBoxPreference
import androidx.preference.ListPreference
import androidx.preference.MultiSelectListPreference
import androidx.preference.SeekBarPreference
import androidx.preference.SwitchPreferenceCompat

fun ListPreference.setOnPreferenceChange(block: ((String) -> Boolean)?) =
    if (block != null) {
        setOnPreferenceChangeListener { _, newValue -> block(newValue as String) }
    } else {
        setOnPreferenceChangeListener(null)
    }

fun SwitchPreferenceCompat.setOnPreferenceChange(block: ((Boolean) -> Boolean)?) =
    if (block != null) {
        setOnPreferenceChangeListener { _, newValue -> block(newValue as Boolean) }
    } else {
        setOnPreferenceChangeListener(null)
    }

fun MultiSelectListPreference.setOnPreferenceChange(block: ((Set<String>) -> Boolean)?) =
    if (block != null) {
        setOnPreferenceChangeListener { _, newValue -> @Suppress("UNCHECKED_CAST") block(newValue as Set<String>) }
    } else {
        setOnPreferenceChangeListener(null)
    }

fun CheckBoxPreference.setOnPreferenceChange(block: ((Boolean) -> Boolean)?) =
    if (block != null) {
        setOnPreferenceChangeListener { _, newValue -> block(newValue as Boolean) }
    } else {
        setOnPreferenceChangeListener(null)
    }

fun SeekBarPreference.setOnPreferenceChange(block: ((Int) -> Boolean)?) =
    if (block != null) {
        setOnPreferenceChangeListener { _, newValue -> block(newValue as Int) }
    } else {
        setOnPreferenceChangeListener(null)
    }

