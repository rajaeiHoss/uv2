package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.List;

public final class zzauk {
    private final String name;
    private int weight = 1;
    private String zzefx;
    private boolean zzefy;
    private boolean zzefz;
    private String zzegc;
    private final List<zzaue> zzege = new ArrayList();

    public zzauk(String str) {
        this.name = str;
    }

    public final zzauj zzabq() {
        String str = this.name;
        String str2 = this.zzefx;
        boolean z = this.zzefy;
        int i = this.weight;
        boolean z2 = this.zzefz;
        List<zzaue> list = this.zzege;
        return new zzauj(str, str2, z, i, z2, (String) null, (zzaue[]) list.toArray(new zzaue[list.size()]), this.zzegc, (zzaum) null);
    }

    public final zzauk zzaq(boolean z) {
        this.zzefy = true;
        return this;
    }

    public final zzauk zzar(boolean z) {
        this.zzefz = true;
        return this;
    }

    public final zzauk zzeu(String str) {
        this.zzefx = str;
        return this;
    }

    public final zzauk zzev(String str) {
        this.zzegc = str;
        return this;
    }
}
