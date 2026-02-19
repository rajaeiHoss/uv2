package com.google.android.gms.vision.text;

import android.graphics.Point;
import android.graphics.Rect;
import android.util.SparseArray;
import com.google.android.gms.internal.zzdlf;
import com.google.android.gms.internal.zzdll;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TextBlock implements Text {
    private Point[] cornerPoints;
    private zzdll[] zzlhz;
    private List<Line> zzlia;
    private String zzlib;
    private Rect zzlic;

    TextBlock(SparseArray<zzdll> sparseArray) {
        this.zzlhz = new zzdll[sparseArray.size()];
        int i = 0;
        while (true) {
            zzdll[] zzdllArr = this.zzlhz;
            if (i < zzdllArr.length) {
                zzdllArr[i] = sparseArray.valueAt(i);
                i++;
            } else {
                return;
            }
        }
    }

    private static Point[] zza(int i, int i2, int i3, int i4, zzdlf zzdlf) {
        int i5 = zzdlf.left;
        int i6 = zzdlf.top;
        double sin = Math.sin(Math.toRadians((double) zzdlf.zzlif));
        double cos = Math.cos(Math.toRadians((double) zzdlf.zzlif));
        Point[] pointArr = {new Point(i, i2), new Point(i3, i2), new Point(i3, i4), new Point(i, i4)};
        for (int i7 = 0; i7 < 4; i7++) {
            pointArr[i7].x = (int) ((((double) pointArr[i7].x) * cos) - (((double) pointArr[i7].y) * sin));
            pointArr[i7].y = (int) ((((double) pointArr[i7].x) * sin) + (((double) pointArr[i7].y) * cos));
            pointArr[i7].offset(i5, i6);
        }
        return pointArr;
    }

    public Rect getBoundingBox() {
        if (this.zzlic == null) {
            this.zzlic = zzc.zza((Text) this);
        }
        return this.zzlic;
    }

    public List<? extends Text> getComponents() {
        if (this.zzlhz.length == 0) {
            return new ArrayList(0);
        }
        if (this.zzlia == null) {
            this.zzlia = new ArrayList(this.zzlhz.length);
            for (zzdll line : this.zzlhz) {
                this.zzlia.add(new Line(line));
            }
        }
        return this.zzlia;
    }

    public Point[] getCornerPoints() {
        TextBlock textBlock;
        zzdll[] zzdllArr;
        TextBlock textBlock2 = this;
        if (textBlock2.cornerPoints == null) {
            char c = 0;
            if (textBlock2.zzlhz.length == 0) {
                textBlock2.cornerPoints = new Point[0];
            } else {
                int i = Integer.MIN_VALUE;
                int i2 = Integer.MIN_VALUE;
                int i3 = Integer.MAX_VALUE;
                int i4 = Integer.MAX_VALUE;
                int i5 = 0;
                while (true) {
                    zzdllArr = textBlock2.zzlhz;
                    if (i5 >= zzdllArr.length) {
                        break;
                    }
                    zzdlf zzdlf = zzdllArr[i5].zzlih;
                    zzdlf zzdlf2 = textBlock2.zzlhz[c].zzlih;
                    double sin = Math.sin(Math.toRadians((double) zzdlf2.zzlif));
                    double cos = Math.cos(Math.toRadians((double) zzdlf2.zzlif));
                    Point[] pointArr = new Point[4];
                    pointArr[0] = new Point(zzdlf.left, zzdlf.top);
                    pointArr[0].offset(-zzdlf2.left, -zzdlf2.top);
                    int i6 = (int) ((((double) pointArr[0].x) * cos) + (((double) pointArr[0].y) * sin));
                    int i7 = (int) ((((double) (-pointArr[0].x)) * sin) + (((double) pointArr[0].y) * cos));
                    pointArr[0].x = i6;
                    pointArr[0].y = i7;
                    pointArr[1] = new Point(zzdlf.width + i6, i7);
                    pointArr[2] = new Point(zzdlf.width + i6, zzdlf.height + i7);
                    pointArr[3] = new Point(i6, i7 + zzdlf.height);
                    i = i;
                    for (int i8 = 0; i8 < 4; i8++) {
                        Point point = pointArr[i8];
                        i3 = Math.min(i3, point.x);
                        i = Math.max(i, point.x);
                        i4 = Math.min(i4, point.y);
                        i2 = Math.max(i2, point.y);
                    }
                    i5++;
                    c = 0;
                    textBlock2 = this;
                }
                textBlock = this;
                textBlock.cornerPoints = zza(i3, i4, i, i2, zzdllArr[0].zzlih);
                return textBlock.cornerPoints;
            }
        }
        textBlock = textBlock2;
        return textBlock.cornerPoints;
    }

    public String getLanguage() {
        String str = this.zzlib;
        if (str != null) {
            return str;
        }
        HashMap hashMap = new HashMap();
        for (zzdll zzdll : this.zzlhz) {
            hashMap.put(zzdll.zzlib, Integer.valueOf((hashMap.containsKey(zzdll.zzlib) ? ((Integer) hashMap.get(zzdll.zzlib)).intValue() : 0) + 1));
        }
        String str2 = (String) ((Map.Entry) Collections.max(hashMap.entrySet(), new zza(this))).getKey();
        this.zzlib = str2;
        if (str2 == null || str2.isEmpty()) {
            this.zzlib = "und";
        }
        return this.zzlib;
    }

    public String getValue() {
        zzdll[] zzdllArr = this.zzlhz;
        if (zzdllArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(zzdllArr[0].zzlik);
        for (int i = 1; i < this.zzlhz.length; i++) {
            sb.append("\n");
            sb.append(this.zzlhz[i].zzlik);
        }
        return sb.toString();
    }
}
