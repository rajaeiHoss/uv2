package com.google.android.gms.internal;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.opengl.GLES20;
import android.util.Log;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.concurrent.CountDownLatch;
import javax.microedition.khronos.egl.EGL10;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.egl.EGLDisplay;
import javax.microedition.khronos.egl.EGLSurface;

@zzabh
public final class zzanl extends Thread implements SurfaceTexture.OnFrameAvailableListener, zzank {
    private static final float[] zzdmn = {-1.0f, -1.0f, -1.0f, 1.0f, -1.0f, -1.0f, -1.0f, 1.0f, -1.0f, 1.0f, 1.0f, -1.0f};
    private int zzalt;
    private int zzalu;
    private final float[] zzdmk = new float[9];
    private final zzani zzdmo;
    private final float[] zzdmp = new float[9];
    private final float[] zzdmq = new float[9];
    private final float[] zzdmr = new float[9];
    private final float[] zzdms = new float[9];
    private final float[] zzdmt = new float[9];
    private final float[] zzdmu = new float[9];
    private float zzdmv = Float.NaN;
    private float zzdmw;
    private float zzdmx;
    private SurfaceTexture zzdmy;
    private SurfaceTexture zzdmz;
    private int zzdna;
    private int zzdnb;
    private int zzdnc;
    private FloatBuffer zzdnd;
    private final CountDownLatch zzdne;
    private final Object zzdnf;
    private EGL10 zzdng;
    private EGLDisplay zzdnh;
    private EGLContext zzdni;
    private EGLSurface zzdnj;
    private volatile boolean zzdnk;
    private volatile boolean zzdnl;

    public zzanl(Context context) {
        super("SphericalVideoProcessor");
        float[] fArr = zzdmn;
        FloatBuffer asFloatBuffer = ByteBuffer.allocateDirect(fArr.length << 2).order(ByteOrder.nativeOrder()).asFloatBuffer();
        this.zzdnd = asFloatBuffer;
        asFloatBuffer.put(fArr).position(0);
        zzani zzani = new zzani(context);
        this.zzdmo = zzani;
        zzani.zza((zzank) this);
        this.zzdne = new CountDownLatch(1);
        this.zzdnf = new Object();
    }

    private static void zza(float[] fArr, float f) {
        fArr[0] = 1.0f;
        fArr[1] = 0.0f;
        fArr[2] = 0.0f;
        fArr[3] = 0.0f;
        double d = (double) f;
        fArr[4] = (float) Math.cos(d);
        fArr[5] = (float) (-Math.sin(d));
        fArr[6] = 0.0f;
        fArr[7] = (float) Math.sin(d);
        fArr[8] = (float) Math.cos(d);
    }

    private static void zza(float[] fArr, float[] fArr2, float[] fArr3) {
        fArr[0] = (fArr2[0] * fArr3[0]) + (fArr2[1] * fArr3[3]) + (fArr2[2] * fArr3[6]);
        fArr[1] = (fArr2[0] * fArr3[1]) + (fArr2[1] * fArr3[4]) + (fArr2[2] * fArr3[7]);
        fArr[2] = (fArr2[0] * fArr3[2]) + (fArr2[1] * fArr3[5]) + (fArr2[2] * fArr3[8]);
        fArr[3] = (fArr2[3] * fArr3[0]) + (fArr2[4] * fArr3[3]) + (fArr2[5] * fArr3[6]);
        fArr[4] = (fArr2[3] * fArr3[1]) + (fArr2[4] * fArr3[4]) + (fArr2[5] * fArr3[7]);
        fArr[5] = (fArr2[3] * fArr3[2]) + (fArr2[4] * fArr3[5]) + (fArr2[5] * fArr3[8]);
        fArr[6] = (fArr2[6] * fArr3[0]) + (fArr2[7] * fArr3[3]) + (fArr2[8] * fArr3[6]);
        fArr[7] = (fArr2[6] * fArr3[1]) + (fArr2[7] * fArr3[4]) + (fArr2[8] * fArr3[7]);
        fArr[8] = (fArr2[6] * fArr3[2]) + (fArr2[7] * fArr3[5]) + (fArr2[8] * fArr3[8]);
    }

    private static void zzb(float[] fArr, float f) {
        double d = (double) f;
        fArr[0] = (float) Math.cos(d);
        fArr[1] = (float) (-Math.sin(d));
        fArr[2] = 0.0f;
        fArr[3] = (float) Math.sin(d);
        fArr[4] = (float) Math.cos(d);
        fArr[5] = 0.0f;
        fArr[6] = 0.0f;
        fArr[7] = 0.0f;
        fArr[8] = 1.0f;
    }

    private static int zzd(int i, String str) {
        int glCreateShader = GLES20.glCreateShader(i);
        zzdb("createShader");
        if (glCreateShader == 0) {
            return glCreateShader;
        }
        GLES20.glShaderSource(glCreateShader, str);
        zzdb("shaderSource");
        GLES20.glCompileShader(glCreateShader);
        zzdb("compileShader");
        int[] iArr = new int[1];
        GLES20.glGetShaderiv(glCreateShader, 35713, iArr, 0);
        zzdb("getShaderiv");
        if (iArr[0] != 0) {
            return glCreateShader;
        }
        StringBuilder sb = new StringBuilder(37);
        sb.append("Could not compile shader ");
        sb.append(i);
        sb.append(":");
        Log.e("SphericalVideoRenderer", sb.toString());
        Log.e("SphericalVideoRenderer", GLES20.glGetShaderInfoLog(glCreateShader));
        GLES20.glDeleteShader(glCreateShader);
        zzdb("deleteShader");
        return 0;
    }

    private static void zzdb(String str) {
        int glGetError = GLES20.glGetError();
        if (glGetError != 0) {
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 21);
            sb.append(str);
            sb.append(": glError ");
            sb.append(glGetError);
            Log.e("SphericalVideoRenderer", sb.toString());
        }
    }

    private final void zzte() {
        while (this.zzdnc > 0) {
            this.zzdmy.updateTexImage();
            this.zzdnc--;
        }
        if (this.zzdmo.zza(this.zzdmk)) {
            if (Float.isNaN(this.zzdmv)) {
                float[] fArr = this.zzdmk;
                float[] fArr2 = {0.0f, 1.0f, 0.0f};
                float[] fArr3 = {(fArr[0] * fArr2[0]) + (fArr[1] * fArr2[1]) + (fArr[2] * fArr2[2]), (fArr[3] * fArr2[0]) + (fArr[4] * fArr2[1]) + (fArr[5] * fArr2[2]), (fArr[6] * fArr2[0]) + (fArr[7] * fArr2[1]) + (fArr[8] * fArr2[2])};
                this.zzdmv = -(((float) Math.atan2((double) fArr3[1], (double) fArr3[0])) - 1.5707964f);
            }
            zzb(this.zzdmt, this.zzdmv + this.zzdmw);
        } else {
            zza(this.zzdmk, -1.5707964f);
            zzb(this.zzdmt, this.zzdmw);
        }
        zza(this.zzdmp, 1.5707964f);
        zza(this.zzdmq, this.zzdmt, this.zzdmp);
        zza(this.zzdmr, this.zzdmk, this.zzdmq);
        zza(this.zzdms, this.zzdmx);
        zza(this.zzdmu, this.zzdms, this.zzdmr);
        GLES20.glUniformMatrix3fv(this.zzdnb, 1, false, this.zzdmu, 0);
        GLES20.glDrawArrays(5, 0, 4);
        zzdb("drawArrays");
        GLES20.glFinish();
        this.zzdng.eglSwapBuffers(this.zzdnh, this.zzdnj);
    }

    private final boolean zztf() {
        EGLSurface eGLSurface = this.zzdnj;
        boolean z = false;
        if (!(eGLSurface == null || eGLSurface == EGL10.EGL_NO_SURFACE)) {
            z = this.zzdng.eglDestroySurface(this.zzdnh, this.zzdnj) | this.zzdng.eglMakeCurrent(this.zzdnh, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_SURFACE, EGL10.EGL_NO_CONTEXT) | false;
            this.zzdnj = null;
        }
        EGLContext eGLContext = this.zzdni;
        if (eGLContext != null) {
            z |= this.zzdng.eglDestroyContext(this.zzdnh, eGLContext);
            this.zzdni = null;
        }
        EGLDisplay eGLDisplay = this.zzdnh;
        if (eGLDisplay == null) {
            return z;
        }
        boolean eglTerminate = z | this.zzdng.eglTerminate(eGLDisplay);
        this.zzdnh = null;
        return eglTerminate;
    }

    public final void onFrameAvailable(SurfaceTexture surfaceTexture) {
        this.zzdnc++;
        synchronized (this.zzdnf) {
            this.zzdnf.notifyAll();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:101:0x02be  */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x02c3  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00af  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00ba  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00c5  */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x01ce  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x01d0  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x01d3 A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r12 = this;
            android.graphics.SurfaceTexture r0 = r12.zzdmz
            if (r0 != 0) goto L_0x000f
            java.lang.String r0 = "SphericalVideoProcessor started with no output texture."
            com.google.android.gms.internal.zzahw.e(r0)
            java.util.concurrent.CountDownLatch r0 = r12.zzdne
            r0.countDown()
            return
        L_0x000f:
            javax.microedition.khronos.egl.EGL r0 = javax.microedition.khronos.egl.EGLContext.getEGL()
            javax.microedition.khronos.egl.EGL10 r0 = (javax.microedition.khronos.egl.EGL10) r0
            r12.zzdng = r0
            java.lang.Object r1 = javax.microedition.khronos.egl.EGL10.EGL_DEFAULT_DISPLAY
            javax.microedition.khronos.egl.EGLDisplay r0 = r0.eglGetDisplay(r1)
            r12.zzdnh = r0
            javax.microedition.khronos.egl.EGLDisplay r1 = javax.microedition.khronos.egl.EGL10.EGL_NO_DISPLAY
            r2 = 0
            r3 = 1
            r4 = 0
            if (r0 != r1) goto L_0x0029
        L_0x0026:
            r0 = 0
            goto L_0x0096
        L_0x0029:
            r0 = 2
            int[] r0 = new int[r0]
            javax.microedition.khronos.egl.EGL10 r1 = r12.zzdng
            javax.microedition.khronos.egl.EGLDisplay r5 = r12.zzdnh
            boolean r0 = r1.eglInitialize(r5, r0)
            if (r0 != 0) goto L_0x0037
            goto L_0x0026
        L_0x0037:
            int[] r0 = new int[r3]
            javax.microedition.khronos.egl.EGLConfig[] r1 = new javax.microedition.khronos.egl.EGLConfig[r3]
            r5 = 11
            int[] r7 = new int[r5]
            r7 = {12352, 4, 12324, 8, 12323, 8, 12322, 8, 12325, 16, 12344} // fill-array
            javax.microedition.khronos.egl.EGL10 r5 = r12.zzdng
            javax.microedition.khronos.egl.EGLDisplay r6 = r12.zzdnh
            r9 = 1
            r8 = r1
            r10 = r0
            boolean r5 = r5.eglChooseConfig(r6, r7, r8, r9, r10)
            if (r5 == 0) goto L_0x0056
            r0 = r0[r4]
            if (r0 <= 0) goto L_0x0056
            r0 = r1[r4]
            goto L_0x0057
        L_0x0056:
            r0 = r2
        L_0x0057:
            if (r0 != 0) goto L_0x005a
            goto L_0x0026
        L_0x005a:
            r1 = 3
            int[] r1 = new int[r1]
            r1 = {12440, 2, 12344} // fill-array
            javax.microedition.khronos.egl.EGL10 r5 = r12.zzdng
            javax.microedition.khronos.egl.EGLDisplay r6 = r12.zzdnh
            javax.microedition.khronos.egl.EGLContext r7 = javax.microedition.khronos.egl.EGL10.EGL_NO_CONTEXT
            javax.microedition.khronos.egl.EGLContext r1 = r5.eglCreateContext(r6, r0, r7, r1)
            r12.zzdni = r1
            if (r1 == 0) goto L_0x0026
            javax.microedition.khronos.egl.EGLContext r5 = javax.microedition.khronos.egl.EGL10.EGL_NO_CONTEXT
            if (r1 != r5) goto L_0x0073
            goto L_0x0026
        L_0x0073:
            javax.microedition.khronos.egl.EGL10 r1 = r12.zzdng
            javax.microedition.khronos.egl.EGLDisplay r5 = r12.zzdnh
            android.graphics.SurfaceTexture r6 = r12.zzdmz
            javax.microedition.khronos.egl.EGLSurface r0 = r1.eglCreateWindowSurface(r5, r0, r6, r2)
            r12.zzdnj = r0
            if (r0 == 0) goto L_0x0026
            javax.microedition.khronos.egl.EGLSurface r1 = javax.microedition.khronos.egl.EGL10.EGL_NO_SURFACE
            if (r0 != r1) goto L_0x0086
            goto L_0x0026
        L_0x0086:
            javax.microedition.khronos.egl.EGL10 r0 = r12.zzdng
            javax.microedition.khronos.egl.EGLDisplay r1 = r12.zzdnh
            javax.microedition.khronos.egl.EGLSurface r5 = r12.zzdnj
            javax.microedition.khronos.egl.EGLContext r6 = r12.zzdni
            boolean r0 = r0.eglMakeCurrent(r1, r5, r5, r6)
            if (r0 != 0) goto L_0x0095
            goto L_0x0026
        L_0x0095:
            r0 = 1
        L_0x0096:
            r1 = 35633(0x8b31, float:4.9932E-41)
            com.google.android.gms.internal.zzny<java.lang.String> r5 = com.google.android.gms.internal.zzoi.zzbqi
            com.google.android.gms.internal.zzog r6 = com.google.android.gms.internal.zzlc.zzio()
            java.lang.Object r6 = r6.zzd(r5)
            java.lang.String r6 = (java.lang.String) r6
            java.lang.Object r7 = r5.zzje()
            boolean r6 = r6.equals(r7)
            if (r6 != 0) goto L_0x00ba
            com.google.android.gms.internal.zzog r6 = com.google.android.gms.internal.zzlc.zzio()
            java.lang.Object r5 = r6.zzd(r5)
            java.lang.String r5 = (java.lang.String) r5
            goto L_0x00bc
        L_0x00ba:
            java.lang.String r5 = "attribute highp vec3 aPosition;varying vec3 pos;void main() {  gl_Position = vec4(aPosition, 1.0);  pos = aPosition;}"
        L_0x00bc:
            int r1 = zzd(r1, r5)
            if (r1 != 0) goto L_0x00c5
        L_0x00c2:
            r6 = 0
            goto L_0x0147
        L_0x00c5:
            r5 = 35632(0x8b30, float:4.9931E-41)
            com.google.android.gms.internal.zzny<java.lang.String> r6 = com.google.android.gms.internal.zzoi.zzbqj
            com.google.android.gms.internal.zzog r7 = com.google.android.gms.internal.zzlc.zzio()
            java.lang.Object r7 = r7.zzd(r6)
            java.lang.String r7 = (java.lang.String) r7
            java.lang.Object r8 = r6.zzje()
            boolean r7 = r7.equals(r8)
            if (r7 != 0) goto L_0x00e9
            com.google.android.gms.internal.zzog r7 = com.google.android.gms.internal.zzlc.zzio()
            java.lang.Object r6 = r7.zzd(r6)
            java.lang.String r6 = (java.lang.String) r6
            goto L_0x00eb
        L_0x00e9:
            java.lang.String r6 = "#extension GL_OES_EGL_image_external : require\n#define INV_PI 0.3183\nprecision highp float;varying vec3 pos;uniform samplerExternalOES uSplr;uniform mat3 uVMat;uniform float uFOVx;uniform float uFOVy;void main() {  vec3 ray = vec3(pos.x * tan(uFOVx), pos.y * tan(uFOVy), -1);  ray = (uVMat * ray).xyz;  ray = normalize(ray);  vec2 texCrd = vec2(    0.5 + atan(ray.x, - ray.z) * INV_PI * 0.5, acos(ray.y) * INV_PI);  gl_FragColor = vec4(texture2D(uSplr, texCrd).xyz, 1.0);}"
        L_0x00eb:
            int r5 = zzd(r5, r6)
            if (r5 != 0) goto L_0x00f2
            goto L_0x00c2
        L_0x00f2:
            int r6 = android.opengl.GLES20.glCreateProgram()
            java.lang.String r7 = "createProgram"
            zzdb(r7)
            if (r6 == 0) goto L_0x0147
            android.opengl.GLES20.glAttachShader(r6, r1)
            java.lang.String r1 = "attachShader"
            zzdb(r1)
            android.opengl.GLES20.glAttachShader(r6, r5)
            java.lang.String r1 = "attachShader"
            zzdb(r1)
            android.opengl.GLES20.glLinkProgram(r6)
            java.lang.String r1 = "linkProgram"
            zzdb(r1)
            int[] r1 = new int[r3]
            r5 = 35714(0x8b82, float:5.0046E-41)
            android.opengl.GLES20.glGetProgramiv(r6, r5, r1, r4)
            java.lang.String r5 = "getProgramiv"
            zzdb(r5)
            r1 = r1[r4]
            if (r1 == r3) goto L_0x013f
            java.lang.String r1 = "SphericalVideoRenderer"
            java.lang.String r5 = "Could not link program: "
            android.util.Log.e(r1, r5)
            java.lang.String r1 = "SphericalVideoRenderer"
            java.lang.String r5 = android.opengl.GLES20.glGetProgramInfoLog(r6)
            android.util.Log.e(r1, r5)
            android.opengl.GLES20.glDeleteProgram(r6)
            java.lang.String r1 = "deleteProgram"
            zzdb(r1)
            goto L_0x00c2
        L_0x013f:
            android.opengl.GLES20.glValidateProgram(r6)
            java.lang.String r1 = "validateProgram"
            zzdb(r1)
        L_0x0147:
            r12.zzdna = r6
            android.opengl.GLES20.glUseProgram(r6)
            java.lang.String r1 = "useProgram"
            zzdb(r1)
            int r1 = r12.zzdna
            java.lang.String r5 = "aPosition"
            int r1 = android.opengl.GLES20.glGetAttribLocation(r1, r5)
            r7 = 3
            r8 = 5126(0x1406, float:7.183E-42)
            r9 = 0
            r10 = 12
            java.nio.FloatBuffer r11 = r12.zzdnd
            r6 = r1
            android.opengl.GLES20.glVertexAttribPointer(r6, r7, r8, r9, r10, r11)
            java.lang.String r5 = "vertexAttribPointer"
            zzdb(r5)
            android.opengl.GLES20.glEnableVertexAttribArray(r1)
            java.lang.String r1 = "enableVertexAttribArray"
            zzdb(r1)
            int[] r1 = new int[r3]
            android.opengl.GLES20.glGenTextures(r3, r1, r4)
            java.lang.String r5 = "genTextures"
            zzdb(r5)
            r1 = r1[r4]
            r5 = 36197(0x8d65, float:5.0723E-41)
            android.opengl.GLES20.glBindTexture(r5, r1)
            java.lang.String r6 = "bindTextures"
            zzdb(r6)
            r6 = 10240(0x2800, float:1.4349E-41)
            r7 = 9729(0x2601, float:1.3633E-41)
            android.opengl.GLES20.glTexParameteri(r5, r6, r7)
            java.lang.String r6 = "texParameteri"
            zzdb(r6)
            r6 = 10241(0x2801, float:1.435E-41)
            android.opengl.GLES20.glTexParameteri(r5, r6, r7)
            java.lang.String r6 = "texParameteri"
            zzdb(r6)
            r6 = 10242(0x2802, float:1.4352E-41)
            r7 = 33071(0x812f, float:4.6342E-41)
            android.opengl.GLES20.glTexParameteri(r5, r6, r7)
            java.lang.String r6 = "texParameteri"
            zzdb(r6)
            r6 = 10243(0x2803, float:1.4354E-41)
            android.opengl.GLES20.glTexParameteri(r5, r6, r7)
            java.lang.String r5 = "texParameteri"
            zzdb(r5)
            int r5 = r12.zzdna
            java.lang.String r6 = "uVMat"
            int r5 = android.opengl.GLES20.glGetUniformLocation(r5, r6)
            r12.zzdnb = r5
            r6 = 9
            float[] r6 = new float[r6]
            r6 = {1065353216, 0, 0, 0, 1065353216, 0, 0, 0, 1065353216} // fill-array
            android.opengl.GLES20.glUniformMatrix3fv(r5, r3, r4, r6, r4)
            int r5 = r12.zzdna
            if (r5 == 0) goto L_0x01d0
            r5 = 1
            goto L_0x01d1
        L_0x01d0:
            r5 = 0
        L_0x01d1:
            if (r0 == 0) goto L_0x02a8
            if (r5 != 0) goto L_0x01d7
            goto L_0x02a8
        L_0x01d7:
            android.graphics.SurfaceTexture r0 = new android.graphics.SurfaceTexture
            r0.<init>(r1)
            r12.zzdmy = r0
            r0.setOnFrameAvailableListener(r12)
            java.util.concurrent.CountDownLatch r0 = r12.zzdne
            r0.countDown()
            com.google.android.gms.internal.zzani r0 = r12.zzdmo
            r0.start()
            r12.zzdnk = r3     // Catch:{ IllegalStateException -> 0x0282, all -> 0x0263 }
        L_0x01ed:
            boolean r0 = r12.zzdnl     // Catch:{ IllegalStateException -> 0x0282, all -> 0x0263 }
            if (r0 != 0) goto L_0x0253
            r12.zzte()     // Catch:{ IllegalStateException -> 0x0282, all -> 0x0263 }
            boolean r0 = r12.zzdnk     // Catch:{ IllegalStateException -> 0x0282, all -> 0x0263 }
            if (r0 == 0) goto L_0x023a
            int r0 = r12.zzalt     // Catch:{ IllegalStateException -> 0x0282, all -> 0x0263 }
            int r1 = r12.zzalu     // Catch:{ IllegalStateException -> 0x0282, all -> 0x0263 }
            android.opengl.GLES20.glViewport(r4, r4, r0, r1)     // Catch:{ IllegalStateException -> 0x0282, all -> 0x0263 }
            java.lang.String r0 = "viewport"
            zzdb(r0)     // Catch:{ IllegalStateException -> 0x0282, all -> 0x0263 }
            int r0 = r12.zzdna     // Catch:{ IllegalStateException -> 0x0282, all -> 0x0263 }
            java.lang.String r1 = "uFOVx"
            int r0 = android.opengl.GLES20.glGetUniformLocation(r0, r1)     // Catch:{ IllegalStateException -> 0x0282, all -> 0x0263 }
            int r1 = r12.zzdna     // Catch:{ IllegalStateException -> 0x0282, all -> 0x0263 }
            java.lang.String r3 = "uFOVy"
            int r1 = android.opengl.GLES20.glGetUniformLocation(r1, r3)     // Catch:{ IllegalStateException -> 0x0282, all -> 0x0263 }
            int r3 = r12.zzalt     // Catch:{ IllegalStateException -> 0x0282, all -> 0x0263 }
            int r5 = r12.zzalu     // Catch:{ IllegalStateException -> 0x0282, all -> 0x0263 }
            r6 = 1063216883(0x3f5f66f3, float:0.87266463)
            if (r3 <= r5) goto L_0x022d
            android.opengl.GLES20.glUniform1f(r0, r6)     // Catch:{ IllegalStateException -> 0x0282, all -> 0x0263 }
            int r0 = r12.zzalu     // Catch:{ IllegalStateException -> 0x0282, all -> 0x0263 }
            float r0 = (float) r0     // Catch:{ IllegalStateException -> 0x0282, all -> 0x0263 }
            float r0 = r0 * r6
            int r3 = r12.zzalt     // Catch:{ IllegalStateException -> 0x0282, all -> 0x0263 }
            float r3 = (float) r3     // Catch:{ IllegalStateException -> 0x0282, all -> 0x0263 }
            float r0 = r0 / r3
            android.opengl.GLES20.glUniform1f(r1, r0)     // Catch:{ IllegalStateException -> 0x0282, all -> 0x0263 }
            goto L_0x0238
        L_0x022d:
            float r3 = (float) r3     // Catch:{ IllegalStateException -> 0x0282, all -> 0x0263 }
            float r3 = r3 * r6
            float r5 = (float) r5     // Catch:{ IllegalStateException -> 0x0282, all -> 0x0263 }
            float r3 = r3 / r5
            android.opengl.GLES20.glUniform1f(r0, r3)     // Catch:{ IllegalStateException -> 0x0282, all -> 0x0263 }
            android.opengl.GLES20.glUniform1f(r1, r6)     // Catch:{ IllegalStateException -> 0x0282, all -> 0x0263 }
        L_0x0238:
            r12.zzdnk = r4     // Catch:{ IllegalStateException -> 0x0282, all -> 0x0263 }
        L_0x023a:
            java.lang.Object r0 = r12.zzdnf     // Catch:{ InterruptedException -> 0x01ed }
            monitor-enter(r0)     // Catch:{ InterruptedException -> 0x01ed }
            boolean r1 = r12.zzdnl     // Catch:{ all -> 0x0250 }
            if (r1 != 0) goto L_0x024e
            boolean r1 = r12.zzdnk     // Catch:{ all -> 0x0250 }
            if (r1 != 0) goto L_0x024e
            int r1 = r12.zzdnc     // Catch:{ all -> 0x0250 }
            if (r1 != 0) goto L_0x024e
            java.lang.Object r1 = r12.zzdnf     // Catch:{ all -> 0x0250 }
            r1.wait()     // Catch:{ all -> 0x0250 }
        L_0x024e:
            monitor-exit(r0)     // Catch:{ all -> 0x0250 }
            goto L_0x01ed
        L_0x0250:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0250 }
            throw r1     // Catch:{ InterruptedException -> 0x01ed }
        L_0x0253:
            com.google.android.gms.internal.zzani r0 = r12.zzdmo
            r0.stop()
            android.graphics.SurfaceTexture r0 = r12.zzdmy
            r0.setOnFrameAvailableListener(r2)
            r12.zzdmy = r2
            r12.zztf()
            return
        L_0x0263:
            r0 = move-exception
            java.lang.String r1 = "SphericalVideoProcessor died."
            com.google.android.gms.internal.zzahw.zzb(r1, r0)     // Catch:{ all -> 0x0297 }
            com.google.android.gms.internal.zzahi r1 = com.google.android.gms.ads.internal.zzbt.zzep()     // Catch:{ all -> 0x0297 }
            java.lang.String r3 = "SphericalVideoProcessor.run.2"
            r1.zza(r0, r3)     // Catch:{ all -> 0x0297 }
            com.google.android.gms.internal.zzani r0 = r12.zzdmo
            r0.stop()
            android.graphics.SurfaceTexture r0 = r12.zzdmy
            r0.setOnFrameAvailableListener(r2)
            r12.zzdmy = r2
            r12.zztf()
            return
        L_0x0282:
            java.lang.String r0 = "SphericalVideoProcessor halted unexpectedly."
            com.google.android.gms.internal.zzahw.zzcz(r0)     // Catch:{ all -> 0x0297 }
            com.google.android.gms.internal.zzani r0 = r12.zzdmo
            r0.stop()
            android.graphics.SurfaceTexture r0 = r12.zzdmy
            r0.setOnFrameAvailableListener(r2)
            r12.zzdmy = r2
            r12.zztf()
            return
        L_0x0297:
            r0 = move-exception
            com.google.android.gms.internal.zzani r1 = r12.zzdmo
            r1.stop()
            android.graphics.SurfaceTexture r1 = r12.zzdmy
            r1.setOnFrameAvailableListener(r2)
            r12.zzdmy = r2
            r12.zztf()
            throw r0
        L_0x02a8:
            javax.microedition.khronos.egl.EGL10 r0 = r12.zzdng
            int r0 = r0.eglGetError()
            java.lang.String r0 = android.opengl.GLUtils.getEGLErrorString(r0)
            java.lang.String r1 = "EGL initialization failed: "
            java.lang.String r0 = java.lang.String.valueOf(r0)
            int r2 = r0.length()
            if (r2 == 0) goto L_0x02c3
            java.lang.String r0 = r1.concat(r0)
            goto L_0x02c8
        L_0x02c3:
            java.lang.String r0 = new java.lang.String
            r0.<init>(r1)
        L_0x02c8:
            com.google.android.gms.internal.zzahw.e(r0)
            com.google.android.gms.internal.zzahi r1 = com.google.android.gms.ads.internal.zzbt.zzep()
            java.lang.Throwable r2 = new java.lang.Throwable
            r2.<init>(r0)
            java.lang.String r0 = "SphericalVideoProcessor.run.1"
            r1.zza(r2, r0)
            r12.zztf()
            java.util.concurrent.CountDownLatch r0 = r12.zzdne
            r0.countDown()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzanl.run():void");
    }

    public final void zza(SurfaceTexture surfaceTexture, int i, int i2) {
        this.zzalt = i;
        this.zzalu = i2;
        this.zzdmz = surfaceTexture;
    }

    public final void zzb(float f, float f2) {
        float f3;
        float f4;
        float f5;
        int i = this.zzalt;
        int i2 = this.zzalu;
        float f6 = f * 1.7453293f;
        if (i > i2) {
            f4 = f6 / ((float) i);
            f3 = f2 * 1.7453293f;
            f5 = (float) i;
        } else {
            f4 = f6 / ((float) i2);
            f3 = f2 * 1.7453293f;
            f5 = (float) i2;
        }
        this.zzdmw -= f4;
        float f7 = this.zzdmx - (f3 / f5);
        this.zzdmx = f7;
        if (f7 < -1.5707964f) {
            this.zzdmx = -1.5707964f;
        }
        if (this.zzdmx > 1.5707964f) {
            this.zzdmx = 1.5707964f;
        }
    }

    public final void zzh(int i, int i2) {
        synchronized (this.zzdnf) {
            this.zzalt = i;
            this.zzalu = i2;
            this.zzdnk = true;
            this.zzdnf.notifyAll();
        }
    }

    public final void zznm() {
        synchronized (this.zzdnf) {
            this.zzdnf.notifyAll();
        }
    }

    public final void zztc() {
        synchronized (this.zzdnf) {
            this.zzdnl = true;
            this.zzdmz = null;
            this.zzdnf.notifyAll();
        }
    }

    public final SurfaceTexture zztd() {
        if (this.zzdmz == null) {
            return null;
        }
        try {
            this.zzdne.await();
        } catch (InterruptedException unused) {
        }
        return this.zzdmy;
    }
}
