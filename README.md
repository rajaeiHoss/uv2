# uview (studio view)

This project is a clean Android Studio wrapper around the UView APK resources and decompiled sources.

## Modules
- `app`: resources-only module used to repack the original dex files.
- `app-clean`: compiles the decompiled sources in `decompiled/sources/` and reuses the same resources.

## What works
- `app/src/main/AndroidManifest.xml`, `res/`, `assets/`, and `jniLibs/` are imported from `unzip-1041/resources/`.
- Decompiled sources are available under `decompiled/sources/` for browsing and compilation.

## Notes
- `app-clean` excludes `com/zycs/UView/R.java` and `com/zycs/UView/BuildConfig.java` to avoid duplicate classes.
- The runnable APK is built by swapping in the original dex files from `UView-release-20250329_1041.apk` when using `app`.

## Open in Android Studio
- Open the folder `uview-studio/`.
- If asked to download Gradle/SDK, allow it.

## Build a runnable APK (with original dex)
This project assembles resources from `app/` and then swaps in the original dex files.

```bash
./gradlew :app:repackDebugWithDex
```

The output APK will be:
`app/build/outputs/apk/debug/app-debug-dex.apk`

## Build a clean APK (from sources)
This compiles the decompiled sources directly.

```bash
./gradlew :app-clean:assembleDebug
```
