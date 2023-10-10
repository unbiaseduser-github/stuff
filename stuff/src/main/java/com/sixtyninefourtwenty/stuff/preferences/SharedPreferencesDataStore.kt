package com.sixtyninefourtwenty.stuff.preferences

import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.preference.PreferenceDataStore

/**
 * [PreferenceDataStore] implementation that delegates its functionality to a [SharedPreferences].
 */
@Suppress("unused", "MemberVisibilityCanBePrivate")
open class SharedPreferencesDataStore(protected val delegate: SharedPreferences) : PreferenceDataStore() {

    override fun putBoolean(key: String, value: Boolean) {
        delegate.edit { putBoolean(key, value) }
    }

    override fun getBoolean(key: String, defValue: Boolean): Boolean {
        return delegate.getBoolean(key, defValue)
    }

    override fun putFloat(key: String, value: Float) {
        delegate.edit { putFloat(key, value) }
    }

    override fun getFloat(key: String, defValue: Float): Float {
        return delegate.getFloat(key, defValue)
    }

    override fun putInt(key: String, value: Int) {
        delegate.edit { putInt(key, value) }
    }

    override fun getInt(key: String, defValue: Int): Int {
        return delegate.getInt(key, defValue)
    }

    override fun putLong(key: String, value: Long) {
        delegate.edit { putLong(key, value) }
    }

    override fun getLong(key: String, defValue: Long): Long {
        return delegate.getLong(key, defValue)
    }

    override fun putString(key: String, value: String?) {
        delegate.edit { putString(key, value) }
    }

    override fun getString(key: String, defValue: String?): String? {
        return delegate.getString(key, defValue)
    }

    override fun putStringSet(key: String, values: Set<String>?) {
        delegate.edit { putStringSet(key, values) }
    }

    override fun getStringSet(key: String, defValues: Set<String>?): Set<String>? {
        return delegate.getStringSet(key, defValues)
    }

}
