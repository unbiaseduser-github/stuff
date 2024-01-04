package com.sixtyninefourtwenty.stuff

import android.os.Parcel
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ParcelExtensionsTest {

    private lateinit var parcel: Parcel

    @Before
    fun setup() {
        parcel = Parcel.obtain()
    }

    @After
    fun tearDown() {
        parcel.recycle()
    }

    @Test
    fun jsonSerializableTest() {
        val obj = SampleJsonSerializable(2, "c")
        parcel.writeJsonSerializable(obj)
        parcel.setDataPosition(0)
        assertEquals(obj, parcel.readJsonSerializable(SampleJsonSerializable.SERIALIZER))
    }

    @Test
    fun nullJsonSerializableTest() {
        parcel.writeJsonSerializable(null)
        parcel.setDataPosition(0)
        assertNull(parcel.readJsonSerializable(SampleJsonSerializable.SERIALIZER))
    }

}