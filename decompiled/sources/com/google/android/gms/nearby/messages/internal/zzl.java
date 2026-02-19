package com.google.android.gms.nearby.messages.internal;

import com.google.android.gms.common.internal.zzbq;
import java.nio.ByteBuffer;
import java.util.UUID;

public final class zzl extends zzc {
    public zzl(java.util.UUID r5, java.lang.Short r6, java.lang.Short r7) {
        this(zza(r5, r6, r7));
    }

    private static byte[] zza(java.util.UUID uuid, java.lang.Short major, java.lang.Short minor) {
        int length = 16 + (major != null ? 2 : 0) + (minor != null ? 2 : 0);
        ByteBuffer buffer = ByteBuffer.allocate(length);
        buffer.putLong(uuid.getMostSignificantBits());
        buffer.putLong(uuid.getLeastSignificantBits());
        if (major != null) {
            buffer.putShort(major.shortValue());
        }
        if (minor != null) {
            buffer.putShort(minor.shortValue());
        }
        return buffer.array();
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public zzl(byte[] bArr) {
        super(bArr);
        zzbq.checkArgument(bArr.length == 16 || bArr.length == 18 || bArr.length == 20, "Prefix must be a UUID, a UUID and a major, or a UUID, a major, and a minor.");
    }

    public final UUID getProximityUuid() {
        ByteBuffer wrap = ByteBuffer.wrap(getBytes());
        return new UUID(wrap.getLong(), wrap.getLong());
    }

    public final String toString() {
        String valueOf = String.valueOf(getProximityUuid());
        String valueOf2 = String.valueOf(zzbdv());
        String valueOf3 = String.valueOf(zzbdw());
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 47 + String.valueOf(valueOf2).length() + String.valueOf(valueOf3).length());
        sb.append("IBeaconIdPrefix{proximityUuid=");
        sb.append(valueOf);
        sb.append(", major=");
        sb.append(valueOf2);
        sb.append(", minor=");
        sb.append(valueOf3);
        sb.append('}');
        return sb.toString();
    }

    public final Short zzbdv() {
        byte[] bytes = getBytes();
        if (bytes.length >= 18) {
            return Short.valueOf(ByteBuffer.wrap(bytes).getShort(16));
        }
        return null;
    }

    public final Short zzbdw() {
        byte[] bytes = getBytes();
        if (bytes.length == 20) {
            return Short.valueOf(ByteBuffer.wrap(bytes).getShort(18));
        }
        return null;
    }
}
