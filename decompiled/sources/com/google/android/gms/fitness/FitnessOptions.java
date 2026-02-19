package com.google.android.gms.fitness;

import android.os.Bundle;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptionsExtension;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.fitness.data.DataType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FitnessOptions implements GoogleSignInOptionsExtension, Api.ApiOptions.HasGoogleSignInAccountOptions {
    public static final int ACCESS_READ = 0;
    public static final int ACCESS_WRITE = 1;
    private final Map<Integer, List<DataType>> zzhhf;
    private final Set<Scope> zzhhg;
    private final GoogleSignInAccount zzhhh;

    public static final class Builder {
        private final Map<Integer, List<DataType>> map;
        private GoogleSignInAccount zzhhh;

        private Builder() {
            this.map = new HashMap();
        }

        /* access modifiers changed from: private */
        public final Builder zzc(GoogleSignInAccount googleSignInAccount) {
            this.zzhhh = googleSignInAccount;
            return this;
        }

        public final Builder addDataType(DataType dataType) {
            return addDataType(dataType, 0);
        }

        public final Builder addDataType(DataType dataType, int i) {
            boolean z = true;
            if (!(i == 0 || i == 1)) {
                z = false;
            }
            zzbq.checkArgument(z, "valid access types are FitnessOptions.ACCESS_READ or FitnessOptions.ACCESS_WRITE");
            List list = this.map.get(Integer.valueOf(i));
            if (list == null) {
                list = new ArrayList();
                this.map.put(Integer.valueOf(i), list);
            }
            list.add(dataType);
            return this;
        }

        public final FitnessOptions build() {
            return new FitnessOptions(this.map, this.zzhhh);
        }
    }

    private FitnessOptions(Map<Integer, List<DataType>> map, GoogleSignInAccount googleSignInAccount) {
        Scope scope;
        this.zzhhf = map;
        this.zzhhh = googleSignInAccount;
        ArrayList arrayList = new ArrayList();
        for (Map.Entry next : map.entrySet()) {
            for (Object item : (List) next.getValue()) {
                DataType dataType = (DataType) item;
                int intValue = ((Integer) next.getKey()).intValue();
                scope = null;
                if (intValue == 0 && dataType.zzarw() != null) {
                    scope = new Scope(dataType.zzarw());
                } else if (intValue == 1 && dataType.zzarx() != null) {
                    scope = new Scope(dataType.zzarx());
                }
                if (scope != null) {
                    arrayList.add(scope);
                }
            }
        }
        this.zzhhg = zzg.zzi(arrayList);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder zzb(GoogleSignInAccount googleSignInAccount) {
        return googleSignInAccount != null ? new Builder().zzc(googleSignInAccount) : new Builder();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            FitnessOptions fitnessOptions = (FitnessOptions) obj;
            return zzbg.equal(this.zzhhf, fitnessOptions.zzhhf) && zzbg.equal(this.zzhhh, fitnessOptions.zzhhh);
        }
        return false;
    }

    public int getExtensionType() {
        return 3;
    }

    public GoogleSignInAccount getGoogleSignInAccount() {
        return this.zzhhh;
    }

    public List<Scope> getImpliedScopes() {
        return new ArrayList(this.zzhhg);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzhhf, this.zzhhh});
    }

    public Bundle toBundle() {
        return new Bundle();
    }
}
