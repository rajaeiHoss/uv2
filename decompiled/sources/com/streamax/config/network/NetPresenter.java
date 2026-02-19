package com.streamax.config.network;

import com.google.gson.Gson;
import com.streamax.Configs;
import com.streamax.config.bean.UserBean;
import com.streamax.config.listener.SuperListener;
import com.streamax.config.network.BaseListener;
import com.streamax.utils.SpUtils;

public class NetPresenter {
    public static volatile NetPresenter instance;

    public static NetPresenter getDefault() {
        synchronized (NetPresenter.class) {
            if (instance == null) {
                instance = new NetPresenter();
            }
        }
        return instance;
    }

    public void getConfig(final BaseListener.GetListener getListener) {
        NetManager.getDefault().getConfig(getListener.requestForGetConfig(), new SuperListener.GetConfigListener() {
            public void onSuccess(String str) {
                getListener.getSuccess(str);
            }

            public void onFailure() {
                getListener.getFailure();
            }
        });
    }

    public void setConfig(final BaseListener.SetListener setListener) {
        NetManager.getDefault().setConfig(setListener.requestForSetConfig(), new SuperListener.SetConfigListener() {
            public void onSuccess() {
                setListener.setSuccess();
            }

            public void onFailure() {
                setListener.setFailure();
            }
        });
    }

    public void getStorageInfo(final BaseListener.GetStorageInfoListener getStorageInfoListener) {
        getStorageInfoListener.getCurFragment().showLoading();
        NetManager.getDefault().getStorageInfo(new SuperListener.GetConfigListener() {
            public void onSuccess(String str) {
                getStorageInfoListener.getCurFragment().hideLoading();
                getStorageInfoListener.getSuccess(str);
            }

            public void onFailure() {
                getStorageInfoListener.getCurFragment().hideLoading();
                getStorageInfoListener.getFailure();
            }
        });
    }

    public void rebootDevice(final BaseListener.RebootListener rebootListener) {
        rebootListener.getCurFragment().showLoading();
        NetManager.getDefault().rebootDev(new SuperListener.SetConfigListener() {
            public void onSuccess() {
                rebootListener.getCurFragment().hideLoading();
                rebootListener.rebootSuccess();
            }

            public void onFailure() {
                rebootListener.getCurFragment().hideLoading();
                rebootListener.rebootFailure();
            }
        });
    }

    public void getUserRigth(final BaseListener.GetListener getListener) {
        UserBean userBean = new UserBean();
        userBean.USERNAME = SpUtils.getString(Configs.Key.CurDeviceName, "");
        String json = new Gson().toJson((Object) userBean);
        getListener.getCurFragment().showLoading();
        NetManager.getDefault().getUserRigth(json, new SuperListener.GetConfigListener() {
            public void onSuccess(String str) {
                getListener.getCurFragment().hideLoading();
                getListener.getSuccess(str);
            }

            public void onFailure() {
                getListener.getCurFragment().hideLoading();
                getListener.getFailure();
            }
        });
    }
}
