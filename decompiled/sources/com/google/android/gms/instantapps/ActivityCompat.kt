package com.google.android.gms.instantapps

import android.content.ComponentName

interface ActivityCompat {
    val callingActivity: ComponentName?

    val callingPackage: String?
}
