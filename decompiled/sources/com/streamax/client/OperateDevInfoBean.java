package com.streamax.client;

import java.util.List;

public class OperateDevInfoBean {
    private List<OPERATEDEVBean> OPERATE_DEV;

    public List<OPERATEDEVBean> getOPERATE_DEV() {
        return this.OPERATE_DEV;
    }

    public void setOPERATE_DEV(List<OPERATEDEVBean> operateDevices) {
        this.OPERATE_DEV = operateDevices;
    }

    public static class OPERATEDEVBean {
        private String DEV_ID;
        private String DEV_IP;
        private int DEV_LAST_LINKTYPE;
        private String DEV_PASSWORD;
        private int DEV_PORT;
        private String DEV_SERIAL;
        private String DEV_TYPE;
        private String DEV_USERNAME;
        private String OPERATE_TYPE;

        public String getOPERATE_TYPE() {
            return this.OPERATE_TYPE;
        }

        public void setOPERATE_TYPE(String operateType) {
            this.OPERATE_TYPE = operateType;
        }

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
