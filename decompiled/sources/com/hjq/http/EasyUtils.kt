package com.hjq.http

import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import com.hjq.http.annotation.HttpIgnore
import com.hjq.http.annotation.HttpRename
import com.hjq.http.body.UpdateBody
import com.hjq.http.model.ContentType
import com.hjq.http.model.FileContentResolver
import java.io.Closeable
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStream
import java.io.UnsupportedEncodingException
import java.lang.reflect.Field
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.net.URLEncoder
import java.util.HashMap
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okio.Okio
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class EasyUtils private constructor() {
    companion object {
        private val HANDLER: Handler = Handler(Looper.getMainLooper())

        @JvmStatic
        fun getProgressProgress(j: Long, j2: Long): Int {
            return ((j2.toDouble() / j.toDouble()) * 100.0).toInt()
        }

        @JvmStatic
        fun post(runnable: Runnable) {
            HANDLER.post(runnable)
        }

        @JvmStatic
        fun postDelayed(runnable: Runnable, j: Long) {
            HANDLER.postDelayed(runnable, j)
        }

        @JvmStatic
        fun closeStream(closeable: Closeable?) {
            if (closeable != null) {
                try {
                    closeable.close()
                } catch (e: Exception) {
                    EasyLog.print(e)
                }
            }
        }

        @JvmStatic
        fun isBeanType(obj: Any?): Boolean {
            return obj != null &&
                obj !is Number &&
                obj !is CharSequence &&
                obj !is Boolean &&
                obj !is File &&
                obj !is InputStream &&
                obj !is RequestBody &&
                obj !is Character &&
                obj !is JSONObject &&
                obj !is JSONArray
        }

        @JvmStatic
        fun isMultipart(list: List<Field>): Boolean {
            for (field in list) {
                field.isAccessible = true
                var cls: Class<*>? = field.type
                while (cls != null && cls != Any::class.java) {
                    val interfaces = cls.interfaces
                    var hasListType = false
                    for (i in 0..interfaces.size) {
                        val type = if (i == interfaces.size) cls else interfaces[i]
                        if (type == List::class.java) {
                            hasListType = true
                            break
                        }
                    }
                    if (hasListType) {
                        val genericType = field.genericType
                        if (genericType is ParameterizedType) {
                            val actualTypeArguments = genericType.actualTypeArguments
                            if (actualTypeArguments.size == 1 && actualTypeArguments[0] == File::class.java) {
                                return true
                            }
                        }
                    }
                    if (cls == File::class.java ||
                        cls == InputStream::class.java ||
                        cls == RequestBody::class.java ||
                        cls == MultipartBody.Part::class.java
                    ) {
                        return true
                    }
                    cls = cls.superclass
                }
            }
            return false
        }

        @JvmStatic
        fun isFileList(list: List<*>?): Boolean {
            if (list == null || list.isEmpty()) {
                return false
            }
            for (obj in list) {
                if (obj !is File) {
                    return false
                }
            }
            return true
        }

        @JvmStatic
        fun isEmpty(obj: Any?): Boolean {
            if (obj == null) {
                return true
            }
            if (obj is List<*> && obj.isEmpty()) {
                return true
            }
            return obj is Map<*, *> && obj.isEmpty()
        }

        @JvmStatic
        fun listToJsonArray(list: List<*>?): JSONArray {
            val jsonArray = JSONArray()
            if (list != null && list.isNotEmpty()) {
                for (next in list) {
                    if (!isEmpty(next)) {
                        when {
                            next is List<*> -> jsonArray.put(listToJsonArray(next))
                            next is Map<*, *> -> jsonArray.put(mapToJsonObject(next))
                            isBeanType(next) -> jsonArray.put(mapToJsonObject(beanToHashMap(next)))
                            else -> jsonArray.put(next)
                        }
                    }
                }
            }
            return jsonArray
        }

        @JvmStatic
        fun mapToJsonObject(map: Map<*, *>?): JSONObject {
            val jsonObject = JSONObject()
            if (map != null && map.isNotEmpty()) {
                for (next in map.keys) {
                    val obj = map[next]
                    if (!isEmpty(obj)) {
                        try {
                            when {
                                obj is List<*> -> jsonObject.put(next.toString(), listToJsonArray(obj))
                                obj is Map<*, *> -> jsonObject.put(next.toString(), mapToJsonObject(obj))
                                isBeanType(obj) -> jsonObject.put(next.toString(), mapToJsonObject(beanToHashMap(obj)))
                                else -> jsonObject.put(next.toString(), obj)
                            }
                        } catch (e: JSONException) {
                            EasyLog.print(e)
                        }
                    }
                }
            }
            return jsonObject
        }

        @JvmStatic
        fun beanToHashMap(obj: Any?): HashMap<String, Any>? {
            if (obj == null) {
                return null
            }
            val declaredFields = obj.javaClass.declaredFields
            val hashMap = HashMap<String, Any>(declaredFields.size)
            for (field in declaredFields) {
                field.isAccessible = true
                try {
                    val value = field.get(obj)
                    if (!isEmpty(value) && !field.isAnnotationPresent(HttpIgnore::class.java)) {
                        val key = if (field.isAnnotationPresent(HttpRename::class.java)) {
                            field.getAnnotation(HttpRename::class.java).value
                        } else {
                            field.name
                        }
                        if (key.matches("this\\$\\d+".toRegex()) || "Companion" == key) {
                            continue
                        }
                        when {
                            value is List<*> -> hashMap[key] = listToJsonArray(value)
                            value is Map<*, *> -> hashMap[key] = mapToJsonObject(value)
                            isBeanType(value) -> hashMap[key] = beanToHashMap(value) as Any
                            else -> hashMap[key] = value as Any
                        }
                    }
                } catch (e: IllegalAccessException) {
                    EasyLog.print(e)
                }
            }
            return hashMap
        }

        @JvmStatic
        fun getReflectType(obj: Any?): Type {
            if (obj == null) {
                return Void::class.java
            }
            val genericInterfaces = obj.javaClass.genericInterfaces
            if (genericInterfaces.isNotEmpty()) {
                return (genericInterfaces[0] as ParameterizedType).actualTypeArguments[0]
            }
            return (obj.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0]
        }

        @JvmStatic
        fun encodeString(str: String?): String {
            if (TextUtils.isEmpty(str)) {
                return ""
            }
            return try {
                URLEncoder.encode(str, "UTF-8")
            } catch (e: UnsupportedEncodingException) {
                e.printStackTrace()
                str ?: ""
            }
        }

        @JvmStatic
        fun createPart(str: String, file: File): MultipartBody.Part? {
            val encodeString = encodeString(file.name)
            if (file is FileContentResolver) {
                return try {
                    val openInputStream = file.openInputStream()
                    if (openInputStream == null) {
                        return null
                    }
                    var fileName = file.getFileName()
                    if (TextUtils.isEmpty(fileName)) {
                        fileName = file.name
                    }
                    MultipartBody.Part.createFormData(
                        str,
                        encodeString,
                        UpdateBody(
                            Okio.source(openInputStream),
                            file.getContentType() ?: ContentType.STREAM,
                            fileName,
                            openInputStream.available().toLong()
                        )
                    )
                } catch (e: IOException) {
                    EasyLog.print(e)
                    EasyLog.print("文件流读取失败，将被忽略上传：$str = ${file.path}")
                    null
                }
            } else {
                return try {
                    MultipartBody.Part.createFormData(str, encodeString, UpdateBody(file))
                } catch (_: FileNotFoundException) {
                    EasyLog.print("文件不存在，将被忽略上传：$str = ${file.path}")
                    null
                }
            }
        }

        @JvmStatic
        fun createPart(str: String, inputStream: InputStream): MultipartBody.Part? {
            return try {
                MultipartBody.Part.createFormData(str, null as String?, UpdateBody(inputStream, str))
            } catch (e: IOException) {
                EasyLog.print(e)
                null
            }
        }
    }
}
