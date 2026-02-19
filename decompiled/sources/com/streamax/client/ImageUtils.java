package com.streamax.client;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.streamax.Configs;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ImageUtils {
    public static List<String> fileList = new ArrayList();
    public static String[] fileNames;

    public static int getCount() {
        if (fileList.size() == 0) {
            return 0;
        }
        return fileList.size();
    }

    public static Bitmap getAt(int i) {
        if (i > fileNames.length) {
            return null;
        }
        return BitmapFactory.decodeFile(Configs.Key.CaptureDirPath + fileList.get(i));
    }

    public static String getCurPicPath(int i) {
        List<String> list = fileList;
        if (list == null || list.isEmpty()) {
            return "";
        }
        return Configs.Key.CaptureDirPath + fileList.get(i);
    }

    public static void updateFileList() {
        fileNames = new File(Configs.Key.CaptureDirPath).list();
        fileList.clear();
        String[] strArr = fileNames;
        if (strArr != null && strArr.length > 0) {
            int i = 0;
            while (true) {
                String[] strArr2 = fileNames;
                if (i < strArr2.length) {
                    fileList.add(strArr2[i]);
                    i++;
                } else {
                    return;
                }
            }
        }
    }
}
