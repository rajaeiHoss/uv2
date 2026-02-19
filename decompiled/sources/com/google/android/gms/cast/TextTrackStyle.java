package com.google.android.gms.cast;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.accessibility.CaptioningManager;
import com.google.android.gms.common.util.zzq;
import com.google.android.gms.common.util.zzs;
import com.google.android.gms.internal.zzbdw;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;
import org.json.JSONException;
import org.json.JSONObject;

public final class TextTrackStyle extends zzbgl {
    public static final int COLOR_UNSPECIFIED = 0;
    public static final Parcelable.Creator<TextTrackStyle> CREATOR = new zzbn();
    public static final float DEFAULT_FONT_SCALE = 1.0f;
    public static final int EDGE_TYPE_DEPRESSED = 4;
    public static final int EDGE_TYPE_DROP_SHADOW = 2;
    public static final int EDGE_TYPE_NONE = 0;
    public static final int EDGE_TYPE_OUTLINE = 1;
    public static final int EDGE_TYPE_RAISED = 3;
    public static final int EDGE_TYPE_UNSPECIFIED = -1;
    public static final int FONT_FAMILY_CASUAL = 4;
    public static final int FONT_FAMILY_CURSIVE = 5;
    public static final int FONT_FAMILY_MONOSPACED_SANS_SERIF = 1;
    public static final int FONT_FAMILY_MONOSPACED_SERIF = 3;
    public static final int FONT_FAMILY_SANS_SERIF = 0;
    public static final int FONT_FAMILY_SERIF = 2;
    public static final int FONT_FAMILY_SMALL_CAPITALS = 6;
    public static final int FONT_FAMILY_UNSPECIFIED = -1;
    public static final int FONT_STYLE_BOLD = 1;
    public static final int FONT_STYLE_BOLD_ITALIC = 3;
    public static final int FONT_STYLE_ITALIC = 2;
    public static final int FONT_STYLE_NORMAL = 0;
    public static final int FONT_STYLE_UNSPECIFIED = -1;
    public static final int WINDOW_TYPE_NONE = 0;
    public static final int WINDOW_TYPE_NORMAL = 1;
    public static final int WINDOW_TYPE_ROUNDED = 2;
    public static final int WINDOW_TYPE_UNSPECIFIED = -1;
    private int zzbxm;
    private String zzesn;
    private JSONObject zzess;
    private float zzezf;
    private int zzezg;
    private int zzezh;
    private int zzezi;
    private int zzezj;
    private int zzezk;
    private int zzezl;
    private String zzezm;
    private int zzezn;
    private int zzezo;

    public TextTrackStyle() {
        this(1.0f, 0, 0, -1, 0, -1, 0, 0, (String) null, -1, -1, (String) null);
    }

    TextTrackStyle(float f, int i, int i2, int i3, int i4, int i5, int i6, int i7, String str, int i8, int i9, String str2) {
        this.zzezf = f;
        this.zzezg = i;
        this.zzbxm = i2;
        this.zzezh = i3;
        this.zzezi = i4;
        this.zzezj = i5;
        this.zzezk = i6;
        this.zzezl = i7;
        this.zzezm = str;
        this.zzezn = i8;
        this.zzezo = i9;
        this.zzesn = str2;
        if (str2 != null) {
            try {
                this.zzess = new JSONObject(this.zzesn);
            } catch (JSONException unused) {
                this.zzess = null;
                this.zzesn = null;
            }
        } else {
            this.zzess = null;
        }
    }

    public static TextTrackStyle fromSystemSettings(Context context) {
        TextTrackStyle textTrackStyle = new TextTrackStyle();
        if (!zzs.zzanv()) {
            return textTrackStyle;
        }
        CaptioningManager captioningManager = (CaptioningManager) context.getSystemService("captioning");
        textTrackStyle.setFontScale(captioningManager.getFontScale());
        CaptioningManager.CaptionStyle userStyle = captioningManager.getUserStyle();
        textTrackStyle.setBackgroundColor(userStyle.backgroundColor);
        textTrackStyle.setForegroundColor(userStyle.foregroundColor);
        int i = userStyle.edgeType;
        if (i == 1) {
            textTrackStyle.setEdgeType(1);
        } else if (i != 2) {
            textTrackStyle.setEdgeType(0);
        } else {
            textTrackStyle.setEdgeType(2);
        }
        textTrackStyle.setEdgeColor(userStyle.edgeColor);
        Typeface typeface = userStyle.getTypeface();
        if (typeface != null) {
            if (Typeface.MONOSPACE.equals(typeface)) {
                textTrackStyle.setFontGenericFamily(1);
            } else if (Typeface.SANS_SERIF.equals(typeface) || !Typeface.SERIF.equals(typeface)) {
                textTrackStyle.setFontGenericFamily(0);
            } else {
                textTrackStyle.setFontGenericFamily(2);
            }
            boolean isBold = typeface.isBold();
            boolean isItalic = typeface.isItalic();
            if (isBold && isItalic) {
                textTrackStyle.setFontStyle(3);
            } else if (isBold) {
                textTrackStyle.setFontStyle(1);
            } else if (isItalic) {
                textTrackStyle.setFontStyle(2);
            } else {
                textTrackStyle.setFontStyle(0);
            }
        }
        return textTrackStyle;
    }

    private static String zzbd(int i) {
        return String.format("#%02X%02X%02X%02X", new Object[]{Integer.valueOf(Color.red(i)), Integer.valueOf(Color.green(i)), Integer.valueOf(Color.blue(i)), Integer.valueOf(Color.alpha(i))});
    }

    private static int zzfo(String str) {
        if (str != null && str.length() == 9 && str.charAt(0) == '#') {
            try {
                return Color.argb(Integer.parseInt(str.substring(7, 9), 16), Integer.parseInt(str.substring(1, 3), 16), Integer.parseInt(str.substring(3, 5), 16), Integer.parseInt(str.substring(5, 7), 16));
            } catch (NumberFormatException unused) {
            }
        }
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TextTrackStyle)) {
            return false;
        }
        TextTrackStyle textTrackStyle = (TextTrackStyle) obj;
        JSONObject jSONObject = this.zzess;
        boolean z = jSONObject == null;
        JSONObject jSONObject2 = textTrackStyle.zzess;
        if (z != (jSONObject2 == null)) {
            return false;
        }
        return (jSONObject == null || jSONObject2 == null || zzq.zzc(jSONObject, jSONObject2)) && this.zzezf == textTrackStyle.zzezf && this.zzezg == textTrackStyle.zzezg && this.zzbxm == textTrackStyle.zzbxm && this.zzezh == textTrackStyle.zzezh && this.zzezi == textTrackStyle.zzezi && this.zzezj == textTrackStyle.zzezj && this.zzezl == textTrackStyle.zzezl && zzbdw.zza(this.zzezm, textTrackStyle.zzezm) && this.zzezn == textTrackStyle.zzezn && this.zzezo == textTrackStyle.zzezo;
    }

    public final int getBackgroundColor() {
        return this.zzbxm;
    }

    public final JSONObject getCustomData() {
        return this.zzess;
    }

    public final int getEdgeColor() {
        return this.zzezi;
    }

    public final int getEdgeType() {
        return this.zzezh;
    }

    public final String getFontFamily() {
        return this.zzezm;
    }

    public final int getFontGenericFamily() {
        return this.zzezn;
    }

    public final float getFontScale() {
        return this.zzezf;
    }

    public final int getFontStyle() {
        return this.zzezo;
    }

    public final int getForegroundColor() {
        return this.zzezg;
    }

    public final int getWindowColor() {
        return this.zzezk;
    }

    public final int getWindowCornerRadius() {
        return this.zzezl;
    }

    public final int getWindowType() {
        return this.zzezj;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Float.valueOf(this.zzezf), Integer.valueOf(this.zzezg), Integer.valueOf(this.zzbxm), Integer.valueOf(this.zzezh), Integer.valueOf(this.zzezi), Integer.valueOf(this.zzezj), Integer.valueOf(this.zzezk), Integer.valueOf(this.zzezl), this.zzezm, Integer.valueOf(this.zzezn), Integer.valueOf(this.zzezo), String.valueOf(this.zzess)});
    }

    public final void setBackgroundColor(int i) {
        this.zzbxm = i;
    }

    public final void setCustomData(JSONObject jSONObject) {
        this.zzess = jSONObject;
    }

    public final void setEdgeColor(int i) {
        this.zzezi = i;
    }

    public final void setEdgeType(int i) {
        if (i < 0 || i > 4) {
            throw new IllegalArgumentException("invalid edgeType");
        }
        this.zzezh = i;
    }

    public final void setFontFamily(String str) {
        this.zzezm = str;
    }

    public final void setFontGenericFamily(int i) {
        if (i < 0 || i > 6) {
            throw new IllegalArgumentException("invalid fontGenericFamily");
        }
        this.zzezn = i;
    }

    public final void setFontScale(float f) {
        this.zzezf = f;
    }

    public final void setFontStyle(int i) {
        if (i < 0 || i > 3) {
            throw new IllegalArgumentException("invalid fontStyle");
        }
        this.zzezo = i;
    }

    public final void setForegroundColor(int i) {
        this.zzezg = i;
    }

    public final void setWindowColor(int i) {
        this.zzezk = i;
    }

    public final void setWindowCornerRadius(int i) {
        if (i >= 0) {
            this.zzezl = i;
            return;
        }
        throw new IllegalArgumentException("invalid windowCornerRadius");
    }

    public final void setWindowType(int i) {
        if (i < 0 || i > 2) {
            throw new IllegalArgumentException("invalid windowType");
        }
        this.zzezj = i;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:50:?, code lost:
        r0.put("fontGenericFamily", r1);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final org.json.JSONObject toJson() {
        /*
            r8 = this;
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
            java.lang.String r1 = "fontScale"
            float r2 = r8.zzezf     // Catch:{ JSONException -> 0x00db }
            double r2 = (double) r2     // Catch:{ JSONException -> 0x00db }
            r0.put(r1, r2)     // Catch:{ JSONException -> 0x00db }
            int r1 = r8.zzezg     // Catch:{ JSONException -> 0x00db }
            if (r1 == 0) goto L_0x001a
            java.lang.String r2 = "foregroundColor"
            java.lang.String r1 = zzbd(r1)     // Catch:{ JSONException -> 0x00db }
            r0.put(r2, r1)     // Catch:{ JSONException -> 0x00db }
        L_0x001a:
            int r1 = r8.zzbxm     // Catch:{ JSONException -> 0x00db }
            if (r1 == 0) goto L_0x0027
            java.lang.String r2 = "backgroundColor"
            java.lang.String r1 = zzbd(r1)     // Catch:{ JSONException -> 0x00db }
            r0.put(r2, r1)     // Catch:{ JSONException -> 0x00db }
        L_0x0027:
            int r1 = r8.zzezh     // Catch:{ JSONException -> 0x00db }
            java.lang.String r2 = "NONE"
            r3 = 3
            r4 = 1
            r5 = 2
            java.lang.String r6 = "edgeType"
            if (r1 == 0) goto L_0x004b
            if (r1 == r4) goto L_0x0048
            if (r1 == r5) goto L_0x0045
            if (r1 == r3) goto L_0x0042
            r7 = 4
            if (r1 == r7) goto L_0x003c
            goto L_0x004e
        L_0x003c:
            java.lang.String r1 = "DEPRESSED"
        L_0x003e:
            r0.put(r6, r1)     // Catch:{ JSONException -> 0x00db }
            goto L_0x004e
        L_0x0042:
            java.lang.String r1 = "RAISED"
            goto L_0x003e
        L_0x0045:
            java.lang.String r1 = "DROP_SHADOW"
            goto L_0x003e
        L_0x0048:
            java.lang.String r1 = "OUTLINE"
            goto L_0x003e
        L_0x004b:
            r0.put(r6, r2)     // Catch:{ JSONException -> 0x00db }
        L_0x004e:
            int r1 = r8.zzezi     // Catch:{ JSONException -> 0x00db }
            if (r1 == 0) goto L_0x005b
            java.lang.String r6 = "edgeColor"
            java.lang.String r1 = zzbd(r1)     // Catch:{ JSONException -> 0x00db }
            r0.put(r6, r1)     // Catch:{ JSONException -> 0x00db }
        L_0x005b:
            int r1 = r8.zzezj     // Catch:{ JSONException -> 0x00db }
            java.lang.String r6 = "NORMAL"
            java.lang.String r7 = "windowType"
            if (r1 == 0) goto L_0x0072
            if (r1 == r4) goto L_0x006e
            if (r1 == r5) goto L_0x0068
            goto L_0x0075
        L_0x0068:
            java.lang.String r1 = "ROUNDED_CORNERS"
            r0.put(r7, r1)     // Catch:{ JSONException -> 0x00db }
            goto L_0x0075
        L_0x006e:
            r0.put(r7, r6)     // Catch:{ JSONException -> 0x00db }
            goto L_0x0075
        L_0x0072:
            r0.put(r7, r2)     // Catch:{ JSONException -> 0x00db }
        L_0x0075:
            int r1 = r8.zzezk     // Catch:{ JSONException -> 0x00db }
            if (r1 == 0) goto L_0x0082
            java.lang.String r2 = "windowColor"
            java.lang.String r1 = zzbd(r1)     // Catch:{ JSONException -> 0x00db }
            r0.put(r2, r1)     // Catch:{ JSONException -> 0x00db }
        L_0x0082:
            int r1 = r8.zzezj     // Catch:{ JSONException -> 0x00db }
            if (r1 != r5) goto L_0x008d
            java.lang.String r1 = "windowRoundedCornerRadius"
            int r2 = r8.zzezl     // Catch:{ JSONException -> 0x00db }
            r0.put(r1, r2)     // Catch:{ JSONException -> 0x00db }
        L_0x008d:
            java.lang.String r1 = r8.zzezm     // Catch:{ JSONException -> 0x00db }
            if (r1 == 0) goto L_0x0096
            java.lang.String r2 = "fontFamily"
            r0.put(r2, r1)     // Catch:{ JSONException -> 0x00db }
        L_0x0096:
            int r1 = r8.zzezn     // Catch:{ JSONException -> 0x00db }
            java.lang.String r2 = "fontGenericFamily"
            switch(r1) {
                case 0: goto L_0x00b3;
                case 1: goto L_0x00b0;
                case 2: goto L_0x00ad;
                case 3: goto L_0x00aa;
                case 4: goto L_0x00a7;
                case 5: goto L_0x00a4;
                case 6: goto L_0x009e;
                default: goto L_0x009d;
            }
        L_0x009d:
            goto L_0x00b6
        L_0x009e:
            java.lang.String r1 = "SMALL_CAPITALS"
        L_0x00a0:
            r0.put(r2, r1)     // Catch:{ JSONException -> 0x00db }
            goto L_0x00b6
        L_0x00a4:
            java.lang.String r1 = "CURSIVE"
            goto L_0x00a0
        L_0x00a7:
            java.lang.String r1 = "CASUAL"
            goto L_0x00a0
        L_0x00aa:
            java.lang.String r1 = "MONOSPACED_SERIF"
            goto L_0x00a0
        L_0x00ad:
            java.lang.String r1 = "SERIF"
            goto L_0x00a0
        L_0x00b0:
            java.lang.String r1 = "MONOSPACED_SANS_SERIF"
            goto L_0x00a0
        L_0x00b3:
            java.lang.String r1 = "SANS_SERIF"
            goto L_0x00a0
        L_0x00b6:
            int r1 = r8.zzezo     // Catch:{ JSONException -> 0x00db }
            java.lang.String r2 = "fontStyle"
            if (r1 == 0) goto L_0x00cf
            if (r1 == r4) goto L_0x00cc
            if (r1 == r5) goto L_0x00c9
            if (r1 == r3) goto L_0x00c3
            goto L_0x00d2
        L_0x00c3:
            java.lang.String r1 = "BOLD_ITALIC"
        L_0x00c5:
            r0.put(r2, r1)     // Catch:{ JSONException -> 0x00db }
            goto L_0x00d2
        L_0x00c9:
            java.lang.String r1 = "ITALIC"
            goto L_0x00c5
        L_0x00cc:
            java.lang.String r1 = "BOLD"
            goto L_0x00c5
        L_0x00cf:
            r0.put(r2, r6)     // Catch:{ JSONException -> 0x00db }
        L_0x00d2:
            org.json.JSONObject r1 = r8.zzess     // Catch:{ JSONException -> 0x00db }
            if (r1 == 0) goto L_0x00db
            java.lang.String r2 = "customData"
            r0.put(r2, r1)     // Catch:{ JSONException -> 0x00db }
        L_0x00db:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.cast.TextTrackStyle.toJson():org.json.JSONObject");
    }

    public final void writeToParcel(Parcel parcel, int i) {
        JSONObject jSONObject = this.zzess;
        this.zzesn = jSONObject == null ? null : jSONObject.toString();
        int zze = zzbgo.zze(parcel);
        zzbgo.zza(parcel, 2, getFontScale());
        zzbgo.zzc(parcel, 3, getForegroundColor());
        zzbgo.zzc(parcel, 4, getBackgroundColor());
        zzbgo.zzc(parcel, 5, getEdgeType());
        zzbgo.zzc(parcel, 6, getEdgeColor());
        zzbgo.zzc(parcel, 7, getWindowType());
        zzbgo.zzc(parcel, 8, getWindowColor());
        zzbgo.zzc(parcel, 9, getWindowCornerRadius());
        zzbgo.zza(parcel, 10, getFontFamily(), false);
        zzbgo.zzc(parcel, 11, getFontGenericFamily());
        zzbgo.zzc(parcel, 12, getFontStyle());
        zzbgo.zza(parcel, 13, this.zzesn, false);
        zzbgo.zzai(parcel, zze);
    }

    public final void zzt(JSONObject jSONObject) throws JSONException {
        int i = -1;
        this.zzezf = (float) jSONObject.optDouble("fontScale", 1.0d);
        this.zzezg = zzfo(jSONObject.optString("foregroundColor"));
        this.zzbxm = zzfo(jSONObject.optString("backgroundColor"));
        if (jSONObject.has("edgeType")) {
            String string = jSONObject.getString("edgeType");
            if ("NONE".equals(string)) {
                this.zzezh = 0;
            } else if ("OUTLINE".equals(string)) {
                this.zzezh = 1;
            } else if ("DROP_SHADOW".equals(string)) {
                this.zzezh = 2;
            } else if ("RAISED".equals(string)) {
                this.zzezh = 3;
            } else if ("DEPRESSED".equals(string)) {
                this.zzezh = 4;
            }
        }
        this.zzezi = zzfo(jSONObject.optString("edgeColor"));
        if (jSONObject.has("windowType")) {
            String string2 = jSONObject.getString("windowType");
            if ("NONE".equals(string2)) {
                this.zzezj = 0;
            } else if ("NORMAL".equals(string2)) {
                this.zzezj = 1;
            } else if ("ROUNDED_CORNERS".equals(string2)) {
                this.zzezj = 2;
            }
        }
        this.zzezk = zzfo(jSONObject.optString("windowColor"));
        if (this.zzezj == 2) {
            this.zzezl = jSONObject.optInt("windowRoundedCornerRadius", 0);
        }
        this.zzezm = jSONObject.optString("fontFamily", (String) null);
        if (jSONObject.has("fontGenericFamily")) {
            String string3 = jSONObject.getString("fontGenericFamily");
            if ("SANS_SERIF".equals(string3)) {
                this.zzezn = 0;
            } else if ("MONOSPACED_SANS_SERIF".equals(string3)) {
                this.zzezn = 1;
            } else if ("SERIF".equals(string3)) {
                this.zzezn = 2;
            } else if ("MONOSPACED_SERIF".equals(string3)) {
                this.zzezn = 3;
            } else if ("CASUAL".equals(string3)) {
                this.zzezn = 4;
            } else {
                if ("CURSIVE".equals(string3)) {
                    i = 5;
                } else if ("SMALL_CAPITALS".equals(string3)) {
                    i = 6;
                }
                this.zzezn = i;
            }
        }
        if (jSONObject.has("fontStyle")) {
            String string4 = jSONObject.getString("fontStyle");
            if ("NORMAL".equals(string4)) {
                this.zzezo = 0;
            } else if ("BOLD".equals(string4)) {
                this.zzezo = 1;
            } else if ("ITALIC".equals(string4)) {
                this.zzezo = 2;
            } else if ("BOLD_ITALIC".equals(string4)) {
                this.zzezo = 3;
            }
        }
        this.zzess = jSONObject.optJSONObject("customData");
    }
}
