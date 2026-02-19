package androidx.browser.customtabs

import android.net.Uri
import android.os.Bundle

open class CustomTabsSession {
    open fun mayLaunchUrl(uri: Uri?, extras: Bundle?, otherLikelyBundles: List<Bundle?>?): Boolean = false
}
