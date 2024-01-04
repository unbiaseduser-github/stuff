package com.sixtyninefourtwenty.stuff

import android.content.Intent
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.runner.RunWith

private const val KEY = "key"

@RunWith(AndroidJUnit4::class)
class IntentExtensionsTest {

    private lateinit var intent: Intent

    @Before
    fun setup() {
        intent = Intent()
    }

    @Test
    fun jsonSerializableTest() {
        val obj = SampleJsonSerializable(3, "d")
        intent.putExtra(KEY, obj)
        assertEquals(obj, intent.getJsonSerializableExtra(KEY, SampleJsonSerializable.SERIALIZER))
        intent.putExtra(KEY, null as SampleJsonSerializable?)
        assertNull(intent.getJsonSerializableExtra(KEY, SampleJsonSerializable.SERIALIZER))
    }

}
