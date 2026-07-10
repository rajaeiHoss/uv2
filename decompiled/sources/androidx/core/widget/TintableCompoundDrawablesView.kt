package androidx.core.widget

import android.content.res.ColorStateList
import android.graphics.PorterDuff

interface TintableCompoundDrawablesView {
    fun getSupportCompoundDrawablesTintList(): ColorStateList?

    fun getSupportCompoundDrawablesTintMode(): PorterDuff.Mode?

    fun setSupportCompoundDrawablesTintList(tint: ColorStateList?)

    fun setSupportCompoundDrawablesTintMode(tintMode: PorterDuff.Mode?)
}
