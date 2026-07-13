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

    public void setNAT_SERVERIP(String natServerIp) {
        this.NAT_SERVERIP = natServerIp;
    }

    public int getNATSERVERPORT() {
        return this.NATSERVERPORT;
    }

    public void setNATSERVERPORT(int natServerPort) {
        this.NATSERVERPORT = natServerPort;
    }

    public String getTRANS_SERVERIP() {
        return this.TRANS_SERVERIP;
    }

    public void setTRANS_SERVERIP(String transferServerIp) {
        this.TRANS_SERVERIP = transferServerIp;
    }

    public int getTRANS_SERVERPORT() {
        return this.TRANS_SERVERPORT;
    }

    public void setTRANS_SERVERPORT(int transferServerPort) {
        this.TRANS_SERVERPORT = transferServerPort;
    }

    public List<DEVICELISTBean> getDEVICELIST() {
        return this.DEVICELIST;
    }

    public void setDEVICELIST(List<DEVICELISTBean> deviceList) {
        this.DEVICELIST = deviceList;
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

        public void setDEV_ID(String deviceId) {
            this.DEV_ID = deviceId;
        }

        public String getDEV_IP() {
            return this.DEV_IP;
        }

        public void setDEV_IP(String deviceIp) {
            this.DEV_IP = deviceIp;
        }

        public int getDEV_PORT() {
            return this.DEV_PORT;
        }

        public void setDEV_PORT(int devicePort) {
            this.DEV_PORT = devicePort;
        }

        public String getDEV_SERIAL() {
            return this.DEV_SERIAL;
        }

        public void setDEV_SERIAL(String deviceSerial) {
            this.DEV_SERIAL = deviceSerial;
        }

        public String getDEV_USERNAME() {
            return this.DEV_USERNAME;
        }

        public void setDEV_USERNAME(String deviceUsername) {
            this.DEV_USERNAME = deviceUsername;
        }

        public String getDEV_PASSWORD() {
            return this.DEV_PASSWORD;
        }

        public void setDEV_PASSWORD(String devicePassword) {
            this.DEV_PASSWORD = devicePassword;
        }

        public String getDEV_TYPE() {
            return this.DEV_TYPE;
        }

        public void setDEV_TYPE(String deviceType) {
            this.DEV_TYPE = deviceType;
        }

        public int getDEV_LAST_LINKTYPE() {
            return this.DEV_LAST_LINKTYPE;
        }

        public void setDEV_LAST_LINKTYPE(int lastLinkType) {
            this.DEV_LAST_LINKTYPE = lastLinkType;
        }
    }
}
