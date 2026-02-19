package com.google.android.gms.nearby.messages;

import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.nearby.messages.internal.zzc;
import com.google.android.gms.nearby.messages.internal.zzg;
import java.util.Arrays;

public class EddystoneUid {
    public static final int INSTANCE_LENGTH = 6;
    public static final int LENGTH = 16;
    public static final int NAMESPACE_LENGTH = 10;
    private final zzg zzkai;

    public EddystoneUid(String str) {
        this(zzc.zzky(str));
    }

    public EddystoneUid(String str, String str2) {
        this.zzkai = new zzg(str, str2);
    }

    private EddystoneUid(byte[] bArr) {
        zzbq.checkArgument(bArr.length == 16, "Bytes must be a namespace plus instance (16 bytes).");
        this.zzkai = new zzg(bArr);
    }

    public static EddystoneUid from(Message message) {
        boolean zzkx = message.zzkx(Message.MESSAGE_TYPE_EDDYSTONE_UID);
        String type = message.getType();
        StringBuilder sb = new StringBuilder(String.valueOf(type).length() + 58);
        sb.append("Message type '");
        sb.append(type);
        sb.append("' is not Message.MESSAGE_TYPE_EDDYSTONE_UID.");
        zzbq.checkArgument(zzkx, sb.toString());
        return new EddystoneUid(message.getContent());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EddystoneUid)) {
            return false;
        }
        return zzbg.equal(this.zzkai, ((EddystoneUid) obj).zzkai);
    }

    public String getHex() {
        return this.zzkai.getHex();
    }

    public String getInstance() {
        byte[] bytes = this.zzkai.getBytes();
        if (bytes.length < 16) {
            return null;
        }
        return zzc.zzw(Arrays.copyOfRange(bytes, 10, 16));
    }

    public String getNamespace() {
        return zzc.zzw(Arrays.copyOfRange(this.zzkai.getBytes(), 0, 10));
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzkai});
    }

    public String toString() {
        String hex = getHex();
        StringBuilder sb = new StringBuilder(String.valueOf(hex).length() + 17);
        sb.append("EddystoneUid{id=");
        sb.append(hex);
        sb.append('}');
        return sb.toString();
    }
}
