package com.google.android.gms.drive;

import java.util.Arrays;

public class TransferPreferencesBuilder {
    public static final TransferPreferences DEFAULT_PREFERENCES = new zza(1, true, 256);
    private int zzgre;
    private boolean zzgrf;
    private int zzgrg;

    static class zza implements TransferPreferences {
        private final int zzgre;
        private final boolean zzgrf;
        private final int zzgrg;

        zza(int i, boolean z, int i2) {
            this.zzgre = i;
            this.zzgrf = z;
            this.zzgrg = i2;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && getClass() == obj.getClass()) {
                zza zza = (zza) obj;
                return zza.zzgre == this.zzgre && zza.zzgrf == this.zzgrf && zza.zzgrg == this.zzgrg;
            }
            return false;
        }

        public final int getBatteryUsagePreference() {
            return this.zzgrg;
        }

        public final int getNetworkPreference() {
            return this.zzgre;
        }

        public final int hashCode() {
            return Arrays.hashCode(new Object[]{Integer.valueOf(this.zzgre), Boolean.valueOf(this.zzgrf), Integer.valueOf(this.zzgrg)});
        }

        public final boolean isRoamingAllowed() {
            return this.zzgrf;
        }

        public final String toString() {
            return String.format("NetworkPreference: %s, IsRoamingAllowed %s, BatteryUsagePreference %s", new Object[]{Integer.valueOf(this.zzgre), Boolean.valueOf(this.zzgrf), Integer.valueOf(this.zzgrg)});
        }
    }

    public TransferPreferencesBuilder() {
        this(DEFAULT_PREFERENCES);
    }

    public TransferPreferencesBuilder(FileUploadPreferences fileUploadPreferences) {
        this.zzgre = fileUploadPreferences.getNetworkTypePreference();
        this.zzgrf = fileUploadPreferences.isRoamingAllowed();
        this.zzgrg = fileUploadPreferences.getBatteryUsagePreference();
    }

    public TransferPreferencesBuilder(TransferPreferences transferPreferences) {
        this.zzgre = transferPreferences.getNetworkPreference();
        this.zzgrf = transferPreferences.isRoamingAllowed();
        this.zzgrg = transferPreferences.getBatteryUsagePreference();
    }

    public TransferPreferences build() {
        return new zza(this.zzgre, this.zzgrf, this.zzgrg);
    }

    public TransferPreferencesBuilder setBatteryUsagePreference(int i) {
        this.zzgrg = i;
        return this;
    }

    public TransferPreferencesBuilder setIsRoamingAllowed(boolean z) {
        this.zzgrf = z;
        return this;
    }

    public TransferPreferencesBuilder setNetworkPreference(int i) {
        this.zzgre = i;
        return this;
    }
}
