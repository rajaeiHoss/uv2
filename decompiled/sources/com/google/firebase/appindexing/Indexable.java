package com.google.firebase.appindexing;

import android.os.Bundle;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzfml;
import com.google.firebase.appindexing.builders.IndexableBuilder;
import com.google.firebase.appindexing.internal.Thing;

public interface Indexable {
    public static final int MAX_BYTE_SIZE = 30000;
    public static final int MAX_INDEXABLES_TO_BE_UPDATED_IN_ONE_CALL = 1000;
    public static final int MAX_NESTING_DEPTH = 5;
    public static final int MAX_NUMBER_OF_FIELDS = 20;
    public static final int MAX_REPEATED_SIZE = 100;
    public static final int MAX_STRING_LENGTH = 20000;
    public static final int MAX_URL_LENGTH = 256;

    public static class Builder extends IndexableBuilder<Builder> {
        public Builder() {
            this("Thing");
        }

        public Builder(String str) {
            super(str);
        }
    }

    public interface Metadata {

        public static final class Builder {
            private static final zzfml zzmnw = new zzfml();
            private int score;
            private final Bundle zzegs = new Bundle();
            private boolean zzmnx;
            private String zzmny;

            public Builder() {
                zzfml zzfml = zzmnw;
                this.zzmnx = zzfml.zzmnx;
                this.score = zzfml.score;
                this.zzmny = zzfml.zzmny;
            }

            public final Builder setScope(int i) {
                boolean z = i > 0 && i <= 3;
                StringBuilder sb = new StringBuilder(69);
                sb.append("The scope of this indexable is not valid, scope value is ");
                sb.append(i);
                sb.append(".");
                zzbq.checkArgument(z, sb.toString());
                IndexableBuilder.zza(this.zzegs, "scope", (long) i);
                return this;
            }

            public final Builder setScore(int i) {
                boolean z = i >= 0;
                StringBuilder sb = new StringBuilder(53);
                sb.append("Negative score values are invalid. Value: ");
                sb.append(i);
                zzbq.checkArgument(z, sb.toString());
                this.score = i;
                return this;
            }

            public final Builder setWorksOffline(boolean z) {
                this.zzmnx = z;
                return this;
            }

            public final Thing.zza zzbte() {
                return new Thing.zza(this.zzmnx, this.score, this.zzmny, this.zzegs);
            }
        }
    }
}
