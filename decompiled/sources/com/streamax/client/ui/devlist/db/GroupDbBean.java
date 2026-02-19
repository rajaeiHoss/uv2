package com.streamax.client.ui.devlist.db;

public interface GroupDbBean {
    public static final String GroupDbName = "DevGroup.db";
    public static final String TableName0 = "GroupForNormal";
    public static final String TableName1 = "GroupForServer";

    public interface GroupTable {
        public static final String dbAccount = "dbAccount";
        public static final String dbChannel = "dbChannel";
        public static final String dbDevName = "dbDevName";
        public static final String dbFlag = "dbFlag";
        public static final String dbGroupName = "dbGroupName";
        public static final String dbId = "dbId";
        public static final String dbPwd = "dbPwd";
    }
}
