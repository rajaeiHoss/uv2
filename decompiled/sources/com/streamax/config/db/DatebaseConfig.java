package com.streamax.config.db;

public interface DatebaseConfig {
    public static final String DatabaseName = "DeviceInfo.db";
    public static final String TableName = "DeviceInfo";

    public interface DeviceInfoTable {
        public static final String dbCurrentChannel = "dbCurrentChannel";
        public static final String dbCurrentDay = "dbCurrentDay";
        public static final String dbCurrentPlan = "dbCurrentPlan";
        public static final String dbCurrentTotalChannel = "dbCurrentTotalChannel";
        public static final String dbCurrentTotalPlan = "dbCurrentTotalPlan";
        public static final String dbDeviceIp = "dbDeviceIp";
    }
}
