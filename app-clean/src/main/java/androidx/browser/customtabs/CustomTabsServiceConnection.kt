package androidx.browser.customtabs

import android.content.ComponentName
import android.content.ServiceConnection
import android.os.IBinder

abstract class CustomTabsServiceConnection : ServiceConnection {
    abstract fun onCustomTabsServiceConnected(name: ComponentName?, client: CustomTabsClient)

    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
        onCustomTabsServiceConnected(name, CustomTabsClient())
    }

    abstract override fun onServiceDisconnected(name: ComponentName?)
}
