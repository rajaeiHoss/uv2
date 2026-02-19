package com.streamax.client;

import android.view.SurfaceHolder;

public interface SurfaceCallBackInterface {
    void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3, int i4);

    void surfaceCreated(SurfaceHolder surfaceHolder, int i);

    void surfaceDestroyed(SurfaceHolder surfaceHolder, int i);
}
