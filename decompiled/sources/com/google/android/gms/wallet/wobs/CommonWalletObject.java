package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;
import java.util.Collection;

public class CommonWalletObject extends zzbgl {
    public static final Parcelable.Creator<CommonWalletObject> CREATOR = new zzb();
    String name;
    int state;
    String zzlll;
    String zzlln;
    String zzllo;
    String zzllp;
    String zzllq;
    String zzllr;
    ArrayList<WalletObjectMessage> zzlls;
    TimeInterval zzllt;
    ArrayList<LatLng> zzllu;
    String zzllv;
    String zzllw;
    ArrayList<LabelValueRow> zzllx;
    boolean zzlly;
    ArrayList<UriData> zzllz;
    ArrayList<TextModuleData> zzlma;
    ArrayList<UriData> zzlmb;
    String zzwc;

    public final class zza {
        private zza() {
        }

        public final zza zza(LabelValueRow labelValueRow) {
            CommonWalletObject.this.zzllx.add(labelValueRow);
            return this;
        }

        public final zza zza(TextModuleData textModuleData) {
            CommonWalletObject.this.zzlma.add(textModuleData);
            return this;
        }

        public final zza zza(TimeInterval timeInterval) {
            CommonWalletObject.this.zzllt = timeInterval;
            return this;
        }

        public final zza zza(UriData uriData) {
            CommonWalletObject.this.zzllz.add(uriData);
            return this;
        }

        public final zza zza(WalletObjectMessage walletObjectMessage) {
            CommonWalletObject.this.zzlls.add(walletObjectMessage);
            return this;
        }

        public final zza zzb(LatLng latLng) {
            CommonWalletObject.this.zzllu.add(latLng);
            return this;
        }

        public final zza zzb(UriData uriData) {
            CommonWalletObject.this.zzlmb.add(uriData);
            return this;
        }

        public final CommonWalletObject zzblv() {
            return CommonWalletObject.this;
        }

        public final zza zzcd(boolean z) {
            CommonWalletObject.this.zzlly = z;
            return this;
        }

        public final zza zzfr(int i) {
            CommonWalletObject.this.state = i;
            return this;
        }

        public final zza zzl(Collection<WalletObjectMessage> collection) {
            CommonWalletObject.this.zzlls.addAll(collection);
            return this;
        }

        public final zza zzm(Collection<LatLng> collection) {
            CommonWalletObject.this.zzllu.addAll(collection);
            return this;
        }

        public final zza zzn(Collection<LabelValueRow> collection) {
            CommonWalletObject.this.zzllx.addAll(collection);
            return this;
        }

        public final zza zzns(String str) {
            CommonWalletObject.this.zzwc = str;
            return this;
        }

        public final zza zznt(String str) {
            CommonWalletObject.this.zzllr = str;
            return this;
        }

        public final zza zznu(String str) {
            CommonWalletObject.this.name = str;
            return this;
        }

        public final zza zznv(String str) {
            CommonWalletObject.this.zzlll = str;
            return this;
        }

        public final zza zznw(String str) {
            CommonWalletObject.this.zzlln = str;
            return this;
        }

        public final zza zznx(String str) {
            CommonWalletObject.this.zzllo = str;
            return this;
        }

        public final zza zzny(String str) {
            CommonWalletObject.this.zzllp = str;
            return this;
        }

        public final zza zznz(String str) {
            CommonWalletObject.this.zzllq = str;
            return this;
        }

        public final zza zzo(Collection<UriData> collection) {
            CommonWalletObject.this.zzllz.addAll(collection);
            return this;
        }

        public final zza zzoa(String str) {
            CommonWalletObject.this.zzllv = str;
            return this;
        }

        public final zza zzob(String str) {
            CommonWalletObject.this.zzllw = str;
            return this;
        }

        public final zza zzp(Collection<TextModuleData> collection) {
            CommonWalletObject.this.zzlma.addAll(collection);
            return this;
        }

        public final zza zzq(Collection<UriData> collection) {
            CommonWalletObject.this.zzlmb.addAll(collection);
            return this;
        }
    }

    CommonWalletObject() {
        this.zzlls = new ArrayList<>();
        this.zzllu = new ArrayList<>();
        this.zzllx = new ArrayList<>();
        this.zzllz = new ArrayList<>();
        this.zzlma = new ArrayList<>();
        this.zzlmb = new ArrayList<>();
    }

    CommonWalletObject(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, int i, ArrayList<WalletObjectMessage> arrayList, TimeInterval timeInterval, ArrayList<LatLng> arrayList2, String str9, String str10, ArrayList<LabelValueRow> arrayList3, boolean z, ArrayList<UriData> arrayList4, ArrayList<TextModuleData> arrayList5, ArrayList<UriData> arrayList6) {
        this.zzwc = str;
        this.zzllr = str2;
        this.name = str3;
        this.zzlll = str4;
        this.zzlln = str5;
        this.zzllo = str6;
        this.zzllp = str7;
        this.zzllq = str8;
        this.state = i;
        this.zzlls = arrayList;
        this.zzllt = timeInterval;
        this.zzllu = arrayList2;
        this.zzllv = str9;
        this.zzllw = str10;
        this.zzllx = arrayList3;
        this.zzlly = z;
        this.zzllz = arrayList4;
        this.zzlma = arrayList5;
        this.zzlmb = arrayList6;
    }

    public static zza zzblu() {
        return new CommonWalletObject().new zza();
    }

    public final String getBarcodeAlternateText() {
        return this.zzlln;
    }

    public final String getBarcodeLabel() {
        return this.zzllq;
    }

    public final String getBarcodeType() {
        return this.zzllo;
    }

    public final String getBarcodeValue() {
        return this.zzllp;
    }

    public final String getClassId() {
        return this.zzllr;
    }

    public final String getId() {
        return this.zzwc;
    }

    public final ArrayList<UriData> getImageModuleDataMainImageUris() {
        return this.zzllz;
    }

    public final String getInfoModuleDataHexBackgroundColor() {
        return this.zzllw;
    }

    public final String getInfoModuleDataHexFontColor() {
        return this.zzllv;
    }

    public final ArrayList<LabelValueRow> getInfoModuleDataLabelValueRows() {
        return this.zzllx;
    }

    public final boolean getInfoModuleDataShowLastUpdateTime() {
        return this.zzlly;
    }

    public final String getIssuerName() {
        return this.zzlll;
    }

    public final ArrayList<UriData> getLinksModuleDataUris() {
        return this.zzlmb;
    }

    public final ArrayList<LatLng> getLocations() {
        return this.zzllu;
    }

    public final ArrayList<WalletObjectMessage> getMessages() {
        return this.zzlls;
    }

    public final String getName() {
        return this.name;
    }

    public final int getState() {
        return this.state;
    }

    public final ArrayList<TextModuleData> getTextModulesData() {
        return this.zzlma;
    }

    public final TimeInterval getValidTimeInterval() {
        return this.zzllt;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, this.zzwc, false);
        zzbgo.zza(parcel, 3, this.zzllr, false);
        zzbgo.zza(parcel, 4, this.name, false);
        zzbgo.zza(parcel, 5, this.zzlll, false);
        zzbgo.zza(parcel, 6, this.zzlln, false);
        zzbgo.zza(parcel, 7, this.zzllo, false);
        zzbgo.zza(parcel, 8, this.zzllp, false);
        zzbgo.zza(parcel, 9, this.zzllq, false);
        zzbgo.zzc(parcel, 10, this.state);
        zzbgo.zzc(parcel, 11, this.zzlls, false);
        zzbgo.zza(parcel, 12, (Parcelable) this.zzllt, i, false);
        zzbgo.zzc(parcel, 13, this.zzllu, false);
        zzbgo.zza(parcel, 14, this.zzllv, false);
        zzbgo.zza(parcel, 15, this.zzllw, false);
        zzbgo.zzc(parcel, 16, this.zzllx, false);
        zzbgo.zza(parcel, 17, this.zzlly);
        zzbgo.zzc(parcel, 18, this.zzllz, false);
        zzbgo.zzc(parcel, 19, this.zzlma, false);
        zzbgo.zzc(parcel, 20, this.zzlmb, false);
        zzbgo.zzai(parcel, zze);
    }
}
