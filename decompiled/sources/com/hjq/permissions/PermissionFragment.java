package com.hjq.permissions;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.hjq.permissions.IPermissionInterceptor;
import com.hjq.permissions.PermissionFragment;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public final class PermissionFragment extends Fragment implements Runnable {
    private static final String REQUEST_CODE = "request_code";
    private static final List<Integer> REQUEST_CODE_ARRAY = new ArrayList();
    private static final String REQUEST_PERMISSIONS = "request_permissions";
    private OnPermissionCallback mCallBack;
    private boolean mDangerousRequest;
    private IPermissionInterceptor mInterceptor;
    private boolean mRequestFlag;
    private int mScreenOrientation;
    private boolean mSpecialRequest;

    public static void beginRequest(Activity activity, ArrayList<String> arrayList, IPermissionInterceptor iPermissionInterceptor, OnPermissionCallback onPermissionCallback) {
        int nextInt;
        List<Integer> list;
        PermissionFragment permissionFragment = new PermissionFragment();
        Bundle bundle = new Bundle();
        do {
            nextInt = new Random().nextInt((int) Math.pow(2.0d, 8.0d));
            list = REQUEST_CODE_ARRAY;
        } while (list.contains(Integer.valueOf(nextInt)));
        list.add(Integer.valueOf(nextInt));
        bundle.putInt(REQUEST_CODE, nextInt);
        bundle.putStringArrayList(REQUEST_PERMISSIONS, arrayList);
        permissionFragment.setArguments(bundle);
        permissionFragment.setRetainInstance(true);
        permissionFragment.setRequestFlag(true);
        permissionFragment.setCallBack(onPermissionCallback);
        permissionFragment.setInterceptor(iPermissionInterceptor);
        permissionFragment.attachActivity(activity);
    }

    public void attachActivity(Activity activity) {
        activity.getFragmentManager().beginTransaction().add(this, toString()).commitAllowingStateLoss();
    }

    public void detachActivity(Activity activity) {
        activity.getFragmentManager().beginTransaction().remove(this).commitAllowingStateLoss();
    }

    public void setCallBack(OnPermissionCallback onPermissionCallback) {
        this.mCallBack = onPermissionCallback;
    }

    public void setRequestFlag(boolean z) {
        this.mRequestFlag = z;
    }

    public void setInterceptor(IPermissionInterceptor iPermissionInterceptor) {
        this.mInterceptor = iPermissionInterceptor;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        Activity activity = getActivity();
        if (activity != null) {
            int requestedOrientation = activity.getRequestedOrientation();
            this.mScreenOrientation = requestedOrientation;
            if (requestedOrientation == -1) {
                PermissionUtils.lockActivityOrientation(activity);
            }
        }
    }

    public void onDetach() {
        super.onDetach();
        Activity activity = getActivity();
        if (activity != null && this.mScreenOrientation == -1 && activity.getRequestedOrientation() != -1) {
            activity.setRequestedOrientation(-1);
        }
    }

    public void onDestroy() {
        super.onDestroy();
        this.mCallBack = null;
    }

    public void onResume() {
        super.onResume();
        if (!this.mRequestFlag) {
            detachActivity(getActivity());
        } else if (!this.mSpecialRequest) {
            this.mSpecialRequest = true;
            requestSpecialPermission();
        }
    }

    public void requestSpecialPermission() {
        Bundle arguments = getArguments();
        Activity activity = getActivity();
        if (arguments != null && activity != null) {
            boolean z = false;
            for (String next : arguments.getStringArrayList(REQUEST_PERMISSIONS)) {
                if (PermissionApi.isSpecialPermission(next) && !PermissionApi.isGrantedPermission(activity, next)) {
                    if (AndroidVersion.isAndroid11() || !PermissionUtils.equalsPermission(next, Permission.MANAGE_EXTERNAL_STORAGE)) {
                        startActivityForResult(PermissionUtils.getSmartPermissionIntent(activity, PermissionUtils.asArrayList(next)), getArguments().getInt(REQUEST_CODE));
                        z = true;
                    }
                }
            }
            if (!z) {
                requestDangerousPermission();
            }
        }
    }

    public void requestDangerousPermission() {
        Activity activity = getActivity();
        Bundle arguments = getArguments();
        if (activity != null && arguments != null) {
            int i = arguments.getInt(REQUEST_CODE);
            ArrayList<String> stringArrayList = arguments.getStringArrayList(REQUEST_PERMISSIONS);
            if (stringArrayList != null && !stringArrayList.isEmpty()) {
                if (!AndroidVersion.isAndroid6()) {
                    int size = stringArrayList.size();
                    int[] iArr = new int[size];
                    for (int i2 = 0; i2 < size; i2++) {
                        iArr[i2] = PermissionApi.isGrantedPermission(activity, stringArrayList.get(i2)) ? 0 : -1;
                    }
                    onRequestPermissionsResult(i, (String[]) stringArrayList.toArray(new String[0]), iArr);
                } else if (AndroidVersion.isAndroid13() && stringArrayList.size() >= 2 && PermissionUtils.containsPermission(stringArrayList, Permission.BODY_SENSORS_BACKGROUND)) {
                    ArrayList arrayList = new ArrayList(stringArrayList);
                    arrayList.remove(Permission.BODY_SENSORS_BACKGROUND);
                    splitTwiceRequestPermission(activity, stringArrayList, arrayList, i);
                } else if (AndroidVersion.isAndroid10() && stringArrayList.size() >= 2 && PermissionUtils.containsPermission(stringArrayList, Permission.ACCESS_BACKGROUND_LOCATION)) {
                    ArrayList arrayList2 = new ArrayList(stringArrayList);
                    arrayList2.remove(Permission.ACCESS_BACKGROUND_LOCATION);
                    splitTwiceRequestPermission(activity, stringArrayList, arrayList2, i);
                } else if (!AndroidVersion.isAndroid10() || !PermissionUtils.containsPermission(stringArrayList, Permission.ACCESS_MEDIA_LOCATION) || !PermissionUtils.containsPermission(stringArrayList, Permission.READ_EXTERNAL_STORAGE)) {
                    requestPermissions((String[]) stringArrayList.toArray(new String[(stringArrayList.size() - 1)]), i);
                } else {
                    ArrayList arrayList3 = new ArrayList(stringArrayList);
                    arrayList3.remove(Permission.ACCESS_MEDIA_LOCATION);
                    splitTwiceRequestPermission(activity, stringArrayList, arrayList3, i);
                }
            }
        }
    }

    public void splitTwiceRequestPermission(Activity activity, ArrayList<String> arrayList, ArrayList<String> arrayList2, int i) {
        final ArrayList arrayList3 = new ArrayList(arrayList);
        Iterator<String> it = arrayList2.iterator();
        while (it.hasNext()) {
            arrayList3.remove(it.next());
        }
        final Activity activity2 = activity;
        final ArrayList<String> arrayList4 = arrayList;
        final int i2 = i;
        beginRequest(activity, arrayList2, new IPermissionInterceptor() {
            public /* synthetic */ void deniedPermissions(Activity activity, List list, List list2, boolean z, OnPermissionCallback onPermissionCallback) {
                IPermissionInterceptor.CC.$default$deniedPermissions(this, activity, list, list2, z, onPermissionCallback);
            }

            public /* synthetic */ void grantedPermissions(Activity activity, List list, List list2, boolean z, OnPermissionCallback onPermissionCallback) {
                IPermissionInterceptor.CC.$default$grantedPermissions(this, activity, list, list2, z, onPermissionCallback);
            }

            public /* synthetic */ void requestPermissions(Activity activity, List list, OnPermissionCallback onPermissionCallback) {
                IPermissionInterceptor.CC.$default$requestPermissions(this, activity, list, onPermissionCallback);
            }
        }, new OnPermissionCallback() {
            public void onGranted(List<String> list, boolean z) {
                if (z && PermissionFragment.this.isAdded()) {
                    PermissionUtils.postDelayed(new Runnable() {
                        public void run() {
                            final ArrayList arrayList = arrayList3;
                            final ArrayList arrayList2 = arrayList4;
                            final int i = i2;
                            PermissionFragment.beginRequest(activity2, arrayList, new IPermissionInterceptor() {
                                public /* synthetic */ void deniedPermissions(Activity activity, List list, List list2, boolean z, OnPermissionCallback onPermissionCallback) {
                                    IPermissionInterceptor.CC.$default$deniedPermissions(this, activity, list, list2, z, onPermissionCallback);
                                }

                                public /* synthetic */ void grantedPermissions(Activity activity, List list, List list2, boolean z, OnPermissionCallback onPermissionCallback) {
                                    IPermissionInterceptor.CC.$default$grantedPermissions(this, activity, list, list2, z, onPermissionCallback);
                                }

                                public /* synthetic */ void requestPermissions(Activity activity, List list, OnPermissionCallback onPermissionCallback) {
                                    IPermissionInterceptor.CC.$default$requestPermissions(this, activity, list, onPermissionCallback);
                                }
                            }, new OnPermissionCallback() {
                                public void onGranted(List<String> list, boolean z) {
                                    if (z && PermissionFragment.this.isAdded()) {
                                        int[] iArr = new int[arrayList2.size()];
                                        Arrays.fill(iArr, 0);
                                        PermissionFragment.this.onRequestPermissionsResult(i, (String[]) arrayList2.toArray(new String[0]), iArr);
                                    }
                                }

                                public void onDenied(List<String> list, boolean z) {
                                    if (PermissionFragment.this.isAdded()) {
                                        int[] iArr = new int[arrayList2.size()];
                                        for (int i = 0; i < arrayList2.size(); i++) {
                                            iArr[i] = PermissionUtils.containsPermission(arrayList, (String) arrayList2.get(i)) ? -1 : 0;
                                        }
                                        PermissionFragment.this.onRequestPermissionsResult(i, (String[]) arrayList2.toArray(new String[0]), iArr);
                                    }
                                }
                            });
                        }
                    }, AndroidVersion.isAndroid13() ? 150 : 0);
                }
            }

            public /* synthetic */ void lambda$onGranted$0$PermissionFragment$2(Activity activity, final ArrayList arrayList, final ArrayList arrayList2, final int i) {
                PermissionFragment.beginRequest(activity, arrayList, new IPermissionInterceptor() {
                    public /* synthetic */ void deniedPermissions(Activity activity, List list, List list2, boolean z, OnPermissionCallback onPermissionCallback) {
                        IPermissionInterceptor.CC.$default$deniedPermissions(this, activity, list, list2, z, onPermissionCallback);
                    }

                    public /* synthetic */ void grantedPermissions(Activity activity, List list, List list2, boolean z, OnPermissionCallback onPermissionCallback) {
                        IPermissionInterceptor.CC.$default$grantedPermissions(this, activity, list, list2, z, onPermissionCallback);
                    }

                    public /* synthetic */ void requestPermissions(Activity activity, List list, OnPermissionCallback onPermissionCallback) {
                        IPermissionInterceptor.CC.$default$requestPermissions(this, activity, list, onPermissionCallback);
                    }
                }, new OnPermissionCallback() {
                    public void onGranted(List<String> list, boolean z) {
                        if (z && PermissionFragment.this.isAdded()) {
                            int[] iArr = new int[arrayList2.size()];
                            Arrays.fill(iArr, 0);
                            PermissionFragment.this.onRequestPermissionsResult(i, (String[]) arrayList2.toArray(new String[0]), iArr);
                        }
                    }

                    public void onDenied(List<String> list, boolean z) {
                        if (PermissionFragment.this.isAdded()) {
                            int[] iArr = new int[arrayList2.size()];
                            for (int i = 0; i < arrayList2.size(); i++) {
                                iArr[i] = PermissionUtils.containsPermission(arrayList, (String) arrayList2.get(i)) ? -1 : 0;
                            }
                            PermissionFragment.this.onRequestPermissionsResult(i, (String[]) arrayList2.toArray(new String[0]), iArr);
                        }
                    }
                });
            }

            public void onDenied(List<String> list, boolean z) {
                if (PermissionFragment.this.isAdded()) {
                    int[] iArr = new int[arrayList4.size()];
                    Arrays.fill(iArr, -1);
                    PermissionFragment.this.onRequestPermissionsResult(i2, (String[]) arrayList4.toArray(new String[0]), iArr);
                }
            }
        });
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (strArr != null && strArr.length != 0 && iArr != null && iArr.length != 0) {
            Bundle arguments = getArguments();
            Activity activity = getActivity();
            if (activity != null && arguments != null && this.mInterceptor != null && i == arguments.getInt(REQUEST_CODE)) {
                OnPermissionCallback onPermissionCallback = this.mCallBack;
                this.mCallBack = null;
                IPermissionInterceptor iPermissionInterceptor = this.mInterceptor;
                this.mInterceptor = null;
                PermissionUtils.optimizePermissionResults(activity, strArr, iArr);
                ArrayList asArrayList = PermissionUtils.asArrayList(strArr);
                REQUEST_CODE_ARRAY.remove(Integer.valueOf(i));
                detachActivity(activity);
                List<String> grantedPermissions = PermissionApi.getGrantedPermissions((List<String>) asArrayList, iArr);
                if (grantedPermissions.size() == asArrayList.size()) {
                    iPermissionInterceptor.grantedPermissions(activity, asArrayList, grantedPermissions, true, onPermissionCallback);
                    return;
                }
                List<String> deniedPermissions = PermissionApi.getDeniedPermissions((List<String>) asArrayList, iArr);
                iPermissionInterceptor.deniedPermissions(activity, asArrayList, deniedPermissions, PermissionApi.isPermissionPermanentDenied(activity, deniedPermissions), onPermissionCallback);
                if (!grantedPermissions.isEmpty()) {
                    iPermissionInterceptor.grantedPermissions(activity, asArrayList, grantedPermissions, false, onPermissionCallback);
                }
            }
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        ArrayList<String> stringArrayList;
        Activity activity = getActivity();
        Bundle arguments = getArguments();
        if (activity != null && arguments != null && !this.mDangerousRequest && i == arguments.getInt(REQUEST_CODE) && (stringArrayList = arguments.getStringArrayList(REQUEST_PERMISSIONS)) != null && !stringArrayList.isEmpty()) {
            this.mDangerousRequest = true;
            PermissionUtils.postActivityResult(stringArrayList, this);
        }
    }

    public void run() {
        if (isAdded()) {
            requestDangerousPermission();
        }
    }
}
