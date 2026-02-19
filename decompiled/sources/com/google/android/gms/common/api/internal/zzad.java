package com.google.android.gms.common.api.internal;

import android.util.Log;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.AvailabilityException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.util.Collections;
import java.util.Map;

final class zzad implements OnCompleteListener<Map<zzh<?>, String>> {
    private /* synthetic */ zzaa zzfwu;
    private zzcu zzfwv;

    zzad(zzaa zzaa, zzcu zzcu) {
        this.zzfwu = zzaa;
        this.zzfwv = zzcu;
    }

    /* access modifiers changed from: package-private */
    public final void cancel() {
        this.zzfwv.zzacm();
    }

    public final void onComplete(Task<Map<zzh<?>, String>> task) {
        zzcu zzcu;
        Map zzg;
        this.zzfwu.zzfwa.lock();
        try {
            if (!this.zzfwu.zzfwp) {
                zzcu = this.zzfwv;
            } else {
                if (task.isSuccessful()) {
                    zzaa zzaa = this.zzfwu;
                    Map unused = zzaa.zzfwr = new ArrayMap(zzaa.zzfwh.size());
                    for (zzz zzahv : this.zzfwu.zzfwh.values()) {
                        this.zzfwu.zzfwr.put(zzahv.zzahv(), ConnectionResult.zzfqt);
                    }
                } else if (task.getException() instanceof AvailabilityException) {
                    AvailabilityException availabilityException = (AvailabilityException) task.getException();
                    if (this.zzfwu.zzfwn) {
                        zzaa zzaa2 = this.zzfwu;
                        Map unused2 = zzaa2.zzfwr = new ArrayMap(zzaa2.zzfwh.size());
                        for (zzz zzz : this.zzfwu.zzfwh.values()) {
                            zzh zzahv2 = zzz.zzahv();
                            ConnectionResult connectionResult = availabilityException.getConnectionResult(zzz);
                            if (this.zzfwu.zza((zzz<?>) zzz, connectionResult)) {
                                zzg = this.zzfwu.zzfwr;
                                connectionResult = new ConnectionResult(16);
                            } else {
                                zzg = this.zzfwu.zzfwr;
                            }
                            zzg.put(zzahv2, connectionResult);
                        }
                    } else {
                        Map unused3 = this.zzfwu.zzfwr = availabilityException.zzahr();
                    }
                } else {
                    Log.e("ConnectionlessGAC", "Unexpected availability exception", task.getException());
                    Map unused4 = this.zzfwu.zzfwr = Collections.emptyMap();
                }
                if (this.zzfwu.isConnected()) {
                    this.zzfwu.zzfwq.putAll(this.zzfwu.zzfwr);
                    if (this.zzfwu.zzajb() == null) {
                        this.zzfwu.zzaiz();
                        this.zzfwu.zzaja();
                        this.zzfwu.zzfwl.signalAll();
                    }
                }
                zzcu = this.zzfwv;
            }
            zzcu.zzacm();
        } finally {
            this.zzfwu.zzfwa.unlock();
        }
    }
}
