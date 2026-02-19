package com.google.android.gms.internal;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigValue;
import java.nio.charset.Charset;
import java.util.regex.Pattern;

public final class zzfar implements FirebaseRemoteConfigValue {
    public static final Charset UTF_8 = Charset.forName("UTF-8");
    public static final Pattern zzgmb = Pattern.compile("^(1|true|t|yes|y|on)$", 2);
    public static final Pattern zzgmc = Pattern.compile("^(0|false|f|no|n|off|)$", 2);
    private final int zzbkq;
    private final byte[] zzost;

    public zzfar(byte[] bArr, int i) {
        this.zzost = bArr;
        this.zzbkq = i;
    }

    public final boolean asBoolean() throws IllegalArgumentException {
        if (this.zzbkq == 0) {
            return false;
        }
        String asString = asString();
        if (zzgmb.matcher(asString).matches()) {
            return true;
        }
        if (zzgmc.matcher(asString).matches()) {
            return false;
        }
        StringBuilder sb = new StringBuilder(String.valueOf(asString).length() + 45);
        sb.append("[Value: ");
        sb.append(asString);
        sb.append("] cannot be interpreted as a boolean.");
        throw new IllegalArgumentException(sb.toString());
    }

    public final byte[] asByteArray() {
        return this.zzbkq == 0 ? FirebaseRemoteConfig.DEFAULT_VALUE_FOR_BYTE_ARRAY : this.zzost;
    }

    public final double asDouble() {
        if (this.zzbkq == 0) {
            return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        }
        String asString = asString();
        try {
            return Double.valueOf(asString).doubleValue();
        } catch (NumberFormatException e) {
            StringBuilder sb = new StringBuilder(String.valueOf(asString).length() + 42);
            sb.append("[Value: ");
            sb.append(asString);
            sb.append("] cannot be converted to a double.");
            throw new IllegalArgumentException(sb.toString(), e);
        }
    }

    public final long asLong() {
        if (this.zzbkq == 0) {
            return 0;
        }
        String asString = asString();
        try {
            return Long.valueOf(asString).longValue();
        } catch (NumberFormatException e) {
            StringBuilder sb = new StringBuilder(String.valueOf(asString).length() + 40);
            sb.append("[Value: ");
            sb.append(asString);
            sb.append("] cannot be converted to a long.");
            throw new IllegalArgumentException(sb.toString(), e);
        }
    }

    public final String asString() {
        if (this.zzbkq == 0) {
            return "";
        }
        if (this.zzost != null) {
            return new String(this.zzost, UTF_8);
        }
        throw new IllegalArgumentException("Value is null, and cannot be converted to the desired type.");
    }

    public final int getSource() {
        return this.zzbkq;
    }
}
