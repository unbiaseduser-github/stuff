@file:JvmName("Bundles")
@file:Suppress("unused")

package com.sixtyninefourtwenty.stuff

import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.core.os.BundleCompat
import com.sixtyninefourtwenty.stuff.annotations.NotSuitableForJava
import java.io.Serializable
import java.util.function.Consumer

@NotSuitableForJava(reason = "Extension wrapper for BundleCompat.getParcelable.")
fun <T : Parcelable> Bundle.getParcelableCompat(key: String, clazz: Class<T>) =
    BundleCompat.getParcelable(this, key, clazz)

inline fun <reified T : Parcelable> Bundle.getParcelableCompat(key: String) =
    getParcelableCompat(key, T::class.java)

fun <T : Serializable> Bundle.getSerializableCompat(key: String, clazz: Class<T>) =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        getSerializable(key, clazz)
    } else {
        @Suppress("DEPRECATION")
        clazz.cast(getSerializable(key))
    }

inline fun <reified T : Serializable> Bundle.getSerializableCompat(key: String) =
    getSerializableCompat(key, T::class.java)

@JvmName("createBundle")
fun bundle(block: Consumer<in Bundle>) = Bundle().apply(block::accept)

@JvmSynthetic
inline fun bundle(block: Bundle.() -> Unit) = Bundle().apply(block)
