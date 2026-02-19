package com.google.android.gms.nearby.messages;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzcuu;
import com.google.android.gms.internal.zzcuz;
import com.google.android.gms.nearby.messages.internal.zzad;
import com.streamax.config.constant.Constants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class MessageFilter extends zzbgl {
    public static final Parcelable.Creator<MessageFilter> CREATOR = new zzc();
    public static final MessageFilter INCLUDE_ALL_MY_TYPES = new Builder().includeAllMyTypes().build();
    private int zzehz;
    private final List<zzad> zzkao;
    private final List<zzcuz> zzkap;
    private final boolean zzkaq;
    private final List<zzcuu> zzkar;
    private final int zzkas;

    public static final class Builder {
        private final List<zzcuz> zzkap = new ArrayList();
        private boolean zzkaq;
        private int zzkas = 0;
        private final Set<zzad> zzkat = new HashSet();
        private final Set<zzcuu> zzkau = new HashSet();

        private final Builder zzat(String str, String str2) {
            this.zzkat.add(new zzad(str, str2));
            return this;
        }

        public final MessageFilter build() {
            zzbq.zza(this.zzkaq || !this.zzkat.isEmpty(), (Object) "At least one of the include methods must be called.");
            return new MessageFilter((List) new ArrayList(this.zzkat), (List) this.zzkap, this.zzkaq, (List) new ArrayList(this.zzkau), this.zzkas);
        }

        public final Builder includeAllMyTypes() {
            this.zzkaq = true;
            return this;
        }

        public final Builder includeAudioBytes(int i) {
            boolean z = true;
            zzbq.checkArgument(this.zzkas == 0, "includeAudioBytes() can only be called once per MessageFilter instance.");
            boolean z2 = i > 0;
            StringBuilder sb = new StringBuilder(44);
            sb.append("Invalid value for numAudioBytes: ");
            sb.append(i);
            zzbq.checkArgument(z2, sb.toString());
            if (i > 10) {
                z = false;
            }
            zzbq.checkArgument(z, "numAudioBytes is capped by AudioBytes.MAX_SIZE = 10");
            zzat(Message.MESSAGE_NAMESPACE_RESERVED, Message.MESSAGE_TYPE_AUDIO_BYTES);
            this.zzkas = i;
            return this;
        }

        public final Builder includeEddystoneUids(String str, String str2) {
            zzat(Message.MESSAGE_NAMESPACE_RESERVED, Message.MESSAGE_TYPE_EDDYSTONE_UID);
            this.zzkap.add(zzcuz.zzau(str, str2));
            return this;
        }

        public final Builder includeFilter(MessageFilter messageFilter) {
            this.zzkat.addAll(messageFilter.zzbdq());
            this.zzkap.addAll(messageFilter.zzbds());
            this.zzkau.addAll(messageFilter.zzbdt());
            this.zzkaq = messageFilter.zzbdr() | this.zzkaq;
            return this;
        }

        public final Builder includeIBeaconIds(UUID uuid, Short sh, Short sh2) {
            zzat(Message.MESSAGE_NAMESPACE_RESERVED, Message.MESSAGE_TYPE_I_BEACON_ID);
            this.zzkap.add(zzcuz.zza(uuid, sh, sh2));
            return this;
        }

        public final Builder includeNamespacedType(String str, String str2) {
            zzbq.zzb(str != null && !str.isEmpty() && !str.contains("*"), "namespace(%s) cannot be null, empty or contain (*).", str);
            zzbq.zzb(str2 != null && !str2.contains("*"), "type(%s) cannot be null or contain (*).", str2);
            return zzat(str, str2);
        }
    }

    MessageFilter(int i, List<zzad> list, List<zzcuz> list2, boolean z, List<zzcuu> list3, int i2) {
        this.zzehz = i;
        this.zzkao = Collections.unmodifiableList((List) zzbq.checkNotNull(list));
        this.zzkaq = z;
        this.zzkap = Collections.unmodifiableList(list2 == null ? Collections.emptyList() : list2);
        this.zzkar = Collections.unmodifiableList(list3 == null ? Collections.emptyList() : list3);
        this.zzkas = i2;
    }

    private MessageFilter(List<zzad> list, List<zzcuz> list2, boolean z, List<zzcuu> list3, int i) {
        this(2, list, list2, z, list3, i);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MessageFilter)) {
            return false;
        }
        MessageFilter messageFilter = (MessageFilter) obj;
        return this.zzkaq == messageFilter.zzkaq && zzbg.equal(this.zzkao, messageFilter.zzkao) && zzbg.equal(this.zzkap, messageFilter.zzkap) && zzbg.equal(this.zzkar, messageFilter.zzkar);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzkao, this.zzkap, Boolean.valueOf(this.zzkaq), this.zzkar});
    }

    public String toString() {
        boolean z = this.zzkaq;
        String valueOf = String.valueOf(this.zzkao);
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 53);
        sb.append("MessageFilter{includeAllMyTypes=");
        sb.append(z);
        sb.append(", messageTypes=");
        sb.append(valueOf);
        sb.append(Constants.JsonSstringSuffix);
        return sb.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zzc(parcel, 1, this.zzkao, false);
        zzbgo.zzc(parcel, 2, this.zzkap, false);
        zzbgo.zza(parcel, 3, this.zzkaq);
        zzbgo.zzc(parcel, 4, this.zzkar, false);
        zzbgo.zzc(parcel, 5, this.zzkas);
        zzbgo.zzc(parcel, 1000, this.zzehz);
        zzbgo.zzai(parcel, zze);
    }

    public final List<zzad> zzbdq() {
        return this.zzkao;
    }

    public final boolean zzbdr() {
        return this.zzkaq;
    }

    /* access modifiers changed from: package-private */
    public final List<zzcuz> zzbds() {
        return this.zzkap;
    }

    public final List<zzcuu> zzbdt() {
        return this.zzkar;
    }
}
