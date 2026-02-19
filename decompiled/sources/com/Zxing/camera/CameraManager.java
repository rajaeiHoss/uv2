package com.Zxing.camera;

import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.Camera;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceHolder;
import com.google.android.gms.search.SearchAuth;
import java.io.IOException;

public final class CameraManager {
    private static final int MAX_FRAME_HEIGHT = 540;
    private static final int MAX_FRAME_WIDTH = 960;
    private static final int MIN_FRAME_HEIGHT = 240;
    private static final int MIN_FRAME_WIDTH = 240;
    static final int SDK_INT;
    private static final String TAG = "CameraManager";
    private static AutoFocusCallback autoFocusCallback;
    private static Camera camera;
    private static CameraManager cameraManager;
    private static PreviewCallback previewCallback;
    private static boolean previewing;
    private static boolean useOneShotPreviewCallback;
    private final CameraConfigurationManager configManager;
    private final Context context;
    private Rect framingRect;
    private Rect framingRectInPreview;
    private boolean initialized;

    static {
        int i;
        try {
            i = Integer.parseInt(Build.VERSION.SDK);
        } catch (NumberFormatException unused) {
            i = SearchAuth.StatusCodes.AUTH_DISABLED;
        }
        SDK_INT = i;
    }

    public static void init(Context context2) {
        if (cameraManager == null) {
            cameraManager = new CameraManager(context2);
        }
    }

    public static Camera getCamera() {
        return camera;
    }

    public static CameraManager get() {
        return cameraManager;
    }

    public static void open() {
        Camera.Parameters parameters = camera.getParameters();
        parameters.setFlashMode("torch");
        camera.setParameters(parameters);
    }

    private CameraManager(Context context2) {
        this.context = context2;
        CameraConfigurationManager cameraConfigurationManager = new CameraConfigurationManager(context2);
        this.configManager = cameraConfigurationManager;
        useOneShotPreviewCallback = Integer.parseInt(Build.VERSION.SDK) > 3;
        previewCallback = new PreviewCallback(cameraConfigurationManager, useOneShotPreviewCallback);
        autoFocusCallback = new AutoFocusCallback();
    }

    public void openDriver(SurfaceHolder surfaceHolder) throws IOException {
        if (camera == null) {
            Camera open = Camera.open();
            camera = open;
            if (open != null) {
                open.setPreviewDisplay(surfaceHolder);
                if (!this.initialized) {
                    this.initialized = true;
                    this.configManager.initFromCameraParameters(camera);
                }
                this.configManager.setDesiredCameraParameters(camera);
                FlashlightManager.enableFlashlight();
                return;
            }
            throw new IOException();
        }
    }

    public void closeDriver() {
        if (camera != null) {
            FlashlightManager.disableFlashlight();
            camera.release();
            camera = null;
        }
    }

    public void startPreview() {
        Camera camera2 = camera;
        if (camera2 != null && !previewing) {
            camera2.startPreview();
            previewing = true;
        }
    }

    public static void stopPreview() {
        Camera camera2 = camera;
        if (camera2 != null && previewing) {
            if (!useOneShotPreviewCallback) {
                camera2.setPreviewCallback((Camera.PreviewCallback) null);
            }
            camera.stopPreview();
            previewCallback.setHandler((Handler) null, 0);
            autoFocusCallback.setHandler((Handler) null, 0);
            previewing = false;
        }
    }

    public void requestPreviewFrame(Handler handler, int i) {
        if (camera != null && previewing) {
            previewCallback.setHandler(handler, i);
            if (useOneShotPreviewCallback) {
                camera.setOneShotPreviewCallback(previewCallback);
            } else {
                camera.setPreviewCallback(previewCallback);
            }
        }
    }

    public void requestAutoFocus(Handler handler, int i) {
        if (camera != null && previewing) {
            autoFocusCallback.setHandler(handler, i);
            camera.autoFocus(autoFocusCallback);
        }
    }

    public Rect getFramingRect() {
        Point screenResolution = this.configManager.getScreenResolution();
        if (this.framingRect == null) {
            if (camera == null) {
                return null;
            }
            int i = (int) (((double) this.context.getResources().getDisplayMetrics().widthPixels) * 0.6d);
            int i2 = (int) (((double) i) * 0.9d);
            int i3 = (screenResolution.x - i) / 2;
            int i4 = (screenResolution.y - i2) / 2;
            this.framingRect = new Rect(i3, i4, i + i3, i2 + i4);
            String str = TAG;
            Log.d(str, "Calculated framing rect: " + this.framingRect);
        }
        return this.framingRect;
    }

    public Rect getFramingRectInPreview() {
        if (this.framingRectInPreview == null) {
            Rect rect = new Rect(getFramingRect());
            Point cameraResolution = this.configManager.getCameraResolution();
            Point screenResolution = this.configManager.getScreenResolution();
            rect.left = (rect.left * cameraResolution.y) / screenResolution.x;
            rect.right = (rect.right * cameraResolution.y) / screenResolution.x;
            rect.top = (rect.top * cameraResolution.x) / screenResolution.y;
            rect.bottom = (rect.bottom * cameraResolution.x) / screenResolution.y;
            this.framingRectInPreview = rect;
        }
        return this.framingRectInPreview;
    }

    public PlanarYUVLuminanceSource buildLuminanceSource(byte[] bArr, int i, int i2) {
        return new PlanarYUVLuminanceSource(bArr, i, i2, 0, 0, i, i2);
    }
}
