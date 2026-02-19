package com.streamax.client.ui.devlist.db;

public class GroupBean {
    public String dbAccont;
    public int dbChannel;
    public String dbDevName;
    public int dbFlag;
    public String dbGroupName;
    public int dbId;
    public String dbPwd;

    public String toString() {
        return "DevGroupBean_dbGroupName:" + this.dbGroupName + ",dbDevName:" + this.dbDevName + ",dbChannel:" + this.dbChannel + ",dbId:" + this.dbId + ",dbFlag:" + this.dbFlag;
    }
}
