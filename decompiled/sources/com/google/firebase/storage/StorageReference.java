package com.google.firebase.storage;

import android.net.Uri;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzfbd;
import com.google.android.gms.internal.zzfbm;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.storage.StreamDownloadTask;
import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class StorageReference {
    private final Uri zzoux;
    private final FirebaseStorage zzouy;

    StorageReference(Uri uri, FirebaseStorage firebaseStorage) {
        boolean z = true;
        zzbq.checkArgument(uri != null, "storageUri cannot be null");
        zzbq.checkArgument(firebaseStorage == null ? false : z, "FirebaseApp cannot be null");
        this.zzoux = uri;
        this.zzouy = firebaseStorage;
    }

    public StorageReference child(String str) {
        zzbq.checkArgument(!TextUtils.isEmpty(str), "childName cannot be null or empty");
        String zzsp = zzfbd.zzsp(str);
        try {
            return new StorageReference(this.zzoux.buildUpon().appendEncodedPath(zzfbd.zzsn(zzsp)).build(), this.zzouy);
        } catch (UnsupportedEncodingException e) {
            String valueOf = String.valueOf(zzsp);
            Log.e("StorageReference", valueOf.length() != 0 ? "Unable to create a valid default Uri. ".concat(valueOf) : new String("Unable to create a valid default Uri. "), e);
            throw new IllegalArgumentException("childName");
        }
    }

    public Task<Void> delete() {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        zzu.zzt(new zzb(this, taskCompletionSource));
        return taskCompletionSource.getTask();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof StorageReference)) {
            return false;
        }
        return ((StorageReference) obj).toString().equals(toString());
    }

    public List<FileDownloadTask> getActiveDownloadTasks() {
        return zzt.zzcod().zzb(this);
    }

    public List<UploadTask> getActiveUploadTasks() {
        return zzt.zzcod().zza(this);
    }

    public String getBucket() {
        return this.zzoux.getAuthority();
    }

    public Task<byte[]> getBytes(long j) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        StreamDownloadTask streamDownloadTask = new StreamDownloadTask(this);
        ((StorageTask) streamDownloadTask.zza((StreamDownloadTask.StreamProcessor) new zzi(this, j, taskCompletionSource)).addOnSuccessListener(new zzh(this, taskCompletionSource))).addOnFailureListener(new zzg(this, taskCompletionSource));
        streamDownloadTask.zzcny();
        return taskCompletionSource.getTask();
    }

    public Task<Uri> getDownloadUrl() {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        Task<StorageMetadata> metadata = getMetadata();
        metadata.addOnSuccessListener(new zze(this, taskCompletionSource));
        metadata.addOnFailureListener(new zzf(this, taskCompletionSource));
        return taskCompletionSource.getTask();
    }

    public FileDownloadTask getFile(Uri uri) {
        FileDownloadTask fileDownloadTask = new FileDownloadTask(this, uri);
        fileDownloadTask.zzcny();
        return fileDownloadTask;
    }

    public FileDownloadTask getFile(File file) {
        return getFile(Uri.fromFile(file));
    }

    public Task<StorageMetadata> getMetadata() {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        zzu.zzt(new zzc(this, taskCompletionSource));
        return taskCompletionSource.getTask();
    }

    public String getName() {
        String path = this.zzoux.getPath();
        int lastIndexOf = path.lastIndexOf(47);
        return lastIndexOf != -1 ? path.substring(lastIndexOf + 1) : path;
    }

    public StorageReference getParent() {
        String path = this.zzoux.getPath();
        if (TextUtils.isEmpty(path)) {
            return null;
        }
        String str = "/";
        if (path.equals(str)) {
            return null;
        }
        int lastIndexOf = path.lastIndexOf(47);
        if (lastIndexOf != -1) {
            str = path.substring(0, lastIndexOf);
        }
        return new StorageReference(this.zzoux.buildUpon().path(str).build(), this.zzouy);
    }

    public String getPath() {
        return this.zzoux.getPath();
    }

    public StorageReference getRoot() {
        return new StorageReference(this.zzoux.buildUpon().path("").build(), this.zzouy);
    }

    public FirebaseStorage getStorage() {
        return this.zzouy;
    }

    public StreamDownloadTask getStream() {
        StreamDownloadTask streamDownloadTask = new StreamDownloadTask(this);
        streamDownloadTask.zzcny();
        return streamDownloadTask;
    }

    public StreamDownloadTask getStream(StreamDownloadTask.StreamProcessor streamProcessor) {
        StreamDownloadTask streamDownloadTask = new StreamDownloadTask(this);
        streamDownloadTask.zza(streamProcessor);
        streamDownloadTask.zzcny();
        return streamDownloadTask;
    }

    public int hashCode() {
        return toString().hashCode();
    }

    public UploadTask putBytes(byte[] bArr) {
        zzbq.checkArgument(bArr != null, "bytes cannot be null");
        UploadTask uploadTask = new UploadTask(this, (StorageMetadata) null, bArr);
        uploadTask.zzcny();
        return uploadTask;
    }

    public UploadTask putBytes(byte[] bArr, StorageMetadata storageMetadata) {
        boolean z = true;
        zzbq.checkArgument(bArr != null, "bytes cannot be null");
        if (storageMetadata == null) {
            z = false;
        }
        zzbq.checkArgument(z, "metadata cannot be null");
        UploadTask uploadTask = new UploadTask(this, storageMetadata, bArr);
        uploadTask.zzcny();
        return uploadTask;
    }

    public UploadTask putFile(Uri uri) {
        zzbq.checkArgument(uri != null, "uri cannot be null");
        UploadTask uploadTask = new UploadTask(this, (StorageMetadata) null, uri, (Uri) null);
        uploadTask.zzcny();
        return uploadTask;
    }

    public UploadTask putFile(Uri uri, StorageMetadata storageMetadata) {
        boolean z = true;
        zzbq.checkArgument(uri != null, "uri cannot be null");
        if (storageMetadata == null) {
            z = false;
        }
        zzbq.checkArgument(z, "metadata cannot be null");
        UploadTask uploadTask = new UploadTask(this, storageMetadata, uri, (Uri) null);
        uploadTask.zzcny();
        return uploadTask;
    }

    public UploadTask putFile(Uri uri, StorageMetadata storageMetadata, Uri uri2) {
        boolean z = true;
        zzbq.checkArgument(uri != null, "uri cannot be null");
        if (storageMetadata == null) {
            z = false;
        }
        zzbq.checkArgument(z, "metadata cannot be null");
        UploadTask uploadTask = new UploadTask(this, storageMetadata, uri, uri2);
        uploadTask.zzcny();
        return uploadTask;
    }

    public UploadTask putStream(InputStream inputStream) {
        zzbq.checkArgument(inputStream != null, "stream cannot be null");
        UploadTask uploadTask = new UploadTask(this, (StorageMetadata) null, inputStream);
        uploadTask.zzcny();
        return uploadTask;
    }

    public UploadTask putStream(InputStream inputStream, StorageMetadata storageMetadata) {
        boolean z = true;
        zzbq.checkArgument(inputStream != null, "stream cannot be null");
        if (storageMetadata == null) {
            z = false;
        }
        zzbq.checkArgument(z, "metadata cannot be null");
        UploadTask uploadTask = new UploadTask(this, storageMetadata, inputStream);
        uploadTask.zzcny();
        return uploadTask;
    }

    public String toString() {
        String authority = this.zzoux.getAuthority();
        String encodedPath = this.zzoux.getEncodedPath();
        StringBuilder sb = new StringBuilder(String.valueOf(authority).length() + 5 + String.valueOf(encodedPath).length());
        sb.append("gs://");
        sb.append(authority);
        sb.append(encodedPath);
        return sb.toString();
    }

    public Task<StorageMetadata> updateMetadata(StorageMetadata storageMetadata) {
        zzbq.checkNotNull(storageMetadata);
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        zzu.zzt(new zzac(this, taskCompletionSource, storageMetadata));
        return taskCompletionSource.getTask();
    }

    /* access modifiers changed from: package-private */
    public final zzfbm zzcnw() throws RemoteException {
        return zzfbm.zzi(getStorage().getApp());
    }

    /* access modifiers changed from: package-private */
    public final Uri zzcnx() {
        return this.zzoux;
    }
}
