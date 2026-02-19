package com.google.firebase.database;

import com.google.android.gms.internal.zzegg;
import com.google.android.gms.internal.zzegr;
import com.google.android.gms.internal.zzegu;
import com.google.android.gms.internal.zzegx;
import com.google.android.gms.internal.zzejp;
import com.google.android.gms.internal.zzeju;
import com.google.android.gms.internal.zzelr;
import com.google.android.gms.internal.zzelu;
import com.google.android.gms.internal.zzemp;
import com.google.android.gms.internal.zzemq;
import com.google.android.gms.internal.zzend;
import com.google.android.gms.internal.zzene;
import com.google.android.gms.internal.zzenh;
import com.google.android.gms.internal.zzenn;
import com.google.android.gms.internal.zzenr;
import com.google.android.gms.internal.zzens;
import com.google.android.gms.internal.zzent;
import com.google.android.gms.internal.zzenv;
import com.google.android.gms.internal.zzenx;
import com.google.android.gms.internal.zzepd;
import com.google.android.gms.internal.zzepf;
import java.util.Objects;

public class Query {
    protected final zzegx zzmwt;
    protected final zzegu zzmxa;
    private zzelr zzmxe;
    private final boolean zzmxf;

    Query(zzegx zzegx, zzegu zzegu) {
        this.zzmwt = zzegx;
        this.zzmxa = zzegu;
        this.zzmxe = zzelr.zznmi;
        this.zzmxf = false;
    }

    private Query(zzegx zzegx, zzegu zzegu, zzelr zzelr, boolean z) throws DatabaseException {
        this.zzmwt = zzegx;
        this.zzmxa = zzegu;
        this.zzmxe = zzelr;
        this.zzmxf = z;
        zzepd.zzb(!zzelr.zzcas() || !zzelr.zzcav() || !zzelr.zzcay() || zzelr.zzcaz(), "Validation of queries failed.");
    }

    private final Query zza(zzenn zzenn, String str) {
        zzepf.zzqp(str);
        if (!zzenn.zzccd() && !zzenn.isEmpty()) {
            throw new IllegalArgumentException("Can only use simple values for startAt()");
        } else if (!this.zzmxe.zzcas()) {
            zzelr zza = this.zzmxe.zza(zzenn, str != null ? zzemq.zzqf(str) : null);
            zzb(zza);
            zza(zza);
            return new Query(this.zzmwt, this.zzmxa, zza, this.zzmxf);
        } else {
            throw new IllegalArgumentException("Can't call startAt() or equalTo() multiple times");
        }
    }

    private final void zza(zzegr zzegr) {
        zzeju.zzbzo().zzj(zzegr);
        this.zzmwt.zzp(new zzq(this, zzegr));
    }

    private static void zza(zzelr zzelr) {
        if (zzelr.zzcba().equals(zzenh.zzccu())) {
            if (zzelr.zzcas()) {
                zzenn zzcat = zzelr.zzcat();
                if (zzelr.zzcau() != zzemq.zzcbw() || !(zzcat instanceof zzenv)) {
                    throw new IllegalArgumentException("You must use startAt(String value), endAt(String value) or equalTo(String value) in combination with orderByKey(). Other type of values or using the version with 2 parameters is not supported");
                }
            }
            if (zzelr.zzcav()) {
                zzenn zzcaw = zzelr.zzcaw();
                if (zzelr.zzcax() != zzemq.zzcbx() || !(zzcaw instanceof zzenv)) {
                    throw new IllegalArgumentException("You must use startAt(String value), endAt(String value) or equalTo(String value) in combination with orderByKey(). Other type of values or using the version with 2 parameters is not supported");
                }
            }
        } else if (!zzelr.zzcba().equals(zzens.zzccy())) {
        } else {
            if ((zzelr.zzcas() && !zzent.zzl(zzelr.zzcat())) || (zzelr.zzcav() && !zzent.zzl(zzelr.zzcaw()))) {
                throw new IllegalArgumentException("When using orderByPriority(), values provided to startAt(), endAt(), or equalTo() must be valid priorities.");
            }
        }
    }

    private final Query zzb(zzenn zzenn, String str) {
        zzepf.zzqp(str);
        if (zzenn.zzccd() || zzenn.isEmpty()) {
            zzemq zzqf = str != null ? zzemq.zzqf(str) : null;
            if (!this.zzmxe.zzcav()) {
                zzelr zzb = this.zzmxe.zzb(zzenn, zzqf);
                zzb(zzb);
                zza(zzb);
                return new Query(this.zzmwt, this.zzmxa, zzb, this.zzmxf);
            }
            throw new IllegalArgumentException("Can't call endAt() or equalTo() multiple times");
        }
        throw new IllegalArgumentException("Can only use simple values for endAt()");
    }

    private final void zzb(zzegr zzegr) {
        zzeju.zzbzo().zzi(zzegr);
        this.zzmwt.zzp(new zzr(this, zzegr));
    }

    private static void zzb(zzelr zzelr) {
        if (zzelr.zzcas() && zzelr.zzcav() && zzelr.zzcay() && !zzelr.zzcaz()) {
            throw new IllegalArgumentException("Can't combine startAt(), endAt() and limit(). Use limitToFirst() or limitToLast() instead");
        }
    }

    private final void zzbvf() {
        if (this.zzmxe.zzcas()) {
            throw new IllegalArgumentException("Can't call equalTo() and startAt() combined");
        } else if (this.zzmxe.zzcav()) {
            throw new IllegalArgumentException("Can't call equalTo() and endAt() combined");
        }
    }

    private final void zzbvg() {
        if (this.zzmxf) {
            throw new IllegalArgumentException("You can't combine multiple orderBy calls!");
        }
    }

    public ChildEventListener addChildEventListener(ChildEventListener childEventListener) {
        zzb((zzegr) new zzegg(this.zzmwt, childEventListener, zzbvi()));
        return childEventListener;
    }

    public void addListenerForSingleValueEvent(ValueEventListener valueEventListener) {
        zzb((zzegr) new zzejp(this.zzmwt, new zzp(this, valueEventListener), zzbvi()));
    }

    public ValueEventListener addValueEventListener(ValueEventListener valueEventListener) {
        zzb((zzegr) new zzejp(this.zzmwt, valueEventListener, zzbvi()));
        return valueEventListener;
    }

    public Query endAt(double d) {
        return endAt(d, (String) null);
    }

    public Query endAt(double d, String str) {
        return zzb(new zzend(Double.valueOf(d), zzene.zzcco()), str);
    }

    public Query endAt(String str) {
        return endAt(str, (String) null);
    }

    public Query endAt(String str, String str2) {
        return zzb(str != null ? new zzenv(str, zzene.zzcco()) : zzene.zzcco(), str2);
    }

    public Query endAt(boolean z) {
        return endAt(z, (String) null);
    }

    public Query endAt(boolean z, String str) {
        return zzb(new zzemp(Boolean.valueOf(z), zzene.zzcco()), str);
    }

    public Query equalTo(double d) {
        zzbvf();
        return startAt(d).endAt(d);
    }

    public Query equalTo(double d, String str) {
        zzbvf();
        return startAt(d, str).endAt(d, str);
    }

    public Query equalTo(String str) {
        zzbvf();
        return startAt(str).endAt(str);
    }

    public Query equalTo(String str, String str2) {
        zzbvf();
        return startAt(str, str2).endAt(str, str2);
    }

    public Query equalTo(boolean z) {
        zzbvf();
        return startAt(z).endAt(z);
    }

    public Query equalTo(boolean z, String str) {
        zzbvf();
        return startAt(z, str).endAt(z, str);
    }

    public DatabaseReference getRef() {
        return new DatabaseReference(this.zzmwt, this.zzmxa);
    }

    public void keepSynced(boolean z) {
        if (this.zzmxa.isEmpty() || !this.zzmxa.zzbyq().equals(zzemq.zzcbz())) {
            this.zzmwt.zzp(new zzs(this, z));
            return;
        }
        throw new DatabaseException("Can't call keepSynced() on .info paths.");
    }

    public Query limitToFirst(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("Limit must be a positive integer!");
        } else if (!this.zzmxe.zzcay()) {
            return new Query(this.zzmwt, this.zzmxa, this.zzmxe.zzhi(i), this.zzmxf);
        } else {
            throw new IllegalArgumentException("Can't call limitToLast on query with previously set limit!");
        }
    }

    public Query limitToLast(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("Limit must be a positive integer!");
        } else if (!this.zzmxe.zzcay()) {
            return new Query(this.zzmwt, this.zzmxa, this.zzmxe.zzhj(i), this.zzmxf);
        } else {
            throw new IllegalArgumentException("Can't call limitToLast on query with previously set limit!");
        }
    }

    public Query orderByChild(String str) {
        Objects.requireNonNull(str, "Key can't be null");
        if (str.equals("$key") || str.equals(".key")) {
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 54);
            sb.append("Can't use '");
            sb.append(str);
            sb.append("' as path, please use orderByKey() instead!");
            throw new IllegalArgumentException(sb.toString());
        } else if (str.equals("$priority") || str.equals(".priority")) {
            StringBuilder sb2 = new StringBuilder(String.valueOf(str).length() + 59);
            sb2.append("Can't use '");
            sb2.append(str);
            sb2.append("' as path, please use orderByPriority() instead!");
            throw new IllegalArgumentException(sb2.toString());
        } else if (str.equals("$value") || str.equals(".value")) {
            StringBuilder sb3 = new StringBuilder(String.valueOf(str).length() + 56);
            sb3.append("Can't use '");
            sb3.append(str);
            sb3.append("' as path, please use orderByValue() instead!");
            throw new IllegalArgumentException(sb3.toString());
        } else {
            zzepf.zzqn(str);
            zzbvg();
            zzegu zzegu = new zzegu(str);
            if (zzegu.size() != 0) {
                return new Query(this.zzmwt, this.zzmxa, this.zzmxe.zza(new zzenr(zzegu)), true);
            }
            throw new IllegalArgumentException("Can't use empty path, use orderByValue() instead!");
        }
    }

    public Query orderByKey() {
        zzbvg();
        zzelr zza = this.zzmxe.zza(zzenh.zzccu());
        zza(zza);
        return new Query(this.zzmwt, this.zzmxa, zza, true);
    }

    public Query orderByPriority() {
        zzbvg();
        zzelr zza = this.zzmxe.zza(zzens.zzccy());
        zza(zza);
        return new Query(this.zzmwt, this.zzmxa, zza, true);
    }

    public Query orderByValue() {
        zzbvg();
        return new Query(this.zzmwt, this.zzmxa, this.zzmxe.zza(zzenx.zzccz()), true);
    }

    public void removeEventListener(ChildEventListener childEventListener) {
        Objects.requireNonNull(childEventListener, "listener must not be null");
        zza((zzegr) new zzegg(this.zzmwt, childEventListener, zzbvi()));
    }

    public void removeEventListener(ValueEventListener valueEventListener) {
        Objects.requireNonNull(valueEventListener, "listener must not be null");
        zza((zzegr) new zzejp(this.zzmwt, valueEventListener, zzbvi()));
    }

    public Query startAt(double d) {
        return startAt(d, (String) null);
    }

    public Query startAt(double d, String str) {
        return zza(new zzend(Double.valueOf(d), zzene.zzcco()), str);
    }

    public Query startAt(String str) {
        return startAt(str, (String) null);
    }

    public Query startAt(String str, String str2) {
        return zza(str != null ? new zzenv(str, zzene.zzcco()) : zzene.zzcco(), str2);
    }

    public Query startAt(boolean z) {
        return startAt(z, (String) null);
    }

    public Query startAt(boolean z, String str) {
        return zza(new zzemp(Boolean.valueOf(z), zzene.zzcco()), str);
    }

    public final zzegu zzbvh() {
        return this.zzmxa;
    }

    public final zzelu zzbvi() {
        return new zzelu(this.zzmxa, this.zzmxe);
    }
}
