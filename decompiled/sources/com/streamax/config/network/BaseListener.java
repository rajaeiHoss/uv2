package com.streamax.config.network;

import com.streamax.config.base.BaseFragment;

public interface BaseListener {

    public interface GetListener extends BaseListener {
        void getFailure();

        void getSuccess(String str);

        String requestForGetConfig();
    }

    public interface GetStorageInfoListener extends BaseListener {
        void getFailure();

        void getSuccess(String str);
    }

    public interface RebootListener extends BaseListener {
        void rebootFailure();

        void rebootSuccess();
    }

    public interface SetListener extends BaseListener {
        String requestForSetConfig();

        void setFailure();

        void setSuccess();
    }

    BaseFragment getCurFragment();
}
