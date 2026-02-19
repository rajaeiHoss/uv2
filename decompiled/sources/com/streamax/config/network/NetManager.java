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

    public void setUserRigth(String str, String str2, SuperListener.SetConfigListener setConfigListener) {
        executor.execute(new SetUserRigthTask(str, str2, setConfigListener));
    }

    public void setRestoreDefault3(byte[] bArr, int i, SuperListener.SetConfigListener setConfigListener) {
        executor.execute(new SetRestoreDefault(bArr, i, setConfigListener));
    }

    public void rebootDev(SuperListener.SetConfigListener setConfigListener) {
        executor.execute(new RebootDevTask(setConfigListener));
    }

    public class SetRestoreDefault implements Runnable {
        public int mLength;
        public SuperListener.SetConfigListener mListener;
        public byte[] mParamBitArray;

        public SetRestoreDefault(byte[] bArr, int i, SuperListener.SetConfigListener setConfigListener) {
            this.mParamBitArray = bArr;
            this.mLength = i;
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
            final int SendCtrlCommand = NetManager.dvrNet.SendCtrlCommand(0);
            NetManager.handler.post(new Runnable() {
                public void run() {
                    if (SendCtrlCommand == 0) {
                        RebootDevTask.this.mListener.onSuccess();
                    } else {
                        RebootDevTask.this.mListener.onFailure();
                    }
                }
            });
        }
    }

    public void formatStorage(int i, SuperListener.SetConfigListener setConfigListener) {
        executor.execute(new FormatStorageTask(i, setConfigListener));
    }

    public class FormatStorageTask implements Runnable {
        public int mIndex;
        public SuperListener.SetConfigListener mListener;

        public FormatStorageTask(int i, SuperListener.SetConfigListener setConfigListener) {
            this.mListener = setConfigListener;
            this.mIndex = i;
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
            final int FormatStorage = NetManager.dvrNet.FormatStorage(this.mIndex);
            NetManager.handler.post(new Runnable() {
                public void run() {
                    if (FormatStorage == 0) {
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

        public SetUserRigthTask(String str, String str2, SuperListener.SetConfigListener setConfigListener) {
            this.mUsername = str;
            this.mUserRigth = str2;
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

    public void getUserRigth(String str, SuperListener.GetConfigListener getConfigListener) {
        executor.execute(new GetUserRigthTask(str, getConfigListener));
    }

    public class GetUserRigthTask implements Runnable {
        public SuperListener.GetConfigListener mListener;
        public String mRequest;

        public GetUserRigthTask(String str, SuperListener.GetConfigListener getConfigListener) {
            this.mRequest = str;
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
            final String GetUserRigth = NetManager.dvrNet.GetUserRigth(this.mRequest);
            NetManager.handler.post(new Runnable() {
                public void run() {
                    if (!TextUtils.isEmpty(GetUserRigth)) {
                        GetUserRigthTask.this.mListener.onSuccess(GetUserRigth);
                    } else {
                        GetUserRigthTask.this.mListener.onFailure();
                    }
                }
            });
        }
    }

    public void getConfig(String str, SuperListener.GetConfigListener getConfigListener) {
        executor.execute(new GetConfigTask(str, getConfigListener));
    }

    public class GetConfigTask implements Runnable {
        public SuperListener.GetConfigListener mListener;
        public String mRequest;

        public GetConfigTask(String str, SuperListener.GetConfigListener getConfigListener) {
            this.mRequest = str;
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
            final String GetConfig = NetManager.dvrNet.GetConfig(this.mRequest);
            NetManager.handler.post(new Runnable() {
                public void run() {
                    if (!TextUtils.isEmpty(GetConfig)) {
                        GetConfigTask.this.mListener.onSuccess(GetConfig);
                    } else {
                        GetConfigTask.this.mListener.onFailure();
                    }
                }
            });
        }
    }

    public void setConfig(String str, SuperListener.SetConfigListener setConfigListener) {
        executor.execute(new SetConfigTask(str, setConfigListener));
    }

    public class SetConfigTask implements Runnable {
        public SuperListener.SetConfigListener mListener;
        public String mRequest;

        public SetConfigTask(String str, SuperListener.SetConfigListener setConfigListener) {
            this.mRequest = str;
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
            final int SetConfig = NetManager.dvrNet.SetConfig(this.mRequest);
            NetManager.handler.post(new Runnable() {
                public void run() {
                    if (SetConfig == 0) {
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
            final String GetStorageInfo = NetManager.dvrNet.GetStorageInfo();
            NetManager.handler.post(new Runnable() {
                public void run() {
                    if (!TextUtils.isEmpty(GetStorageInfo)) {
                        GetStorageInfoTask.this.mListener.onSuccess(GetStorageInfo);
                    } else {
                        GetStorageInfoTask.this.mListener.onFailure();
                    }
                }
            });
        }
    }
}
