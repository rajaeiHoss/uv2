package com.google.android.gms.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.List;

public interface zzro extends IInterface {
    void destroy() throws RemoteException;

    List<String> getAvailableAssetNames() throws RemoteException;

    String getCustomTemplateId() throws RemoteException;

    zzmm getVideoController() throws RemoteException;

    void performClick(String str) throws RemoteException;

    void recordImpression() throws RemoteException;

    String zzap(String str) throws RemoteException;

    zzqs zzaq(String str) throws RemoteException;

    boolean zzf(IObjectWrapper iObjectWrapper) throws RemoteException;

    IObjectWrapper zzkd() throws RemoteException;

    IObjectWrapper zzkk() throws RemoteException;
}
