package com.google.ads.mediation;

import android.location.Location;
import com.google.ads.AdRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

@Deprecated
public class MediationAdRequest {
    private final Date zzhh;
    private final AdRequest.Gender zzhi;
    private final Set<String> zzhj;
    private final boolean zzhk;
    private final Location zzhl;

    public MediationAdRequest(Date date, AdRequest.Gender gender, Set<String> set, boolean z, Location location) {
        this.zzhh = date;
        this.zzhi = gender;
        this.zzhj = set;
        this.zzhk = z;
        this.zzhl = location;
    }

    public Integer getAgeInYears() {
        if (this.zzhh == null) {
            return null;
        }
        Calendar instance = Calendar.getInstance();
        Calendar instance2 = Calendar.getInstance();
        instance.setTime(this.zzhh);
        Integer valueOf = Integer.valueOf(instance2.get(1) - instance.get(1));
        return (instance2.get(2) < instance.get(2) || (instance2.get(2) == instance.get(2) && instance2.get(5) < instance.get(5))) ? Integer.valueOf(valueOf.intValue() - 1) : valueOf;
    }

    public Date getBirthday() {
        return this.zzhh;
    }

    public AdRequest.Gender getGender() {
        return this.zzhi;
    }

    public Set<String> getKeywords() {
        return this.zzhj;
    }

    public Location getLocation() {
        return this.zzhl;
    }

    public boolean isTesting() {
        return this.zzhk;
    }
}
