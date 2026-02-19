package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

public final class PasswordSpecification extends zzbgl implements ReflectedParcelable {
    public static final Parcelable.Creator<PasswordSpecification> CREATOR = new zzj();
    public static final PasswordSpecification zzeli = new zza().zzj(12, 16).zzey("abcdefghijkmnopqrstxyzABCDEFGHJKLMNPQRSTXY3456789").zze("abcdefghijkmnopqrstxyz", 1).zze("ABCDEFGHJKLMNPQRSTXY", 1).zze("3456789", 1).zzaca();
    private static PasswordSpecification zzelj = new zza().zzj(12, 16).zzey("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890").zze("abcdefghijklmnopqrstuvwxyz", 1).zze("ABCDEFGHIJKLMNOPQRSTUVWXYZ", 1).zze("1234567890", 1).zzaca();
    private final Random zzbje;
    private String zzelk;
    private List<String> zzell;
    private List<Integer> zzelm;
    private int zzeln;
    private int zzelo;
    private final int[] zzelp;

    public static class zza {
        private final List<String> zzell = new ArrayList();
        private final List<Integer> zzelm = new ArrayList();
        private int zzeln = 12;
        private int zzelo = 16;
        private final TreeSet<Character> zzelq = new TreeSet<>();

        private static TreeSet<Character> zzn(String str, String str2) {
            if (!TextUtils.isEmpty(str)) {
                TreeSet<Character> treeSet = new TreeSet<>();
                char[] charArray = str.toCharArray();
                int length = charArray.length;
                int i = 0;
                while (i < length) {
                    char c = charArray[i];
                    if (!PasswordSpecification.zzc(c, 32, 126)) {
                        treeSet.add(Character.valueOf(c));
                        i++;
                    } else {
                        throw new zzb(String.valueOf(str2).concat(" must only contain ASCII printable characters"));
                    }
                }
                return treeSet;
            }
            throw new zzb(String.valueOf(str2).concat(" cannot be null or empty"));
        }

        public final PasswordSpecification zzaca() {
            if (!this.zzelq.isEmpty()) {
                int i = 0;
                for (Integer intValue : this.zzelm) {
                    i += intValue.intValue();
                }
                if (i <= this.zzelo) {
                    boolean[] zArr = new boolean[95];
                    for (String charArray : this.zzell) {
                        char[] charArray2 = charArray.toCharArray();
                        int length = charArray2.length;
                        int i2 = 0;
                        while (true) {
                            if (i2 < length) {
                                char c = charArray2[i2];
                                int i3 = c - ' ';
                                if (!zArr[i3]) {
                                    zArr[i3] = true;
                                    i2++;
                                } else {
                                    StringBuilder sb = new StringBuilder(58);
                                    sb.append("character ");
                                    sb.append(c);
                                    sb.append(" occurs in more than one required character set");
                                    throw new zzb(sb.toString());
                                }
                            }
                        }
                    }
                    return new PasswordSpecification(PasswordSpecification.zzb(this.zzelq), this.zzell, this.zzelm, this.zzeln, this.zzelo);
                }
                throw new zzb("required character count cannot be greater than the max password size");
            }
            throw new zzb("no allowed characters specified");
        }

        public final zza zze(String str, int i) {
            this.zzell.add(PasswordSpecification.zzb(zzn(str, "requiredChars")));
            this.zzelm.add(1);
            return this;
        }

        public final zza zzey(String str) {
            this.zzelq.addAll(zzn(str, "allowedChars"));
            return this;
        }

        public final zza zzj(int i, int i2) {
            this.zzeln = 12;
            this.zzelo = 16;
            return this;
        }
    }

    public static class zzb extends Error {
        public zzb(String str) {
            super(str);
        }
    }

    PasswordSpecification(String str, List<String> list, List<Integer> list2, int i, int i2) {
        this.zzelk = str;
        this.zzell = Collections.unmodifiableList(list);
        this.zzelm = Collections.unmodifiableList(list2);
        this.zzeln = i;
        this.zzelo = i2;
        int[] iArr = new int[95];
        Arrays.fill(iArr, -1);
        int i3 = 0;
        for (String charArray : this.zzell) {
            char[] charArray2 = charArray.toCharArray();
            int length = charArray2.length;
            for (int i4 = 0; i4 < length; i4++) {
                iArr[charArray2[i4] - ' '] = i3;
            }
            i3++;
        }
        this.zzelp = iArr;
        this.zzbje = new SecureRandom();
    }

    /* access modifiers changed from: private */
    public static String zzb(Collection<Character> collection) {
        char[] cArr = new char[collection.size()];
        int i = 0;
        for (Character charValue : collection) {
            cArr[i] = charValue.charValue();
            i++;
        }
        return new String(cArr);
    }

    /* access modifiers changed from: private */
    public static boolean zzc(int i, int i2, int i3) {
        return i < 32 || i > 126;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, this.zzelk, false);
        zzbgo.zzb(parcel, 2, this.zzell, false);
        zzbgo.zza(parcel, 3, this.zzelm, false);
        zzbgo.zzc(parcel, 4, this.zzeln);
        zzbgo.zzc(parcel, 5, this.zzelo);
        zzbgo.zzai(parcel, zze);
    }
}
