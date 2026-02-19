package com.google.android.gms.drive;

@Deprecated
public final class zzo extends ExecutionOptions {
    private String zzgqj;
    private String zzgqk;

    private zzo(String str, boolean z, String str2, String str3, int i) {
        super(str, z, i);
        this.zzgqj = str2;
        this.zzgqk = str3;
    }

    /* synthetic */ zzo(String str, boolean z, String str2, String str3, int i, zzp zzp) {
        this(str, z, (String) null, (String) null, i);
    }

    public static zzo zza(ExecutionOptions executionOptions) {
        zzq zzq = new zzq();
        if (executionOptions != null) {
            if (executionOptions.zzapq() == 0) {
                String zzapo = executionOptions.zzapo();
                if (zzapo != null) {
                    zzq.setTrackingTag(zzapo);
                }
                zzq.setNotifyOnCompletion(executionOptions.zzapp());
            } else {
                throw new IllegalStateException("May not set a conflict strategy for new file creation.");
            }
        }
        return (zzo) zzq.build();
    }

    public final String zzaps() {
        return this.zzgqj;
    }

    public final String zzapt() {
        return this.zzgqk;
    }
}
