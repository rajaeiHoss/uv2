package com.google.android.gms.games.stats;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.games.internal.zzc;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;

public final class zza extends zzc implements PlayerStats {
    public static final Parcelable.Creator<zza> CREATOR = new zzb();
    private final float zziic;
    private final float zziid;
    private final int zziie;
    private final int zziif;
    private final int zziig;
    private final float zziih;
    private final float zziii;
    private final Bundle zziij;
    private final float zziik;
    private final float zziil;
    private final float zziim;

    zza(float f, float f2, int i, int i2, int i3, float f3, float f4, Bundle bundle, float f5, float f6, float f7) {
        this.zziic = f;
        this.zziid = f2;
        this.zziie = i;
        this.zziif = i2;
        this.zziig = i3;
        this.zziih = f3;
        this.zziii = f4;
        this.zziij = bundle;
        this.zziik = f5;
        this.zziil = f6;
        this.zziim = f7;
    }

    public zza(PlayerStats playerStats) {
        this.zziic = playerStats.getAverageSessionLength();
        this.zziid = playerStats.getChurnProbability();
        this.zziie = playerStats.getDaysSinceLastPlayed();
        this.zziif = playerStats.getNumberOfPurchases();
        this.zziig = playerStats.getNumberOfSessions();
        this.zziih = playerStats.getSessionPercentile();
        this.zziii = playerStats.getSpendPercentile();
        this.zziik = playerStats.getSpendProbability();
        this.zziil = playerStats.getHighSpenderProbability();
        this.zziim = playerStats.getTotalSpendNext28Days();
        this.zziij = playerStats.zzavz();
    }

    static int zza(PlayerStats playerStats) {
        return Arrays.hashCode(new Object[]{Float.valueOf(playerStats.getAverageSessionLength()), Float.valueOf(playerStats.getChurnProbability()), Integer.valueOf(playerStats.getDaysSinceLastPlayed()), Integer.valueOf(playerStats.getNumberOfPurchases()), Integer.valueOf(playerStats.getNumberOfSessions()), Float.valueOf(playerStats.getSessionPercentile()), Float.valueOf(playerStats.getSpendPercentile()), Float.valueOf(playerStats.getSpendProbability()), Float.valueOf(playerStats.getHighSpenderProbability()), Float.valueOf(playerStats.getTotalSpendNext28Days())});
    }

    static boolean zza(PlayerStats playerStats, Object obj) {
        if (!(obj instanceof PlayerStats)) {
            return false;
        }
        if (playerStats == obj) {
            return true;
        }
        PlayerStats playerStats2 = (PlayerStats) obj;
        return zzbg.equal(Float.valueOf(playerStats2.getAverageSessionLength()), Float.valueOf(playerStats.getAverageSessionLength())) && zzbg.equal(Float.valueOf(playerStats2.getChurnProbability()), Float.valueOf(playerStats.getChurnProbability())) && zzbg.equal(Integer.valueOf(playerStats2.getDaysSinceLastPlayed()), Integer.valueOf(playerStats.getDaysSinceLastPlayed())) && zzbg.equal(Integer.valueOf(playerStats2.getNumberOfPurchases()), Integer.valueOf(playerStats.getNumberOfPurchases())) && zzbg.equal(Integer.valueOf(playerStats2.getNumberOfSessions()), Integer.valueOf(playerStats.getNumberOfSessions())) && zzbg.equal(Float.valueOf(playerStats2.getSessionPercentile()), Float.valueOf(playerStats.getSessionPercentile())) && zzbg.equal(Float.valueOf(playerStats2.getSpendPercentile()), Float.valueOf(playerStats.getSpendPercentile())) && zzbg.equal(Float.valueOf(playerStats2.getSpendProbability()), Float.valueOf(playerStats.getSpendProbability())) && zzbg.equal(Float.valueOf(playerStats2.getHighSpenderProbability()), Float.valueOf(playerStats.getHighSpenderProbability())) && zzbg.equal(Float.valueOf(playerStats2.getTotalSpendNext28Days()), Float.valueOf(playerStats.getTotalSpendNext28Days()));
    }

    static String zzb(PlayerStats playerStats) {
        return zzbg.zzx(playerStats).zzg("AverageSessionLength", Float.valueOf(playerStats.getAverageSessionLength())).zzg("ChurnProbability", Float.valueOf(playerStats.getChurnProbability())).zzg("DaysSinceLastPlayed", Integer.valueOf(playerStats.getDaysSinceLastPlayed())).zzg("NumberOfPurchases", Integer.valueOf(playerStats.getNumberOfPurchases())).zzg("NumberOfSessions", Integer.valueOf(playerStats.getNumberOfSessions())).zzg("SessionPercentile", Float.valueOf(playerStats.getSessionPercentile())).zzg("SpendPercentile", Float.valueOf(playerStats.getSpendPercentile())).zzg("SpendProbability", Float.valueOf(playerStats.getSpendProbability())).zzg("HighSpenderProbability", Float.valueOf(playerStats.getHighSpenderProbability())).zzg("TotalSpendNext28Days", Float.valueOf(playerStats.getTotalSpendNext28Days())).toString();
    }

    public final boolean equals(Object obj) {
        return zza(this, obj);
    }

    public final PlayerStats freeze() {
        return this;
    }

    public final float getAverageSessionLength() {
        return this.zziic;
    }

    public final float getChurnProbability() {
        return this.zziid;
    }

    public final int getDaysSinceLastPlayed() {
        return this.zziie;
    }

    public final float getHighSpenderProbability() {
        return this.zziil;
    }

    public final int getNumberOfPurchases() {
        return this.zziif;
    }

    public final int getNumberOfSessions() {
        return this.zziig;
    }

    public final float getSessionPercentile() {
        return this.zziih;
    }

    public final float getSpendPercentile() {
        return this.zziii;
    }

    public final float getSpendProbability() {
        return this.zziik;
    }

    public final float getTotalSpendNext28Days() {
        return this.zziim;
    }

    public final int hashCode() {
        return zza(this);
    }

    public final boolean isDataValid() {
        return true;
    }

    public final String toString() {
        return zzb(this);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 1, getAverageSessionLength());
        zzbgo.zza(parcel, 2, getChurnProbability());
        zzbgo.zzc(parcel, 3, getDaysSinceLastPlayed());
        zzbgo.zzc(parcel, 4, getNumberOfPurchases());
        zzbgo.zzc(parcel, 5, getNumberOfSessions());
        zzbgo.zza(parcel, 6, getSessionPercentile());
        zzbgo.zza(parcel, 7, getSpendPercentile());
        zzbgo.zza(parcel, 8, this.zziij, false);
        zzbgo.zza(parcel, 9, getSpendProbability());
        zzbgo.zza(parcel, 10, getHighSpenderProbability());
        zzbgo.zza(parcel, 11, getTotalSpendNext28Days());
        zzbgo.zzai(parcel, zze);
    }

    public final Bundle zzavz() {
        return this.zziij;
    }
}
