package androidx.core.app

import android.app.Notification

interface NotificationBuilderWithBuilderAccessor {
    fun getBuilder(): Notification.Builder
}
