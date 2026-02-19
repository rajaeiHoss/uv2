package com.google.android.gms.nearby.messages;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzcux;
import java.util.Arrays;

public class Message extends zzbgl implements ReflectedParcelable {
    public static final Parcelable.Creator<Message> CREATOR = new zza();
    public static final int MAX_CONTENT_SIZE_BYTES = 102400;
    public static final int MAX_TYPE_LENGTH = 32;
    public static final String MESSAGE_NAMESPACE_RESERVED = "__reserved_namespace";
    public static final String MESSAGE_TYPE_AUDIO_BYTES = "__audio_bytes";
    public static final String MESSAGE_TYPE_EDDYSTONE_UID = "__eddystone_uid";
    public static final String MESSAGE_TYPE_I_BEACON_ID = "__i_beacon_id";
    private static final zzcux[] zzkak = {zzcux.zzkck};
    private final byte[] content;
    private final String type;
    private int versionCode;
    private final String zzkal;
    @Deprecated
    private zzcux[] zzkam;
    private final long zzkan;

    Message(int i, byte[] bArr, String str, String str2, zzcux[] zzcuxArr, long j) {
        this.versionCode = i;
        this.type = (String) zzbq.checkNotNull(str2);
        this.zzkal = str == null ? "" : str;
        this.zzkan = j;
        zzbq.checkNotNull(bArr);
        zzbq.zzb(bArr.length <= 102400, "Content length(%d) must not exceed MAX_CONTENT_SIZE_BYTES(%d)", Integer.valueOf(bArr.length), Integer.valueOf(MAX_CONTENT_SIZE_BYTES));
        this.content = bArr;
        this.zzkam = (zzcuxArr == null || zzcuxArr.length == 0) ? zzkak : zzcuxArr;
        zzbq.zzb(str2.length() <= 32, "Type length(%d) must not exceed MAX_TYPE_LENGTH(%d)", Integer.valueOf(str2.length()), 32);
    }

    public Message(byte[] bArr) {
        this(bArr, "", "");
    }

    public Message(byte[] bArr, String str) {
        this(bArr, "", str);
    }

    public Message(byte[] bArr, String str, String str2) {
        this(bArr, str, str2, zzkak);
    }

    private Message(byte[] bArr, String str, String str2, zzcux[] zzcuxArr) {
        this(bArr, str, str2, zzcuxArr, 0);
    }

    private Message(byte[] bArr, String str, String str2, zzcux[] zzcuxArr, long j) {
        this(2, bArr, str, str2, zzcuxArr, 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Message)) {
            return false;
        }
        Message message = (Message) obj;
        return TextUtils.equals(this.zzkal, message.zzkal) && TextUtils.equals(this.type, message.type) && Arrays.equals(this.content, message.content) && this.zzkan == message.zzkan;
    }

    public byte[] getContent() {
        return this.content;
    }

    public String getNamespace() {
        return this.zzkal;
    }

    public String getType() {
        return this.type;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzkal, this.type, Integer.valueOf(Arrays.hashCode(this.content)), Long.valueOf(this.zzkan)});
    }

    public String toString() {
        String str = this.zzkal;
        String str2 = this.type;
        byte[] bArr = this.content;
        int length = bArr == null ? 0 : bArr.length;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 59 + String.valueOf(str2).length());
        sb.append("Message{namespace='");
        sb.append(str);
        sb.append("', type='");
        sb.append(str2);
        sb.append("', content=[");
        sb.append(length);
        sb.append(" bytes]}");
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, getContent(), false);
        zzbgo.zza(parcel, 2, getType(), false);
        zzbgo.zza(parcel, 3, getNamespace(), false);
        zzbgo.zza(parcel, 4, this.zzkam, i, false);
        zzbgo.zza(parcel, 5, this.zzkan);
        zzbgo.zzc(parcel, 1000, this.versionCode);
        zzbgo.zzai(parcel, zze);
    }

    public final boolean zzkx(String str) {
        return MESSAGE_NAMESPACE_RESERVED.equals(getNamespace()) && str.equals(getType());
    }
}
