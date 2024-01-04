@file:JvmName("Parcels")
@file:Suppress("unused")

package com.sixtyninefourtwenty.stuff

import android.os.Parcel
import com.sixtyninefourtwenty.stuff.interfaces.JsonSerializable
import com.sixtyninefourtwenty.stuff.interfaces.JsonSerializer

fun <T : JsonSerializable<T>> Parcel.writeJsonSerializable(obj: T?) =
    writeString(obj?.jsonSerializer?.toJsonString(obj))

fun <T> Parcel.readJsonSerializable(jsonSerializer: JsonSerializer<T>) =
    readString()?.let { jsonSerializer.fromJsonString(it) }
