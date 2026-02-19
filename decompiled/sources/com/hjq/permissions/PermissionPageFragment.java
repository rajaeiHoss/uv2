package com.hjq.permissions;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

public final class PermissionPageFragment extends Fragment implements Runnable {
    private static final String REQUEST_PERMISSIONS = "request_permissions";
    private OnPermissionPageCallback mCallBack;
    private boolean mRequestFlag;
    private boolean mStartActivityFlag;

    public static void beginRequest(Activity activity, ArrayList<String> arrayList, OnPermissionPageCallback onPermissionPageCallback) {
        PermissionPageFragment permissionPageFragment = new PermissionPageFragment();
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(REQUEST_PERMISSIONS, arrayList);
        permissionPageFragment.setArguments(bundle);
        permissionPageFragment.setRetainInstance(true);
        permissionPageFragment.setRequestFlag(true);
        permissionPageFragment.setCallBack(onPermissionPageCallback);
        permissionPageFragment.attachActivity(activity);
    }

    public void attachActivity(Activity activity) {
        activity.getFragmentManager().beginTransaction().add(this, toString()).commitAllowingStateLoss();
    }

    public void detachActivity(Activity activity) {
        activity.getFragmentManager().beginTransaction().remove(this).commitAllowingStateLoss();
    }

    public void onResume() {
        super.onResume();
        if (!this.mRequestFlag) {
            detachActivity(getActivity());
        } else if (!this.mStartActivityFlag) {
            this.mStartActivityFlag = true;
            Bundle arguments = getArguments();
            Activity activity = getActivity();
            if (arguments != null && activity != null) {
                startActivityForResult(PermissionUtils.getSmartPermissionIntent(getActivity(), arguments.getStringArrayList(REQUEST_PERMISSIONS)), 1025);
            }
        }
    }

    public void setCallBack(OnPermissionPageCallback onPermissionPageCallback) {
        this.mCallBack = onPermissionPageCallback;
    }

    public void setRequestFlag(boolean z) {
        this.mRequestFlag = z;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        ArrayList<String> stringArrayList;
        if (i == 1025) {
            Activity activity = getActivity();
            Bundle arguments = getArguments();
            if (activity != null && arguments != null && (stringArrayList = arguments.getStringArrayList(REQUEST_PERMISSIONS)) != null && !stringArrayList.isEmpty()) {
                PermissionUtils.postActivityResult(stringArrayList, this);
            }
        }
    }

    public void run() {
        Activity activity;
        if (isAdded() && (activity = getActivity()) != null) {
            OnPermissionPageCallback onPermissionPageCallback = this.mCallBack;
            this.mCallBack = null;
            if (onPermissionPageCallback == null) {
                detachActivity(getActivity());
                return;
            }
            ArrayList<String> stringArrayList = getArguments().getStringArrayList(REQUEST_PERMISSIONS);
            if (PermissionApi.getGrantedPermissions((Context) activity, (List<String>) stringArrayList).size() == stringArrayList.size()) {
                onPermissionPageCallback.onGranted();
            } else {
                onPermissionPageCallback.onDenied();
            }
        }
    }
}
