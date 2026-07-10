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
| `r0`, `r1`, ... | 8 |
| `str`, `str2`, ... | 82 |
| `bArr`, `bArr2`, ... | 10 |
| `i2`, `j2`, ... | 63 |
| `z`, `z2`, ... | 50 |
| App-owned files with at least one generic identifier | 111 |

Top app-owned cleanup targets by generic identifier count:

| File | Generic identifier hits |
| --- | ---: |
| `decompiled/sources/com/streamax/config/fragment/network/DdnsOfNetwork.java` | 302 |
| `decompiled/sources/com/streamax/config/fragment/datetime/DstOfDt.java` | 288 |
| `decompiled/sources/com/streamax/client/VideoGroup.java` | 237 |
| `decompiled/sources/com/streamax/client/VideoContainer.java` | 237 |
| `decompiled/sources/com/streamax/client/VideoView.java` | 217 |
| `decompiled/sources/com/streamax/client/LiveViewUi.java` | 215 |
| `decompiled/sources/com/streamax/proxy/ConnDeviceProxy.java` | 182 |
| `decompiled/sources/com/streamax/config/fragment/video/StreamOfVideo.java` | 159 |
| `decompiled/sources/com/streamax/config/fragment/alarm/TriggerOfAlarm.java` | 146 |
| `decompiled/sources/com/streamax/client/WebService.java` | 141 |
| `decompiled/sources/com/streamax/client/CustomImageView.java` | 140 |
| `decompiled/sources/com/streamax/config/fragment/record/PlanOfRecord.java` | 120 |
| `decompiled/sources/com/streamax/config/fragment/alarm/ScheduleOfAlarm.java` | 109 |
| `decompiled/sources/com/streamax/client/EventPlaybackActivity.java` | 108 |
| `decompiled/sources/com/streamax/client/ui/devlist/ui/DevGroupFragment.java` | 101 |
| `decompiled/sources/com/streamax/config/fragment/network/EmailOfNetwork.java` | 94 |
| `decompiled/sources/com/streamax/client/PlayFragmentSearch.java` | 91 |
| `decompiled/sources/com/streamax/view/CustomSurfaceView.java` | 77 |
| `decompiled/sources/com/streamax/config/proxy/ThreadPoolProxy.java` | 60 |
| `decompiled/sources/com/streamax/config/fragment/FragmentCMS.java` | 59 |

Generic identifier concentration by app-owned namespace:

| Namespace | Files | Hits |
| --- | ---: | ---: |
| `decompiled/sources/com/streamax` | 111 | 4,712 |
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

The latest safe chunks cleaned executable/local identifiers in `RealPlayActivity.java`, removed its non-executable `SwitchChannelRunnable` decompiler dump, cleaned the smaller layout/control methods and `TurnNextView` in `VideoGroup.java`, removed the non-executable `PlayerView.onTouchEvent` decompiler dump, cleaned `VideoContainer.TurnNextView`, and reconstructed the frame-rate helpers in `StreamOfVideo.java`. `RealPlayActivity.java` and `PlayerView.java` no longer have generic identifier hits under this audit scan; `VideoGroup.java` is down from 620 to 237 hits, `VideoContainer.java` is down from 476 to 237 hits, and `StreamOfVideo.java` is down from 472 to 159 hits.

The next safe chunk is continued Java-heavy playback UI cleanup, starting with the highest-hit files:

- `decompiled/sources/com/streamax/config/fragment/network/DdnsOfNetwork.java`
- `decompiled/sources/com/streamax/config/fragment/datetime/DstOfDt.java`
- `decompiled/sources/com/streamax/client/VideoGroup.java`
- `decompiled/sources/com/streamax/client/VideoContainer.java`

Keep each Java cleanup chunk narrow because these files contain decompiled control flow and playback/channel state. `VideoContainer.java` no longer contains the large non-executable decompiler register dump for `ArrayViews`; its remaining hits are in real Java methods.
