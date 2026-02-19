package com.google.android.gms.internal;

import android.os.Parcel;
import android.util.Base64;
import com.google.android.gms.ads.internal.zzbt;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@zzabh
final class zzuu {
    final String zzapp;
    final zzkk zzarx;
    final int zzcee;

    private zzuu(zzkk zzkk, String str, int i) {
        this.zzarx = zzkk;
        this.zzapp = str;
        this.zzcee = i;
    }

    zzuu(zzuq zzuq) {
        this(zzuq.zzli(), zzuq.getAdUnitId(), zzuq.getNetworkType());
    }

    static zzuu zzba(String str) throws IOException {
        String[] split = str.split("\u0000");
        if (split.length == 3) {
            Parcel obtain = Parcel.obtain();
            try {
                String str2 = new String(Base64.decode(split[0], 0), "UTF-8");
                int parseInt = Integer.parseInt(split[1]);
                byte[] decode = Base64.decode(split[2], 0);
                obtain.unmarshall(decode, 0, decode.length);
                obtain.setDataPosition(0);
                zzuu zzuu = new zzuu(zzkk.CREATOR.createFromParcel(obtain), str2, parseInt);
                obtain.recycle();
                return zzuu;
            } catch (zzbgn | IllegalArgumentException | IllegalStateException e) {
                zzbt.zzep().zza(e, "QueueSeed.decode");
                throw new IOException("Malformed QueueSeed encoding.", e);
            } catch (Throwable th) {
                obtain.recycle();
                throw th;
            }
        } else {
            throw new IOException("Incorrect field count for QueueSeed.");
        }
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: package-private */
    public final String zzlw() {
        Parcel obtain = Parcel.obtain();
        try {
            String encodeToString = Base64.encodeToString(this.zzapp.getBytes("UTF-8"), 0);
            String num = Integer.toString(this.zzcee);
            this.zzarx.writeToParcel(obtain, 0);
            String encodeToString2 = Base64.encodeToString(obtain.marshall(), 0);
            StringBuilder sb = new StringBuilder(String.valueOf(encodeToString).length() + 2 + String.valueOf(num).length() + String.valueOf(encodeToString2).length());
            sb.append(encodeToString);
            sb.append("\u0000");
            sb.append(num);
            sb.append("\u0000");
            sb.append(encodeToString2);
            String sb2 = sb.toString();
            obtain.recycle();
            return sb2;
        } catch (UnsupportedEncodingException unused) {
            zzahw.e("QueueSeed encode failed because UTF-8 is not available.");
            obtain.recycle();
            return "";
        } catch (Throwable th) {
            obtain.recycle();
            throw th;
        }
    }
}
