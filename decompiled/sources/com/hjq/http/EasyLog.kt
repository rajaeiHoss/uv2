package com.hjq.http

class EasyLog private constructor() {
    companion object {
        @JvmStatic
        fun print() {
            print("----------------------------------------")
        }

        @JvmStatic
        fun print(str: String?) {
            if (EasyConfig.getInstance().isLogEnabled()) {
                EasyConfig.getInstance().getLogStrategy().print(str)
            }
        }

        @JvmStatic
        fun json(str: String?) {
            if (EasyConfig.getInstance().isLogEnabled()) {
                EasyConfig.getInstance().getLogStrategy().json(str)
            }
        }

        @JvmStatic
        fun print(str: String?, str2: String?) {
            if (EasyConfig.getInstance().isLogEnabled()) {
                EasyConfig.getInstance().getLogStrategy().print(str, str2)
            }
        }

        @JvmStatic
        fun print(th: Throwable) {
            if (EasyConfig.getInstance().isLogEnabled()) {
                EasyConfig.getInstance().getLogStrategy().print(th)
            }
        }

        @JvmStatic
        fun print(stackTraceElementArr: Array<StackTraceElement>) {
            if (EasyConfig.getInstance().isLogEnabled()) {
                EasyConfig.getInstance().getLogStrategy().print(stackTraceElementArr)
            }
        }
    }
}
