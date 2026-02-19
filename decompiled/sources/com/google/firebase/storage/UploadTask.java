package com.google.firebase.storage;

import android.net.Uri;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzfbb;
import com.google.android.gms.internal.zzfbc;
import com.google.android.gms.internal.zzfbh;
import com.google.android.gms.internal.zzfbn;
import com.google.firebase.storage.StorageTask;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.atomic.AtomicLong;

public class UploadTask extends StorageTask<UploadTask.TaskSnapshot> {
    private volatile int mResultCode;
    private final Uri mUri;
    private volatile Exception zzleq;
    /* access modifiers changed from: private */
    public final StorageReference zzotm;
    private zzfbc zzoto;
    private volatile StorageMetadata zzouu;
    private final long zzowv;
    private final zzfbb zzoww;
    private final AtomicLong zzowx;
    private int zzowy;
    private boolean zzowz;
    private volatile Uri zzoxa;
    private volatile Exception zzoxb;
    private volatile String zzoxc;

    public class TaskSnapshot extends StorageTask<TaskSnapshot>.SnapshotBase {
        private final StorageMetadata zzouu;
        private final Uri zzoxa;
        private final long zzoxf;

        TaskSnapshot(Exception exc, long j, Uri uri, StorageMetadata storageMetadata) {
            super(exc);
            this.zzoxf = j;
            this.zzoxa = uri;
            this.zzouu = storageMetadata;
        }

        public long getBytesTransferred() {
            return this.zzoxf;
        }

        public Uri getDownloadUrl() {
            StorageMetadata metadata = getMetadata();
            if (metadata != null) {
                return metadata.getDownloadUrl();
            }
            return null;
        }

        public StorageMetadata getMetadata() {
            return this.zzouu;
        }

        public long getTotalByteCount() {
            return UploadTask.this.getTotalByteCount();
        }

        public Uri getUploadSessionUri() {
            return this.zzoxa;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0079 A[Catch:{ FileNotFoundException -> 0x00ab }] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x007e A[Catch:{ FileNotFoundException -> 0x00ab }] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0098  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00c1  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00c6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    UploadTask(com.google.firebase.storage.StorageReference r10, com.google.firebase.storage.StorageMetadata r11, android.net.Uri r12, android.net.Uri r13) {
        /*
            r9 = this;
            java.lang.String r0 = "UploadTask"
            r9.<init>()
            java.util.concurrent.atomic.AtomicLong r1 = new java.util.concurrent.atomic.AtomicLong
            r2 = 0
            r1.<init>(r2)
            r9.zzowx = r1
            r1 = 262144(0x40000, float:3.67342E-40)
            r9.zzowy = r1
            r2 = 0
            r9.zzoxa = r2
            r9.zzleq = r2
            r9.zzoxb = r2
            r3 = 0
            r9.mResultCode = r3
            com.google.android.gms.common.internal.zzbq.checkNotNull(r10)
            com.google.android.gms.common.internal.zzbq.checkNotNull(r12)
            r9.zzotm = r10
            r9.zzouu = r11
            r9.mUri = r12
            com.google.android.gms.internal.zzfbc r11 = new com.google.android.gms.internal.zzfbc
            com.google.firebase.storage.FirebaseStorage r3 = r10.getStorage()
            com.google.firebase.FirebaseApp r3 = r3.getApp()
            com.google.firebase.storage.FirebaseStorage r4 = r10.getStorage()
            long r4 = r4.getMaxUploadRetryTimeMillis()
            r11.<init>(r3, r4)
            r9.zzoto = r11
            r3 = -1
            com.google.firebase.storage.FirebaseStorage r10 = r10.getStorage()     // Catch:{ FileNotFoundException -> 0x00ae }
            com.google.firebase.FirebaseApp r10 = r10.getApp()     // Catch:{ FileNotFoundException -> 0x00ae }
            android.content.Context r10 = r10.getApplicationContext()     // Catch:{ FileNotFoundException -> 0x00ae }
            android.content.ContentResolver r10 = r10.getContentResolver()     // Catch:{ FileNotFoundException -> 0x00ae }
            java.lang.String r11 = "r"
            android.os.ParcelFileDescriptor r11 = r10.openFileDescriptor(r12, r11)     // Catch:{ NullPointerException -> 0x0088, IOException -> 0x0065 }
            if (r11 == 0) goto L_0x008f
            long r5 = r11.getStatSize()     // Catch:{ NullPointerException -> 0x0088, IOException -> 0x0065 }
            r11.close()     // Catch:{ NullPointerException -> 0x0063, IOException -> 0x0061 }
            goto L_0x0090
        L_0x0061:
            r11 = move-exception
            goto L_0x0067
        L_0x0063:
            r11 = move-exception
            goto L_0x008a
        L_0x0065:
            r11 = move-exception
            r5 = r3
        L_0x0067:
            java.lang.String r12 = "could not retrieve file size for upload "
            android.net.Uri r7 = r9.mUri     // Catch:{ FileNotFoundException -> 0x00ab }
            java.lang.String r7 = r7.toString()     // Catch:{ FileNotFoundException -> 0x00ab }
            java.lang.String r7 = java.lang.String.valueOf(r7)     // Catch:{ FileNotFoundException -> 0x00ab }
            int r8 = r7.length()     // Catch:{ FileNotFoundException -> 0x00ab }
            if (r8 == 0) goto L_0x007e
            java.lang.String r12 = r12.concat(r7)     // Catch:{ FileNotFoundException -> 0x00ab }
            goto L_0x0084
        L_0x007e:
            java.lang.String r7 = new java.lang.String     // Catch:{ FileNotFoundException -> 0x00ab }
            r7.<init>(r12)     // Catch:{ FileNotFoundException -> 0x00ab }
            r12 = r7
        L_0x0084:
            android.util.Log.w(r0, r12, r11)     // Catch:{ FileNotFoundException -> 0x00ab }
            goto L_0x0090
        L_0x0088:
            r11 = move-exception
            r5 = r3
        L_0x008a:
            java.lang.String r12 = "NullPointerException during file size calculation."
            android.util.Log.w(r0, r12, r11)     // Catch:{ FileNotFoundException -> 0x00ab }
        L_0x008f:
            r5 = r3
        L_0x0090:
            android.net.Uri r11 = r9.mUri     // Catch:{ FileNotFoundException -> 0x00ab }
            java.io.InputStream r2 = r10.openInputStream(r11)     // Catch:{ FileNotFoundException -> 0x00ab }
            if (r2 == 0) goto L_0x00d2
            int r10 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r10 != 0) goto L_0x00a3
            int r10 = r2.available()     // Catch:{ IOException -> 0x00a3 }
            if (r10 < 0) goto L_0x00a3
            long r5 = (long) r10
        L_0x00a3:
            r3 = r5
            java.io.BufferedInputStream r10 = new java.io.BufferedInputStream     // Catch:{ FileNotFoundException -> 0x00ae }
            r10.<init>(r2)     // Catch:{ FileNotFoundException -> 0x00ae }
            r2 = r10
            goto L_0x00d1
        L_0x00ab:
            r10 = move-exception
            r3 = r5
            goto L_0x00af
        L_0x00ae:
            r10 = move-exception
        L_0x00af:
            java.lang.String r11 = "could not locate file for uploading:"
            android.net.Uri r12 = r9.mUri
            java.lang.String r12 = r12.toString()
            java.lang.String r12 = java.lang.String.valueOf(r12)
            int r5 = r12.length()
            if (r5 == 0) goto L_0x00c6
            java.lang.String r11 = r11.concat(r12)
            goto L_0x00cc
        L_0x00c6:
            java.lang.String r12 = new java.lang.String
            r12.<init>(r11)
            r11 = r12
        L_0x00cc:
            android.util.Log.e(r0, r11)
            r9.zzleq = r10
        L_0x00d1:
            r5 = r3
        L_0x00d2:
            r9.zzowv = r5
            com.google.android.gms.internal.zzfbb r10 = new com.google.android.gms.internal.zzfbb
            r10.<init>(r2, r1)
            r9.zzoww = r10
            r10 = 1
            r9.zzowz = r10
            r9.zzoxa = r13
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.storage.UploadTask.<init>(com.google.firebase.storage.StorageReference, com.google.firebase.storage.StorageMetadata, android.net.Uri, android.net.Uri):void");
    }

    UploadTask(StorageReference storageReference, StorageMetadata storageMetadata, InputStream inputStream) {
        this.zzowx = new AtomicLong(0);
        this.zzowy = 262144;
        this.zzoxa = null;
        this.zzleq = null;
        this.zzoxb = null;
        this.mResultCode = 0;
        zzbq.checkNotNull(storageReference);
        zzbq.checkNotNull(inputStream);
        this.zzowv = -1;
        this.zzotm = storageReference;
        this.zzouu = storageMetadata;
        this.zzoww = new zzfbb(inputStream, 262144);
        this.zzowz = false;
        this.mUri = null;
        this.zzoto = new zzfbc(storageReference.getStorage().getApp(), storageReference.getStorage().getMaxUploadRetryTimeMillis());
    }

    UploadTask(StorageReference storageReference, StorageMetadata storageMetadata, byte[] bArr) {
        this.zzowx = new AtomicLong(0);
        this.zzowy = 262144;
        this.zzoxa = null;
        this.zzleq = null;
        this.zzoxb = null;
        this.mResultCode = 0;
        zzbq.checkNotNull(storageReference);
        zzbq.checkNotNull(bArr);
        this.zzowv = (long) bArr.length;
        this.zzotm = storageReference;
        this.zzouu = storageMetadata;
        this.mUri = null;
        this.zzoww = new zzfbb(new ByteArrayInputStream(bArr), 262144);
        this.zzowz = true;
        this.zzoto = new zzfbc(storageReference.getStorage().getApp(), storageReference.getStorage().getMaxUploadRetryTimeMillis());
    }

    private final boolean zzb(zzfbn zzfbn) {
        zzfbn.zze(zzfbh.zzh(this.zzotm.getStorage().getApp()), this.zzotm.getStorage().getApp().getApplicationContext());
        return zzd(zzfbn);
    }

    private final boolean zzc(zzfbn zzfbn) {
        this.zzoto.zza(zzfbn, true);
        return zzd(zzfbn);
    }

    private final boolean zzcoi() {
        if (zzcnz() == 128) {
            return false;
        }
        if (Thread.interrupted()) {
            this.zzleq = new InterruptedException();
            zzk(64, false);
            return false;
        } else if (zzcnz() == 32) {
            zzk(256, false);
            return false;
        } else if (zzcnz() == 8) {
            zzk(16, false);
            return false;
        } else if (!zzcoj()) {
            return false;
        } else {
            if (this.zzoxa == null) {
                if (this.zzleq == null) {
                    this.zzleq = new IllegalStateException("Unable to obtain an upload URL.");
                }
                zzk(64, false);
                return false;
            } else if (this.zzleq != null) {
                zzk(64, false);
                return false;
            } else {
                if (!(this.zzoxb != null || this.mResultCode < 200 || this.mResultCode >= 300) || zzde(true)) {
                    return true;
                }
                if (zzcoj()) {
                    zzk(64, false);
                }
                return false;
            }
        }
    }

    private final boolean zzcoj() {
        if (!"final".equals(this.zzoxc)) {
            return true;
        }
        if (this.zzleq == null) {
            this.zzleq = new IOException("The server has terminated the upload session", this.zzoxb);
        }
        zzk(64, false);
        return false;
    }

    private final boolean zzd(zzfbn zzfbn) {
        int resultCode = zzfbn.getResultCode();
        if (zzfbc.zzjd(resultCode)) {
            resultCode = -2;
        }
        this.mResultCode = resultCode;
        this.zzoxb = zzfbn.getException();
        this.zzoxc = zzfbn.zzst("X-Goog-Upload-Status");
        int i = this.mResultCode;
        return (i == 308 || (i >= 200 && i < 300)) && this.zzoxb == null;
    }

    private final boolean zzde(boolean z) {
        Exception exception;
        try {
            zzfbn zzb = this.zzotm.zzcnw().zzb(this.zzotm.zzcnx(), this.zzoxa.toString());
            if ("final".equals(this.zzoxc)) {
                return false;
            }
            if (z) {
                if (!zzc(zzb)) {
                    return false;
                }
            } else if (!zzb(zzb)) {
                return false;
            }
            if ("final".equals(zzb.zzst("X-Goog-Upload-Status"))) {
                exception = new IOException("The server has terminated the upload session");
            } else {
                String zzst = zzb.zzst("X-Goog-Upload-Size-Received");
                long parseLong = !TextUtils.isEmpty(zzst) ? Long.parseLong(zzst) : 0;
                long j = this.zzowx.get();
                int i = (j > parseLong ? 1 : (j == parseLong ? 0 : -1));
                if (i > 0) {
                    exception = new IOException("Unexpected error. The server lost a chunk update.");
                } else if (i >= 0) {
                    return true;
                } else {
                    try {
                        long j2 = parseLong - j;
                        if (((long) this.zzoww.zzja((int) j2)) != j2) {
                            this.zzleq = new IOException("Unexpected end of stream encountered.");
                            return false;
                        } else if (this.zzowx.compareAndSet(j, parseLong)) {
                            return true;
                        } else {
                            Log.e("UploadTask", "Somehow, the uploaded bytes changed during an uploaded.  This should nothappen");
                            this.zzleq = new IllegalStateException("uploaded bytes changed unexpectedly.");
                            return false;
                        }
                    } catch (IOException e) {
                        exception = e;
                        Log.e("UploadTask", "Unable to recover position in Stream during resumable upload", exception);
                        this.zzleq = exception;
                        return false;
                    }
                }
            }
            this.zzleq = exception;
            return false;
        } catch (RemoteException e2) {
            exception = e2;
            Log.e("UploadTask", "Unable to recover status during resumable upload", exception);
            this.zzleq = exception;
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public final StorageReference getStorage() {
        return this.zzotm;
    }

    /* access modifiers changed from: package-private */
    public final long getTotalByteCount() {
        return this.zzowv;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onCanceled() {
        /*
            r3 = this;
            com.google.android.gms.internal.zzfbc r0 = r3.zzoto
            r0.cancel()
            android.net.Uri r0 = r3.zzoxa
            if (r0 == 0) goto L_0x0028
            com.google.firebase.storage.StorageReference r0 = r3.zzotm     // Catch:{ RemoteException -> 0x0020 }
            com.google.android.gms.internal.zzfbm r0 = r0.zzcnw()     // Catch:{ RemoteException -> 0x0020 }
            com.google.firebase.storage.StorageReference r1 = r3.zzotm     // Catch:{ RemoteException -> 0x0020 }
            android.net.Uri r1 = r1.zzcnx()     // Catch:{ RemoteException -> 0x0020 }
            android.net.Uri r2 = r3.zzoxa     // Catch:{ RemoteException -> 0x0020 }
            java.lang.String r2 = r2.toString()     // Catch:{ RemoteException -> 0x0020 }
            com.google.android.gms.internal.zzfbn r0 = r0.zza((android.net.Uri) r1, (java.lang.String) r2)     // Catch:{ RemoteException -> 0x0020 }
            goto L_0x0029
        L_0x0020:
            r0 = move-exception
            java.lang.String r1 = "UploadTask"
            java.lang.String r2 = "Unable to create chunk upload request"
            android.util.Log.e(r1, r2, r0)
        L_0x0028:
            r0 = 0
        L_0x0029:
            if (r0 == 0) goto L_0x0033
            com.google.firebase.storage.zzad r1 = new com.google.firebase.storage.zzad
            r1.<init>(r3, r0)
            com.google.firebase.storage.zzu.zzt(r1)
        L_0x0033:
            com.google.android.gms.common.api.Status r0 = com.google.android.gms.common.api.Status.zzftu
            com.google.firebase.storage.StorageException r0 = com.google.firebase.storage.StorageException.fromErrorStatus(r0)
            r3.zzleq = r0
            super.onCanceled()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.storage.UploadTask.onCanceled():void");
    }

    /* access modifiers changed from: protected */
    public void resetState() {
        this.zzleq = null;
        this.zzoxb = null;
        this.mResultCode = 0;
        this.zzoxc = null;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0169 A[Catch:{ IOException -> 0x0180 }] */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x016e A[Catch:{ IOException -> 0x0180 }] */
    /* JADX WARNING: Removed duplicated region for block: B:72:0x018e  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x00ab A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r13 = this;
            com.google.android.gms.internal.zzfbc r0 = r13.zzoto
            r0.reset()
            r0 = 4
            r1 = 0
            boolean r2 = r13.zzk(r0, r1)
            java.lang.String r3 = "UploadTask"
            if (r2 != 0) goto L_0x0015
            java.lang.String r0 = "The upload cannot continue as it is not in a valid state."
            android.util.Log.d(r3, r0)
            return
        L_0x0015:
            com.google.firebase.storage.StorageReference r2 = r13.zzotm
            com.google.firebase.storage.StorageReference r2 = r2.getParent()
            if (r2 != 0) goto L_0x0026
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.String r4 = "Cannot upload to getRoot. You should upload to a storage location such as .getReference('image.png').putFile..."
            r2.<init>(r4)
            r13.zzleq = r2
        L_0x0026:
            java.lang.Exception r2 = r13.zzleq
            if (r2 == 0) goto L_0x002b
            return
        L_0x002b:
            android.net.Uri r2 = r13.zzoxa
            if (r2 != 0) goto L_0x00a4
            com.google.firebase.storage.StorageMetadata r2 = r13.zzouu
            r4 = 0
            if (r2 == 0) goto L_0x003b
            com.google.firebase.storage.StorageMetadata r2 = r13.zzouu
            java.lang.String r2 = r2.getContentType()
            goto L_0x003c
        L_0x003b:
            r2 = r4
        L_0x003c:
            android.net.Uri r5 = r13.mUri
            if (r5 == 0) goto L_0x005e
            boolean r5 = android.text.TextUtils.isEmpty(r2)
            if (r5 == 0) goto L_0x005e
            com.google.firebase.storage.StorageReference r2 = r13.zzotm
            com.google.firebase.storage.FirebaseStorage r2 = r2.getStorage()
            com.google.firebase.FirebaseApp r2 = r2.getApp()
            android.content.Context r2 = r2.getApplicationContext()
            android.content.ContentResolver r2 = r2.getContentResolver()
            android.net.Uri r5 = r13.mUri
            java.lang.String r2 = r2.getType(r5)
        L_0x005e:
            boolean r5 = android.text.TextUtils.isEmpty(r2)
            if (r5 == 0) goto L_0x0066
            java.lang.String r2 = "application/octet-stream"
        L_0x0066:
            com.google.firebase.storage.StorageReference r5 = r13.zzotm     // Catch:{ JSONException -> 0x009b, RemoteException -> 0x0099 }
            com.google.android.gms.internal.zzfbm r5 = r5.zzcnw()     // Catch:{ JSONException -> 0x009b, RemoteException -> 0x0099 }
            com.google.firebase.storage.StorageReference r6 = r13.zzotm     // Catch:{ JSONException -> 0x009b, RemoteException -> 0x0099 }
            android.net.Uri r6 = r6.zzcnx()     // Catch:{ JSONException -> 0x009b, RemoteException -> 0x0099 }
            com.google.firebase.storage.StorageMetadata r7 = r13.zzouu     // Catch:{ JSONException -> 0x009b, RemoteException -> 0x0099 }
            if (r7 == 0) goto L_0x007c
            com.google.firebase.storage.StorageMetadata r4 = r13.zzouu     // Catch:{ JSONException -> 0x009b, RemoteException -> 0x0099 }
            org.json.JSONObject r4 = r4.zzcnu()     // Catch:{ JSONException -> 0x009b, RemoteException -> 0x0099 }
        L_0x007c:
            com.google.android.gms.internal.zzfbn r2 = r5.zza(r6, r4, r2)     // Catch:{ JSONException -> 0x009b, RemoteException -> 0x0099 }
            boolean r4 = r13.zzc(r2)
            if (r4 == 0) goto L_0x00a7
            java.lang.String r4 = "X-Goog-Upload-URL"
            java.lang.String r2 = r2.zzst(r4)
            boolean r4 = android.text.TextUtils.isEmpty(r2)
            if (r4 != 0) goto L_0x00a7
            android.net.Uri r2 = android.net.Uri.parse(r2)
            r13.zzoxa = r2
            goto L_0x00a7
        L_0x0099:
            r2 = move-exception
            goto L_0x009c
        L_0x009b:
            r2 = move-exception
        L_0x009c:
            java.lang.String r4 = "Unable to create a network request from metadata"
            android.util.Log.e(r3, r4, r2)
            r13.zzleq = r2
            goto L_0x00a7
        L_0x00a4:
            r13.zzde(r1)
        L_0x00a7:
            boolean r2 = r13.zzcoi()
        L_0x00ab:
            if (r2 == 0) goto L_0x0193
            com.google.android.gms.internal.zzfbb r2 = r13.zzoww     // Catch:{ IOException -> 0x0180 }
            int r4 = r13.zzowy     // Catch:{ IOException -> 0x0180 }
            r2.zzjb(r4)     // Catch:{ IOException -> 0x0180 }
            int r2 = r13.zzowy     // Catch:{ IOException -> 0x0180 }
            com.google.android.gms.internal.zzfbb r4 = r13.zzoww     // Catch:{ IOException -> 0x0180 }
            int r4 = r4.available()     // Catch:{ IOException -> 0x0180 }
            int r2 = java.lang.Math.min(r2, r4)     // Catch:{ IOException -> 0x0180 }
            com.google.firebase.storage.StorageReference r4 = r13.zzotm     // Catch:{ RemoteException -> 0x0179 }
            com.google.android.gms.internal.zzfbm r5 = r4.zzcnw()     // Catch:{ RemoteException -> 0x0179 }
            com.google.firebase.storage.StorageReference r4 = r13.zzotm     // Catch:{ RemoteException -> 0x0179 }
            android.net.Uri r6 = r4.zzcnx()     // Catch:{ RemoteException -> 0x0179 }
            android.net.Uri r4 = r13.zzoxa     // Catch:{ RemoteException -> 0x0179 }
            java.lang.String r7 = r4.toString()     // Catch:{ RemoteException -> 0x0179 }
            com.google.android.gms.internal.zzfbb r4 = r13.zzoww     // Catch:{ RemoteException -> 0x0179 }
            byte[] r8 = r4.zzcom()     // Catch:{ RemoteException -> 0x0179 }
            java.util.concurrent.atomic.AtomicLong r4 = r13.zzowx     // Catch:{ RemoteException -> 0x0179 }
            long r9 = r4.get()     // Catch:{ RemoteException -> 0x0179 }
            com.google.android.gms.internal.zzfbb r4 = r13.zzoww     // Catch:{ RemoteException -> 0x0179 }
            boolean r12 = r4.isFinished()     // Catch:{ RemoteException -> 0x0179 }
            r11 = r2
            com.google.android.gms.internal.zzfbn r4 = r5.zza(r6, r7, r8, r9, r11, r12)     // Catch:{ RemoteException -> 0x0179 }
            boolean r5 = r13.zzb(r4)     // Catch:{ IOException -> 0x0180 }
            if (r5 != 0) goto L_0x010b
            r2 = 262144(0x40000, float:3.67342E-40)
            r13.zzowy = r2     // Catch:{ IOException -> 0x0180 }
            r4 = 35
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0180 }
            r5.<init>(r4)     // Catch:{ IOException -> 0x0180 }
            java.lang.String r4 = "Resetting chunk size to "
            r5.append(r4)     // Catch:{ IOException -> 0x0180 }
            r5.append(r2)     // Catch:{ IOException -> 0x0180 }
            java.lang.String r2 = r5.toString()     // Catch:{ IOException -> 0x0180 }
        L_0x0106:
            android.util.Log.d(r3, r2)     // Catch:{ IOException -> 0x0180 }
            goto L_0x0188
        L_0x010b:
            java.util.concurrent.atomic.AtomicLong r5 = r13.zzowx     // Catch:{ IOException -> 0x0180 }
            long r6 = (long) r2     // Catch:{ IOException -> 0x0180 }
            r5.getAndAdd(r6)     // Catch:{ IOException -> 0x0180 }
            com.google.android.gms.internal.zzfbb r5 = r13.zzoww     // Catch:{ IOException -> 0x0180 }
            boolean r5 = r5.isFinished()     // Catch:{ IOException -> 0x0180 }
            if (r5 != 0) goto L_0x013c
            com.google.android.gms.internal.zzfbb r4 = r13.zzoww     // Catch:{ IOException -> 0x0180 }
            r4.zzja(r2)     // Catch:{ IOException -> 0x0180 }
            int r2 = r13.zzowy     // Catch:{ IOException -> 0x0180 }
            r4 = 33554432(0x2000000, float:9.403955E-38)
            if (r2 >= r4) goto L_0x0188
            int r2 = r2 << 1
            r13.zzowy = r2     // Catch:{ IOException -> 0x0180 }
            r4 = 36
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0180 }
            r5.<init>(r4)     // Catch:{ IOException -> 0x0180 }
            java.lang.String r4 = "Increasing chunk size to "
            r5.append(r4)     // Catch:{ IOException -> 0x0180 }
            r5.append(r2)     // Catch:{ IOException -> 0x0180 }
            java.lang.String r2 = r5.toString()     // Catch:{ IOException -> 0x0180 }
            goto L_0x0106
        L_0x013c:
            com.google.firebase.storage.StorageMetadata$Builder r2 = new com.google.firebase.storage.StorageMetadata$Builder     // Catch:{ JSONException -> 0x0158, RemoteException -> 0x0156 }
            org.json.JSONObject r5 = r4.zzcov()     // Catch:{ JSONException -> 0x0158, RemoteException -> 0x0156 }
            com.google.firebase.storage.StorageReference r6 = r13.zzotm     // Catch:{ JSONException -> 0x0158, RemoteException -> 0x0156 }
            r2.<init>(r5, r6)     // Catch:{ JSONException -> 0x0158, RemoteException -> 0x0156 }
            com.google.firebase.storage.StorageMetadata r2 = r2.build()     // Catch:{ JSONException -> 0x0158, RemoteException -> 0x0156 }
            r13.zzouu = r2     // Catch:{ JSONException -> 0x0158, RemoteException -> 0x0156 }
            r13.zzk(r0, r1)     // Catch:{ IOException -> 0x0180 }
            r2 = 128(0x80, float:1.794E-43)
            r13.zzk(r2, r1)     // Catch:{ IOException -> 0x0180 }
            goto L_0x0188
        L_0x0156:
            r2 = move-exception
            goto L_0x0159
        L_0x0158:
            r2 = move-exception
        L_0x0159:
            java.lang.String r5 = "Unable to parse resulting metadata from upload:"
            java.lang.String r4 = r4.zzcoq()     // Catch:{ IOException -> 0x0180 }
            java.lang.String r4 = java.lang.String.valueOf(r4)     // Catch:{ IOException -> 0x0180 }
            int r6 = r4.length()     // Catch:{ IOException -> 0x0180 }
            if (r6 == 0) goto L_0x016e
            java.lang.String r4 = r5.concat(r4)     // Catch:{ IOException -> 0x0180 }
            goto L_0x0173
        L_0x016e:
            java.lang.String r4 = new java.lang.String     // Catch:{ IOException -> 0x0180 }
            r4.<init>(r5)     // Catch:{ IOException -> 0x0180 }
        L_0x0173:
            android.util.Log.e(r3, r4, r2)     // Catch:{ IOException -> 0x0180 }
        L_0x0176:
            r13.zzleq = r2     // Catch:{ IOException -> 0x0180 }
            goto L_0x0188
        L_0x0179:
            r2 = move-exception
            java.lang.String r4 = "Unable to create chunk upload request"
            android.util.Log.e(r3, r4, r2)     // Catch:{ IOException -> 0x0180 }
            goto L_0x0176
        L_0x0180:
            r2 = move-exception
            java.lang.String r4 = "Unable to read bytes for uploading"
            android.util.Log.e(r3, r4, r2)
            r13.zzleq = r2
        L_0x0188:
            boolean r2 = r13.zzcoi()
            if (r2 == 0) goto L_0x00ab
            r13.zzk(r0, r1)
            goto L_0x00ab
        L_0x0193:
            boolean r0 = r13.zzowz
            if (r0 == 0) goto L_0x01ab
            int r0 = r13.zzcnz()
            r1 = 16
            if (r0 == r1) goto L_0x01ab
            com.google.android.gms.internal.zzfbb r0 = r13.zzoww     // Catch:{ IOException -> 0x01a5 }
            r0.close()     // Catch:{ IOException -> 0x01a5 }
            return
        L_0x01a5:
            r0 = move-exception
            java.lang.String r1 = "Unable to close stream."
            android.util.Log.e(r3, r1, r0)
        L_0x01ab:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.storage.UploadTask.run():void");
    }

    /* access modifiers changed from: protected */
    public void schedule() {
        zzu.zzu(zzbmh());
    }

    /* access modifiers changed from: package-private */
    public final TaskSnapshot zzcnt() {
        return new TaskSnapshot(StorageException.fromExceptionAndHttpCode(this.zzleq != null ? this.zzleq : this.zzoxb, this.mResultCode), this.zzowx.get(), this.zzoxa, this.zzouu);
    }
}
