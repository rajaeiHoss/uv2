package com.google.android.gms.wearable.internal;

import android.app.Activity;
import android.content.Context;
import android.content.IntentFilter;
import android.net.Uri;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.api.internal.zzck;
import com.google.android.gms.common.api.internal.zzcm;
import com.google.android.gms.common.internal.zzbj;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.internal.zzc;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.wearable.Asset;
import com.google.android.gms.wearable.DataApi;
import com.google.android.gms.wearable.DataClient;
import com.google.android.gms.wearable.DataItem;
import com.google.android.gms.wearable.DataItemAsset;
import com.google.android.gms.wearable.DataItemBuffer;
import com.google.android.gms.wearable.PutDataRequest;

public final class zzcj extends DataClient {
    private final DataApi zzluc = new zzbw();

    public zzcj(Activity activity, GoogleApi.zza zza) {
        super(activity, zza);
    }

    public zzcj(Context context, GoogleApi.zza zza) {
        super(context, zza);
    }

    private final Task<Void> zza(DataClient.OnDataChangedListener onDataChangedListener, IntentFilter[] intentFilterArr) {
        zzci zzb = zzcm.zzb(onDataChangedListener, getLooper(), "DataListener");
        return zza(new zzcv(onDataChangedListener, intentFilterArr, zzb), new zzcw(onDataChangedListener, zzb.zzakx()));
    }

    public final Task<Void> addListener(DataClient.OnDataChangedListener onDataChangedListener) {
        return zza(onDataChangedListener, new IntentFilter[]{zzgj.zzoe("com.google.android.gms.wearable.DATA_CHANGED")});
    }

    public final Task<Void> addListener(DataClient.OnDataChangedListener onDataChangedListener, Uri uri, int i) {
        zzc.zzb(uri, "uri must not be null");
        zzbq.checkArgument(i == 0 || i == 1, "invalid filter type");
        return zza(onDataChangedListener, new IntentFilter[]{zzgj.zza("com.google.android.gms.wearable.DATA_CHANGED", uri, i)});
    }

    public final Task<Integer> deleteDataItems(Uri uri) {
        return zzbj.zza(this.zzluc.deleteDataItems(zzahw(), uri), zzcp.zzgui);
    }

    public final Task<Integer> deleteDataItems(Uri uri, int i) {
        return zzbj.zza(this.zzluc.deleteDataItems(zzahw(), uri, i), zzcq.zzgui);
    }

    public final Task<DataItem> getDataItem(Uri uri) {
        return zzbj.zza(this.zzluc.getDataItem(zzahw(), uri), zzcl.zzgui);
    }

    public final Task<DataItemBuffer> getDataItems() {
        return zzbj.zza(this.zzluc.getDataItems(zzahw()), com.google.android.gms.wearable.internal.zzcm.zzgui);
    }

    public final Task<DataItemBuffer> getDataItems(Uri uri) {
        return zzbj.zza(this.zzluc.getDataItems(zzahw(), uri), zzcn.zzgui);
    }

    public final Task<DataItemBuffer> getDataItems(Uri uri, int i) {
        return zzbj.zza(this.zzluc.getDataItems(zzahw(), uri, i), zzco.zzgui);
    }

    public final Task<DataClient.GetFdForAssetResponse> getFdForAsset(Asset asset) {
        return zzbj.zza(this.zzluc.getFdForAsset(zzahw(), asset), zzcr.zzgui);
    }

    public final Task<DataClient.GetFdForAssetResponse> getFdForAsset(DataItemAsset dataItemAsset) {
        return zzbj.zza(this.zzluc.getFdForAsset(zzahw(), dataItemAsset), zzcs.zzgui);
    }

    public final Task<DataItem> putDataItem(PutDataRequest putDataRequest) {
        return zzbj.zza(this.zzluc.putDataItem(zzahw(), putDataRequest), com.google.android.gms.wearable.internal.zzck.zzgui);
    }

    public final Task<Boolean> removeListener(DataClient.OnDataChangedListener onDataChangedListener) {
        return zza((zzck<?>) zzcm.zzb(onDataChangedListener, getLooper(), "DataListener").zzakx());
    }
}
