package com.hjq.http.config;

public interface ILogStrategy {
    void json(String str);

    void print();

    void print(String str);

    void print(String str, String str2);

    void print(Throwable th);

    void print(StackTraceElement[] stackTraceElementArr);

    /* renamed from: com.hjq.http.config.ILogStrategy$-CC  reason: invalid class name */
    public final /* synthetic */ class CC {
        public static void $default$print(ILogStrategy _this) {
            _this.print("--------------------");
        }

        public static String formatJson(String str) {
            if (str == null) {
                return "";
            }
            StringBuilder sb = new StringBuilder();
            int length = str.length();
            int i = 0;
            int i2 = 0;
            char c = 0;
            while (i < length) {
                char charAt = str.charAt(i);
                if (charAt == '{') {
                    i2++;
                    sb.append(charAt);
                    sb.append("\n");
                    sb.append(getSpaceOrTab(i2));
                } else if (charAt == '}') {
                    i2--;
                    sb.append("\n");
                    sb.append(getSpaceOrTab(i2));
                    sb.append(charAt);
                } else if (charAt == ',') {
                    int lastIndexOf = str.lastIndexOf(":", i);
                    if (lastIndexOf == -1 || str.lastIndexOf(":\"", i) != lastIndexOf || str.charAt(i + -1) == '\"') {
                        sb.append(charAt);
                        sb.append("\n");
                        sb.append(getSpaceOrTab(i2));
                    } else {
                        sb.append(charAt);
                    }
                } else if (charAt == ':') {
                    if (i <= 0 || str.charAt(i - 1) != '\"') {
                        sb.append(charAt);
                    } else {
                        sb.append(" ");
                        sb.append(charAt);
                        sb.append(" ");
                    }
                } else if (charAt == '[') {
                    i2++;
                    if (str.charAt(i + 1) == ']') {
                        sb.append(charAt);
                    } else {
                        sb.append(charAt);
                        sb.append("\n");
                        sb.append(getSpaceOrTab(i2));
                    }
                } else if (charAt == ']') {
                    i2--;
                    if (c == '[') {
                        sb.append(charAt);
                    } else {
                        sb.append("\n");
                        sb.append(getSpaceOrTab(i2));
                        sb.append(charAt);
                    }
                } else {
                    sb.append(charAt);
                }
                i++;
                c = charAt;
            }
            return sb.toString();
        }

        public static String getSpaceOrTab(int i) {
            StringBuilder sb = new StringBuilder();
            for (int i2 = 0; i2 < i; i2++) {
                sb.append(9);
            }
            return sb.toString();
        }
    }
}
