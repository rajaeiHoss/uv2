package androidx.core.view

import android.content.res.ColorStateList
import android.graphics.PorterDuff

interface TintableBackgroundView {
    fun getSupportBackgroundTintList(): ColorStateList?

    fun getSupportBackgroundTintMode(): PorterDuff.Mode?

    fun setSupportBackgroundTintList(tint: ColorStateList?)

    fun setSupportBackgroundTintMode(tintMode: PorterDuff.Mode?)
}
