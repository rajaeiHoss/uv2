package com.google.android.gms.iid;

import android.os.Bundle;
import android.util.Log;
import com.google.android.gms.tasks.TaskCompletionSource;

abstract class zzu<T> {
    final int what;
    final TaskCompletionSource<T> zzgyc = new TaskCompletionSource<>();
    final int zzino;
    final Bundle zzinp;

    zzu(int i, int i2, Bundle bundle) {
        this.zzino = i;
        this.what = i2;
        this.zzinp = bundle;
    }

    public String toString() {
        int i = this.what;
        int i2 = this.zzino;
        zzaww();
        StringBuilder sb = new StringBuilder(55);
        sb.append("Request { what=");
        sb.append(i);
        sb.append(" id=");
        sb.append(i2);
        sb.append(" oneWay=false}");
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public abstract boolean zzaww();

    /* access modifiers changed from: package-private */
    public final void zzb(zzv zzv) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String valueOf = String.valueOf(this);
            String valueOf2 = String.valueOf(zzv);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 14 + String.valueOf(valueOf2).length());
            sb.append("Failing ");
            sb.append(valueOf);
            sb.append(" with ");
            sb.append(valueOf2);
            Log.d("MessengerIpcClient", sb.toString());
        }
        this.zzgyc.setException(zzv);
    }

    /* access modifiers changed from: package-private */
    public abstract void zzx(Bundle bundle);
}
