package com.google.android.gms.vision;

import android.content.Context;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.SystemClock;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.WindowManager;
import com.google.android.gms.common.images.Size;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.jivesoftware.smackx.GroupChatInvitation;

public class CameraSource {
    public static final int CAMERA_FACING_BACK = 0;
    public static final int CAMERA_FACING_FRONT = 1;
    /* access modifiers changed from: private */
    public Context mContext;
    /* access modifiers changed from: private */
    public int zzcma;
    /* access modifiers changed from: private */
    public final Object zzlew;
    /* access modifiers changed from: private */
    public Camera zzlex;
    /* access modifiers changed from: private */
    public int zzley;
    /* access modifiers changed from: private */
    public Size zzlez;
    /* access modifiers changed from: private */
    public float zzlfa;
    /* access modifiers changed from: private */
    public int zzlfb;
    /* access modifiers changed from: private */
    public int zzlfc;
    /* access modifiers changed from: private */
    public boolean zzlfd;
    private SurfaceTexture zzlfe;
    private boolean zzlff;
    private Thread zzlfg;
    /* access modifiers changed from: private */
    public zzb zzlfh;
    /* access modifiers changed from: private */
    public Map<byte[], ByteBuffer> zzlfi;

    public static class Builder {
        private final Detector<?> zzlfj;
        private CameraSource zzlfk;

        public Builder(Context context, Detector<?> detector) {
            CameraSource cameraSource = new CameraSource();
            this.zzlfk = cameraSource;
            if (context == null) {
                throw new IllegalArgumentException("No context supplied.");
            } else if (detector != null) {
                this.zzlfj = detector;
                Context unused = cameraSource.mContext = context;
            } else {
                throw new IllegalArgumentException("No detector supplied.");
            }
        }

        public CameraSource build() {
            CameraSource cameraSource = this.zzlfk;
            cameraSource.getClass();
            zzb unused = cameraSource.zzlfh = cameraSource.new zzb(this.zzlfj);
            return this.zzlfk;
        }

        public Builder setAutoFocusEnabled(boolean z) {
            boolean unused = this.zzlfk.zzlfd = z;
            return this;
        }

        public Builder setFacing(int i) {
            if (i == 0 || i == 1) {
                int unused = this.zzlfk.zzley = i;
                return this;
            }
            StringBuilder sb = new StringBuilder(27);
            sb.append("Invalid camera: ");
            sb.append(i);
            throw new IllegalArgumentException(sb.toString());
        }

        public Builder setRequestedFps(float f) {
            if (f > 0.0f) {
                float unused = this.zzlfk.zzlfa = f;
                return this;
            }
            StringBuilder sb = new StringBuilder(28);
            sb.append("Invalid fps: ");
            sb.append(f);
            throw new IllegalArgumentException(sb.toString());
        }

        public Builder setRequestedPreviewSize(int i, int i2) {
            if (i <= 0 || i > 1000000 || i2 <= 0 || i2 > 1000000) {
                StringBuilder sb = new StringBuilder(45);
                sb.append("Invalid preview size: ");
                sb.append(i);
                sb.append(GroupChatInvitation.ELEMENT_NAME);
                sb.append(i2);
                throw new IllegalArgumentException(sb.toString());
            }
            int unused = this.zzlfk.zzlfb = i;
            int unused2 = this.zzlfk.zzlfc = i2;
            return this;
        }
    }

    public interface PictureCallback {
        void onPictureTaken(byte[] bArr);
    }

    public interface ShutterCallback {
        void onShutter();
    }

    class zza implements Camera.PreviewCallback {
        private zza() {
        }

        public final void onPreviewFrame(byte[] bArr, Camera camera) {
            CameraSource.this.zzlfh.zza(bArr, camera);
        }
    }

    class zzb implements Runnable {
        private boolean mActive = true;
        private final Object mLock = new Object();
        private long zzebf = SystemClock.elapsedRealtime();
        private Detector<?> zzlfj;
        private long zzlfm;
        private int zzlfn = 0;
        private ByteBuffer zzlfo;

        zzb(Detector<?> detector) {
            this.zzlfj = detector;
        }

        /* access modifiers changed from: package-private */
        public final void release() {
            this.zzlfj.release();
            this.zzlfj = null;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
            r6.zzlfj.receiveFrame(r1);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:23:0x0073, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
            android.util.Log.e("CameraSource", "Exception thrown from receiver.", r0);
         */
        /* JADX WARNING: Code restructure failed: missing block: B:28:0x008a, code lost:
            r0 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:29:0x008b, code lost:
            com.google.android.gms.vision.CameraSource.zzb(r6.zzlfl).addCallbackBuffer(r2.array());
         */
        /* JADX WARNING: Code restructure failed: missing block: B:30:0x0098, code lost:
            throw r0;
         */
        /* JADX WARNING: Removed duplicated region for block: B:18:0x001f A[Catch:{ InterruptedException -> 0x0011 }] */
        /* JADX WARNING: Removed duplicated region for block: B:36:0x001d A[SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void run() {
            /*
                r6 = this;
            L_0x0000:
                java.lang.Object r0 = r6.mLock
                monitor-enter(r0)
            L_0x0003:
                boolean r1 = r6.mActive     // Catch:{ all -> 0x0099 }
                if (r1 == 0) goto L_0x001b
                java.nio.ByteBuffer r2 = r6.zzlfo     // Catch:{ all -> 0x0099 }
                if (r2 != 0) goto L_0x001b
                java.lang.Object r1 = r6.mLock     // Catch:{ InterruptedException -> 0x0011 }
                r1.wait()     // Catch:{ InterruptedException -> 0x0011 }
                goto L_0x0003
            L_0x0011:
                r1 = move-exception
                java.lang.String r2 = "CameraSource"
                java.lang.String r3 = "Frame processing loop terminated."
                android.util.Log.d(r2, r3, r1)     // Catch:{ all -> 0x0099 }
                monitor-exit(r0)     // Catch:{ all -> 0x0099 }
                return
            L_0x001b:
                if (r1 != 0) goto L_0x001f
                monitor-exit(r0)     // Catch:{ all -> 0x0099 }
                return
            L_0x001f:
                com.google.android.gms.vision.Frame$Builder r1 = new com.google.android.gms.vision.Frame$Builder     // Catch:{ all -> 0x0099 }
                r1.<init>()     // Catch:{ all -> 0x0099 }
                java.nio.ByteBuffer r2 = r6.zzlfo     // Catch:{ all -> 0x0099 }
                com.google.android.gms.vision.CameraSource r3 = com.google.android.gms.vision.CameraSource.this     // Catch:{ all -> 0x0099 }
                com.google.android.gms.common.images.Size r3 = r3.zzlez     // Catch:{ all -> 0x0099 }
                int r3 = r3.getWidth()     // Catch:{ all -> 0x0099 }
                com.google.android.gms.vision.CameraSource r4 = com.google.android.gms.vision.CameraSource.this     // Catch:{ all -> 0x0099 }
                com.google.android.gms.common.images.Size r4 = r4.zzlez     // Catch:{ all -> 0x0099 }
                int r4 = r4.getHeight()     // Catch:{ all -> 0x0099 }
                r5 = 17
                com.google.android.gms.vision.Frame$Builder r1 = r1.setImageData(r2, r3, r4, r5)     // Catch:{ all -> 0x0099 }
                int r2 = r6.zzlfn     // Catch:{ all -> 0x0099 }
                com.google.android.gms.vision.Frame$Builder r1 = r1.setId(r2)     // Catch:{ all -> 0x0099 }
                long r2 = r6.zzlfm     // Catch:{ all -> 0x0099 }
                com.google.android.gms.vision.Frame$Builder r1 = r1.setTimestampMillis(r2)     // Catch:{ all -> 0x0099 }
                com.google.android.gms.vision.CameraSource r2 = com.google.android.gms.vision.CameraSource.this     // Catch:{ all -> 0x0099 }
                int r2 = r2.zzcma     // Catch:{ all -> 0x0099 }
                com.google.android.gms.vision.Frame$Builder r1 = r1.setRotation(r2)     // Catch:{ all -> 0x0099 }
                com.google.android.gms.vision.Frame r1 = r1.build()     // Catch:{ all -> 0x0099 }
                java.nio.ByteBuffer r2 = r6.zzlfo     // Catch:{ all -> 0x0099 }
                r3 = 0
                r6.zzlfo = r3     // Catch:{ all -> 0x0099 }
                monitor-exit(r0)     // Catch:{ all -> 0x0099 }
                com.google.android.gms.vision.Detector<?> r0 = r6.zzlfj     // Catch:{ all -> 0x0073 }
                r0.receiveFrame(r1)     // Catch:{ all -> 0x0073 }
                com.google.android.gms.vision.CameraSource r0 = com.google.android.gms.vision.CameraSource.this
                android.hardware.Camera r0 = r0.zzlex
                byte[] r1 = r2.array()
                r0.addCallbackBuffer(r1)
                goto L_0x0000
            L_0x0073:
                r0 = move-exception
                java.lang.String r1 = "CameraSource"
                java.lang.String r3 = "Exception thrown from receiver."
                android.util.Log.e(r1, r3, r0)     // Catch:{ all -> 0x008a }
                com.google.android.gms.vision.CameraSource r0 = com.google.android.gms.vision.CameraSource.this
                android.hardware.Camera r0 = r0.zzlex
                byte[] r1 = r2.array()
                r0.addCallbackBuffer(r1)
                goto L_0x0000
            L_0x008a:
                r0 = move-exception
                com.google.android.gms.vision.CameraSource r1 = com.google.android.gms.vision.CameraSource.this
                android.hardware.Camera r1 = r1.zzlex
                byte[] r2 = r2.array()
                r1.addCallbackBuffer(r2)
                throw r0
            L_0x0099:
                r1 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x0099 }
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.vision.CameraSource.zzb.run():void");
        }

        /* access modifiers changed from: package-private */
        public final void setActive(boolean z) {
            synchronized (this.mLock) {
                this.mActive = z;
                this.mLock.notifyAll();
            }
        }

        /* access modifiers changed from: package-private */
        public final void zza(byte[] bArr, Camera camera) {
            synchronized (this.mLock) {
                ByteBuffer byteBuffer = this.zzlfo;
                if (byteBuffer != null) {
                    camera.addCallbackBuffer(byteBuffer.array());
                    this.zzlfo = null;
                }
                if (!CameraSource.this.zzlfi.containsKey(bArr)) {
                    Log.d("CameraSource", "Skipping frame. Could not find ByteBuffer associated with the image data from the camera.");
                    return;
                }
                this.zzlfm = SystemClock.elapsedRealtime() - this.zzebf;
                this.zzlfn++;
                this.zzlfo = (ByteBuffer) CameraSource.this.zzlfi.get(bArr);
                this.mLock.notifyAll();
            }
        }
    }

    class zzc implements Camera.PictureCallback {
        /* access modifiers changed from: private */
        public PictureCallback zzlfp;

        private zzc() {
        }

        public final void onPictureTaken(byte[] bArr, Camera camera) {
            PictureCallback pictureCallback = this.zzlfp;
            if (pictureCallback != null) {
                pictureCallback.onPictureTaken(bArr);
            }
            synchronized (CameraSource.this.zzlew) {
                if (CameraSource.this.zzlex != null) {
                    CameraSource.this.zzlex.startPreview();
                }
            }
        }
    }

    static class zzd implements Camera.ShutterCallback {
        /* access modifiers changed from: private */
        public ShutterCallback zzlfq;

        private zzd() {
        }

        public final void onShutter() {
            ShutterCallback shutterCallback = this.zzlfq;
            if (shutterCallback != null) {
                shutterCallback.onShutter();
            }
        }
    }

    static class zze {
        private Size zzlfr;
        private Size zzlfs;

        public zze(Camera.Size size, Camera.Size size2) {
            this.zzlfr = new Size(size.width, size.height);
            if (size2 != null) {
                this.zzlfs = new Size(size2.width, size2.height);
            }
        }

        public final Size zzbli() {
            return this.zzlfr;
        }

        public final Size zzblj() {
            return this.zzlfs;
        }
    }

    private CameraSource() {
        this.zzlew = new Object();
        this.zzley = 0;
        this.zzlfa = 30.0f;
        this.zzlfb = 1024;
        this.zzlfc = 768;
        this.zzlfd = false;
        this.zzlfi = new HashMap();
    }

    private static zze zza(Camera camera, int i, int i2) {
        Camera.Parameters parameters = camera.getParameters();
        List<Camera.Size> supportedPreviewSizes = parameters.getSupportedPreviewSizes();
        List<Camera.Size> supportedPictureSizes = parameters.getSupportedPictureSizes();
        ArrayList arrayList = new ArrayList();
        for (Camera.Size next : supportedPreviewSizes) {
            float f = ((float) next.width) / ((float) next.height);
            Iterator<Camera.Size> it = supportedPictureSizes.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Camera.Size next2 = it.next();
                if (Math.abs(f - (((float) next2.width) / ((float) next2.height))) < 0.01f) {
                    arrayList.add(new zze(next, next2));
                    break;
                }
            }
        }
        zze zze2 = null;
        if (arrayList.size() == 0) {
            Log.w("CameraSource", "No preview sizes have a corresponding same-aspect-ratio picture size");
            for (Camera.Size zze3 : supportedPreviewSizes) {
                arrayList.add(new zze(zze3, (Camera.Size) null));
            }
        }
        int i3 = Integer.MAX_VALUE;
        ArrayList arrayList2 = arrayList;
        int size = arrayList2.size();
        int i4 = 0;
        while (i4 < size) {
            Object obj = arrayList2.get(i4);
            i4++;
            zze zze4 = (zze) obj;
            Size zzbli = zze4.zzbli();
            int abs = Math.abs(zzbli.getWidth() - i) + Math.abs(zzbli.getHeight() - i2);
            if (abs < i3) {
                zze2 = zze4;
                i3 = abs;
            }
        }
        return zze2;
    }

    private final byte[] zza(Size size) {
        byte[] bArr = new byte[(((int) Math.ceil(((double) ((long) ((size.getHeight() * size.getWidth()) * ImageFormat.getBitsPerPixel(17)))) / 8.0d)) + 1)];
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        if (!wrap.hasArray() || wrap.array() != bArr) {
            throw new IllegalStateException("Failed to create valid buffer for camera source.");
        }
        this.zzlfi.put(bArr, wrap);
        return bArr;
    }

    private static int[] zza(Camera camera, float f) {
        int i = (int) (f * 1000.0f);
        int[] iArr = null;
        int i2 = Integer.MAX_VALUE;
        for (int[] next : camera.getParameters().getSupportedPreviewFpsRange()) {
            int abs = Math.abs(i - next[0]) + Math.abs(i - next[1]);
            if (abs < i2) {
                iArr = next;
                i2 = abs;
            }
        }
        return iArr;
    }

    private final Camera zzblh() throws IOException {
        int i;
        int i2;
        int i3 = this.zzley;
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        int i4 = 0;
        int i5 = 0;
        while (true) {
            if (i5 >= Camera.getNumberOfCameras()) {
                i5 = -1;
                break;
            }
            Camera.getCameraInfo(i5, cameraInfo);
            if (cameraInfo.facing == i3) {
                break;
            }
            i5++;
        }
        if (i5 != -1) {
            Camera open = Camera.open(i5);
            zze zza2 = zza(open, this.zzlfb, this.zzlfc);
            if (zza2 != null) {
                Size zzblj = zza2.zzblj();
                this.zzlez = zza2.zzbli();
                int[] zza3 = zza(open, this.zzlfa);
                if (zza3 != null) {
                    Camera.Parameters parameters = open.getParameters();
                    if (zzblj != null) {
                        parameters.setPictureSize(zzblj.getWidth(), zzblj.getHeight());
                    }
                    parameters.setPreviewSize(this.zzlez.getWidth(), this.zzlez.getHeight());
                    parameters.setPreviewFpsRange(zza3[0], zza3[1]);
                    parameters.setPreviewFormat(17);
                    int rotation = ((WindowManager) this.mContext.getSystemService("window")).getDefaultDisplay().getRotation();
                    if (rotation != 0) {
                        if (rotation == 1) {
                            i4 = 90;
                        } else if (rotation == 2) {
                            i4 = 180;
                        } else if (rotation != 3) {
                            StringBuilder sb = new StringBuilder(31);
                            sb.append("Bad rotation value: ");
                            sb.append(rotation);
                            Log.e("CameraSource", sb.toString());
                        } else {
                            i4 = 270;
                        }
                    }
                    Camera.CameraInfo cameraInfo2 = new Camera.CameraInfo();
                    Camera.getCameraInfo(i5, cameraInfo2);
                    int i6 = cameraInfo2.facing;
                    int i7 = cameraInfo2.orientation;
                    if (i6 == 1) {
                        i2 = (i7 + i4) % 360;
                        i = (360 - i2) % 360;
                    } else {
                        i2 = ((i7 - i4) + 360) % 360;
                        i = i2;
                    }
                    this.zzcma = i2 / 90;
                    open.setDisplayOrientation(i);
                    parameters.setRotation(i2);
                    if (this.zzlfd) {
                        if (parameters.getSupportedFocusModes().contains("continuous-video")) {
                            parameters.setFocusMode("continuous-video");
                        } else {
                            Log.i("CameraSource", "Camera auto focus is not supported on this device.");
                        }
                    }
                    open.setParameters(parameters);
                    open.setPreviewCallbackWithBuffer(new zza());
                    open.addCallbackBuffer(zza(this.zzlez));
                    open.addCallbackBuffer(zza(this.zzlez));
                    open.addCallbackBuffer(zza(this.zzlez));
                    open.addCallbackBuffer(zza(this.zzlez));
                    return open;
                }
                throw new IOException("Could not find suitable preview frames per second range.");
            }
            throw new IOException("Could not find suitable preview size.");
        }
        throw new IOException("Could not find requested camera.");
    }

    public int getCameraFacing() {
        return this.zzley;
    }

    public Size getPreviewSize() {
        return this.zzlez;
    }

    public void release() {
        synchronized (this.zzlew) {
            stop();
            this.zzlfh.release();
        }
    }

    public CameraSource start() throws IOException {
        synchronized (this.zzlew) {
            if (this.zzlex != null) {
                return this;
            }
            this.zzlex = zzblh();
            SurfaceTexture surfaceTexture = new SurfaceTexture(100);
            this.zzlfe = surfaceTexture;
            this.zzlex.setPreviewTexture(surfaceTexture);
            this.zzlff = true;
            this.zzlex.startPreview();
            this.zzlfg = new Thread(this.zzlfh);
            this.zzlfh.setActive(true);
            this.zzlfg.start();
            return this;
        }
    }

    public CameraSource start(SurfaceHolder surfaceHolder) throws IOException {
        synchronized (this.zzlew) {
            if (this.zzlex != null) {
                return this;
            }
            Camera zzblh = zzblh();
            this.zzlex = zzblh;
            zzblh.setPreviewDisplay(surfaceHolder);
            this.zzlex.startPreview();
            this.zzlfg = new Thread(this.zzlfh);
            this.zzlfh.setActive(true);
            this.zzlfg.start();
            this.zzlff = false;
            return this;
        }
    }

    public void stop() {
        synchronized (this.zzlew) {
            this.zzlfh.setActive(false);
            Thread thread = this.zzlfg;
            if (thread != null) {
                try {
                    thread.join();
                } catch (InterruptedException unused) {
                    Log.d("CameraSource", "Frame processing thread interrupted on release.");
                }
                this.zzlfg = null;
            }
            Camera camera = this.zzlex;
            if (camera != null) {
                camera.stopPreview();
                this.zzlex.setPreviewCallbackWithBuffer((Camera.PreviewCallback) null);
                try {
                    if (this.zzlff) {
                        this.zzlex.setPreviewTexture((SurfaceTexture) null);
                    } else {
                        this.zzlex.setPreviewDisplay((SurfaceHolder) null);
                    }
                } catch (Exception e) {
                    String valueOf = String.valueOf(e);
                    StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 32);
                    sb.append("Failed to clear camera preview: ");
                    sb.append(valueOf);
                    Log.e("CameraSource", sb.toString());
                }
                this.zzlex.release();
                this.zzlex = null;
            }
            this.zzlfi.clear();
        }
    }

    public void takePicture(ShutterCallback shutterCallback, PictureCallback pictureCallback) {
        synchronized (this.zzlew) {
            if (this.zzlex != null) {
                zzd zzd2 = new zzd();
                ShutterCallback unused = zzd2.zzlfq = shutterCallback;
                zzc zzc2 = new zzc();
                PictureCallback unused2 = zzc2.zzlfp = pictureCallback;
                this.zzlex.takePicture(zzd2, (Camera.PictureCallback) null, (Camera.PictureCallback) null, zzc2);
            }
        }
    }
}
