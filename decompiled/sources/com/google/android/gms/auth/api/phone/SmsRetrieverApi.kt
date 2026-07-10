package com.google.android.gms.auth.api.phone

import com.google.android.gms.tasks.Task
import java.lang.Void

interface SmsRetrieverApi {
    fun startSmsRetriever(): Task<Void>
}
