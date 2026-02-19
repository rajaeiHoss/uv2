package com.google.android.gms.internal;

import com.google.android.gms.wearable.Asset;
import com.google.android.gms.wearable.DataMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public final class zzdng {
    private static int zza(String str, zzdnk[] zzdnkArr) {
        int i = 14;
        for (zzdnk zzdnk : zzdnkArr) {
            int i2 = zzdnk.type;
            if (i == 14) {
                if (i2 == 9 || zzdnk.type == 2 || zzdnk.type == 6) {
                    i = zzdnk.type;
                } else if (zzdnk.type != 14) {
                    int i3 = zzdnk.type;
                    StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 48);
                    sb.append("Unexpected TypedValue type: ");
                    sb.append(i3);
                    sb.append(" for key ");
                    sb.append(str);
                    throw new IllegalArgumentException(sb.toString());
                }
            } else if (i2 != i) {
                int i4 = zzdnk.type;
                StringBuilder sb2 = new StringBuilder(String.valueOf(str).length() + 126);
                sb2.append("The ArrayList elements should all be the same type, but ArrayList with key ");
                sb2.append(str);
                sb2.append(" contains items of type ");
                sb2.append(i);
                sb2.append(" and ");
                sb2.append(i4);
                throw new IllegalArgumentException(sb2.toString());
            }
        }
        return i;
    }

    public static zzdnh zza(DataMap dataMap) {
        zzdni zzdni = new zzdni();
        ArrayList arrayList = new ArrayList();
        zzdni.zzlwm = zza(dataMap, (List<Asset>) arrayList);
        return new zzdnh(zzdni, arrayList);
    }

    private static zzdnk zza(List<Asset> list, Object obj) {
        zzdnk zzdnk = new zzdnk();
        if (obj == null) {
            zzdnk.type = 14;
            return zzdnk;
        }
        zzdnk.zzlwq = new zzdnl();
        if (obj instanceof String) {
            zzdnk.type = 2;
            zzdnk.zzlwq.zzlws = (String) obj;
        } else if (obj instanceof Integer) {
            zzdnk.type = 6;
            zzdnk.zzlwq.zzlww = ((Integer) obj).intValue();
        } else if (obj instanceof Long) {
            zzdnk.type = 5;
            zzdnk.zzlwq.zzlwv = ((Long) obj).longValue();
        } else if (obj instanceof Double) {
            zzdnk.type = 3;
            zzdnk.zzlwq.zzlwt = ((Double) obj).doubleValue();
        } else if (obj instanceof Float) {
            zzdnk.type = 4;
            zzdnk.zzlwq.zzlwu = ((Float) obj).floatValue();
        } else if (obj instanceof Boolean) {
            zzdnk.type = 8;
            zzdnk.zzlwq.zzlwy = ((Boolean) obj).booleanValue();
        } else if (obj instanceof Byte) {
            zzdnk.type = 7;
            zzdnk.zzlwq.zzlwx = ((Byte) obj).byteValue();
        } else if (obj instanceof byte[]) {
            zzdnk.type = 1;
            zzdnk.zzlwq.zzlwr = (byte[]) obj;
        } else if (obj instanceof String[]) {
            zzdnk.type = 11;
            zzdnk.zzlwq.zzlxb = (String[]) obj;
        } else if (obj instanceof long[]) {
            zzdnk.type = 12;
            zzdnk.zzlwq.zzlxc = (long[]) obj;
        } else if (obj instanceof float[]) {
            zzdnk.type = 15;
            zzdnk.zzlwq.zzlxd = (float[]) obj;
        } else if (obj instanceof Asset) {
            zzdnk.type = 13;
            zzdnl zzdnl = zzdnk.zzlwq;
            list.add((Asset) obj);
            zzdnl.zzlxe = (long) (list.size() - 1);
        } else {
            int i = 0;
            if (obj instanceof DataMap) {
                zzdnk.type = 9;
                DataMap dataMap = (DataMap) obj;
                TreeSet treeSet = new TreeSet(dataMap.keySet());
                zzdnj[] zzdnjArr = new zzdnj[treeSet.size()];
                Iterator it = treeSet.iterator();
                while (it.hasNext()) {
                    String str = (String) it.next();
                    zzdnjArr[i] = new zzdnj();
                    zzdnjArr[i].name = str;
                    zzdnjArr[i].zzlwo = zza(list, dataMap.get(str));
                    i++;
                }
                zzdnk.zzlwq.zzlwz = zzdnjArr;
            } else if (obj instanceof ArrayList) {
                zzdnk.type = 10;
                ArrayList arrayList = (ArrayList) obj;
                zzdnk[] zzdnkArr = new zzdnk[arrayList.size()];
                Object obj2 = null;
                int size = arrayList.size();
                int i2 = 14;
                while (i < size) {
                    Object obj3 = arrayList.get(i);
                    zzdnk zza = zza(list, obj3);
                    if (zza.type == 14 || zza.type == 2 || zza.type == 6 || zza.type == 9) {
                        if (i2 == 14 && zza.type != 14) {
                            i2 = zza.type;
                            obj2 = obj3;
                        } else if (zza.type != i2) {
                            String valueOf = String.valueOf(obj2.getClass());
                            String valueOf2 = String.valueOf(obj3.getClass());
                            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 80 + String.valueOf(valueOf2).length());
                            sb.append("ArrayList elements must all be of the sameclass, but this one contains a ");
                            sb.append(valueOf);
                            sb.append(" and a ");
                            sb.append(valueOf2);
                            throw new IllegalArgumentException(sb.toString());
                        }
                        zzdnkArr[i] = zza;
                        i++;
                    } else {
                        String valueOf3 = String.valueOf(obj3.getClass());
                        StringBuilder sb2 = new StringBuilder(String.valueOf(valueOf3).length() + 130);
                        sb2.append("The only ArrayList element types supported by DataBundleUtil are String, Integer, Bundle, and null, but this ArrayList contains a ");
                        sb2.append(valueOf3);
                        throw new IllegalArgumentException(sb2.toString());
                    }
                }
                zzdnk.zzlwq.zzlxa = zzdnkArr;
            } else {
                String valueOf4 = String.valueOf(obj.getClass().getSimpleName());
                throw new RuntimeException(valueOf4.length() != 0 ? "newFieldValueFromValue: unexpected value ".concat(valueOf4) : new String("newFieldValueFromValue: unexpected value "));
            }
        }
        return zzdnk;
    }

    public static DataMap zza(zzdnh zzdnh) {
        DataMap dataMap = new DataMap();
        for (zzdnj zzdnj : zzdnh.zzlwk.zzlwm) {
            zza(zzdnh.zzlwl, dataMap, zzdnj.name, zzdnj.zzlwo);
        }
        return dataMap;
    }

    private static ArrayList zza(List<Asset> list, zzdnl zzdnl, int i) {
        Object valueOf;
        ArrayList arrayList = new ArrayList(zzdnl.zzlxa.length);
        for (zzdnk zzdnk : zzdnl.zzlxa) {
            if (zzdnk.type == 14) {
                valueOf = null;
            } else if (i == 9) {
                DataMap dataMap = new DataMap();
                for (zzdnj zzdnj : zzdnk.zzlwq.zzlwz) {
                    zza(list, dataMap, zzdnj.name, zzdnj.zzlwo);
                }
                valueOf = dataMap;
            } else if (i == 2) {
                valueOf = zzdnk.zzlwq.zzlws;
            } else if (i == 6) {
                valueOf = Integer.valueOf(zzdnk.zzlwq.zzlww);
            } else {
                StringBuilder sb = new StringBuilder(39);
                sb.append("Unexpected typeOfArrayList: ");
                sb.append(i);
                throw new IllegalArgumentException(sb.toString());
            }
            arrayList.add(valueOf);
        }
        return arrayList;
    }

    private static void zza(List<Asset> list, DataMap dataMap, String str, zzdnk zzdnk) {
        int i = zzdnk.type;
        if (i == 14) {
            dataMap.putString(str, (String) null);
            return;
        }
        zzdnl zzdnl = zzdnk.zzlwq;
        if (i == 1) {
            dataMap.putByteArray(str, zzdnl.zzlwr);
        } else if (i == 11) {
            dataMap.putStringArray(str, zzdnl.zzlxb);
        } else if (i == 12) {
            dataMap.putLongArray(str, zzdnl.zzlxc);
        } else if (i == 15) {
            dataMap.putFloatArray(str, zzdnl.zzlxd);
        } else if (i == 2) {
            dataMap.putString(str, zzdnl.zzlws);
        } else if (i == 3) {
            dataMap.putDouble(str, zzdnl.zzlwt);
        } else if (i == 4) {
            dataMap.putFloat(str, zzdnl.zzlwu);
        } else if (i == 5) {
            dataMap.putLong(str, zzdnl.zzlwv);
        } else if (i == 6) {
            dataMap.putInt(str, zzdnl.zzlww);
        } else if (i == 7) {
            dataMap.putByte(str, (byte) zzdnl.zzlwx);
        } else if (i == 8) {
            dataMap.putBoolean(str, zzdnl.zzlwy);
        } else if (i == 13) {
            if (list == null) {
                String valueOf = String.valueOf(str);
                throw new RuntimeException(valueOf.length() != 0 ? "populateBundle: unexpected type for: ".concat(valueOf) : new String("populateBundle: unexpected type for: "));
            } else {
                dataMap.putAsset(str, list.get((int) zzdnl.zzlxe));
            }
        } else if (i == 9) {
            DataMap dataMap2 = new DataMap();
            for (zzdnj zzdnj : zzdnl.zzlwz) {
                zza(list, dataMap2, zzdnj.name, zzdnj.zzlwo);
            }
            dataMap.putDataMap(str, dataMap2);
        } else if (i == 10) {
            int zza = zza(str, zzdnl.zzlxa);
            ArrayList zza2 = zza(list, zzdnl, zza);
            if (zza == 14) {
                dataMap.putStringArrayList(str, zza2);
            } else if (zza == 9) {
                dataMap.putDataMapArrayList(str, zza2);
            } else if (zza == 2) {
                dataMap.putStringArrayList(str, zza2);
            } else if (zza == 6) {
                dataMap.putIntegerArrayList(str, zza2);
            } else {
                StringBuilder sb = new StringBuilder(39);
                sb.append("Unexpected typeOfArrayList: ");
                sb.append(zza);
                throw new IllegalStateException(sb.toString());
            }
        } else {
            StringBuilder sb2 = new StringBuilder(43);
            sb2.append("populateBundle: unexpected type ");
            sb2.append(i);
            throw new RuntimeException(sb2.toString());
        }
    }

    private static zzdnj[] zza(DataMap dataMap, List<Asset> list) {
        TreeSet treeSet = new TreeSet(dataMap.keySet());
        zzdnj[] zzdnjArr = new zzdnj[treeSet.size()];
        Iterator it = treeSet.iterator();
        int i = 0;
        while (it.hasNext()) {
            String str = (String) it.next();
            Object obj = dataMap.get(str);
            zzdnjArr[i] = new zzdnj();
            zzdnjArr[i].name = str;
            zzdnjArr[i].zzlwo = zza(list, obj);
            i++;
        }
        return zzdnjArr;
    }
}
