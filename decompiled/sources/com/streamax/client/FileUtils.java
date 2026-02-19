package com.streamax.client;

import com.streamax.Configs;
import com.streamax.utils.StringUtils;
import com.zycs.UView.R;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class FileUtils {
    public static int count;
    public static List<FileEntry> fileList = new ArrayList();
    public LocalRecordFileList mListView;

    public static class FileEntry {
        public boolean bSelect;
        public String name;
        public int size = 0;
    }

    public int compare(Object obj, Object obj2) {
        return 0;
    }

    public static void refreshFiles(LocalRecordFileList localRecordFileList) {
        String[] list = new File(Configs.Key.RecordDirPath).list();
        fileList.clear();
        if (list != null) {
            for (int i = 0; i < list.length; i++) {
                FileEntry file2 = new FileEntry();
                file2.name = list[i];
                file2.bSelect = false;
                file2.size = getFileSize(Configs.Key.RecordDirPath + list[i]);
                fileList.add(file2);
            }
        }
    }

    public static int getFileSize(String str) {
        return (int) new File(str).length();
    }

    public static void deleteSelectItems() {
        Iterator<FileEntry> it = fileList.iterator();
        while (it.hasNext()) {
            FileEntry next = it.next();
            if (next.bSelect) {
                new File(Configs.Key.RecordDirPath + next.name).delete();
                it.remove();
            }
        }
    }

    public static void setCheckState(int i, boolean z) {
        if (i <= fileList.size()) {
            FileEntry file2 = fileList.get(i);
            if (file2.bSelect != z) {
                file2.bSelect = z;
                if (z) {
                    count++;
                } else {
                    count--;
                }
            }
        }
    }

    public static int getSelCount() {
        return count;
    }

    public static int getCount() {
        List<FileEntry> list = fileList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public static Map<String, Object> getAt(int i) {
        if (i > fileList.size()) {
            return null;
        }
        HashMap hashMap = new HashMap();
        String str = fileList.get(i).name;
        String format = String.format("%s:%s-%s-%s %s:%s:%s %s:%s", new Object[]{StringUtils.getString(Integer.valueOf(R.string.time)), str.substring(0, 4), str.substring(4, 6), str.substring(6, 8), str.substring(8, 10), str.substring(10, 12), str.substring(12, 14), StringUtils.getString(Integer.valueOf(R.string.channel)), str.substring(14, 16)});
        String format2 = String.format("%s:%d K", new Object[]{StringUtils.getString(Integer.valueOf(R.string.size)), Integer.valueOf(fileList.get(i).size / 1000)});
        hashMap.put("filename", str);
        hashMap.put("info", format);
        hashMap.put("path", Configs.Key.RecordDirPath + str);
        hashMap.put("size", format2);
        hashMap.put("select", Boolean.valueOf(fileList.get(i).bSelect));
        return hashMap;
    }
}
