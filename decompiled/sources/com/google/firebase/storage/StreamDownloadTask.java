package com.google.firebase.storage;

import android.os.RemoteException;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzfbc;
import com.google.android.gms.internal.zzfbn;
import com.google.android.gms.wallet.WalletConstants;
import com.google.firebase.storage.StorageTask;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Callable;

public class StreamDownloadTask extends StorageTask<StreamDownloadTask.TaskSnapshot> {
    private volatile int mResultCode = 0;
    private long zzgsn = -1;
    private volatile Exception zzleq = null;
    /* access modifiers changed from: private */
    public InputStream zzltm;
    private StorageReference zzotm;
    private zzfbc zzoto;
    private long zzotq;
    private String zzotr;
    private StreamProcessor zzowa;
    private long zzowb;
    /* access modifiers changed from: private */
    public zzfbn zzowc;

    public interface StreamProcessor {
        void doInBackground(TaskSnapshot taskSnapshot, InputStream inputStream) throws IOException;
    }

    public class TaskSnapshot extends StorageTask<TaskSnapshot>.SnapshotBase {
        private final long zzotq;

        TaskSnapshot(Exception exc, long j) {
            super(exc);
            this.zzotq = j;
        }

        public long getBytesTransferred() {
            return this.zzotq;
        }

        public InputStream getStream() {
            return StreamDownloadTask.this.zzltm;
        }

        public long getTotalByteCount() {
            return StreamDownloadTask.this.getTotalBytes();
        }
    }

    static class zza extends InputStream {
        private StreamDownloadTask zzowe;
        private InputStream zzowf;
        private Callable<InputStream> zzowg;
        private IOException zzowh;
        private int zzowi;
        private int zzowj;
        private boolean zzowk;

        zza(Callable<InputStream> callable, StreamDownloadTask streamDownloadTask) {
            this.zzowe = streamDownloadTask;
            this.zzowg = callable;
        }

        private final void zzcof() throws IOException {
            StreamDownloadTask streamDownloadTask = this.zzowe;
            if (streamDownloadTask != null && streamDownloadTask.zzcnz() == 32) {
                throw new IOException("Download canceled");
            }
        }

        /* access modifiers changed from: private */
        public final boolean zzcog() throws IOException {
            zzcof();
            if (this.zzowh != null) {
                try {
                    InputStream inputStream = this.zzowf;
                    if (inputStream != null) {
                        inputStream.close();
                    }
                } catch (IOException unused) {
                }
                this.zzowf = null;
                int i = this.zzowj;
                int i2 = this.zzowi;
                if (i == i2) {
                    Log.i("StreamDownloadTask", "Encountered exception during stream operation. Aborting.", this.zzowh);
                    return false;
                }
                StringBuilder sb = new StringBuilder(70);
                sb.append("Encountered exception during stream operation. Retrying at ");
                sb.append(i2);
                Log.i("StreamDownloadTask", sb.toString(), this.zzowh);
                this.zzowj = this.zzowi;
                this.zzowh = null;
            }
            if (this.zzowk) {
                throw new IOException("Can't perform operation on closed stream");
            } else if (this.zzowf != null) {
                return true;
            } else {
                try {
                    this.zzowf = this.zzowg.call();
                    return true;
                } catch (Exception e) {
                    if (e instanceof IOException) {
                        throw ((IOException) e);
                    }
                    throw new IOException("Unable to open stream", e);
                }
            }
        }

        private final void zzcq(long j) {
            StreamDownloadTask streamDownloadTask = this.zzowe;
            if (streamDownloadTask != null) {
                streamDownloadTask.zzcq(j);
            }
            this.zzowi = (int) (((long) this.zzowi) + j);
        }

        public final int available() throws IOException {
            while (zzcog()) {
                try {
                    return this.zzowf.available();
                } catch (IOException e) {
                    this.zzowh = e;
                }
            }
            throw this.zzowh;
        }

        public final void close() throws IOException {
            InputStream inputStream = this.zzowf;
            if (inputStream != null) {
                inputStream.close();
            }
            this.zzowk = true;
            StreamDownloadTask streamDownloadTask = this.zzowe;
            if (!(streamDownloadTask == null || streamDownloadTask.zzowc == null)) {
                this.zzowe.zzowc.zzcon();
                zzfbn unused = this.zzowe.zzowc = null;
            }
            zzcof();
        }

        public final void mark(int i) {
        }

        public final boolean markSupported() {
            return false;
        }

        public final int read() throws IOException {
            while (zzcog()) {
                try {
                    int read = this.zzowf.read();
                    if (read != -1) {
                        zzcq(1);
                    }
                    return read;
                } catch (IOException e) {
                    this.zzowh = e;
                }
            }
            throw this.zzowh;
        }

        public final int read(byte[] bArr, int i, int i2) throws IOException {
            int i3 = 0;
            while (zzcog()) {
                while (((long) i2) > PlaybackStateCompat.ACTION_SET_REPEAT_MODE) {
                    try {
                        int read = this.zzowf.read(bArr, i, 262144);
                        if (read != -1) {
                            i3 += read;
                            i += read;
                            i2 -= read;
                            zzcq((long) read);
                            zzcof();
                        } else if (i3 == 0) {
                            return -1;
                        } else {
                            return i3;
                        }
                    } catch (IOException e) {
                        this.zzowh = e;
                    }
                }
                if (i2 > 0) {
                    int read2 = this.zzowf.read(bArr, i, i2);
                    if (read2 != -1) {
                        i += read2;
                        i3 += read2;
                        i2 -= read2;
                        zzcq((long) read2);
                    } else if (i3 == 0) {
                        return -1;
                    } else {
                        return i3;
                    }
                }
                if (i2 == 0) {
                    return i3;
                }
            }
            throw this.zzowh;
        }

        public final long skip(long j) throws IOException {
            IOException e;
            int i = 0;
            while (zzcog()) {
                while (j > PlaybackStateCompat.ACTION_SET_REPEAT_MODE) {
                    try {
                        long skip = this.zzowf.skip(PlaybackStateCompat.ACTION_SET_REPEAT_MODE);
                        if (skip >= 0) {
                            i = (int) (((long) i) + skip);
                            j -= skip;
                            zzcq(skip);
                            zzcof();
                        } else if (i == 0) {
                            return -1;
                        } else {
                            return (long) i;
                        }
                    } catch (IOException e2) {
                        e = e2;
                        this.zzowh = e;
                    }
                }
                if (j > 0) {
                    long skip2 = this.zzowf.skip(j);
                    if (skip2 >= 0) {
                        int i2 = (int) (((long) i) + skip2);
                        j -= skip2;
                        zzcq(skip2);
                        i = i2;
                    } else if (i == 0) {
                        return -1;
                    } else {
                        return (long) i;
                    }
                }
                if (j == 0) {
                    return (long) i;
                }
            }
            throw this.zzowh;
        }
    }

    StreamDownloadTask(StorageReference storageReference) {
        this.zzotm = storageReference;
        this.zzoto = new zzfbc(this.zzotm.getStorage().getApp(), this.zzotm.getStorage().getMaxDownloadRetryTimeMillis());
    }

    /* access modifiers changed from: private */
    public final InputStream zzcoe() throws Exception {
        String str;
        this.zzoto.reset();
        zzfbn zzfbn = this.zzowc;
        if (zzfbn != null) {
            zzfbn.zzcon();
        }
        try {
            zzfbn zza2 = this.zzotm.zzcnw().zza(this.zzotm.zzcnx(), this.zzotq);
            this.zzowc = zza2;
            boolean z = false;
            this.zzoto.zza(zza2, false);
            this.mResultCode = this.zzowc.getResultCode();
            this.zzleq = this.zzowc.getException() != null ? this.zzowc.getException() : this.zzleq;
            int i = this.mResultCode;
            if ((i == 308 || (i >= 200 && i < 300)) && this.zzleq == null && zzcnz() == 4) {
                z = true;
            }
            if (z) {
                String zzst = this.zzowc.zzst("ETag");
                if (TextUtils.isEmpty(zzst) || (str = this.zzotr) == null || str.equals(zzst)) {
                    this.zzotr = zzst;
                    if (this.zzgsn == -1) {
                        this.zzgsn = (long) this.zzowc.zzcot();
                    }
                    return this.zzowc.getStream();
                }
                this.mResultCode = WalletConstants.ERROR_CODE_BUYER_ACCOUNT_ERROR;
                throw new IOException("The ETag on the server changed.");
            }
            throw new IOException("Could not open resulting stream.");
        } catch (RemoteException e) {
            Log.e("StreamDownloadTask", "Unable to create firebase storage network request.", e);
            throw e;
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

    /* access modifiers changed from: protected */
    public void onProgress() {
        this.zzowb = this.zzotq;
    }

    public boolean pause() {
        throw new UnsupportedOperationException("this operation is not supported on StreamDownloadTask.");
    }

    public boolean resume() {
        throw new UnsupportedOperationException("this operation is not supported on StreamDownloadTask.");
    }

    /* access modifiers changed from: package-private */
    public final void run() {
        int i = 64;
        if (this.zzleq != null) {
            zzk(64, false);
        } else if (zzk(4, false)) {
            zza zza2 = new zza(new zzw(this), this);
            this.zzltm = new BufferedInputStream(zza2);
            try {
                boolean unused = zza2.zzcog();
                StreamProcessor streamProcessor = this.zzowa;
                if (streamProcessor != null) {
                    try {
                        streamProcessor.doInBackground((TaskSnapshot) zzcoa(), this.zzltm);
                    } catch (Exception e) {
                        Log.w("StreamDownloadTask", "Exception occurred calling doInBackground.", e);
                        this.zzleq = e;
                    }
                }
            } catch (IOException e2) {
                Log.d("StreamDownloadTask", "Initial opening of Stream failed", e2);
                this.zzleq = e2;
            }
            if (this.zzltm == null) {
                this.zzowc.zzcon();
                this.zzowc = null;
            }
            if (this.zzleq == null && zzcnz() == 4) {
                zzk(4, false);
                zzk(128, false);
                return;
            }
            if (zzcnz() == 32) {
                i = 256;
            }
            if (!zzk(i, false)) {
                int zzcnz = zzcnz();
                StringBuilder sb = new StringBuilder(62);
                sb.append("Unable to change download task to final state from ");
                sb.append(zzcnz);
                Log.w("StreamDownloadTask", sb.toString());
            }
        }
    }

    /* access modifiers changed from: protected */
    public void schedule() {
        zzu.zzv(zzbmh());
    }

    /* access modifiers changed from: package-private */
    public final StreamDownloadTask zza(StreamProcessor streamProcessor) {
        zzbq.checkNotNull(streamProcessor);
        zzbq.checkState(this.zzowa == null);
        this.zzowa = streamProcessor;
        return this;
    }

    /* access modifiers changed from: package-private */
    public final TaskSnapshot zzcnt() {
        return new TaskSnapshot(StorageException.fromExceptionAndHttpCode(this.zzleq, this.mResultCode), this.zzowb);
    }

    /* access modifiers changed from: package-private */
    public final void zzcq(long j) {
        long j2 = this.zzotq + j;
        this.zzotq = j2;
        if (this.zzowb + PlaybackStateCompat.ACTION_SET_REPEAT_MODE > j2) {
            return;
        }
        if (zzcnz() == 4) {
            zzk(4, false);
        } else {
            this.zzowb = this.zzotq;
        }
    }
}
