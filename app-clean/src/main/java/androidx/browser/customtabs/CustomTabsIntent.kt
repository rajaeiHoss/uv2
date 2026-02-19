package androidx.browser.customtabs

import android.content.Intent

class CustomTabsIntent private constructor(@JvmField val intent: Intent) {
    class Builder {
        fun build(): CustomTabsIntent = CustomTabsIntent(Intent(Intent.ACTION_VIEW))
    }
}
