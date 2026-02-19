package javax.annotation.meta

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

@MustBeDocumented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = [ElementType.ANNOTATION_TYPE])
annotation class TypeQualifierDefault(val value: Array<ElementType> = [])
