package com.streamax.config.listener;

public interface SuperListener {

    public interface GetConfigListener {
        void onFailure();

        void onSuccess(String str);
    }

    public interface LoadDeviceInfoListener {
        void onFailure();

        void onSuccess(Object obj);
    }

    public interface SetConfigListener {
        void onFailure();

        void onSuccess();
    }

    public interface SetRestoreDefaultListener {
        void onSuccess();
    }
}
