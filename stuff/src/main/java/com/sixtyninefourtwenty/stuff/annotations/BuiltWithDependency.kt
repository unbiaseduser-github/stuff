package com.sixtyninefourtwenty.stuff.annotations

/**
 * Informs users what dependency is required to use the annotated element since said dependency
 * is not included with the library.
 */
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.CLASS)
@MustBeDocumented
@Retention
@Suppress("unused")
internal annotation class BuiltWithDependency(val dependency: String, val version: String)
