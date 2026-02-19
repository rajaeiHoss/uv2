package javax.annotation

import java.lang.annotation.Documented
import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target

@MustBeDocumented
@Retention(RetentionPolicy.RUNTIME)
@Target(
    value = [
        ElementType.METHOD,
        ElementType.PARAMETER,
        ElementType.FIELD
    ]
)
annotation class Nullable
