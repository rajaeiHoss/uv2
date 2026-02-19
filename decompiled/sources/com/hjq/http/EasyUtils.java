package com.hjq.http;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.hjq.http.annotation.HttpIgnore;
import com.hjq.http.annotation.HttpRename;
import com.hjq.http.body.UpdateBody;
import com.hjq.http.model.FileContentResolver;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okio.Okio;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class EasyUtils {
    private static final Handler HANDLER = new Handler(Looper.getMainLooper());

    public static int getProgressProgress(long j, long j2) {
        return (int) ((((double) j2) / ((double) j)) * 100.0d);
    }

    public static void post(Runnable runnable) {
        HANDLER.post(runnable);
    }

    public static void postDelayed(Runnable runnable, long j) {
        HANDLER.postDelayed(runnable, j);
    }

    public static void closeStream(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                EasyLog.print((Throwable) e);
            }
        }
    }

    public static boolean isBeanType(Object obj) {
        return obj != null && !(obj instanceof Number) && !(obj instanceof CharSequence) && !(obj instanceof Boolean) && !(obj instanceof File) && !(obj instanceof InputStream) && !(obj instanceof RequestBody) && !(obj instanceof Character) && !(obj instanceof JSONObject) && !(obj instanceof JSONArray);
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x006c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean isMultipart(java.util.List<java.lang.reflect.Field> r8) {
        /*
            java.util.Iterator r8 = r8.iterator()
        L_0x0004:
            boolean r0 = r8.hasNext()
            r1 = 0
            if (r0 == 0) goto L_0x007c
            java.lang.Object r0 = r8.next()
            java.lang.reflect.Field r0 = (java.lang.reflect.Field) r0
            r2 = 1
            r0.setAccessible(r2)
            java.lang.Class r3 = r0.getType()
            java.lang.Class[] r4 = r3.getInterfaces()
            r5 = 0
        L_0x001e:
            int r6 = r4.length
            if (r5 > r6) goto L_0x004b
            int r6 = r4.length
            if (r5 != r6) goto L_0x0026
            r6 = r3
            goto L_0x0028
        L_0x0026:
            r6 = r4[r5]
        L_0x0028:
            java.lang.Class<java.util.List> r7 = java.util.List.class
            boolean r6 = r7.equals(r6)
            if (r6 == 0) goto L_0x0048
            java.lang.reflect.Type r6 = r0.getGenericType()
            java.lang.reflect.ParameterizedType r6 = (java.lang.reflect.ParameterizedType) r6
            java.lang.reflect.Type[] r6 = r6.getActualTypeArguments()
            int r7 = r6.length
            if (r7 != r2) goto L_0x0048
            java.lang.Class<java.io.File> r7 = java.io.File.class
            r6 = r6[r1]
            boolean r6 = r7.equals(r6)
            if (r6 == 0) goto L_0x0048
            return r2
        L_0x0048:
            int r5 = r5 + 1
            goto L_0x001e
        L_0x004b:
            java.lang.Class<java.io.File> r0 = java.io.File.class
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L_0x007b
            java.lang.Class<java.io.InputStream> r0 = java.io.InputStream.class
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L_0x007b
            java.lang.Class<okhttp3.RequestBody> r0 = okhttp3.RequestBody.class
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L_0x007b
            java.lang.Class<okhttp3.MultipartBody$Part> r0 = okhttp3.MultipartBody.Part.class
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L_0x006c
            goto L_0x007b
        L_0x006c:
            java.lang.Class r3 = r3.getSuperclass()
            if (r3 == 0) goto L_0x0004
            java.lang.Class<java.lang.Object> r0 = java.lang.Object.class
            boolean r0 = r0.equals(r3)
            if (r0 == 0) goto L_0x004b
            goto L_0x0004
        L_0x007b:
            return r2
        L_0x007c:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hjq.http.EasyUtils.isMultipart(java.util.List):boolean");
    }

    public static boolean isFileList(List<?> list) {
        if (list == null || list.isEmpty()) {
            return false;
        }
        for (Object obj : list) {
            if (!(obj instanceof File)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        if ((obj instanceof List) && ((List) obj).isEmpty()) {
            return true;
        }
        if (!(obj instanceof Map) || !((Map) obj).isEmpty()) {
            return false;
        }
        return true;
    }

    public static JSONArray listToJsonArray(List<?> list) {
        JSONArray jSONArray = new JSONArray();
        if (list != null && !list.isEmpty()) {
            for (Object next : list) {
                if (!isEmpty(next)) {
                    if (next instanceof List) {
                        jSONArray.put(listToJsonArray((List) next));
                    } else if (next instanceof Map) {
                        jSONArray.put(mapToJsonObject((Map) next));
                    } else if (isBeanType(next)) {
                        jSONArray.put(mapToJsonObject(beanToHashMap(next)));
                    } else {
                        jSONArray.put(next);
                    }
                }
            }
        }
        return jSONArray;
    }

    public static JSONObject mapToJsonObject(Map<?, ?> map) {
        JSONObject jSONObject = new JSONObject();
        if (map != null && !map.isEmpty()) {
            for (Object next : map.keySet()) {
                Object obj = map.get(next);
                if (!isEmpty(obj)) {
                    try {
                        if (obj instanceof List) {
                            jSONObject.put(String.valueOf(next), listToJsonArray((List) obj));
                        } else if (obj instanceof Map) {
                            jSONObject.put(String.valueOf(next), mapToJsonObject((Map) obj));
                        } else if (isBeanType(obj)) {
                            jSONObject.put(String.valueOf(next), mapToJsonObject(beanToHashMap(obj)));
                        } else {
                            jSONObject.put(String.valueOf(next), obj);
                        }
                    } catch (JSONException e) {
                        EasyLog.print((Throwable) e);
                    }
                }
            }
        }
        return jSONObject;
    }

    public static HashMap<String, Object> beanToHashMap(Object obj) {
        String str;
        if (obj == null) {
            return null;
        }
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        HashMap<String, Object> hashMap = new HashMap<>(declaredFields.length);
        for (Field field : declaredFields) {
            field.setAccessible(true);
            try {
                Object obj2 = field.get(obj);
                if (!isEmpty(obj2)) {
                    if (!field.isAnnotationPresent(HttpIgnore.class)) {
                        if (field.isAnnotationPresent(HttpRename.class)) {
                            str = ((HttpRename) field.getAnnotation(HttpRename.class)).value();
                        } else {
                            str = field.getName();
                            if (!str.matches("this\\$\\d+")) {
                                if ("Companion".equals(str)) {
                                }
                            }
                        }
                        if (obj2 instanceof List) {
                            hashMap.put(str, listToJsonArray((List) obj2));
                        } else if (obj2 instanceof Map) {
                            hashMap.put(str, mapToJsonObject((Map) obj2));
                        } else if (isBeanType(obj2)) {
                            hashMap.put(str, beanToHashMap(obj2));
                        } else {
                            hashMap.put(str, obj2);
                        }
                    }
                }
            } catch (IllegalAccessException e) {
                EasyLog.print((Throwable) e);
            }
        }
        return hashMap;
    }

    public static Type getReflectType(Object obj) {
        if (obj == null) {
            return Void.class;
        }
        Type[] genericInterfaces = obj.getClass().getGenericInterfaces();
        if (genericInterfaces.length > 0) {
            return ((ParameterizedType) genericInterfaces[0]).getActualTypeArguments()[0];
        }
        return ((ParameterizedType) obj.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public static String encodeString(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return str;
        }
    }

    public static MultipartBody.Part createPart(String str, File file) {
        String encodeString = encodeString(file.getName());
        if (file instanceof FileContentResolver) {
            try {
                FileContentResolver fileContentResolver = (FileContentResolver) file;
                InputStream openInputStream = fileContentResolver.openInputStream();
                if (openInputStream == null) {
                    return null;
                }
                String fileName = fileContentResolver.getFileName();
                if (TextUtils.isEmpty(fileName)) {
                    fileName = fileContentResolver.getName();
                }
                return MultipartBody.Part.createFormData(str, encodeString, new UpdateBody(Okio.source(openInputStream), fileContentResolver.getContentType(), fileName, (long) openInputStream.available()));
            } catch (IOException e) {
                EasyLog.print((Throwable) e);
                EasyLog.print("文件流读取失败，将被忽略上传：" + str + " = " + file.getPath());
                return null;
            }
        } else {
            try {
                return MultipartBody.Part.createFormData(str, encodeString, new UpdateBody(file));
            } catch (FileNotFoundException unused) {
                EasyLog.print("文件不存在，将被忽略上传：" + str + " = " + file.getPath());
                return null;
            }
        }
    }

    public static MultipartBody.Part createPart(String str, InputStream inputStream) {
        try {
            return MultipartBody.Part.createFormData(str, (String) null, new UpdateBody(inputStream, str));
        } catch (IOException e) {
            EasyLog.print((Throwable) e);
            return null;
        }
    }
}
