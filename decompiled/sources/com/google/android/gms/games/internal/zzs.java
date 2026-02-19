package com.google.android.gms.games.internal;

import android.net.Uri;
import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.zzc;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessage;
import com.google.android.gms.games.video.VideoCapabilities;

public interface zzs extends IInterface {
    void onCaptureOverlayStateChanged(int i) throws RemoteException;

    void onInvitationRemoved(String str) throws RemoteException;

    void onLeftRoom(int i, String str) throws RemoteException;

    void onP2PConnected(String str) throws RemoteException;

    void onP2PDisconnected(String str) throws RemoteException;

    void onRealTimeMessageReceived(RealTimeMessage realTimeMessage) throws RemoteException;

    void onRequestRemoved(String str) throws RemoteException;

    void onTurnBasedMatchRemoved(String str) throws RemoteException;

    void zza(int i, Uri uri) throws RemoteException;

    void zza(int i, VideoCapabilities videoCapabilities) throws RemoteException;

    void zza(int i, String str, boolean z) throws RemoteException;

    void zza(int i, boolean z, boolean z2) throws RemoteException;

    void zza(DataHolder dataHolder, DataHolder dataHolder2) throws RemoteException;

    void zza(DataHolder dataHolder, zzc zzc) throws RemoteException;

    void zza(DataHolder dataHolder, String str, zzc zzc, zzc zzc2, zzc zzc3) throws RemoteException;

    void zza(DataHolder dataHolder, String[] strArr) throws RemoteException;

    void zza(DataHolder[] dataHolderArr) throws RemoteException;

    void zzaa(DataHolder dataHolder) throws RemoteException;

    void zzab(DataHolder dataHolder) throws RemoteException;

    void zzac(DataHolder dataHolder) throws RemoteException;

    void zzad(DataHolder dataHolder) throws RemoteException;

    void zzae(DataHolder dataHolder) throws RemoteException;

    void zzaf(DataHolder dataHolder) throws RemoteException;

    void zzag(DataHolder dataHolder) throws RemoteException;

    void zzah(DataHolder dataHolder) throws RemoteException;

    void zzai(DataHolder dataHolder) throws RemoteException;

    void zzaj(Status status) throws RemoteException;

    void zzaj(DataHolder dataHolder) throws RemoteException;

    void zzak(Status status) throws RemoteException;

    void zzak(DataHolder dataHolder) throws RemoteException;

    void zzako() throws RemoteException;

    void zzal(Status status) throws RemoteException;

    void zzal(DataHolder dataHolder) throws RemoteException;

    void zzam(Status status) throws RemoteException;

    void zzam(DataHolder dataHolder) throws RemoteException;

    void zzan(DataHolder dataHolder) throws RemoteException;

    void zzao(DataHolder dataHolder) throws RemoteException;

    void zzap(DataHolder dataHolder) throws RemoteException;

    void zzaq(DataHolder dataHolder) throws RemoteException;

    void zzar(DataHolder dataHolder) throws RemoteException;

    void zzas(DataHolder dataHolder) throws RemoteException;

    void zzat(DataHolder dataHolder) throws RemoteException;

    void zzau(DataHolder dataHolder) throws RemoteException;

    void zzav(DataHolder dataHolder) throws RemoteException;

    void zzaw(DataHolder dataHolder) throws RemoteException;

    void zzax(DataHolder dataHolder) throws RemoteException;

    void zzay(DataHolder dataHolder) throws RemoteException;

    void zzaz(DataHolder dataHolder) throws RemoteException;

    void zzb(int i, int i2, String str) throws RemoteException;

    void zzb(int i, Bundle bundle) throws RemoteException;

    void zzb(int i, String str, String str2) throws RemoteException;

    void zzb(DataHolder dataHolder, String[] strArr) throws RemoteException;

    void zzba(DataHolder dataHolder) throws RemoteException;

    void zzbb(DataHolder dataHolder) throws RemoteException;

    void zzbc(DataHolder dataHolder) throws RemoteException;

    void zzbd(DataHolder dataHolder) throws RemoteException;

    void zzbe(DataHolder dataHolder) throws RemoteException;

    void zzbf(DataHolder dataHolder) throws RemoteException;

    void zzc(int i, Bundle bundle) throws RemoteException;

    void zzc(DataHolder dataHolder, String[] strArr) throws RemoteException;

    void zzd(int i, Bundle bundle) throws RemoteException;

    void zzd(DataHolder dataHolder, String[] strArr) throws RemoteException;

    void zzdi(int i) throws RemoteException;

    void zzdj(int i) throws RemoteException;

    void zzdk(int i) throws RemoteException;

    void zzdl(int i) throws RemoteException;

    void zzdm(int i) throws RemoteException;

    void zzdn(int i) throws RemoteException;

    void zzdo(int i) throws RemoteException;

    void zzdp(int i) throws RemoteException;

    void zzdq(int i) throws RemoteException;

    void zzdr(int i) throws RemoteException;

    void zze(int i, Bundle bundle) throws RemoteException;

    void zze(DataHolder dataHolder, String[] strArr) throws RemoteException;

    void zzf(int i, Bundle bundle) throws RemoteException;

    void zzf(DataHolder dataHolder) throws RemoteException;

    void zzf(DataHolder dataHolder, String[] strArr) throws RemoteException;

    void zzg(int i, Bundle bundle) throws RemoteException;

    void zzg(int i, String str) throws RemoteException;

    void zzg(DataHolder dataHolder) throws RemoteException;

    void zzh(int i, Bundle bundle) throws RemoteException;

    void zzh(int i, String str) throws RemoteException;

    void zzh(DataHolder dataHolder) throws RemoteException;

    void zzi(int i, String str) throws RemoteException;

    void zzi(int i, boolean z) throws RemoteException;

    void zzi(DataHolder dataHolder) throws RemoteException;

    void zzj(int i, String str) throws RemoteException;

    void zzj(DataHolder dataHolder) throws RemoteException;

    void zzk(int i, String str) throws RemoteException;

    void zzk(DataHolder dataHolder) throws RemoteException;

    void zzl(DataHolder dataHolder) throws RemoteException;

    void zzm(DataHolder dataHolder) throws RemoteException;

    void zzn(DataHolder dataHolder) throws RemoteException;

    void zzo(DataHolder dataHolder) throws RemoteException;

    void zzp(DataHolder dataHolder) throws RemoteException;

    void zzq(DataHolder dataHolder) throws RemoteException;

    void zzr(DataHolder dataHolder) throws RemoteException;

    void zzs(DataHolder dataHolder) throws RemoteException;

    void zzt(DataHolder dataHolder) throws RemoteException;

    void zzu(DataHolder dataHolder) throws RemoteException;

    void zzv(DataHolder dataHolder) throws RemoteException;

    void zzw(DataHolder dataHolder) throws RemoteException;

    void zzx(DataHolder dataHolder) throws RemoteException;

    void zzy(DataHolder dataHolder) throws RemoteException;

    void zzz(DataHolder dataHolder) throws RemoteException;
}
