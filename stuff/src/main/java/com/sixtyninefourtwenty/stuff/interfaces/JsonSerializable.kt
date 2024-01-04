package com.sixtyninefourtwenty.stuff.interfaces

@Suppress("unused")
interface JsonSerializable<T> {
    val jsonSerializer: JsonSerializer<T>
}