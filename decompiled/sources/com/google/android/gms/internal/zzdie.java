package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzi;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class zzdie {
    private final Context mContext;
    private final zze zzata;
    private String zzkog;
    /* access modifiers changed from: private */
    public final zzdip zzlba;
    private Map<String, zzdih<zzdjc>> zzlbb;
    private final Map<String, zzdja> zzlbc;

    public zzdie(Context context) {
        this(context, new HashMap(), new zzdip(context), zzi.zzanq());
    }

    private zzdie(Context context, Map<String, zzdja> map, zzdip zzdip, zze zze) {
        this.zzkog = null;
        this.zzlbb = new HashMap();
        this.mContext = context.getApplicationContext();
        this.zzata = zze;
        this.zzlba = zzdip;
        this.zzlbc = map;
    }

    /* access modifiers changed from: package-private */
    public final void zza(Status status, zzdio zzdio) {
        String containerId = zzdio.zzbjy().getContainerId();
        zzdjc zzbjz = zzdio.zzbjz();
        if (this.zzlbb.containsKey(containerId)) {
            zzdih zzdih = this.zzlbb.get(containerId);
            zzdih.zzbk(this.zzata.currentTimeMillis());
            if (status == Status.zzftq) {
                zzdih.zzav(status);
                zzdih.zzat(zzbjz);
                return;
            }
            return;
        }
        this.zzlbb.put(containerId, new zzdih(status, zzbjz, this.zzata.currentTimeMillis()));
    }

    /* access modifiers changed from: package-private */
    public final void zza(zzdim zzdim, List<Integer> list, int i, zzdif zzdif, zzczp zzczp) {
        List<Integer> list2 = list;
        int i2 = i;
        while (true) {
            if (i2 == 0) {
                zzdal.v("Starting to fetch a new resource");
            }
            boolean z = true;
            if (i2 >= list.size()) {
                String valueOf = String.valueOf(zzdim.zzbjt().getContainerId());
                String concat = valueOf.length() != 0 ? "There is no valid resource for the container: ".concat(valueOf) : new String("There is no valid resource for the container: ");
                zzdal.v(concat);
                zzdif.zza(new zzdin(new Status(16, concat), list2.get(i2 - 1).intValue()));
                return;
            }
            zzdif zzdif2 = zzdif;
            int intValue = list2.get(i2).intValue();
            if (intValue == 0) {
                zzdia zzbjt = zzdim.zzbjt();
                zzdih zzdih = this.zzlbb.get(zzbjt.getContainerId());
                if (!zzdim.zzbjt().zzbjp()) {
                    if ((zzdih != null ? zzdih.zzbjs() : this.zzlba.zznb(zzbjt.getContainerId())) + 900000 >= this.zzata.currentTimeMillis()) {
                        z = false;
                    }
                }
                if (z) {
                    zzdja zzdja = this.zzlbc.get(zzdim.getId());
                    if (zzdja == null) {
                        zzdja = new zzdja();
                        this.zzlbc.put(zzdim.getId(), zzdja);
                    }
                    zzdja zzdja2 = zzdja;
                    String containerId = zzbjt.getContainerId();
                    StringBuilder sb = new StringBuilder(String.valueOf(containerId).length() + 43);
                    sb.append("Attempting to fetch container ");
                    sb.append(containerId);
                    sb.append(" from network");
                    zzdal.v(sb.toString());
                    zzdja2.zza(this.mContext, zzdim, 0, new zzdig(this, 0, zzdim, zzdij.zzlbi, list, i2, zzdif, zzczp));
                    return;
                }
                i2++;
            } else if (intValue == 1) {
                zzdia zzbjt2 = zzdim.zzbjt();
                String containerId2 = zzbjt2.getContainerId();
                StringBuilder sb2 = new StringBuilder(String.valueOf(containerId2).length() + 52);
                sb2.append("Attempting to fetch container ");
                sb2.append(containerId2);
                sb2.append(" from a saved resource");
                zzdal.v(sb2.toString());
                this.zzlba.zza(zzbjt2.zzbjo(), new zzdig(this, 1, zzdim, zzdij.zzlbi, list, i2, zzdif, (zzczp) null));
                return;
            } else if (intValue == 2) {
                zzdia zzbjt3 = zzdim.zzbjt();
                String containerId3 = zzbjt3.getContainerId();
                StringBuilder sb3 = new StringBuilder(String.valueOf(containerId3).length() + 56);
                sb3.append("Attempting to fetch container ");
                sb3.append(containerId3);
                sb3.append(" from the default resource");
                zzdal.v(sb3.toString());
                this.zzlba.zza(zzbjt3.zzbjo(), zzbjt3.zzbjm(), new zzdig(this, 2, zzdim, zzdij.zzlbi, list, i2, zzdif, (zzczp) null));
                return;
            } else {
                StringBuilder sb4 = new StringBuilder(36);
                sb4.append("Unknown fetching source: ");
                sb4.append(i2);
                throw new UnsupportedOperationException(sb4.toString());
            }
        }
    }

    public final void zza(String str, String str2, String str3, List<Integer> list, zzdif zzdif, zzczp zzczp) {
        boolean z;
        zzbq.checkArgument(!list.isEmpty());
        zzdim zzdim = new zzdim();
        zzdat zzbja = zzdat.zzbja();
        if (zzbja.isPreview()) {
            if (str.equals(zzbja.getContainerId())) {
                z = true;
                zza(zzdim.zza(new zzdia(str, str2, str3, z, zzdat.zzbja().zzbjb())), Collections.unmodifiableList(list), 0, zzdif, zzczp);
            }
        } else {
            String str4 = str;
        }
        z = false;
        zza(zzdim.zza(new zzdia(str, str2, str3, z, zzdat.zzbja().zzbjb())), Collections.unmodifiableList(list), 0, zzdif, zzczp);
    }
}
