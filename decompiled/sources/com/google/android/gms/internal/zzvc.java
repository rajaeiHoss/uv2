package com.google.android.gms.internal;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import org.json.JSONException;
import org.json.JSONObject;

@zzabh
public final class zzvc {
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    public static final zzvb<JSONObject> zzcgm = new zzve();
    private static zzuz<InputStream> zzcgn = zzvd.zzcgo;

    static final /* synthetic */ InputStream zzf(JSONObject jSONObject) throws JSONException {
        return new ByteArrayInputStream(jSONObject.toString().getBytes(UTF_8));
    }
}
