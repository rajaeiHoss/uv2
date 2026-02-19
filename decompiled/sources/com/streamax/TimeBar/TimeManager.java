package com.streamax.TimeBar;

import java.util.ArrayList;
import java.util.List;

public class TimeManager {
    public List<TimeCell> mCellList = new ArrayList();

    public void AddCell(TimeCell timeCell) {
        this.mCellList.add(timeCell);
    }

    public int GetCount() {
        List<TimeCell> list = this.mCellList;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public boolean GetIndex(int i, int[] iArr, int[] iArr2) {
        List<TimeCell> list = this.mCellList;
        if (list == null || i >= list.size()) {
            return false;
        }
        this.mCellList.get(i).GetTimeSpan(iArr, iArr2);
        return true;
    }
}
