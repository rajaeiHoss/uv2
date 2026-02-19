package com.google.android.gms.internal;

import java.util.Collections;
import java.util.Iterator;

public final class zzene extends zzems implements zzenn {
    private static final zzene zznos = new zzene();

    private zzene() {
    }

    public static zzene zzcco() {
        return zznos;
    }

    public final int compareTo(zzenn zzenn) {
        return super.compareTo(zzenn);
    }

    public final boolean equals(Object obj) {
        if (obj instanceof zzene) {
            return true;
        }
        if (!(obj instanceof zzenn)) {
            return false;
        }
        zzenn zzenn = (zzenn) obj;
        return zzenn.isEmpty() && equals(zzenn.zzcce());
    }

    public final int getChildCount() {
        return 0;
    }

    public final Object getValue() {
        return null;
    }

    public final Object getValue(boolean z) {
        return null;
    }

    public final int hashCode() {
        return 0;
    }

    public final boolean isEmpty() {
        return true;
    }

    public final Iterator<zzenm> iterator() {
        return Collections.<zzenm>emptyList().iterator();
    }

    public final String toString() {
        return "<Empty Node>";
    }

    public final String zza(zzenp zzenp) {
        return "";
    }

    public final zzenn zzan(zzegu zzegu) {
        return this;
    }

    public final Iterator<zzenm> zzbvr() {
        return Collections.<zzenm>emptyList().iterator();
    }

    public final String zzccc() {
        return "";
    }

    public final boolean zzccd() {
        return false;
    }

    public final zzenn zzcce() {
        return this;
    }

    public final zzenn zze(zzemq zzemq, zzenn zzenn) {
        return (!zzenn.isEmpty() && !zzemq.zzcca()) ? new zzems().zze(zzemq, zzenn) : this;
    }

    public final /* bridge */ /* synthetic */ zzenn zzf(zzenn zzenn) {
        return this;
    }

    public final int zzg(zzenn zzenn) {
        return zzenn.isEmpty() ? 0 : -1;
    }

    public final boolean zzk(zzemq zzemq) {
        return false;
    }

    public final zzemq zzl(zzemq zzemq) {
        return null;
    }

    public final zzenn zzl(zzegu zzegu, zzenn zzenn) {
        return zzegu.isEmpty() ? zzenn : zze(zzegu.zzbyq(), zzl(zzegu.zzbyr(), zzenn));
    }

    public final zzenn zzm(zzemq zzemq) {
        return this;
    }
}
