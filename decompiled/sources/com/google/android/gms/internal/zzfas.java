package com.google.android.gms.internal;

import java.util.HashMap;
import java.util.Map;

public final class zzfas {
    private boolean zzosi;
    private long zzosl;
    private int zzosr;
    private long zzosu;
    private Map<String, zzfam> zzosv;

    public zzfas() {
        this(-1);
    }

    private zzfas(int i, long j, Map<String, zzfam> map, boolean z) {
        this(0, -1, (Map<String, zzfam>) null, false, -1);
    }

    private zzfas(int i, long j, Map<String, zzfam> map, boolean z, long j2) {
        this.zzosr = 0;
        this.zzosu = j;
        this.zzosv = new HashMap();
        this.zzosi = false;
        this.zzosl = -1;
    }

    private zzfas(long j) {
        this(0, -1, (Map<String, zzfam>) null, false);
    }

    public final int getLastFetchStatus() {
        return this.zzosr;
    }

    public final boolean isDeveloperModeEnabled() {
        return this.zzosi;
    }

    public final void zza(String str, zzfam zzfam) {
        this.zzosv.put(str, zzfam);
    }

    public final void zzav(Map<String, zzfam> map) {
        if (map == null) {
            map = new HashMap<>();
        }
        this.zzosv = map;
    }

    public final Map<String, zzfam> zzcnn() {
        return this.zzosv;
    }

    public final long zzcno() {
        return this.zzosu;
    }

    public final long zzcnp() {
        return this.zzosl;
    }

    public final void zzco(long j) {
        this.zzosu = j;
    }

    public final void zzcp(long j) {
        this.zzosl = j;
    }

    public final void zzdd(boolean z) {
        this.zzosi = z;
    }

    public final void zziy(int i) {
        this.zzosr = i;
    }

    public final void zzsl(String str) {
        if (this.zzosv.get(str) != null) {
            this.zzosv.remove(str);
        }
    }
}
