@file:JvmName("Pickers")
@file:Suppress("unused")

package com.sixtyninefourtwenty.stuff

import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneOffset
import java.util.function.BiConsumer
import java.util.function.Consumer
import androidx.core.util.Pair as AndroidXPair

//Discards the boolean return value.
fun MaterialTimePicker.addOnPositiveClick(block: Consumer<in LocalTime>) {
    addOnPositiveButtonClickListener {
        block.accept(LocalTime.of(hour, minute))
    }
}

fun MaterialDatePicker<Long>.addOnPositiveClick(block: Consumer<in LocalDate>) {
    addOnPositiveButtonClickListener {
        block.accept(Instant.ofEpochMilli(it).atOffset(ZoneOffset.UTC).toLocalDate())
    }
}

fun MaterialDatePicker<AndroidXPair<Long, Long>>.addOnPositiveClick(block: BiConsumer<in LocalDate, in LocalDate>) {
    addOnPositiveButtonClickListener {
        block.accept(
            Instant.ofEpochMilli(it.first).atOffset(ZoneOffset.UTC).toLocalDate(),
            Instant.ofEpochMilli(it.second).atOffset(ZoneOffset.UTC).toLocalDate()
        )
    }
}
