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

    public int compare(Object left, Object right) {
        return 0;
    }

    public static void refreshFiles(LocalRecordFileList localRecordFileList) {
        String[] fileNames = new File(Configs.Key.RecordDirPath).list();
        fileList.clear();
        count = 0;
        if (fileNames != null) {
            for (int fileIndex = 0; fileIndex < fileNames.length; fileIndex++) {
                FileEntry fileEntry = new FileEntry();
                fileEntry.name = fileNames[fileIndex];
                fileEntry.bSelect = false;
                fileEntry.size = getFileSize(Configs.Key.RecordDirPath + fileNames[fileIndex]);
                fileList.add(fileEntry);
            }
        }
    }

    public static int getFileSize(String filePath) {
        return (int) new File(filePath).length();
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

    public static void setCheckState(int fileIndex, boolean selected) {
        if (fileIndex >= 0 && fileIndex < fileList.size()) {
            FileEntry fileEntry = fileList.get(fileIndex);
            if (fileEntry.bSelect != selected) {
                fileEntry.bSelect = selected;
                if (selected) {
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

    public static Map<String, Object> getAt(int fileIndex) {
        if (fileIndex < 0 || fileIndex >= fileList.size()) {
            return null;
        }
        HashMap hashMap = new HashMap();
        String fileName = fileList.get(fileIndex).name;
        String infoText = String.format("%s:%s-%s-%s %s:%s:%s %s:%s", new Object[]{StringUtils.getString(Integer.valueOf(R.string.time)), fileName.substring(0, 4), fileName.substring(4, 6), fileName.substring(6, 8), fileName.substring(8, 10), fileName.substring(10, 12), fileName.substring(12, 14), StringUtils.getString(Integer.valueOf(R.string.channel)), fileName.substring(14, 16)});
        String sizeText = String.format("%s:%d K", new Object[]{StringUtils.getString(Integer.valueOf(R.string.size)), Integer.valueOf(fileList.get(fileIndex).size / 1000)});
        hashMap.put("filename", fileName);
        hashMap.put("info", infoText);
        hashMap.put("path", Configs.Key.RecordDirPath + fileName);
        hashMap.put("size", sizeText);
        hashMap.put("select", Boolean.valueOf(fileList.get(fileIndex).bSelect));
        return hashMap;
    }
}
