package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.NotFoundException;

final class FieldParser {
    private static final Object[][] FOUR_DIGIT_DATA_LENGTH;
    private static final Object[][] THREE_DIGIT_DATA_LENGTH;
    private static final Object[][] THREE_DIGIT_PLUS_DIGIT_DATA_LENGTH;
    private static final Object[][] TWO_DIGIT_DATA_LENGTH;
    private static final Object VARIABLE_LENGTH;

    static {
        Object obj = new Object();
        VARIABLE_LENGTH = obj;
        TWO_DIGIT_DATA_LENGTH = new Object[][]{new Object[]{"00", new Integer(18)}, new Object[]{"01", new Integer(14)}, new Object[]{"02", new Integer(14)}, new Object[]{"10", obj, new Integer(20)}, new Object[]{"11", new Integer(6)}, new Object[]{"12", new Integer(6)}, new Object[]{"13", new Integer(6)}, new Object[]{"15", new Integer(6)}, new Object[]{"17", new Integer(6)}, new Object[]{"20", new Integer(2)}, new Object[]{"21", obj, new Integer(20)}, new Object[]{"22", obj, new Integer(29)}, new Object[]{"30", obj, new Integer(8)}, new Object[]{"37", obj, new Integer(8)}, new Object[]{"90", obj, new Integer(30)}, new Object[]{"91", obj, new Integer(30)}, new Object[]{"92", obj, new Integer(30)}, new Object[]{"93", obj, new Integer(30)}, new Object[]{"94", obj, new Integer(30)}, new Object[]{"95", obj, new Integer(30)}, new Object[]{"96", obj, new Integer(30)}, new Object[]{"97", obj, new Integer(30)}, new Object[]{"98", obj, new Integer(30)}, new Object[]{"99", obj, new Integer(30)}};
        THREE_DIGIT_DATA_LENGTH = new Object[][]{new Object[]{"240", obj, new Integer(30)}, new Object[]{"241", obj, new Integer(30)}, new Object[]{"242", obj, new Integer(6)}, new Object[]{"250", obj, new Integer(30)}, new Object[]{"251", obj, new Integer(30)}, new Object[]{"253", obj, new Integer(17)}, new Object[]{"254", obj, new Integer(20)}, new Object[]{"400", obj, new Integer(30)}, new Object[]{"401", obj, new Integer(30)}, new Object[]{"402", new Integer(17)}, new Object[]{"403", obj, new Integer(30)}, new Object[]{"410", new Integer(13)}, new Object[]{"411", new Integer(13)}, new Object[]{"412", new Integer(13)}, new Object[]{"413", new Integer(13)}, new Object[]{"414", new Integer(13)}, new Object[]{"420", obj, new Integer(20)}, new Object[]{"421", obj, new Integer(15)}, new Object[]{"422", new Integer(3)}, new Object[]{"423", obj, new Integer(15)}, new Object[]{"424", new Integer(3)}, new Object[]{"425", new Integer(3)}, new Object[]{"426", new Integer(3)}};
        THREE_DIGIT_PLUS_DIGIT_DATA_LENGTH = new Object[][]{new Object[]{"310", new Integer(6)}, new Object[]{"311", new Integer(6)}, new Object[]{"312", new Integer(6)}, new Object[]{"313", new Integer(6)}, new Object[]{"314", new Integer(6)}, new Object[]{"315", new Integer(6)}, new Object[]{"316", new Integer(6)}, new Object[]{"320", new Integer(6)}, new Object[]{"321", new Integer(6)}, new Object[]{"322", new Integer(6)}, new Object[]{"323", new Integer(6)}, new Object[]{"324", new Integer(6)}, new Object[]{"325", new Integer(6)}, new Object[]{"326", new Integer(6)}, new Object[]{"327", new Integer(6)}, new Object[]{"328", new Integer(6)}, new Object[]{"329", new Integer(6)}, new Object[]{"330", new Integer(6)}, new Object[]{"331", new Integer(6)}, new Object[]{"332", new Integer(6)}, new Object[]{"333", new Integer(6)}, new Object[]{"334", new Integer(6)}, new Object[]{"335", new Integer(6)}, new Object[]{"336", new Integer(6)}, new Object[]{"340", new Integer(6)}, new Object[]{"341", new Integer(6)}, new Object[]{"342", new Integer(6)}, new Object[]{"343", new Integer(6)}, new Object[]{"344", new Integer(6)}, new Object[]{"345", new Integer(6)}, new Object[]{"346", new Integer(6)}, new Object[]{"347", new Integer(6)}, new Object[]{"348", new Integer(6)}, new Object[]{"349", new Integer(6)}, new Object[]{"350", new Integer(6)}, new Object[]{"351", new Integer(6)}, new Object[]{"352", new Integer(6)}, new Object[]{"353", new Integer(6)}, new Object[]{"354", new Integer(6)}, new Object[]{"355", new Integer(6)}, new Object[]{"356", new Integer(6)}, new Object[]{"357", new Integer(6)}, new Object[]{"360", new Integer(6)}, new Object[]{"361", new Integer(6)}, new Object[]{"362", new Integer(6)}, new Object[]{"363", new Integer(6)}, new Object[]{"364", new Integer(6)}, new Object[]{"365", new Integer(6)}, new Object[]{"366", new Integer(6)}, new Object[]{"367", new Integer(6)}, new Object[]{"368", new Integer(6)}, new Object[]{"369", new Integer(6)}, new Object[]{"390", obj, new Integer(15)}, new Object[]{"391", obj, new Integer(18)}, new Object[]{"392", obj, new Integer(15)}, new Object[]{"393", obj, new Integer(18)}, new Object[]{"703", obj, new Integer(30)}};
        FOUR_DIGIT_DATA_LENGTH = new Object[][]{new Object[]{"7001", new Integer(13)}, new Object[]{"7002", obj, new Integer(30)}, new Object[]{"7003", new Integer(10)}, new Object[]{"8001", new Integer(14)}, new Object[]{"8002", obj, new Integer(20)}, new Object[]{"8003", obj, new Integer(30)}, new Object[]{"8004", obj, new Integer(30)}, new Object[]{"8005", new Integer(6)}, new Object[]{"8006", new Integer(18)}, new Object[]{"8007", obj, new Integer(30)}, new Object[]{"8008", obj, new Integer(12)}, new Object[]{"8018", new Integer(18)}, new Object[]{"8020", obj, new Integer(25)}, new Object[]{"8100", new Integer(6)}, new Object[]{"8101", new Integer(10)}, new Object[]{"8102", new Integer(2)}, new Object[]{"8110", obj, new Integer(30)}};
    }

    private FieldParser() {
    }

    static String parseFieldsInGeneralPurpose(String str) throws NotFoundException {
        if (str.length() == 0) {
            return "";
        }
        if (str.length() >= 2) {
            String substring = str.substring(0, 2);
            int i = 0;
            while (true) {
                Object[][] objArr = TWO_DIGIT_DATA_LENGTH;
                if (i < objArr.length) {
                    if (objArr[i][0].equals(substring)) {
                        return objArr[i][1] == VARIABLE_LENGTH ? processVariableAI(2, ((Integer) objArr[i][2]).intValue(), str) : processFixedAI(2, ((Integer) objArr[i][1]).intValue(), str);
                    }
                    i++;
                } else if (str.length() >= 3) {
                    String substring2 = str.substring(0, 3);
                    int i2 = 0;
                    while (true) {
                        Object[][] objArr2 = THREE_DIGIT_DATA_LENGTH;
                        if (i2 >= objArr2.length) {
                            int i3 = 0;
                            while (true) {
                                Object[][] objArr3 = THREE_DIGIT_PLUS_DIGIT_DATA_LENGTH;
                                if (i3 < objArr3.length) {
                                    if (objArr3[i3][0].equals(substring2)) {
                                        return objArr3[i3][1] == VARIABLE_LENGTH ? processVariableAI(4, ((Integer) objArr3[i3][2]).intValue(), str) : processFixedAI(4, ((Integer) objArr3[i3][1]).intValue(), str);
                                    }
                                    i3++;
                                } else if (str.length() >= 4) {
                                    String substring3 = str.substring(0, 4);
                                    int i4 = 0;
                                    while (true) {
                                        Object[][] objArr4 = FOUR_DIGIT_DATA_LENGTH;
                                        if (i4 >= objArr4.length) {
                                            throw NotFoundException.getNotFoundInstance();
                                        } else if (objArr4[i4][0].equals(substring3)) {
                                            return objArr4[i4][1] == VARIABLE_LENGTH ? processVariableAI(4, ((Integer) objArr4[i4][2]).intValue(), str) : processFixedAI(4, ((Integer) objArr4[i4][1]).intValue(), str);
                                        } else {
                                            i4++;
                                        }
                                    }
                                } else {
                                    throw NotFoundException.getNotFoundInstance();
                                }
                            }
                        } else if (objArr2[i2][0].equals(substring2)) {
                            return objArr2[i2][1] == VARIABLE_LENGTH ? processVariableAI(3, ((Integer) objArr2[i2][2]).intValue(), str) : processFixedAI(3, ((Integer) objArr2[i2][1]).intValue(), str);
                        } else {
                            i2++;
                        }
                    }
                } else {
                    throw NotFoundException.getNotFoundInstance();
                }
            }
        } else {
            throw NotFoundException.getNotFoundInstance();
        }
    }

    private static String processFixedAI(int i, int i2, String str) throws NotFoundException {
        if (str.length() >= i) {
            String substring = str.substring(0, i);
            int i3 = i2 + i;
            if (str.length() >= i3) {
                String substring2 = str.substring(i, i3);
                String substring3 = str.substring(i3);
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append('(');
                stringBuffer.append(substring);
                stringBuffer.append(')');
                stringBuffer.append(substring2);
                stringBuffer.append(parseFieldsInGeneralPurpose(substring3));
                return stringBuffer.toString();
            }
            throw NotFoundException.getNotFoundInstance();
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static String processVariableAI(int i, int i2, String str) throws NotFoundException {
        String substring = str.substring(0, i);
        int i3 = i2 + i;
        if (str.length() < i3) {
            i3 = str.length();
        }
        String substring2 = str.substring(i, i3);
        String substring3 = str.substring(i3);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append('(');
        stringBuffer.append(substring);
        stringBuffer.append(')');
        stringBuffer.append(substring2);
        stringBuffer.append(parseFieldsInGeneralPurpose(substring3));
        return stringBuffer.toString();
    }
}
