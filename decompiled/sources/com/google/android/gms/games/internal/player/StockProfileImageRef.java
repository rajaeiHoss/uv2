package com.google.android.gms.games.internal.player;

import android.net.Uri;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzc;

public class StockProfileImageRef extends zzc implements StockProfileImage {
    public StockProfileImageRef(DataHolder dataHolder, int i) {
        super(dataHolder, i);
    }

    public int describeContents() {
        throw new NoSuchMethodError();
    }

    public StockProfileImage freeze() {
        throw new NoSuchMethodError();
    }

    public String getImageUrl() {
        return getString("image_url");
    }

    public void writeToParcel(Parcel parcel, int i) {
        throw new NoSuchMethodError();
    }

    public final Uri zzavn() {
        throw new NoSuchMethodError();
    }
}
