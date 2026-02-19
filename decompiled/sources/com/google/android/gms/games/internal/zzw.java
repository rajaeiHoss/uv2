package com.google.android.gms.games.internal;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.zzc;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.games.multiplayer.ParticipantResult;
import com.google.android.gms.games.multiplayer.realtime.RoomEntity;
import com.google.android.gms.games.snapshot.zze;

public interface zzw extends IInterface {
    String getAppId() throws RemoteException;

    int zza(zzs zzs, byte[] bArr, String str, String str2) throws RemoteException;

    Intent zza(int i, byte[] bArr, int i2, String str) throws RemoteException;

    Intent zza(PlayerEntity playerEntity) throws RemoteException;

    Intent zza(RoomEntity roomEntity, int i) throws RemoteException;

    Intent zza(String str, boolean z, boolean z2, int i) throws RemoteException;

    void zza(IBinder iBinder, Bundle bundle) throws RemoteException;

    void zza(zzc zzc) throws RemoteException;

    void zza(zzs zzs) throws RemoteException;

    void zza(zzs zzs, int i) throws RemoteException;

    void zza(zzs zzs, int i, int i2, int i3) throws RemoteException;

    void zza(zzs zzs, int i, int i2, String[] strArr, Bundle bundle) throws RemoteException;

    void zza(zzs zzs, int i, boolean z, boolean z2) throws RemoteException;

    void zza(zzs zzs, int i, int[] iArr) throws RemoteException;

    void zza(zzs zzs, long j) throws RemoteException;

    void zza(zzs zzs, Bundle bundle, int i, int i2) throws RemoteException;

    void zza(zzs zzs, IBinder iBinder, int i, String[] strArr, Bundle bundle, boolean z, long j) throws RemoteException;

    void zza(zzs zzs, IBinder iBinder, String str, boolean z, long j) throws RemoteException;

    void zza(zzs zzs, String str) throws RemoteException;

    void zza(zzs zzs, String str, int i, int i2, int i3, boolean z) throws RemoteException;

    void zza(zzs zzs, String str, int i, IBinder iBinder, Bundle bundle) throws RemoteException;

    void zza(zzs zzs, String str, int i, boolean z, boolean z2) throws RemoteException;

    void zza(zzs zzs, String str, long j, String str2) throws RemoteException;

    void zza(zzs zzs, String str, IBinder iBinder, Bundle bundle) throws RemoteException;

    void zza(zzs zzs, String str, zze zze, zzc zzc) throws RemoteException;

    void zza(zzs zzs, String str, String str2) throws RemoteException;

    void zza(zzs zzs, String str, String str2, int i, int i2) throws RemoteException;

    void zza(zzs zzs, String str, String str2, zze zze, zzc zzc) throws RemoteException;

    void zza(zzs zzs, String str, boolean z) throws RemoteException;

    void zza(zzs zzs, String str, boolean z, int i) throws RemoteException;

    void zza(zzs zzs, String str, byte[] bArr, String str2, ParticipantResult[] participantResultArr) throws RemoteException;

    void zza(zzs zzs, String str, byte[] bArr, ParticipantResult[] participantResultArr) throws RemoteException;

    void zza(zzs zzs, boolean z) throws RemoteException;

    void zza(zzs zzs, boolean z, String[] strArr) throws RemoteException;

    void zza(zzs zzs, int[] iArr, int i, boolean z) throws RemoteException;

    void zza(zzs zzs, String[] strArr) throws RemoteException;

    void zza(zzs zzs, String[] strArr, boolean z) throws RemoteException;

    void zza(zzu zzu, long j) throws RemoteException;

    void zza(String str, IBinder iBinder, Bundle bundle) throws RemoteException;

    void zza(String str, zzs zzs) throws RemoteException;

    void zzac(long j) throws RemoteException;

    void zzad(long j) throws RemoteException;

    void zzae(long j) throws RemoteException;

    void zzaf(long j) throws RemoteException;

    void zzag(long j) throws RemoteException;

    Bundle zzagp() throws RemoteException;

    void zzah(long j) throws RemoteException;

    String zzate() throws RemoteException;

    Intent zzatk() throws RemoteException;

    Intent zzatm() throws RemoteException;

    Intent zzatn() throws RemoteException;

    Intent zzato() throws RemoteException;

    Intent zzatv() throws RemoteException;

    Intent zzatx() throws RemoteException;

    int zzatz() throws RemoteException;

    int zzauc() throws RemoteException;

    Intent zzaue() throws RemoteException;

    int zzauf() throws RemoteException;

    int zzaug() throws RemoteException;

    int zzauh() throws RemoteException;

    int zzauj() throws RemoteException;

    boolean zzaun() throws RemoteException;

    void zzaur() throws RemoteException;

    String zzaut() throws RemoteException;

    DataHolder zzauu() throws RemoteException;

    DataHolder zzauv() throws RemoteException;

    Intent zzauw() throws RemoteException;

    int zzb(byte[] bArr, String str, String[] strArr) throws RemoteException;

    Intent zzb(int i, int i2, boolean z) throws RemoteException;

    void zzb(zzs zzs) throws RemoteException;

    void zzb(zzs zzs, int i) throws RemoteException;

    void zzb(zzs zzs, long j) throws RemoteException;

    void zzb(zzs zzs, String str) throws RemoteException;

    void zzb(zzs zzs, String str, int i, int i2, int i3, boolean z) throws RemoteException;

    void zzb(zzs zzs, String str, int i, IBinder iBinder, Bundle bundle) throws RemoteException;

    void zzb(zzs zzs, String str, IBinder iBinder, Bundle bundle) throws RemoteException;

    void zzb(zzs zzs, String str, String str2) throws RemoteException;

    void zzb(zzs zzs, String str, boolean z) throws RemoteException;

    void zzb(zzs zzs, boolean z) throws RemoteException;

    void zzb(zzs zzs, String[] strArr) throws RemoteException;

    Intent zzc(int[] iArr) throws RemoteException;

    void zzc(zzs zzs) throws RemoteException;

    void zzc(zzs zzs, long j) throws RemoteException;

    void zzc(zzs zzs, String str) throws RemoteException;

    void zzc(zzs zzs, boolean z) throws RemoteException;

    Intent zzd(int i, int i2, boolean z) throws RemoteException;

    void zzd(zzs zzs) throws RemoteException;

    void zzd(zzs zzs, long j) throws RemoteException;

    void zzd(zzs zzs, String str) throws RemoteException;

    void zzd(zzs zzs, boolean z) throws RemoteException;

    void zzdt(int i) throws RemoteException;

    void zze(zzs zzs, long j) throws RemoteException;

    void zze(zzs zzs, String str) throws RemoteException;

    void zze(zzs zzs, boolean z) throws RemoteException;

    void zzf(zzs zzs, String str) throws RemoteException;

    void zzf(zzs zzs, boolean z) throws RemoteException;

    void zzg(zzs zzs, String str) throws RemoteException;

    void zzh(zzs zzs, String str) throws RemoteException;

    Intent zzia(String str) throws RemoteException;

    void zzic(String str) throws RemoteException;

    Intent zzk(String str, int i, int i2) throws RemoteException;

    void zzp(String str, int i) throws RemoteException;

    void zzq(String str, int i) throws RemoteException;

    void zzs(String str, int i) throws RemoteException;
}
