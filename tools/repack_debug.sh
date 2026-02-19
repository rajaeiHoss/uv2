#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
SRC_APK="$ROOT_DIR/app/build/outputs/apk/debug/app-debug.apk"
DEX_APK="$ROOT_DIR/../UView-release-20250329_1041.apk"
OUT_UNALIGNED="$ROOT_DIR/app/build/outputs/apk/debug/app-debug-dex-unaligned.apk"
OUT_APK="$ROOT_DIR/app/build/outputs/apk/debug/app-debug-dex.apk"

SDK_ROOT="${ANDROID_SDK_ROOT:-$HOME/Library/Android/sdk}"
BUILD_TOOLS_DIR="$SDK_ROOT/build-tools"
if [[ ! -d "$BUILD_TOOLS_DIR" ]]; then
  echo "Android SDK build-tools not found at $BUILD_TOOLS_DIR" >&2
  exit 1
fi
BUILD_TOOLS_VERSION=$(ls -1 "$BUILD_TOOLS_DIR" | sort -V | tail -n 1)
ZIPALIGN="$BUILD_TOOLS_DIR/$BUILD_TOOLS_VERSION/zipalign"
APKSIGNER="$BUILD_TOOLS_DIR/$BUILD_TOOLS_VERSION/apksigner"

if [[ ! -x "$ZIPALIGN" || ! -x "$APKSIGNER" ]]; then
  echo "zipalign or apksigner not found in $BUILD_TOOLS_DIR/$BUILD_TOOLS_VERSION" >&2
  exit 1
fi

tmp_dir="$(mktemp -d)"
cleanup() { rm -rf "$tmp_dir"; }
trap cleanup EXIT

cp "$SRC_APK" "$OUT_UNALIGNED"

# Remove any existing dex files.
zip -q -d "$OUT_UNALIGNED" "classes*.dex" || true

# Extract dex files from the original APK.
unzip -p "$DEX_APK" "classes.dex" > "$tmp_dir/classes.dex"
if unzip -p "$DEX_APK" "classes2.dex" > "$tmp_dir/classes2.dex" 2>/dev/null; then :; fi
if unzip -p "$DEX_APK" "classes3.dex" > "$tmp_dir/classes3.dex" 2>/dev/null; then :; fi

# Add dex files back uncompressed.
(
  cd "$tmp_dir"
  zip -q -0 "$OUT_UNALIGNED" classes*.dex
)

"$ZIPALIGN" -f 4 "$OUT_UNALIGNED" "$OUT_APK"
"$APKSIGNER" sign --ks "$HOME/.android/debug.keystore" \
  --ks-pass pass:android --key-pass pass:android --ks-key-alias androiddebugkey \
  "$OUT_APK"

echo "Repacked APK: $OUT_APK"
