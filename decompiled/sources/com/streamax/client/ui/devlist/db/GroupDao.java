package com.streamax.client.ui.devlist.db;

import java.util.List;

public abstract class GroupDao {
    public abstract boolean add(String str, int i, String str2, int i2, int i3);

    public abstract boolean delete(int i);

    public abstract boolean delete(int i, int i2);

    public abstract boolean delete(String str);

    public abstract boolean delete(String str, int i, String str2, int i2);

    public abstract void deleteDb();

    public abstract int getChCount(String str, int i);

    public abstract List<GroupBean> getGroupDatas();

    public abstract List<GroupBean> getGroupDatasByName(String str);

    public abstract boolean isEmpty();

    public abstract boolean query(String str);

    public abstract boolean query(String str, int i);

    public abstract boolean query(String str, int i, String str2, int i2);

    public abstract boolean queryByGroupAndDevName(String str, String str2, int i);

    public abstract boolean queryDevName(String str);

    public abstract String queryDevNameByDevId();

    public abstract boolean update(String str, int i);

    public abstract boolean update(String str, int i, String str2, int i2, int i3);

    public abstract boolean update(String str, String str2);

    public abstract boolean updateDevNameById(int i, String str);
}
