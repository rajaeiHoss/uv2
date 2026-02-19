package com.google.firebase.database;

import com.google.android.gms.internal.zzegu;
import com.google.android.gms.internal.zzeik;
import com.google.android.gms.internal.zzejo;
import com.google.android.gms.internal.zzemq;
import com.google.android.gms.internal.zzene;
import com.google.android.gms.internal.zzeng;
import com.google.android.gms.internal.zzenn;
import com.google.android.gms.internal.zzenq;
import com.google.android.gms.internal.zzent;
import com.google.android.gms.internal.zzepf;
import com.google.android.gms.internal.zzepg;

public class MutableData {
    /* access modifiers changed from: private */
    public final zzeik zzmww;
    /* access modifiers changed from: private */
    public final zzegu zzmwx;

    private MutableData(zzeik zzeik, zzegu zzegu) {
        this.zzmww = zzeik;
        this.zzmwx = zzegu;
        zzejo.zza(zzegu, getValue());
    }

    /* synthetic */ MutableData(zzeik zzeik, zzegu zzegu, zzi zzi) {
        this(zzeik, zzegu);
    }

    MutableData(zzenn zzenn) {
        this(new zzeik(zzenn), new zzegu(""));
    }

    public MutableData child(String str) {
        zzepf.zzqn(str);
        return new MutableData(this.zzmww, this.zzmwx.zzh(new zzegu(str)));
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof MutableData)) {
            return false;
        }
        MutableData mutableData = (MutableData) obj;
        return this.zzmww.equals(mutableData.zzmww) && this.zzmwx.equals(mutableData.zzmwx);
    }

    public Iterable<MutableData> getChildren() {
        zzenn zzbve = zzbve();
        return (zzbve.isEmpty() || zzbve.zzccd()) ? new zzi(this) : new zzk(this, zzeng.zzj(zzbve).iterator());
    }

    public long getChildrenCount() {
        return (long) zzbve().getChildCount();
    }

    public String getKey() {
        if (this.zzmwx.zzbyt() != null) {
            return this.zzmwx.zzbyt().asString();
        }
        return null;
    }

    public Object getPriority() {
        return zzbve().zzcce().getValue();
    }

    public Object getValue() {
        return zzbve().getValue();
    }

    public <T> T getValue(GenericTypeIndicator<T> genericTypeIndicator) {
        return zzepg.zza(zzbve().getValue(), genericTypeIndicator);
    }

    public <T> T getValue(Class<T> cls) {
        return zzepg.zza(zzbve().getValue(), cls);
    }

    public boolean hasChild(String str) {
        return !zzbve().zzan(new zzegu(str)).isEmpty();
    }

    public boolean hasChildren() {
        zzenn zzbve = zzbve();
        return !zzbve.zzccd() && !zzbve.isEmpty();
    }

    public void setPriority(Object obj) {
        this.zzmww.zzg(this.zzmwx, zzbve().zzf(zzent.zzc(this.zzmwx, obj)));
    }

    public void setValue(Object obj) throws DatabaseException {
        zzejo.zza(this.zzmwx, obj);
        Object zzca = zzepg.zzca(obj);
        zzepf.zzbz(zzca);
        this.zzmww.zzg(this.zzmwx, zzenq.zza(zzca, zzene.zzcco()));
    }

    public String toString() {
        zzemq zzbyq = this.zzmwx.zzbyq();
        String asString = zzbyq != null ? zzbyq.asString() : "<none>";
        String valueOf = String.valueOf(this.zzmww.zzbza().getValue(true));
        StringBuilder sb = new StringBuilder(String.valueOf(asString).length() + 32 + String.valueOf(valueOf).length());
        sb.append("MutableData { key = ");
        sb.append(asString);
        sb.append(", value = ");
        sb.append(valueOf);
        sb.append(" }");
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public final zzenn zzbve() {
        return this.zzmww.zzp(this.zzmwx);
    }
}
