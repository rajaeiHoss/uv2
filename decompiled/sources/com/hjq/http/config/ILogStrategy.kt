package com.hjq.http.config

interface ILogStrategy {
    fun json(str: String?)

    fun print() {
        print("--------------------")
    }

    fun print(str: String?)

    fun print(str: String?, str2: String?)

    fun print(th: Throwable)

    fun print(stackTraceElementArr: Array<StackTraceElement>)

    companion object {
        @JvmStatic
        fun formatJson(str: String?): String {
            if (str == null) {
                return ""
            }
            val sb = StringBuilder()
            val length = str.length
            var i = 0
            var i2 = 0
            var c = 0.toChar()
            while (i < length) {
                val charAt = str[i]
                when (charAt) {
                    '{' -> {
                        i2++
                        sb.append(charAt)
                        sb.append("\n")
                        sb.append(getSpaceOrTab(i2))
                    }

                    '}' -> {
                        i2--
                        sb.append("\n")
                        sb.append(getSpaceOrTab(i2))
                        sb.append(charAt)
                    }

                    ',' -> {
                        val lastIndexOf = str.lastIndexOf(":", i)
                        if (lastIndexOf == -1 || str.lastIndexOf(":\"", i) != lastIndexOf || str[i - 1] == '\"') {
                            sb.append(charAt)
                            sb.append("\n")
                            sb.append(getSpaceOrTab(i2))
                        } else {
                            sb.append(charAt)
                        }
                    }

                    ':' -> {
                        if (i <= 0 || str[i - 1] != '\"') {
                            sb.append(charAt)
                        } else {
                            sb.append(" ")
                            sb.append(charAt)
                            sb.append(" ")
                        }
                    }

                    '[' -> {
                        i2++
                        if (i + 1 < str.length && str[i + 1] == ']') {
                            sb.append(charAt)
                        } else {
                            sb.append(charAt)
                            sb.append("\n")
                            sb.append(getSpaceOrTab(i2))
                        }
                    }

                    ']' -> {
                        i2--
                        if (c == '[') {
                            sb.append(charAt)
                        } else {
                            sb.append("\n")
                            sb.append(getSpaceOrTab(i2))
                            sb.append(charAt)
                        }
                    }

                    else -> {
                        sb.append(charAt)
                    }
                }
                i++
                c = charAt
            }
            return sb.toString()
        }

        @JvmStatic
        fun getSpaceOrTab(i: Int): String {
            val sb = StringBuilder()
            for (i2 in 0 until i) {
                sb.append('\t')
            }
            return sb.toString()
        }
    }
}
