package com.streamax;

import android.os.Environment;

public class Configs {
    public static int chForCurDevice;
    public static int dayForCurDevice;
    public static int planForCurDevice;

    public static class DeviceConnectionTypes {
        public static final int DIRECT = 0;
        public static final int DISCONN = -1;
        public static final int MSG = 2;
        public static final int NAT = 1;
    }

    public static class Key {
        public static final String AutoLogin = "AutoLogin";
        public static final String CaptureDirPath = (Environment.getExternalStorageDirectory() + "/streaming/capture/");
        public static final String CurDeviceName = "CurDeviceName";
        public static final String ExtraServerType = "ExtraServerType";
        public static final String LastServerHostName = "LastServerHostName";
        public static final String LoginType = "logintype";
        public static final String MultiStreamType = "MultiStreamType";
        public static final String RecordDirPath = (Environment.getExternalStorageDirectory() + "/streaming/record/");
        public static final String ServerHostName = "ServerHostName";
        public static final String ServerHostName1 = "ServerHostName1";
        public static final String ServerHostName2 = "ServerHostName2";
        public static final String ServerHostName3 = "ServerHostName3";
        public static final String SingleStreamType = "SingleStreamType";
        public static final String SmartStreamStatus = "SmartStreamStatus";
        public static final String WifiStatus = "wifi";
    }

    public static class More {
        public static final String SMART_STREAM_SWITCH = "SmartStreamSwitch";
        public static final String SMART_STREAM_TYPE = "SmartStreamType";
    }

    public static class NetWork {
        public static int[] ddnsArray = {0, 1, 4, 13, 15};
    }

    public interface StreamControl {
        public static final int AudioCcontrol = 4;
        public static final int StreamPause = 2;
        public static final int StreamRestore = 1;
        public static final int StreamSwitch = 3;
    }

    public interface StreamType {
        public static final int MainStream = 1;
        public static final int MobileStream = 2;
        public static final int SubStream = 0;
    }

    public static class Tag {
        public static int TagErrorFragment = -2;
        public static int TagNull = -1;

        public static class AlarmTag {
            public static final int TagBt = 2;
            public static final int TagCh = 0;
            public static final int TagChl = 3;
            public static final int TagDay = 7;
            public static final int TagDrs = 6;
            public static final int TagPlan = 8;
            public static final int TagPrs = 5;
            public static final int TagSot = 4;
            public static final int TagSst = 1;
        }

        public static class DateTimeTag {
            public static final int TagDateMode = 2;
            public static final int TagNtpServer = 3;
            public static final int TagTimeFormat = 0;
            public static final int TagTimeZone = 1;
        }

        public static class DiskTag {
            public static final int TagFormat = 2;
            public static final int TagId = 0;
            public static final int TagOm = 1;
        }

        public static class DstTag {
            public static final int TagEDay = 7;
            public static final int TagEMonth = 5;
            public static final int TagEWeek = 6;
            public static final int TagMode = 0;
            public static final int TagOffset = 1;
            public static final int TagSDay = 4;
            public static final int TagSMonth = 2;
            public static final int TagSWeek = 3;
        }

        public static class NetworkTag {
            public static final int TagServer = 1;
            public static final int TagSstg = 0;
        }

        public static class RecordTag {
            public static final int TagAdd = 4;
            public static final int TagCh = 0;
            public static final int TagCopyTo = 5;
            public static final int TagDay = 3;
            public static final int TagPlan = 2;
            public static final int TagRm = 1;
        }

        public static class SystemSetTag {
            public static final int TagReset = 1;
            public static final int TagVideoFormat = 0;
        }

        public static class VideoTag {
            public static final int TagBr = 4;
            public static final int TagBrm = 0;
            public static final int TagChannel = 2;
            public static final int TagCopyTo = 6;
            public static final int TagFr = 3;
            public static final int TagQlt = 1;
            public static final int TagRst = 5;
        }
    }
}
