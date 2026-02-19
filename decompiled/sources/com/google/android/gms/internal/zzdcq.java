package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbq;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.ArrayList;
import java.util.List;

public final class zzdcq {
    public static double zza(zzdjq<?> zzdjq, zzdjq<?> zzdjq2) {
        boolean z = true;
        zzbq.checkArgument(zzdjq != null);
        if (zzdjq2 == null) {
            z = false;
        }
        zzbq.checkArgument(z);
        double zzb = zzb(zzdjq);
        double zzb2 = zzb(zzdjq2);
        if (Double.isNaN(zzb) || Double.isNaN(zzb2)) {
            return Double.NaN;
        }
        if ((zzb == Double.POSITIVE_INFINITY && zzb2 == Double.NEGATIVE_INFINITY) || (zzb == Double.NEGATIVE_INFINITY && zzb2 == Double.POSITIVE_INFINITY)) {
            return Double.NaN;
        }
        return (!Double.isInfinite(zzb) || Double.isInfinite(zzb2)) ? (Double.isInfinite(zzb) || !Double.isInfinite(zzb2)) ? zzb + zzb2 : zzb2 : zzb;
    }

    public static boolean zza(zzdjq<?> zzdjq) {
        zzbq.checkArgument(zzdjq != null);
        if (zzdjq == zzdjw.zzlcz || zzdjq == zzdjw.zzlcy) {
            return false;
        }
        if (zzdjq instanceof zzdjt) {
            return ((Boolean) ((zzdjt) zzdjq).value()).booleanValue();
        }
        if (zzdjq instanceof zzdju) {
            zzdju zzdju = (zzdju) zzdjq;
            if (((Double) zzdju.value()).doubleValue() == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE || ((Double) zzdju.value()).doubleValue() == -0.0d || Double.isNaN(((Double) zzdju.value()).doubleValue())) {
                return false;
            }
        } else if (zzdjq instanceof zzdkc) {
            if (((String) ((zzdkc) zzdjq).value()).isEmpty()) {
                return false;
            }
        } else if (zzf(zzdjq)) {
            String zzdjq2 = zzdjq.toString();
            StringBuilder sb = new StringBuilder(String.valueOf(zzdjq2).length() + 33);
            sb.append("Illegal type given to isTruthy: ");
            sb.append(zzdjq2);
            sb.append(".");
            throw new IllegalArgumentException(sb.toString());
        }
        return true;
    }

    public static double zzb(zzdjq<?> zzdjq) {
        while (true) {
            zzbq.checkArgument(zzdjq != null);
            if (zzdjq == zzdjw.zzlcz) {
                return Double.NaN;
            }
            if (zzdjq == zzdjw.zzlcy) {
                return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
            }
            if (zzdjq instanceof zzdjt) {
                if (((Boolean) ((zzdjt) zzdjq).value()).booleanValue()) {
                    return 1.0d;
                }
                return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
            } else if (zzdjq instanceof zzdju) {
                return ((Double) ((zzdju) zzdjq).value()).doubleValue();
            } else {
                if (zzdjq instanceof zzdjx) {
                    zzdjx zzdjx = (zzdjx) zzdjq;
                    if (!((List) zzdjx.value()).isEmpty()) {
                        if (((List) zzdjx.value()).size() != 1) {
                            break;
                        }
                        zzdjq = new zzdkc(zzd(zzdjx.zzfh(0)));
                    } else {
                        return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
                    }
                } else if (zzdjq instanceof zzdkc) {
                    zzdkc zzdkc = (zzdkc) zzdjq;
                    if (((String) zzdkc.value()).isEmpty()) {
                        return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
                    }
                    try {
                        return Double.parseDouble((String) zzdkc.value());
                    } catch (NumberFormatException unused) {
                        return Double.NaN;
                    }
                }
            }
        }
        if (!zzf(zzdjq)) {
            return Double.NaN;
        }
        String zzdjq2 = zzdjq.toString();
        StringBuilder sb = new StringBuilder(String.valueOf(zzdjq2).length() + 41);
        sb.append("Illegal type given to numberEquivalent: ");
        sb.append(zzdjq2);
        sb.append(".");
        throw new IllegalArgumentException(sb.toString());
    }

    public static boolean zzb(zzdjq<?> zzdjq, zzdjq<?> zzdjq2) {
        zzbq.checkArgument(zzdjq != null);
        zzbq.checkArgument(zzdjq2 != null);
        if (zzf(zzdjq)) {
            String zzdjq3 = zzdjq.toString();
            StringBuilder sb = new StringBuilder(String.valueOf(zzdjq3).length() + 50);
            sb.append("Illegal type given to abstractRelationalCompare: ");
            sb.append(zzdjq3);
            sb.append(".");
            throw new IllegalArgumentException(sb.toString());
        } else if (!zzf(zzdjq2)) {
            if ((zzdjq instanceof zzdka) || (zzdjq instanceof zzdjx) || (zzdjq instanceof zzdjv)) {
                zzdjq = new zzdkc(zzd(zzdjq));
            }
            if ((zzdjq2 instanceof zzdka) || (zzdjq2 instanceof zzdjx) || (zzdjq2 instanceof zzdjv)) {
                zzdjq2 = new zzdkc(zzd(zzdjq2));
            }
            if ((zzdjq instanceof zzdkc) && (zzdjq2 instanceof zzdkc)) {
                return ((String) ((zzdkc) zzdjq).value()).compareTo((String) ((zzdkc) zzdjq2).value()) < 0;
            }
            double zzb = zzb(zzdjq);
            double zzb2 = zzb(zzdjq2);
            if (Double.isNaN(zzb) || Double.isNaN(zzb2) || ((zzb == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE && zzb2 == -0.0d) || ((zzb == -0.0d && zzb2 == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) || zzb == Double.POSITIVE_INFINITY))) {
                return false;
            }
            if (zzb2 == Double.POSITIVE_INFINITY) {
                return true;
            }
            if (zzb2 == Double.NEGATIVE_INFINITY) {
                return false;
            }
            return zzb == Double.NEGATIVE_INFINITY || Double.compare(zzb, zzb2) < 0;
        } else {
            String zzdjq4 = zzdjq2.toString();
            StringBuilder sb2 = new StringBuilder(String.valueOf(zzdjq4).length() + 50);
            sb2.append("Illegal type given to abstractRelationalCompare: ");
            sb2.append(zzdjq4);
            sb2.append(".");
            throw new IllegalArgumentException(sb2.toString());
        }
    }

    public static double zzc(zzdjq<?> zzdjq) {
        double zzb = zzb(zzdjq);
        return Double.isNaN(zzb) ? FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE : (zzb == FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE || zzb == -0.0d || Double.isInfinite(zzb)) ? zzb : Math.signum(zzb) * Math.floor(Math.abs(zzb));
    }

    public static boolean zzc(zzdjq<?> zzdjq, zzdjq<?> zzdjq2) {
        while (true) {
            zzbq.checkArgument(zzdjq != null);
            zzbq.checkArgument(zzdjq2 != null);
            if (zzf(zzdjq)) {
                String zzdjq4 = zzdjq.toString();
                StringBuilder sb = new StringBuilder(String.valueOf(zzdjq4).length() + 48);
                sb.append("Illegal type given to abstractEqualityCompare: ");
                sb.append(zzdjq4);
                sb.append(".");
                throw new IllegalArgumentException(sb.toString());
            } else if (!zzf(zzdjq2)) {
                String zze = zze(zzdjq);
                String zze2 = zze(zzdjq2);
                if (zze.equals(zze2)) {
                    zze.hashCode();
                    char c = 65535;
                    switch (zze.hashCode()) {
                        case -1950496919:
                            if (zze.equals("Number")) {
                                c = 0;
                                break;
                            }
                            break;
                        case -1939501217:
                            if (zze.equals("Object")) {
                                c = 1;
                                break;
                            }
                            break;
                        case -1808118735:
                            if (zze.equals("String")) {
                                c = 2;
                                break;
                            }
                            break;
                        case 2439591:
                            if (zze.equals("Null")) {
                                c = 3;
                                break;
                            }
                            break;
                        case 965837104:
                            if (zze.equals("Undefined")) {
                                c = 4;
                                break;
                            }
                            break;
                        case 1729365000:
                            if (zze.equals("Boolean")) {
                                c = 5;
                                break;
                            }
                            break;
                    }
                    switch (c) {
                        case 0:
                            double doubleValue = ((Double) ((zzdju) zzdjq).value()).doubleValue();
                            double doubleValue2 = ((Double) ((zzdju) zzdjq2).value()).doubleValue();
                            return !Double.isNaN(doubleValue) && !Double.isNaN(doubleValue2) && doubleValue == doubleValue2;
                        case 1:
                            return zzdjq == zzdjq2;
                        case 2:
                            return ((String) ((zzdkc) zzdjq).value()).equals((String) ((zzdkc) zzdjq2).value());
                        case 3:
                        case 4:
                            return true;
                        case 5:
                            return ((Boolean) ((zzdjt) zzdjq).value()) == ((Boolean) ((zzdjt) zzdjq2).value());
                        default:
                            return false;
                    }
                } else if ((zzdjq == zzdjw.zzlcz || zzdjq == zzdjw.zzlcy) && (zzdjq2 == zzdjw.zzlcz || zzdjq2 == zzdjw.zzlcy)) {
                    return true;
                } else {
                    if (!zze.equals("Number") || !zze2.equals("String")) {
                        if (zze.equals("String") && zze2.equals("Number")) {
                            zzdjq = new zzdju(Double.valueOf(zzb(zzdjq)));
                        } else if (zze.equals("Boolean")) {
                            zzdjq = new zzdju(Double.valueOf(zzb(zzdjq)));
                        } else if (zze2.equals("Boolean")) {
                            zzdjq2 = new zzdju(Double.valueOf(zzb(zzdjq2)));
                        } else if ((zze.equals("String") || zze.equals("Number")) && zze2.equals("Object")) {
                            zzdjq2 = new zzdkc(zzd(zzdjq2));
                        } else if (zze.equals("Object") && (zze2.equals("String") || zze2.equals("Number"))) {
                            zzdjq = new zzdkc(zzd(zzdjq));
                        } else {
                            return false;
                        }
                    } else {
                        zzdjq2 = new zzdju(Double.valueOf(zzb(zzdjq2)));
                    }
                }
            } else {
                String zzdjq5 = zzdjq2.toString();
                StringBuilder sb2 = new StringBuilder(String.valueOf(zzdjq5).length() + 48);
                sb2.append("Illegal type given to abstractEqualityCompare: ");
                sb2.append(zzdjq5);
                sb2.append(".");
                throw new IllegalArgumentException(sb2.toString());
            }
        }
    }

    public static String zzd(zzdjq<?> zzdjq) {
        String str;
        String str2;
        zzbq.checkArgument(zzdjq != null);
        if (zzdjq == zzdjw.zzlcz) {
            return "undefined";
        }
        if (zzdjq == zzdjw.zzlcy) {
            return "null";
        }
        if (zzdjq instanceof zzdjt) {
            return ((Boolean) ((zzdjt) zzdjq).value()).booleanValue() ? "true" : "false";
        }
        if (zzdjq instanceof zzdju) {
            String d = Double.toString(((Double) ((zzdju) zzdjq).value()).doubleValue());
            int indexOf = d.indexOf("E");
            if (indexOf > 0) {
                int parseInt = Integer.parseInt(d.substring(indexOf + 1, d.length()));
                if (parseInt < 0) {
                    if (parseInt > -7) {
                        String replace = d.substring(0, indexOf).replace(".", "");
                        StringBuilder sb = new StringBuilder();
                        sb.append("0.");
                        while (true) {
                            parseInt++;
                            if (parseInt < 0) {
                                sb.append("0");
                            } else {
                                sb.append(replace);
                                return sb.toString();
                            }
                        }
                    } else {
                        str2 = "e";
                    }
                } else if (parseInt < 21) {
                    String replace2 = d.substring(0, indexOf).replace(".", "");
                    int length = (parseInt + 1) - (replace2.length() - (replace2.startsWith("-") ? 1 : 0));
                    StringBuilder sb2 = new StringBuilder();
                    if (length < 0) {
                        int length2 = replace2.length() + length;
                        sb2.append(replace2.substring(0, length2));
                        sb2.append(".");
                        sb2.append(replace2.substring(length2, replace2.length()));
                    } else {
                        sb2.append(replace2);
                        while (length > 0) {
                            sb2.append("0");
                            length--;
                        }
                    }
                    return sb2.toString();
                } else {
                    str2 = "e+";
                }
                return d.replace("E", str2);
            } else if (!d.endsWith(".0")) {
                return d;
            } else {
                String substring = d.substring(0, d.length() - 2);
                return substring.equals("-0") ? "0" : substring;
            }
        } else {
            if (zzdjq instanceof zzdjv) {
                zzdcp zzdcp = (zzdcp) ((zzdjv) zzdjq).value();
                if (zzdcp instanceof zzdco) {
                    return ((zzdco) zzdcp).getName();
                }
            } else if (zzdjq instanceof zzdjx) {
                ArrayList arrayList = new ArrayList();
                for (Object value : (List) ((zzdjx) zzdjq).value()) {
                    zzdjq zzdjq2 = (zzdjq) value;
                    if (zzdjq2 == zzdjw.zzlcy || zzdjq2 == zzdjw.zzlcz) {
                        arrayList.add("");
                    } else {
                        arrayList.add(zzd(zzdjq2));
                    }
                }
                return TextUtils.join(",", arrayList);
            } else if (zzdjq instanceof zzdka) {
                return "[object Object]";
            } else {
                if (zzdjq instanceof zzdkc) {
                    return (String) ((zzdkc) zzdjq).value();
                }
            }
            if (zzf(zzdjq)) {
                String zzdjq3 = zzdjq.toString();
                StringBuilder sb3 = new StringBuilder(String.valueOf(zzdjq3).length() + 41);
                sb3.append("Illegal type given to stringEquivalent: ");
                sb3.append(zzdjq3);
                sb3.append(".");
                str = sb3.toString();
            } else {
                str = "Unknown type in stringEquivalent.";
            }
            throw new IllegalArgumentException(str);
        }
    }

    public static boolean zzd(zzdjq<?> zzdjq, zzdjq<?> zzdjq2) {
        zzbq.checkArgument(zzdjq != null);
        zzbq.checkArgument(zzdjq2 != null);
        if (zzf(zzdjq)) {
            String zzdjq3 = zzdjq.toString();
            StringBuilder sb = new StringBuilder(String.valueOf(zzdjq3).length() + 46);
            sb.append("Illegal type given to strictEqualityCompare: ");
            sb.append(zzdjq3);
            sb.append(".");
            throw new IllegalArgumentException(sb.toString());
        } else if (!zzf(zzdjq2)) {
            String zze = zze(zzdjq);
            if (!zze.equals(zze(zzdjq2))) {
                return false;
            }
            zze.hashCode();
            char c = 65535;
            switch (zze.hashCode()) {
                case -1950496919:
                    if (zze.equals("Number")) {
                        c = 0;
                        break;
                    }
                    break;
                case -1808118735:
                    if (zze.equals("String")) {
                        c = 1;
                        break;
                    }
                    break;
                case 2439591:
                    if (zze.equals("Null")) {
                        c = 2;
                        break;
                    }
                    break;
                case 965837104:
                    if (zze.equals("Undefined")) {
                        c = 3;
                        break;
                    }
                    break;
                case 1729365000:
                    if (zze.equals("Boolean")) {
                        c = 4;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    double doubleValue = ((Double) ((zzdju) zzdjq).value()).doubleValue();
                    double doubleValue2 = ((Double) ((zzdju) zzdjq2).value()).doubleValue();
                    return !Double.isNaN(doubleValue) && !Double.isNaN(doubleValue2) && doubleValue == doubleValue2;
                case 1:
                    return ((String) ((zzdkc) zzdjq).value()).equals((String) ((zzdkc) zzdjq2).value());
                case 2:
                case 3:
                    return true;
                case 4:
                    return ((Boolean) ((zzdjt) zzdjq).value()) == ((Boolean) ((zzdjt) zzdjq2).value());
                default:
                    return zzdjq == zzdjq2;
            }
        } else {
            String zzdjq4 = zzdjq2.toString();
            StringBuilder sb2 = new StringBuilder(String.valueOf(zzdjq4).length() + 46);
            sb2.append("Illegal type given to strictEqualityCompare: ");
            sb2.append(zzdjq4);
            sb2.append(".");
            throw new IllegalArgumentException(sb2.toString());
        }
    }

    private static String zze(zzdjq<?> zzdjq) {
        return zzdjq == zzdjw.zzlcz ? "Undefined" : zzdjq == zzdjw.zzlcy ? "Null" : zzdjq instanceof zzdjt ? "Boolean" : zzdjq instanceof zzdju ? "Number" : zzdjq instanceof zzdkc ? "String" : "Object";
    }

    private static boolean zzf(zzdjq<?> zzdjq) {
        if (!(zzdjq instanceof zzdkb)) {
            return (!(zzdjq instanceof zzdjw) || zzdjq == zzdjw.zzlcz || zzdjq == zzdjw.zzlcy) ? false : true;
        }
        return true;
    }
}
