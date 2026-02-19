package com.hjq.http.body;

import com.hjq.http.model.ContentType;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;
import org.json.JSONArray;
import org.json.JSONObject;

public class JsonBody extends RequestBody {
    private final byte[] mBytes;
    private final String mJson;

    public JsonBody(Map<?, ?> map) {
        this(new JSONObject(map));
    }

    public JsonBody(List<?> list) {
        this(new JSONArray(list));
    }

    public JsonBody(JSONObject jSONObject) {
        String jSONObject2 = jSONObject.toString();
        this.mJson = jSONObject2;
        this.mBytes = jSONObject2.getBytes();
    }

    public JsonBody(JSONArray jSONArray) {
        String jSONArray2 = jSONArray.toString();
        this.mJson = jSONArray2;
        this.mBytes = jSONArray2.getBytes();
    }

    public JsonBody(String str) {
        this.mJson = str;
        this.mBytes = str.getBytes();
    }

    public MediaType contentType() {
        return ContentType.JSON;
    }

    public long contentLength() {
        return (long) this.mBytes.length;
    }

    public void writeTo(BufferedSink bufferedSink) throws IOException {
        byte[] bArr = this.mBytes;
        bufferedSink.write(bArr, 0, bArr.length);
    }

    public String getJson() {
        return this.mJson;
    }

    public String toString() {
        return this.mJson;
    }
}
