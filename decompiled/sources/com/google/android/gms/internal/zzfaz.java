package com.google.android.gms.internal;

import android.app.Activity;
import android.util.Log;
import com.google.android.gms.common.api.internal.LifecycleCallback;
import com.google.android.gms.common.api.internal.zzce;
import com.google.android.gms.common.api.internal.zzcf;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class zzfaz {
    private static final zzfaz zzoxg = new zzfaz();
    private final Map<Object, zzfba> zzoxh = new HashMap();
    private final Object zzoxi = new Object();

    static class zza extends LifecycleCallback {
        private final List<zzfba> zzffp = new ArrayList();

        private zza(zzcf zzcf) {
            super(zzcf);
            this.zzgam.zza("StorageOnStopCallback", (LifecycleCallback) this);
        }

        public static zza zzu(Activity activity) {
            zzcf zzb = zzb(new zzce(activity));
            zza zza = (zza) zzb.zza("StorageOnStopCallback", zza.class);
            return zza == null ? new zza(zzb) : zza;
        }

        public final void onStop() {
            ArrayList arrayList;
            synchronized (this.zzffp) {
                arrayList = new ArrayList(this.zzffp);
                this.zzffp.clear();
            }
            ArrayList arrayList2 = arrayList;
            int size = arrayList2.size();
            int i = 0;
            while (i < size) {
                Object obj = arrayList2.get(i);
                i++;
                zzfba zzfba = (zzfba) obj;
                if (zzfba != null) {
                    Log.d("StorageOnStopCallback", "removing subscription from activity.");
                    zzfba.zzbmh().run();
                    zzfaz.zzcok().zzcq(zzfba.zzcol());
                }
            }
        }

        public final void zza(zzfba zzfba) {
            synchronized (this.zzffp) {
                this.zzffp.add(zzfba);
            }
        }

        public final void zzb(zzfba zzfba) {
            synchronized (this.zzffp) {
                this.zzffp.remove(zzfba);
            }
        }
    }

    private zzfaz() {
    }

    public static zzfaz zzcok() {
        return zzoxg;
    }

    public final void zza(Activity activity, Object obj, Runnable runnable) {
        synchronized (this.zzoxi) {
            zzfba zzfba = new zzfba(activity, runnable, obj);
            zza.zzu(activity).zza(zzfba);
            this.zzoxh.put(obj, zzfba);
        }
    }

    public final void zzcq(Object obj) {
        synchronized (this.zzoxi) {
            zzfba zzfba = this.zzoxh.get(obj);
            if (zzfba != null) {
                zza.zzu(zzfba.getActivity()).zzb(zzfba);
            }
        }
    }
}
