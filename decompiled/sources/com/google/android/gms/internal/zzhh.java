package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzbt;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

@zzabh
public final class zzhh {
    private final Object mLock = new Object();
    private int zzayr;
    private List<zzhg> zzays = new LinkedList();

    public final boolean zza(zzhg zzhg) {
        synchronized (this.mLock) {
            return this.zzays.contains(zzhg);
        }
    }

    public final boolean zzb(zzhg zzhg) {
        synchronized (this.mLock) {
            Iterator<zzhg> it = this.zzays.iterator();
            while (it.hasNext()) {
                zzhg next = it.next();
                if (!((Boolean) zzlc.zzio().zzd(zzoi.zzbnn)).booleanValue() || zzbt.zzep().zzqe().zzqp()) {
                    if (((Boolean) zzlc.zzio().zzd(zzoi.zzbnp)).booleanValue() && !zzbt.zzep().zzqe().zzqr() && zzhg != next && next.zzgq().equals(zzhg.zzgq())) {
                        it.remove();
                        return true;
                    }
                } else if (zzhg != next && next.zzgo().equals(zzhg.zzgo())) {
                    it.remove();
                    return true;
                }
            }
            return false;
        }
    }

    public final void zzc(zzhg zzhg) {
        synchronized (this.mLock) {
            if (this.zzays.size() >= 10) {
                int size = this.zzays.size();
                StringBuilder sb = new StringBuilder(41);
                sb.append("Queue is full, current size = ");
                sb.append(size);
                zzahw.zzby(sb.toString());
                this.zzays.remove(0);
            }
            int i = this.zzayr;
            this.zzayr = i + 1;
            zzhg.zzo(i);
            this.zzays.add(zzhg);
        }
    }

    public final zzhg zzgw() {
        synchronized (this.mLock) {
            zzhg zzhg = null;
            if (this.zzays.size() == 0) {
                zzahw.zzby("Queue empty");
                return null;
            }
            int i = 0;
            if (this.zzays.size() >= 2) {
                int i2 = Integer.MIN_VALUE;
                int i3 = 0;
                for (zzhg next : this.zzays) {
                    int score = next.getScore();
                    if (score > i2) {
                        i = i3;
                        zzhg = next;
                        i2 = score;
                    }
                    i3++;
                }
                this.zzays.remove(i);
                return zzhg;
            }
            zzhg zzhg2 = this.zzays.get(0);
            zzhg2.zzgr();
            return zzhg2;
        }
    }
}
