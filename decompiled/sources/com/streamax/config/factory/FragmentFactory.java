package com.streamax.config.factory;

import com.streamax.config.base.ConfigFragment;
import com.streamax.config.fragment.AlarmFragment;
import com.streamax.config.fragment.DateTimeFragment;
import com.streamax.config.fragment.DiskFragment;
import com.streamax.config.fragment.NetworkFragment;
import com.streamax.config.fragment.RecordFragment;
import com.streamax.config.fragment.SystemInfoFragment;
import com.streamax.config.fragment.SystemSetFragment;
import com.streamax.config.fragment.UserFragment;
import com.streamax.config.fragment.VideoFragment;
import com.streamax.config.fragment.network.DdnsOfNetwork;
import com.streamax.config.fragment.network.EmailOfNetwork;
import com.streamax.config.fragment.network.FtpOfNetwork;
import com.streamax.config.fragment.network.LanOfNetwork;
import com.streamax.config.fragment.network.PortOfNetwork;
import com.streamax.config.fragment.network.UpnpOfNetwork;

public class FragmentFactory {
    private static final int FragmentAlarm = 4;
    private static final int FragmentDateTime = 2;
    private static final int FragmentDisk = 8;
    private static final int FragmentInformation = 5;
    private static final int FragmentNetwork = 6;
    private static final int FragmentRecord = 0;
    private static final int FragmentSystem = 7;
    private static final int FragmentUser = 3;
    private static final int FragmentVideo = 1;

    public static ConfigFragment createFragment(int i) {
        switch (i) {
            case 0:
                return new RecordFragment();
            case 1:
                return new VideoFragment();
            case 2:
                return new DateTimeFragment();
            case 3:
                return new UserFragment();
            case 4:
                return new AlarmFragment();
            case 5:
                return new SystemInfoFragment();
            case 6:
                return new NetworkFragment();
            case 7:
                return new SystemSetFragment();
            case 8:
                return new DiskFragment();
            default:
                return null;
        }
    }

    public static class NetworkFactory {
        public static final int FRAGMENT_DDNS = 2;
        public static final int FRAGMENT_EMAIL = 3;
        public static final int FRAGMENT_FTP = 4;
        public static final int FRAGMENT_LAN = 0;
        public static final int FRAGMENT_PORT = 1;
        public static final int FRAGMENT_UPNP = 5;

        public static ConfigFragment createFragment(int i) {
            if (i == 0) {
                return new LanOfNetwork();
            }
            if (i == 1) {
                return new PortOfNetwork();
            }
            if (i == 2) {
                return new DdnsOfNetwork();
            }
            if (i == 3) {
                return new EmailOfNetwork();
            }
            if (i == 4) {
                return new FtpOfNetwork();
            }
            if (i != 5) {
                return null;
            }
            return new UpnpOfNetwork();
        }
    }
}
