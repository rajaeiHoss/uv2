package com.streamax.config.bean;

public class DeviceJsonBean {
    private String MODULE;
    private String OPERATION;
    private PARAMETER PARAMETER;
    private String SESSION;

    public void setMODULE(String module) {
        this.MODULE = module;
    }

    public String getMODULE() {
        return this.MODULE;
    }

    public void setOPERATION(String operation) {
        this.OPERATION = operation;
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

    public void setSESSION(String session) {
        this.SESSION = session;
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

        public NEWCONF(String username, String password, String enabled, String operateMask) {
            this.USERNAME = username;
            this.PW = password;
            this.EN = enabled;
            this.OPERATEMASK = operateMask;
        }

        public void setUSERNAME(String username) {
            this.USERNAME = username;
        }

        public String getUSERNAME() {
            return this.USERNAME;
        }

        public void setPW(String password) {
            this.PW = password;
        }

        public String getPW() {
            return this.PW;
        }

        public void setEN(String enabled) {
            this.EN = enabled;
        }

        public String getEN() {
            return this.EN;
        }

        public void setOPERATEMASK(String operateMask) {
            this.OPERATEMASK = operateMask;
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
