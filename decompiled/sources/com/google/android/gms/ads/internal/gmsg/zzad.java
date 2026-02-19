package com.google.android.gms.ads.internal.gmsg;

import android.text.TextUtils;
import com.google.android.gms.internal.zzabh;
import com.google.android.gms.internal.zzahw;
import java.util.HashMap;
import java.util.Map;
import org.jivesoftware.smackx.Form;
import org.json.JSONException;
import org.json.JSONObject;

@zzabh
public final class zzad implements zzt<Object> {
    private final Object mLock = new Object();
    private final Map<String, zzae> zzccv = new HashMap();

    public final void zza(Object obj, Map<String, String> map) {
        String str;
        String str2 = map.get("id");
        String str3 = map.get("fail");
        String str4 = map.get("fail_reason");
        String str5 = map.get("fail_stack");
        String str6 = map.get(Form.TYPE_RESULT);
        if (TextUtils.isEmpty(str5)) {
            str4 = "Unknown Fail Reason.";
        }
        if (TextUtils.isEmpty(str5)) {
            str = "";
        } else {
            String valueOf = String.valueOf(str5);
            str = valueOf.length() != 0 ? "\n".concat(valueOf) : new String("\n");
        }
        synchronized (this.mLock) {
            zzae remove = this.zzccv.remove(str2);
            if (remove == null) {
                String valueOf2 = String.valueOf(str2);
                zzahw.zzcz(valueOf2.length() != 0 ? "Received result for unexpected method invocation: ".concat(valueOf2) : new String("Received result for unexpected method invocation: "));
            } else if (!TextUtils.isEmpty(str3)) {
                String valueOf3 = String.valueOf(str4);
                String valueOf4 = String.valueOf(str);
                remove.zzau(valueOf4.length() != 0 ? valueOf3.concat(valueOf4) : new String(valueOf3));
            } else if (str6 == null) {
                remove.zzd((JSONObject) null);
            } else {
                try {
                    JSONObject jSONObject = new JSONObject(str6);
                    if (zzahw.zzqk()) {
                        String valueOf5 = String.valueOf(jSONObject.toString(2));
                        zzahw.v(valueOf5.length() != 0 ? "Result GMSG: ".concat(valueOf5) : new String("Result GMSG: "));
                    }
                    remove.zzd(jSONObject);
                } catch (JSONException e) {
                    remove.zzau(e.getMessage());
                }
            }
        }
    }

    public final void zza(String str, zzae zzae) {
        synchronized (this.mLock) {
            this.zzccv.put(str, zzae);
        }
    }
}
