# Obfuscation Audit

Date: 2026-07-10

This audit identifies remaining obfuscated or decompiler-generated names in the decompiled source tree. It does not rename code by itself; it defines safe cleanup targets for later chunks.

## Scope

Source root: `decompiled/sources`

Total source files: 12,134

| Type | Count |
| --- | ---: |
| Java files | 11,232 |
| Kotlin files | 902 |
| App-owned files (`com.streamax`, `com.dvr`) | 223 |
| App-owned Java files | 161 |
| App-owned Kotlin files | 62 |

## Obfuscated File Basenames

The broad file-name scan looked for class/file basenames matching `zz*` and very short lowercase basenames (`a`, `ab`, `abc`). These names are primarily Google Play Services / Firebase API and internal implementation names.

| Pattern | Count |
| --- | ---: |
| `zz*` basename files | 6,453 |
| `zz*` Java files | 6,245 |
| `zz*` Kotlin files | 208 |
| Short lowercase basename files | 1,504 |
| Short lowercase Java files | 1,449 |
| Short lowercase Kotlin files | 55 |
| App-owned `zz*` basename files | 0 |
| App-owned short lowercase basename files | 0 |

Top `zz*` namespaces:

| Namespace | Count |
| --- | ---: |
| `com/google/android/gms/internal` | 3,887 |
| `com/google/android/gms/games` | 312 |
| `com/google/android/gms/common` | 267 |
| `com/google/android/gms/wearable` | 242 |
| `com/google/android/gms/tagmanager` | 196 |
| `com/google/android/gms/ads` | 178 |
| `com/google/android/gms/maps` | 166 |
| `com/google/android/gms/cast` | 160 |
| `com/google/android/gms/fitness` | 114 |
| `com/google/android/gms/location` | 107 |
| `com/google/android/gms/drive` | 102 |
| `com/google/android/gms/nearby` | 97 |
| `com/google/android/gms/auth` | 87 |
| `com/google/firebase/database` | 57 |
| `com/google/firebase/auth` | 51 |

## App-Owned Decompiled Identifiers

App-owned source files do not have obfuscated file names, but many still contain decompiler-style identifiers such as `r0`, `str`, `bArr`, `i2`, and `z`.

| Identifier family | App-owned files hit |
| --- | ---: |
| `r0`, `r1`, ... | 0 |
| `str`, `str2`, ... | 53 |
| `bArr`, `bArr2`, ... | 2 |
| `i2`, `j2`, ... | 29 |
| `z`, `z2`, ... | 31 |
| App-owned files with at least one generic identifier | 71 |

Top app-owned cleanup targets by generic identifier count:

| File | Generic identifier hits |
| --- | ---: |
| `decompiled/sources/com/streamax/config/fragment/AlarmFragment.java` | 31 |
| `decompiled/sources/com/streamax/client/CalibrationActivity.java` | 31 |
| `decompiled/sources/com/streamax/config/fragment/DateTimeFragment.java` | 30 |
| `decompiled/sources/com/streamax/client/RemotePlayback.java` | 30 |
| `decompiled/sources/com/streamax/client/LocalPlaybackActivity.java` | 30 |
| `decompiled/sources/com/streamax/client/VideoFrame.java` | 29 |
| `decompiled/sources/com/streamax/client/MainActivity.java` | 29 |
| `decompiled/sources/com/streamax/client/ui/devlist/CustomExpandAdapter.java` | 27 |
| `decompiled/sources/com/streamax/utils/SpUtils.java` | 26 |
| `decompiled/sources/com/streamax/client/DevSelectUi.java` | 26 |
| `decompiled/sources/com/streamax/config/network/NetManager.java` | 24 |
| `decompiled/sources/com/streamax/config/fragment/network/LanOfNetwork.java` | 23 |
| `decompiled/sources/com/streamax/config/bean/DeviceJsonBean.java` | 22 |
| `decompiled/sources/com/streamax/client/manager/PickerLayoutManager.java` | 22 |
| `decompiled/sources/com/streamax/client/PlayFragmentDown.java` | 22 |
| `decompiled/sources/com/streamax/config/base/ConfigFragment.java` | 21 |
| `decompiled/sources/com/streamax/client/PushInfo.java` | 20 |
| `decompiled/sources/com/streamax/client/SmackCcsClient.java` | 19 |
| `decompiled/sources/com/streamax/config/fragment/RecordFragment.java` | 18 |
| `decompiled/sources/com/streamax/utils/TimeUtils.java` | 17 |

Generic identifier concentration by app-owned namespace:

| Namespace | Files | Hits |
| --- | ---: | ---: |
| `decompiled/sources/com/streamax` | 71 | 831 |
| `decompiled/sources/com/dvr` | 0 | 0 |

## Kotlin Generic Parameter Names

Converted Kotlin files still include some generic parameter names. These are safer to clean when they are method parameters or locals and when ABI-sensitive method/class names remain unchanged.

| Namespace | Kotlin files with generic params |
| --- | ---: |
| `com/hjq/http` | 36 |
| `com/google/android` | 17 |
| `org/jivesoftware/smackx` | 3 |
| `org/jivesoftware/smack` | 3 |
| `com/wifi/net` | 3 |
| `com/kenai/jbosh` | 1 |
| `com/amo/demo` | 1 |
| `freemarker/log/LoggerFactory.kt` | 1 |

## Cleanup Recommendation

1. Clean app-owned local variables and private parameters first in `com.streamax` and `com.dvr`.
2. Prioritize files with the highest generic identifier count only when each chunk can still compile and launch on a physical device.
3. Keep public API, native bridge, callback, Parcelable, database, and reflection-sensitive names unchanged unless all call sites are updated together.
4. Do not rename Google/Firebase/AndroidX `zz*` classes or short public class names as a readability-only task. Those names are dependency/API-compatible names and renaming them can break compiled references, reflection, serialized names, or generated-resource linkage.
5. For third-party packages, prefer parameter/local cleanup over class or method renaming.

## Next Safe Chunk

All app-owned Kotlin parameter cleanup is complete under the current generic-identifier scan.

The latest safe chunks cleaned executable/local identifiers in `RealPlayActivity.java`, removed its non-executable `SwitchChannelRunnable` decompiler dump, cleaned the smaller layout/control methods plus `TurnNextView` and `TurnLastView` in `VideoGroup.java`, removed the non-executable `PlayerView.onTouchEvent` decompiler dump, reconstructed `VideoContainer.ArrayViews`, cleaned `VideoContainer.TurnNextView` and `TurnLastView`, reconstructed the frame-rate helpers and copy-channel compatibility flow in `StreamOfVideo.java`, reconstructed the DDNS refresh flow in `DdnsOfNetwork.java`, reconstructed the DST time-picker flow in `DstOfDt.java`, cleaned the render/zoom/touch locals in `VideoView.java`, simplified `LiveViewUi` surface visibility while removing its stale `SwitchChannelRunnable` JADX dump, reconstructed `ConnDeviceProxy.connDeviceByAuto`, simplified `TriggerOfAlarm.refreshUi`, cleaned `WebService.java` SOAP/CSV parsing locals, reconstructed `CustomImageView.writeIn`, clarified `PlanOfRecord` schedule merge logic, fixed the Ai Cal round-menu direction mapping plus `CalibrationActivity` vertical-line refresh, clarified `ScheduleOfAlarm` schedule merging, cleaned `EventPlaybackActivity.java` playback/capture locals, clarified `DevGroupFragment.java` group/channel save flow, clarified `EmailOfNetwork.java` email config parsing/saving flow, clarified `PlayFragmentSearch.java` remote playback search flow, cleaned the remaining `LiveViewUi.java` playback/capture/autoplay locals, clarified `CustomSurfaceView.java` drag/zoom bounds locals, cleaned the remaining `StreamOfVideo.java` stream config locals, reconstructed `ThreadPoolProxy.java` executor initialization, clarified `FragmentCMS.java` CMS server selection locals, clarified `TriggerOfAlarm.java` alarm trigger update mappings, clarified `DeviceInfoDao.java` database query/update parameters, clarified `DevListFragment.java` device/group list variables, clarified `TimeBar.java` drawing coordinate variables, clarified `FragmentSysTime.java` date/time config variables, clarified `DateDialog.java` picker variables, clarified `PlayActivity.java` playback control variables, clarified `TimeDialog.java` picker variables, clarified `Login2Activity.java` login/Wi-Fi callback variables, clarified `CustomExpandLv.java` sticky-header geometry variables, clarified `RemoteFileList.java` remote playback date/time picker variables, clarified `EventPlayback.java` alarm-event list variables, clarified `LogUtils.java` log message parameters, clarified `DevAddFragment.java` group/channel update variables, clarified `RemotePlaybackActivity.java` remote playback control variables, clarified `LvVideoFrame.java` video-frame state parameters, clarified `SingleVideoView.java` bitmap renderer parameters and scale locals, clarified `WebViewFragment.java` WebView URL-construction variables, clarified `ConfigPageActivity.java` WebView URL-construction variables, clarified `FragmentXGNet.java` mobile-network config variables, and clarified `DiskFragment.java` storage and overwrite config variables. Physical-device verification is deferred for chunks after `DevAddFragment.java` because device access is currently unavailable; keep Gradle build plus audit checks as the cleanup gate until device access returns. `RealPlayActivity.java`, `PlayerView.java`, `DdnsOfNetwork.java`, `DstOfDt.java`, `VideoGroup.java`, `VideoContainer.java`, `VideoView.java`, `ConnDeviceProxy.java`, `WebService.java`, `CustomImageView.java`, `PlanOfRecord.java`, `ScheduleOfAlarm.java`, `EventPlaybackActivity.java`, `DevGroupFragment.java`, `EmailOfNetwork.java`, `PlayFragmentSearch.java`, `LiveViewUi.java`, `CustomSurfaceView.java`, `StreamOfVideo.java`, `ThreadPoolProxy.java`, `FragmentCMS.java`, `TriggerOfAlarm.java`, `DeviceInfoDao.java`, `DevListFragment.java`, `TimeBar.java`, `FragmentSysTime.java`, `DateDialog.java`, `PlayActivity.java`, `TimeDialog.java`, `Login2Activity.java`, `CustomExpandLv.java`, `RemoteFileList.java`, `EventPlayback.java`, `LogUtils.java`, `DevAddFragment.java`, `RemotePlaybackActivity.java`, `LvVideoFrame.java`, `SingleVideoView.java`, `WebViewFragment.java`, `ConfigPageActivity.java`, `FragmentXGNet.java`, and `DiskFragment.java` no longer have generic identifier hits under this audit scan; `CalibrationActivity.java` is down from 40 to 31 hits.

The next safe chunk is continued Java-heavy app UI cleanup, starting with the highest-hit files:

- `decompiled/sources/com/streamax/config/fragment/AlarmFragment.java`
- `decompiled/sources/com/streamax/client/CalibrationActivity.java`
- `decompiled/sources/com/streamax/config/fragment/DateTimeFragment.java`
- `decompiled/sources/com/streamax/client/RemotePlayback.java`

Keep each Java cleanup chunk narrow because these files contain decompiled control flow and playback/channel state.
