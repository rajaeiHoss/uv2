package com.google.firebase.storage;

import android.util.Log;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.storage.StreamDownloadTask;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

final class zzi implements StreamDownloadTask.StreamProcessor {
    private /* synthetic */ TaskCompletionSource zzova;
    private /* synthetic */ long zzovb;

    zzi(StorageReference storageReference, long j, TaskCompletionSource taskCompletionSource) {
        this.zzovb = j;
        this.zzova = taskCompletionSource;
    }

    public final void doInBackground(StreamDownloadTask.TaskSnapshot taskSnapshot, InputStream inputStream) throws IOException {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[16384];
            int i = 0;
            while (true) {
                int read = inputStream.read(bArr, 0, 16384);
                if (read != -1) {
                    i += read;
                    if (((long) i) <= this.zzovb) {
                        byteArrayOutputStream.write(bArr, 0, read);
                    } else {
                        Log.e("StorageReference", "the maximum allowed buffer size was exceeded.");
                        throw new IndexOutOfBoundsException("the maximum allowed buffer size was exceeded.");
                    }
                } else {
                    byteArrayOutputStream.flush();
                    this.zzova.setResult(byteArrayOutputStream.toByteArray());
                    return;
                }
            }
        } finally {
            inputStream.close();
        }
    }
}
