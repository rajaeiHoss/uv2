package com.streamax.TimeBar;

public class TimeCell {
    public int nEndDay;
    public int nEndHour;
    public int nEndMinute;
    public int nEndMonth;
    public int nEndSecond;
    public int nEndYear;
    public int nStartDay;
    public int nStartHour;
    public int nStartMinute;
    public int nStartMonth;
    public int nStartSecond;
    public int nStartYear;
    public int nType = 0;

    public void GetTimeSpan(int[] iArr, int[] iArr2) {
        iArr[0] = (this.nStartHour * 3600) + (this.nStartMinute * 60) + this.nStartSecond;
        iArr2[0] = (this.nEndHour * 3600) + (this.nEndMinute * 60) + this.nEndSecond;
    }
}
