package com.google.android.gms.drive;

import com.google.android.gms.drive.query.Filter;
import com.google.android.gms.drive.query.internal.FilterHolder;
import java.util.List;

public final class OpenFileActivityOptions {
    public static final String EXTRA_RESPONSE_DRIVE_ID = "response_drive_id";
    public final String title;
    public final String[] zzgqu;
    public final FilterHolder zzgqv;
    public final DriveId zzgqw;

    public static class Builder {
        private final OpenFileActivityBuilder zzgqx = new OpenFileActivityBuilder();

        public OpenFileActivityOptions build() {
            this.zzgqx.zzapi();
            return new OpenFileActivityOptions(this.zzgqx.getTitle(), this.zzgqx.zzapx(), this.zzgqx.zzapy(), this.zzgqx.zzapz());
        }

        public Builder setActivityStartFolder(DriveId driveId) {
            this.zzgqx.setActivityStartFolder(driveId);
            return this;
        }

        public Builder setActivityTitle(String str) {
            this.zzgqx.setActivityTitle(str);
            return this;
        }

        public Builder setMimeType(List<String> list) {
            this.zzgqx.setMimeType((String[]) list.toArray(new String[0]));
            return this;
        }

        public Builder setSelectionFilter(Filter filter) {
            this.zzgqx.setSelectionFilter(filter);
            return this;
        }
    }

    private OpenFileActivityOptions(String str, String[] strArr, Filter filter, DriveId driveId) {
        this.title = str;
        this.zzgqu = strArr;
        this.zzgqv = filter == null ? null : new FilterHolder(filter);
        this.zzgqw = driveId;
    }
}
