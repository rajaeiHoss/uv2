package com.streamax.client;

import java.util.List;

public class InitDevInfoBean {
    private List<DEVICELISTBean> DEVICELIST;
    private int NATSERVERPORT;
    private String NAT_SERVERIP;
    private String TRANS_SERVERIP;
    private int TRANS_SERVERPORT;

    public String getNAT_SERVERIP() {
        return this.NAT_SERVERIP;
    }

    public void setNAT_SERVERIP(String str) {
        this.NAT_SERVERIP = str;
    }

    public int getNATSERVERPORT() {
        return this.NATSERVERPORT;
    }

    public void setNATSERVERPORT(int i) {
        this.NATSERVERPORT = i;
    }

    public String getTRANS_SERVERIP() {
        return this.TRANS_SERVERIP;
    }

    public void setTRANS_SERVERIP(String str) {
        this.TRANS_SERVERIP = str;
    }

    public int getTRANS_SERVERPORT() {
        return this.TRANS_SERVERPORT;
    }

    public void setTRANS_SERVERPORT(int i) {
        this.TRANS_SERVERPORT = i;
    }

    public List<DEVICELISTBean> getDEVICELIST() {
        return this.DEVICELIST;
    }

    public void setDEVICELIST(List<DEVICELISTBean> list) {
        this.DEVICELIST = list;
    }

    public static class DEVICELISTBean {
        private String DEV_ID;
        private String DEV_IP;
        private int DEV_LAST_LINKTYPE;
        private String DEV_PASSWORD;
        private int DEV_PORT;
        private String DEV_SERIAL;
        private String DEV_TYPE;
        private String DEV_USERNAME;

        public String getDEV_ID() {
            return this.DEV_ID;
        }

        public void setDEV_ID(String str) {
            this.DEV_ID = str;
        }

        public String getDEV_IP() {
            return this.DEV_IP;
        }

        public void setDEV_IP(String str) {
            this.DEV_IP = str;
        }

        public int getDEV_PORT() {
            return this.DEV_PORT;
        }

        public void setDEV_PORT(int i) {
            this.DEV_PORT = i;
        }

        public String getDEV_SERIAL() {
            return this.DEV_SERIAL;
        }

        public void setDEV_SERIAL(String str) {
            this.DEV_SERIAL = str;
        }

        public String getDEV_USERNAME() {
            return this.DEV_USERNAME;
        }

        public void setDEV_USERNAME(String str) {
            this.DEV_USERNAME = str;
        }

        public String getDEV_PASSWORD() {
            return this.DEV_PASSWORD;
        }

        public void setDEV_PASSWORD(String str) {
            this.DEV_PASSWORD = str;
        }

        public String getDEV_TYPE() {
            return this.DEV_TYPE;
        }

        public void setDEV_TYPE(String str) {
            this.DEV_TYPE = str;
        }

        public int getDEV_LAST_LINKTYPE() {
            return this.DEV_LAST_LINKTYPE;
        }

        public void setDEV_LAST_LINKTYPE(int i) {
            this.DEV_LAST_LINKTYPE = i;
        }
    }
}
