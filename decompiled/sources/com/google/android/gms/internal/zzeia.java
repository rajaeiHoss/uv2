package com.google.android.gms.internal;

public final class zzeia {
    public String host;
    public boolean secure;
    public String zzkal;
    public String zznhd;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        zzeia zzeia = (zzeia) obj;
        if (this.secure == zzeia.secure && this.host.equals(zzeia.host)) {
            return this.zzkal.equals(zzeia.zzkal);
        }
        return false;
    }

    public final int hashCode() {
        return (((this.host.hashCode() * 31) + (this.secure ? 1 : 0)) * 31) + this.zzkal.hashCode();
    }

    public final String toString() {
        String str = this.secure ? "s" : "";
        String str2 = this.host;
        StringBuilder sb = new StringBuilder(str.length() + 7 + String.valueOf(str2).length());
        sb.append("http");
        sb.append(str);
        sb.append("://");
        sb.append(str2);
        return sb.toString();
    }
}
