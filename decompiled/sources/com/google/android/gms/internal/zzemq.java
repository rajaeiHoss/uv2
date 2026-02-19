package com.google.android.gms.internal;

public class zzemq implements Comparable<zzemq> {
    private static final zzemq zznnw = new zzemq("[MIN_KEY]");
    private static final zzemq zznnx = new zzemq("[MAX_KEY]");
    private static final zzemq zznny = new zzemq(".priority");
    private static final zzemq zznnz = new zzemq(".info");
    /* access modifiers changed from: private */
    public final String key;

    static class zza extends zzemq {
        private final int zzgin;

        zza(String str, int i) {
            super(str);
            this.zzgin = i;
        }

        /* access modifiers changed from: protected */
        public final int intValue() {
            return this.zzgin;
        }

        public final String toString() {
            String zzj = this.key;
            StringBuilder sb = new StringBuilder(String.valueOf(zzj).length() + 20);
            sb.append("IntegerChildName(\"");
            sb.append(zzj);
            sb.append("\")");
            return sb.toString();
        }

        /* access modifiers changed from: protected */
        public final boolean zzccb() {
            return true;
        }
    }

    private zzemq(String str) {
        this.key = str;
    }

    public static zzemq zzcbw() {
        return zznnw;
    }

    public static zzemq zzcbx() {
        return zznnx;
    }

    public static zzemq zzcby() {
        return zznny;
    }

    public static zzemq zzcbz() {
        return zznnz;
    }

    public static zzemq zzqf(String str) {
        Integer zzqm = zzepd.zzqm(str);
        return zzqm != null ? new zza(str, zzqm.intValue()) : str.equals(".priority") ? zznny : new zzemq(str);
    }

    public final String asString() {
        return this.key;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof zzemq)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        return this.key.equals(((zzemq) obj).key);
    }

    public int hashCode() {
        return this.key.hashCode();
    }

    /* access modifiers changed from: protected */
    public int intValue() {
        return 0;
    }

    public String toString() {
        String str = this.key;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 12);
        sb.append("ChildKey(\"");
        sb.append(str);
        sb.append("\")");
        return sb.toString();
    }

    public final boolean zzcca() {
        return this == zznny;
    }

    /* access modifiers changed from: protected */
    public boolean zzccb() {
        return false;
    }

    /* renamed from: zzi */
    public final int compareTo(zzemq zzemq) {
        zzemq zzemq2;
        if (this == zzemq) {
            return 0;
        }
        zzemq zzemq3 = zznnw;
        if (this == zzemq3 || zzemq == (zzemq2 = zznnx)) {
            return -1;
        }
        if (zzemq == zzemq3 || this == zzemq2) {
            return 1;
        }
        if (zzccb()) {
            if (!zzemq.zzccb()) {
                return -1;
            }
            int zzy = zzepd.zzy(intValue(), zzemq.intValue());
            return zzy == 0 ? zzepd.zzy(this.key.length(), zzemq.key.length()) : zzy;
        } else if (zzemq.zzccb()) {
            return 1;
        } else {
            return this.key.compareTo(zzemq.key);
        }
    }
}
