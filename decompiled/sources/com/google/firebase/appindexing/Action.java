package com.google.firebase.appindexing;

import com.google.android.gms.common.internal.zzbq;
import com.google.firebase.appindexing.internal.zza;
import com.google.firebase.appindexing.internal.zzb;

public interface Action {

    public static class Builder {
        public static final String ACTIVATE_ACTION = "ActivateAction";
        public static final String ADD_ACTION = "AddAction";
        public static final String BOOKMARK_ACTION = "BookmarkAction";
        public static final String COMMENT_ACTION = "CommentAction";
        public static final String LIKE_ACTION = "LikeAction";
        public static final String LISTEN_ACTION = "ListenAction";
        public static final String SEND_ACTION = "SendAction";
        public static final String SHARE_ACTION = "ShareAction";
        public static final String STATUS_TYPE_ACTIVE = "http://schema.org/ActiveActionStatus";
        public static final String STATUS_TYPE_COMPLETED = "http://schema.org/CompletedActionStatus";
        public static final String STATUS_TYPE_FAILED = "http://schema.org/FailedActionStatus";
        public static final String VIEW_ACTION = "ViewAction";
        public static final String WATCH_ACTION = "WatchAction";
        private final String zzmnn;
        private String zzmno;
        private String zzmnp;
        private String zzmnq;
        private zzb zzmnr;
        private String zzmns;

        public Builder(String str) {
            this.zzmnn = str;
        }

        public Action build() {
            zzbq.checkNotNull(this.zzmno, "setObject is required before calling build().");
            zzbq.checkNotNull(this.zzmnp, "setObject is required before calling build().");
            String str = this.zzmnn;
            String str2 = this.zzmno;
            String str3 = this.zzmnp;
            String str4 = this.zzmnq;
            zzb zzb = this.zzmnr;
            if (zzb == null) {
                zzb = new Metadata.Builder().zzbtd();
            }
            return new zza(str, str2, str3, str4, zzb, this.zzmns);
        }

        public Builder setActionStatus(String str) {
            zzbq.checkNotNull(str);
            this.zzmns = str;
            return this;
        }

        public Builder setMetadata(Metadata.Builder builder) {
            zzbq.checkNotNull(builder);
            this.zzmnr = builder.zzbtd();
            return this;
        }

        public Builder setObject(String str, String str2) {
            zzbq.checkNotNull(str);
            zzbq.checkNotNull(str2);
            this.zzmno = str;
            this.zzmnp = str2;
            return this;
        }

        public Builder setObject(String str, String str2, String str3) {
            zzbq.checkNotNull(str);
            zzbq.checkNotNull(str2);
            zzbq.checkNotNull(str3);
            this.zzmno = str;
            this.zzmnp = str2;
            this.zzmnq = str3;
            return this;
        }
    }

    public interface Metadata {

        public static class Builder {
            private boolean zzmnt = true;
            private boolean zzmnu = false;

            public Builder setUpload(boolean z) {
                this.zzmnt = z;
                return this;
            }

            public final zzb zzbtd() {
                return new zzb(this.zzmnt, (String) null, (String) null, (byte[]) null, false);
            }
        }
    }
}
