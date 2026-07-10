package androidx.core.widget

import android.content.res.ColorStateList
import android.graphics.PorterDuff

interface TintableCompoundButton {
    fun getSupportButtonTintList(): ColorStateList?

    fun getSupportButtonTintMode(): PorterDuff.Mode?

    fun setSupportButtonTintList(tint: ColorStateList?)

    fun setSupportButtonTintMode(tintMode: PorterDuff.Mode?)
}
