package com.streamax.config.network;

import android.os.Handler;
import android.text.TextUtils;
import com.dvr.net.DvrNet;
import com.streamax.config.base.ConfigFragment;
import com.streamax.config.factory.ThreadPoolProxyFactory;
import com.streamax.config.listener.SuperListener;
import com.streamax.config.proxy.ThreadPoolProxy;
import com.streamax.config.utils.JsonUtils;
import com.streamax.utils.AppProxy;
import com.streamax.utils.LogUtils;

public class NetManager {
    public static DvrNet dvrNet;
    public static ThreadPoolProxy executor;
    public static Handler handler;
    public static volatile NetManager instance;

    public static NetManager getDefault() {
        synchronized (NetManager.class) {
            if (instance == null) {
                instance = new NetManager();
            } else {
                dvrNet = ConfigFragment.dvrNet;
            }
        }
        return instance;
    }

    public NetManager() {
        dvrNet = ConfigFragment.dvrNet;
        handler = AppProxy.getHandler();
        executor = ThreadPoolProxyFactory.getDevConfigThreadPoolProxy();
    }

    public static void unInitDvrNet(DvrNet dvrNet2) {
        if (dvrNet2 != null) {
            dvrNet2.CloseDeviceHandle();
        }
        dvrNet = null;
    }

    public void setUserRigth(String username, String userRight, SuperListener.SetConfigListener setConfigListener) {
        executor.execute(new SetUserRigthTask(username, userRight, setConfigListener));
    }

    public void setRestoreDefault3(byte[] paramBitArray, int length, SuperListener.SetConfigListener setConfigListener) {
        executor.execute(new SetRestoreDefault(paramBitArray, length, setConfigListener));
    }

    public void rebootDev(SuperListener.SetConfigListener setConfigListener) {
        executor.execute(new RebootDevTask(setConfigListener));
    }

    public class SetRestoreDefault implements Runnable {
        public int mLength;
        public SuperListener.SetConfigListener mListener;
        public byte[] mParamBitArray;

        public SetRestoreDefault(byte[] paramBitArray, int length, SuperListener.SetConfigListener setConfigListener) {
            this.mParamBitArray = paramBitArray;
            this.mLength = length;
            this.mListener = setConfigListener;
        }

        public void run() {
            if (NetManager.dvrNet == null) {
                NetManager.handler.post(new Runnable() {
                    public void run() {
                        SetRestoreDefault.this.mListener.onFailure();
                    }
                });
            } else {
                NetManager.handler.post(new Runnable() {
                    public void run() {
                        SetRestoreDefault.this.mListener.onSuccess();
                    }
                });
            }
        }
    }

    public class RebootDevTask implements Runnable {
        public SuperListener.SetConfigListener mListener;

        public RebootDevTask(SuperListener.SetConfigListener setConfigListener) {
            this.mListener = setConfigListener;
        }

        public void run() {
            if (NetManager.dvrNet == null) {
                NetManager.handler.post(new Runnable() {
                    public void run() {
                        RebootDevTask.this.mListener.onFailure();
                    }
                });
                return;
            }
            final int commandResult = NetManager.dvrNet.SendCtrlCommand(0);
            NetManager.handler.post(new Runnable() {
                public void run() {
                    if (commandResult == 0) {
                        RebootDevTask.this.mListener.onSuccess();
                    } else {
                        RebootDevTask.this.mListener.onFailure();
                    }
                }
            });
        }
    }

    public void formatStorage(int storageIndex, SuperListener.SetConfigListener setConfigListener) {
        executor.execute(new FormatStorageTask(storageIndex, setConfigListener));
    }

    public class FormatStorageTask implements Runnable {
        public int mIndex;
        public SuperListener.SetConfigListener mListener;

        public FormatStorageTask(int storageIndex, SuperListener.SetConfigListener setConfigListener) {
            this.mListener = setConfigListener;
            this.mIndex = storageIndex;
        }

        public void run() {
            if (NetManager.dvrNet == null) {
                NetManager.handler.post(new Runnable() {
                    public void run() {
                        FormatStorageTask.this.mListener.onFailure();
                    }
                });
                return;
            }
            final int formatResult = NetManager.dvrNet.FormatStorage(this.mIndex);
            NetManager.handler.post(new Runnable() {
                public void run() {
                    if (formatResult == 0) {
                        FormatStorageTask.this.mListener.onSuccess();
                    } else {
                        FormatStorageTask.this.mListener.onFailure();
                    }
                }
            });
        }
    }

    public class SetUserRigthTask implements Runnable {
        public SuperListener.SetConfigListener mListener;
        public String mUserRigth;
        public String mUsername;

        public SetUserRigthTask(String username, String userRight, SuperListener.SetConfigListener setConfigListener) {
            this.mUsername = username;
            this.mUserRigth = userRight;
            this.mListener = setConfigListener;
        }

        public void run() {
            if (NetManager.dvrNet == null) {
                NetManager.handler.post(new Runnable() {
                    public void run() {
                        SetUserRigthTask.this.mListener.onFailure();
                    }
                });
                return;
            }
            NetManager.dvrNet.SetUserRigth(1, this.mUsername, this.mUserRigth);
            NetManager.handler.post(new Runnable() {
                public void run() {
                    SetUserRigthTask.this.mListener.onSuccess();
                }
            });
        }
    }

    public void getUserRigth(String request, SuperListener.GetConfigListener getConfigListener) {
        executor.execute(new GetUserRigthTask(request, getConfigListener));
    }

    public class GetUserRigthTask implements Runnable {
        public SuperListener.GetConfigListener mListener;
        public String mRequest;

        public GetUserRigthTask(String request, SuperListener.GetConfigListener getConfigListener) {
            this.mRequest = request;
            this.mListener = getConfigListener;
        }

        public void run() {
            if (NetManager.dvrNet == null) {
                NetManager.handler.post(new Runnable() {
                    public void run() {
                        GetUserRigthTask.this.mListener.onFailure();
                    }
                });
                return;
            }
            this.mRequest = JsonUtils.getUpJsonString(this.mRequest);
            final String userRightResponse = NetManager.dvrNet.GetUserRigth(this.mRequest);
            NetManager.handler.post(new Runnable() {
                public void run() {
                    if (!TextUtils.isEmpty(userRightResponse)) {
                        GetUserRigthTask.this.mListener.onSuccess(userRightResponse);
                    } else {
                        GetUserRigthTask.this.mListener.onFailure();
                    }
                }
            });
        }
    }

    public void getConfig(String request, SuperListener.GetConfigListener getConfigListener) {
        executor.execute(new GetConfigTask(request, getConfigListener));
    }

    public class GetConfigTask implements Runnable {
        public SuperListener.GetConfigListener mListener;
        public String mRequest;

        public GetConfigTask(String request, SuperListener.GetConfigListener getConfigListener) {
            this.mRequest = request;
            this.mListener = getConfigListener;
        }

        public void run() {
            LogUtils.e("UserFragment", "GetConfigTask 1, dvrNet: " + NetManager.dvrNet);
            if (NetManager.dvrNet == null) {
                NetManager.handler.post(new Runnable() {
                    public void run() {
                        GetConfigTask.this.mListener.onFailure();
                    }
                });
                return;
            }
            this.mRequest = JsonUtils.getDownJsonString(this.mRequest);
            final String configResponse = NetManager.dvrNet.GetConfig(this.mRequest);
            NetManager.handler.post(new Runnable() {
                public void run() {
                    if (!TextUtils.isEmpty(configResponse)) {
                        GetConfigTask.this.mListener.onSuccess(configResponse);
                    } else {
                        GetConfigTask.this.mListener.onFailure();
                    }
                }
            });
        }
    }

    public void setConfig(String request, SuperListener.SetConfigListener setConfigListener) {
        executor.execute(new SetConfigTask(request, setConfigListener));
    }

    public class SetConfigTask implements Runnable {
        public SuperListener.SetConfigListener mListener;
        public String mRequest;

        public SetConfigTask(String request, SuperListener.SetConfigListener setConfigListener) {
            this.mRequest = request;
            this.mListener = setConfigListener;
        }

        public void run() {
            if (NetManager.dvrNet == null) {
                NetManager.handler.post(new Runnable() {
                    public void run() {
                        SetConfigTask.this.mListener.onFailure();
                    }
                });
                return;
            }
            this.mRequest = JsonUtils.getUpJsonString(this.mRequest);
            final int setConfigResult = NetManager.dvrNet.SetConfig(this.mRequest);
            NetManager.handler.post(new Runnable() {
                public void run() {
                    if (setConfigResult == 0) {
                        SetConfigTask.this.mListener.onSuccess();
                    } else {
                        SetConfigTask.this.mListener.onFailure();
                    }
                }
            });
        }
    }

    public void getStorageInfo(SuperListener.GetConfigListener getConfigListener) {
        executor.execute(new GetStorageInfoTask(getConfigListener));
    }

    public class GetStorageInfoTask implements Runnable {
        public SuperListener.GetConfigListener mListener;

        public GetStorageInfoTask(SuperListener.GetConfigListener getConfigListener) {
            this.mListener = getConfigListener;
        }

        public void run() {
            if (NetManager.dvrNet == null) {
                NetManager.handler.post(new Runnable() {
                    public void run() {
                        GetStorageInfoTask.this.mListener.onFailure();
                    }
                });
                return;
            }
            final String storageInfoResponse = NetManager.dvrNet.GetStorageInfo();
            NetManager.handler.post(new Runnable() {
                public void run() {
                    if (!TextUtils.isEmpty(storageInfoResponse)) {
                        GetStorageInfoTask.this.mListener.onSuccess(storageInfoResponse);
                    } else {
                        GetStorageInfoTask.this.mListener.onFailure();
                    }
                }
            });
        }
    }
}
