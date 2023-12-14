package com.sixtyninefourtwenty.stuff.annotations

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.CLASS)
@MustBeDocumented
@Retention
@Suppress("unused")
internal annotation class BuiltWithDependency(val dependency: String, val version: String)
