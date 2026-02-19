package com.hjq.http.config;

import android.text.TextUtils;
import android.util.Log;
import com.hjq.http.EasyConfig;
import com.hjq.http.EasyHttp;
import com.hjq.http.config.ILogStrategy;

public final class LogStrategy implements ILogStrategy {
    public /* synthetic */ void print() {
        ILogStrategy.CC.$default$print(this);
    }

    public void print(String str) {
        String logTag = EasyConfig.getInstance().getLogTag();
        if (str == null) {
            str = "null";
        }
        Log.i(logTag, str);
    }

    public void json(String str) {
        String formatJson = ILogStrategy.CC.formatJson(str);
        if (!TextUtils.isEmpty(formatJson)) {
            String str2 = " \n" + formatJson;
            if (((long) str2.length()) <= ((long) 3072)) {
                print(str2);
                return;
            }
            while (str2.length() > 3072) {
                String substring = str2.substring(0, 3072);
                str2 = str2.replace(substring, "");
                print(substring);
            }
            print(str2);
        }
    }

    public void print(String str, String str2) {
        print(str + " = " + str2);
    }

    public void print(Throwable th) {
        Log.e(EasyConfig.getInstance().getLogTag(), th.getMessage(), th);
    }

    public void print(StackTraceElement[] stackTraceElementArr) {
        int length = stackTraceElementArr.length;
        int i = 0;
        while (i < length) {
            StackTraceElement stackTraceElement = stackTraceElementArr[i];
            int lineNumber = stackTraceElement.getLineNumber();
            String className = stackTraceElement.getClassName();
            if (lineNumber <= 0 || className.startsWith(EasyHttp.class.getPackage().getName())) {
                i++;
            } else {
                print("RequestCode = (" + stackTraceElement.getFileName() + ":" + lineNumber + ") ");
                return;
            }
        }
    }
}
