package androidx.browser.customtabs

import android.content.Context

open class CustomTabsClient {
    open fun warmup(flags: Long): Boolean = false

    open fun newSession(callback: CustomTabsCallback?): CustomTabsSession = CustomTabsSession()

    companion object {
        @JvmStatic
        fun bindCustomTabsService(
            context: Context?,
            packageName: String?,
            connection: CustomTabsServiceConnection?
        ): Boolean = false
    }
}
