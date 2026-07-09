package com.streamax.client

import android.view.SurfaceHolder

interface SurfaceCallBackInterface {
    fun surfaceChanged(
        surfaceHolder: SurfaceHolder,
        index: Int,
        format: Int,
        width: Int,
        height: Int,
    )

    fun surfaceCreated(surfaceHolder: SurfaceHolder, index: Int)

    fun surfaceDestroyed(surfaceHolder: SurfaceHolder, index: Int)
}
