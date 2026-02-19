package com.google.android.gms.drive;

@Deprecated
public final class zzr extends ExecutionOptions {
    private boolean zzgql;

    zzr(String str, boolean z, int i, boolean z2) {
        super(str, z, i);
        this.zzgql = z2;
    }

    public static zzr zzb(ExecutionOptions executionOptions) {
        zzt zzt = new zzt();
        if (executionOptions != null) {
            zzt.setConflictStrategy(executionOptions.zzapq());
            zzt.setNotifyOnCompletion(executionOptions.zzapp());
            String zzapo = executionOptions.zzapo();
            if (zzapo != null) {
                zzt.setTrackingTag(zzapo);
            }
        }
        return (zzr) zzt.build();
    }

    public final boolean zzapu() {
        return this.zzgql;
    }
}
