package com.google.firebase.storage;

import android.net.Uri;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzfbc;
import com.google.android.gms.internal.zzfbm;
import com.google.android.gms.internal.zzfbn;
import com.google.firebase.storage.StorageTask;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileDownloadTask extends StorageTask<FileDownloadTask.TaskSnapshot> {
    private int mResultCode;
    private long zzgsn = -1;
    private volatile Exception zzleq = null;
    private StorageReference zzotm;
    private zzfbc zzoto;
    private final Uri zzotp;
    private long zzotq;
    private String zzotr = null;
    private long zzots = 0;

    public class TaskSnapshot extends StorageTask<TaskSnapshot>.SnapshotBase {
        private final long zzotq;

        TaskSnapshot(Exception exc, long j) {
            super(exc);
            this.zzotq = j;
        }

        public long getBytesTransferred() {
            return this.zzotq;
        }

        public long getTotalByteCount() {
            return FileDownloadTask.this.getTotalBytes();
        }
    }

    FileDownloadTask(StorageReference storageReference, Uri uri) {
        this.zzotm = storageReference;
        this.zzotp = uri;
        this.zzoto = new zzfbc(this.zzotm.getStorage().getApp(), this.zzotm.getStorage().getMaxDownloadRetryTimeMillis());
    }

    private final int zza(InputStream inputStream, byte[] bArr) {
        int i = 0;
        boolean z = false;
        while (i != bArr.length) {
            int read;
            try {
                read = inputStream.read(bArr, i, bArr.length - i);
            } catch (IOException e) {
                this.zzleq = e;
                break;
            }
            if (read == -1) {
                break;
            }
            z = true;
            i += read;
        }
        return z ? i : -1;
    }

    /* JADX INFO: finally extract failed */
    private final boolean zza(zzfbn zzfbn) throws IOException {
        FileOutputStream fileOutputStream;
        InputStream stream = zzfbn.getStream();
        if (stream != null) {
            File file = new File(this.zzotp.getPath());
            if (!file.exists()) {
                if (this.zzots > 0) {
                    String valueOf = String.valueOf(file.getAbsolutePath());
                    Log.e("FileDownloadTask", valueOf.length() != 0 ? "The file downloading to has been deleted:".concat(valueOf) : new String("The file downloading to has been deleted:"));
                    throw new IllegalStateException("expected a file to resume from.");
                } else if (!file.createNewFile()) {
                    String valueOf2 = String.valueOf(file.getAbsolutePath());
                    Log.w("FileDownloadTask", valueOf2.length() != 0 ? "unable to create file:".concat(valueOf2) : new String("unable to create file:"));
                }
            }
            boolean z = true;
            if (this.zzots > 0) {
                String absolutePath = file.getAbsolutePath();
                long j = this.zzots;
                StringBuilder sb = new StringBuilder(String.valueOf(absolutePath).length() + 47);
                sb.append("Resuming download file ");
                sb.append(absolutePath);
                sb.append(" at ");
                sb.append(j);
                Log.d("FileDownloadTask", sb.toString());
                fileOutputStream = new FileOutputStream(file, true);
            } else {
                fileOutputStream = new FileOutputStream(file);
            }
            try {
                byte[] bArr = new byte[262144];
                while (z) {
                    int zza = zza(stream, bArr);
                    if (zza == -1) {
                        break;
                    }
                    fileOutputStream.write(bArr, 0, zza);
                    this.zzotq += (long) zza;
                    if (this.zzleq != null) {
                        Log.d("FileDownloadTask", "Exception occurred during file download. Retrying.", this.zzleq);
                        this.zzleq = null;
                        z = false;
                    }
                    if (!zzk(4, false)) {
                        z = false;
                    }
                }
                fileOutputStream.flush();
                fileOutputStream.close();
                stream.close();
                return z;
            } catch (Throwable th) {
                fileOutputStream.flush();
                fileOutputStream.close();
                stream.close();
                throw th;
            }
        } else {
            this.zzleq = new IllegalStateException("Unable to open Firebase Storage stream.");
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public final StorageReference getStorage() {
        return this.zzotm;
    }

    /* access modifiers changed from: package-private */
    public final long getTotalBytes() {
        return this.zzgsn;
    }

    /* access modifiers changed from: protected */
    public void onCanceled() {
        this.zzoto.cancel();
        this.zzleq = StorageException.fromErrorStatus(Status.zzftu);
    }

    /* access modifiers changed from: package-private */
    public final void run() {
        String str;
        if (this.zzleq != null) {
            zzk(64, false);
        } else if (zzk(4, false)) {
            do {
                this.zzotq = 0;
                this.zzleq = null;
                this.zzoto.reset();
                try {
                    zzfbn zza = zzfbm.zzi(this.zzotm.getStorage().getApp()).zza(this.zzotm.zzcnx(), this.zzots);
                    this.zzoto.zza(zza, false);
                    this.mResultCode = zza.getResultCode();
                    this.zzleq = zza.getException() != null ? zza.getException() : this.zzleq;
                    int i = this.mResultCode;
                    boolean z = true;
                    boolean z2 = (i == 308 || (i >= 200 && i < 300)) && this.zzleq == null && zzcnz() == 4;
                    if (z2) {
                        this.zzgsn = (long) zza.zzcot();
                        String zzst = zza.zzst("ETag");
                        if (TextUtils.isEmpty(zzst) || (str = this.zzotr) == null || str.equals(zzst)) {
                            this.zzotr = zzst;
                            try {
                                z2 = zza(zza);
                            } catch (IOException e) {
                                Log.e("FileDownloadTask", "Exception occurred during file write.  Aborting.", e);
                                this.zzleq = e;
                            }
                        } else {
                            Log.w("FileDownloadTask", "The file at the server has changed.  Restarting from the beginning.");
                            this.zzots = 0;
                            this.zzotr = null;
                            zza.zzcon();
                            schedule();
                            return;
                        }
                    }
                    zza.zzcon();
                    if (!(z2 && this.zzleq == null && zzcnz() == 4)) {
                        z = false;
                    }
                    if (z) {
                        zzk(128, false);
                        return;
                    }
                    File file = new File(this.zzotp.getPath());
                    if (file.exists()) {
                        this.zzots = file.length();
                    } else {
                        this.zzots = 0;
                    }
                    if (zzcnz() == 8) {
                        zzk(16, false);
                        return;
                    } else if (zzcnz() == 32) {
                        if (!zzk(256, false)) {
                            int zzcnz = zzcnz();
                            StringBuilder sb = new StringBuilder(62);
                            sb.append("Unable to change download task to final state from ");
                            sb.append(zzcnz);
                            Log.w("FileDownloadTask", sb.toString());
                            return;
                        }
                        return;
                    }
                } catch (RemoteException e2) {
                    Log.e("FileDownloadTask", "Unable to create firebase storage network request.", e2);
                    this.zzleq = e2;
                    zzk(64, false);
                    return;
                }
            } while (this.zzotq > 0);
            zzk(64, false);
        }
    }

    /* access modifiers changed from: protected */
    public void schedule() {
        zzu.zzv(zzbmh());
    }

    /* access modifiers changed from: package-private */
    public final TaskSnapshot zzcnt() {
        return new TaskSnapshot(StorageException.fromExceptionAndHttpCode(this.zzleq, this.mResultCode), this.zzotq + this.zzots);
    }
}
