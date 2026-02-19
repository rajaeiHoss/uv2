#!/usr/bin/env python3
import sys
import zipfile
from pathlib import Path


def read_dex_files(dex_apk: Path):
    dex_names = ["classes.dex", "classes2.dex", "classes3.dex"]
    dex_data = {}
    with zipfile.ZipFile(dex_apk, "r") as zin:
        for name in dex_names:
            try:
                dex_data[name] = zin.read(name)
            except KeyError:
                continue
    if not dex_data:
        raise SystemExit(f"No classes*.dex found in {dex_apk}")
    return dex_data


def repack_with_dex(src_apk: Path, dex_apk: Path, out_apk: Path):
    dex_data = read_dex_files(dex_apk)
    with zipfile.ZipFile(src_apk, "r") as zin, zipfile.ZipFile(
        out_apk, "w", compression=zipfile.ZIP_DEFLATED
    ) as zout:
        for item in zin.infolist():
            if item.filename in dex_data:
                continue
            data = zin.read(item.filename)
            zout.writestr(item, data)
        for name, data in dex_data.items():
            zout.writestr(name, data)


if __name__ == "__main__":
    if len(sys.argv) != 4:
        raise SystemExit(
            "Usage: repack_with_original_dex.py <src_apk> <dex_apk> <out_apk>"
        )
    src_apk = Path(sys.argv[1])
    dex_apk = Path(sys.argv[2])
    out_apk = Path(sys.argv[3])
    repack_with_dex(src_apk, dex_apk, out_apk)
