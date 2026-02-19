package com.streamax.config.bean;

public class DeviceJsonBean {
    private String MODULE;
    private String OPERATION;
    private PARAMETER PARAMETER;
    private String SESSION;

    public void setMODULE(String str) {
        this.MODULE = str;
    }

    public String getMODULE() {
        return this.MODULE;
    }

    public void setOPERATION(String str) {
        this.OPERATION = str;
    }

    public String getOPERATION() {
        return this.OPERATION;
    }

    public void setPARAMETER(PARAMETER parameter) {
        this.PARAMETER = parameter;
    }

    public PARAMETER getPARAMETER() {
        return this.PARAMETER;
    }

    public void setSESSION(String str) {
        this.SESSION = str;
    }

    public String getSESSION() {
        return this.SESSION;
    }

    public static class PARAMETER {
        private NEWCONF NEWCONF;

        public void setNEWCONF(NEWCONF newconf) {
            this.NEWCONF = newconf;
        }

        public NEWCONF getNEWCONF() {
            return this.NEWCONF;
        }

        public String toString() {
            return getNEWCONF().toString();
        }
    }

    public static class NEWCONF {
        private String EN;
        private String OPERATEMASK;
        private String PW;
        private String USERNAME;

        public NEWCONF(String str, String str2, String str3, String str4) {
            this.USERNAME = str;
            this.PW = str2;
            this.EN = str3;
            this.OPERATEMASK = str4;
        }

        public void setUSERNAME(String str) {
            this.USERNAME = str;
        }

        public String getUSERNAME() {
            return this.USERNAME;
        }

        public void setPW(String str) {
            this.PW = str;
        }

        public String getPW() {
            return this.PW;
        }

        public void setEN(String str) {
            this.EN = str;
        }

        public String getEN() {
            return this.EN;
        }

        public void setOPERATEMASK(String str) {
            this.OPERATEMASK = str;
        }

        public String getOPERATEMASK() {
            return this.OPERATEMASK;
        }

        public String toString() {
            return "\"NEWCONF\":{\"USERNAME\":\"" + this.USERNAME + "\",\"PW\":\"" + this.PW + "\",\"EN\":\"" + this.EN + "\",\"OPERATEMASK\":\"" + this.OPERATEMASK + "\"}";
        }
    }

    public String toString() {
        return "{\"MODULE\":\"" + this.MODULE + "\",\"OPERATION\":\"" + this.OPERATION + "\",\"PARAMETER\":{" + getPARAMETER() + "},\"SESSION\":\"" + this.SESSION + "\"}";
    }
}
