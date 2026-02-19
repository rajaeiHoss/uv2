package com.google.android.gms.common.data;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.internal.zzbgp;
import org.jivesoftware.smackx.bytestreams.ibb.packet.DataPacketExtension;

public class zzd<T extends zzbgp> extends AbstractDataBuffer<T> {
    private static final String[] zzgcj = {DataPacketExtension.ELEMENT_NAME};
    private final Parcelable.Creator<T> zzgck;

    public zzd(DataHolder dataHolder, Parcelable.Creator<T> creator) {
        super(dataHolder);
        this.zzgck = creator;
    }

    public static <T extends zzbgp> void zza(DataHolder.zza zza, T t) {
        Parcel obtain = Parcel.obtain();
        t.writeToParcel(obtain, 0);
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataPacketExtension.ELEMENT_NAME, obtain.marshall());
        zza.zza(contentValues);
        obtain.recycle();
    }

    public static DataHolder.zza zzalh() {
        return DataHolder.zzb(zzgcj);
    }

    /* renamed from: zzbx */
    public T get(int i) {
        byte[] zzg = this.zzfxb.zzg(DataPacketExtension.ELEMENT_NAME, i, this.zzfxb.zzby(i));
        Parcel obtain = Parcel.obtain();
        obtain.unmarshall(zzg, 0, zzg.length);
        obtain.setDataPosition(0);
        T t = (T) this.zzgck.createFromParcel(obtain);
        obtain.recycle();
        return t;
    }
}
