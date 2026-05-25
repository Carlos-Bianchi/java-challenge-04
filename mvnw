#!/usr/bin/env sh
set -eu

MVN_VERSION="3.9.9"
BASE_DIR="$(CDPATH= cd -- "$(dirname -- "$0")" && pwd)"
MVN_DIR="$BASE_DIR/.mvn/apache-maven-$MVN_VERSION"
MVN_BIN="$MVN_DIR/bin/mvn"

if command -v mvn >/dev/null 2>&1; then
  exec mvn "$@"
fi

if [ ! -x "$MVN_BIN" ]; then
  ARCHIVE="$BASE_DIR/.mvn/apache-maven-$MVN_VERSION-bin.tar.gz"
  mkdir -p "$BASE_DIR/.mvn"
  if command -v curl >/dev/null 2>&1; then
    curl -fsSL "https://archive.apache.org/dist/maven/maven-3/$MVN_VERSION/binaries/apache-maven-$MVN_VERSION-bin.tar.gz" -o "$ARCHIVE"
  elif command -v wget >/dev/null 2>&1; then
    wget -q "https://archive.apache.org/dist/maven/maven-3/$MVN_VERSION/binaries/apache-maven-$MVN_VERSION-bin.tar.gz" -O "$ARCHIVE"
  else
    printf '%s\n' "Maven is not installed and neither curl nor wget is available." >&2
    exit 1
  fi
  tar -xzf "$ARCHIVE" -C "$BASE_DIR/.mvn"
fi

exec "$MVN_BIN" "$@"
