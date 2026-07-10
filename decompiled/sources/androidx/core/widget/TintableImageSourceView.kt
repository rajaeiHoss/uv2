package androidx.core.widget

import android.content.res.ColorStateList
import android.graphics.PorterDuff

interface TintableImageSourceView {
    fun getSupportImageTintList(): ColorStateList?

    fun getSupportImageTintMode(): PorterDuff.Mode?

    fun setSupportImageTintList(tint: ColorStateList?)

    fun setSupportImageTintMode(tintMode: PorterDuff.Mode?)
}
