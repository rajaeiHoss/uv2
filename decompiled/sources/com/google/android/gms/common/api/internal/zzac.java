package com.google.android.gms.common.api.internal;

import android.util.Log;
import androidx.collection.ArrayMap;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.AvailabilityException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.util.Collections;
import java.util.Map;

final class zzac implements OnCompleteListener<Map<zzh<?>, String>> {
    private /* synthetic */ zzaa zzfwu;

    zzac(zzaa zzaa) {
        this.zzfwu = zzaa;
    }

    public final void onComplete(Task<Map<zzh<?>, String>> task) {
        zzaa zzaa;
        ConnectionResult connectionResult;
        Map zzd;
        this.zzfwu.zzfwa.lock();
        try {
            if (this.zzfwu.zzfwp) {
                if (task.isSuccessful()) {
                    zzaa zzaa2 = this.zzfwu;
                    Map unused = zzaa2.zzfwq = new ArrayMap(zzaa2.zzfwg.size());
                    for (zzz zzahv : this.zzfwu.zzfwg.values()) {
                        this.zzfwu.zzfwq.put(zzahv.zzahv(), ConnectionResult.zzfqt);
                    }
                } else {
                    if (task.getException() instanceof AvailabilityException) {
                        AvailabilityException availabilityException = (AvailabilityException) task.getException();
                        if (this.zzfwu.zzfwn) {
                            zzaa zzaa3 = this.zzfwu;
                            Map unused2 = zzaa3.zzfwq = new ArrayMap(zzaa3.zzfwg.size());
                            for (zzz zzz : this.zzfwu.zzfwg.values()) {
                                zzh zzahv2 = zzz.zzahv();
                                ConnectionResult connectionResult2 = availabilityException.getConnectionResult(zzz);
                                if (this.zzfwu.zza((zzz<?>) zzz, connectionResult2)) {
                                    zzd = this.zzfwu.zzfwq;
                                    connectionResult2 = new ConnectionResult(16);
                                } else {
                                    zzd = this.zzfwu.zzfwq;
                                }
                                zzd.put(zzahv2, connectionResult2);
                            }
                        } else {
                            Map unused3 = this.zzfwu.zzfwq = availabilityException.zzahr();
                        }
                        zzaa = this.zzfwu;
                        connectionResult = zzaa.zzajb();
                    } else {
                        Log.e("ConnectionlessGAC", "Unexpected availability exception", task.getException());
                        Map unused4 = this.zzfwu.zzfwq = Collections.emptyMap();
                        zzaa = this.zzfwu;
                        connectionResult = new ConnectionResult(8);
                    }
                    ConnectionResult unused5 = zzaa.zzfwt = connectionResult;
                }
                if (this.zzfwu.zzfwr != null) {
                    this.zzfwu.zzfwq.putAll(this.zzfwu.zzfwr);
                    zzaa zzaa4 = this.zzfwu;
                    ConnectionResult unused6 = zzaa4.zzfwt = zzaa4.zzajb();
                }
                if (this.zzfwu.zzfwt == null) {
                    this.zzfwu.zzaiz();
                    this.zzfwu.zzaja();
                } else {
                    boolean unused7 = this.zzfwu.zzfwp = false;
                    this.zzfwu.zzfwj.zzc(this.zzfwu.zzfwt);
                }
                this.zzfwu.zzfwl.signalAll();
            }
        } finally {
            this.zzfwu.zzfwa.unlock();
        }
    }
}
