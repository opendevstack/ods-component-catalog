#!/bin/bash

# This script updates the Bitbucket REST client sources
BB_CLIENT_SRC_FROM="./generated-sources/openapi-client-bitbucket/src/%s/java/com/boehringer/componentcatalog/client/bitbucket/v89"
BB_CLIENT_SRC_TO="../../src/%s/java/com/boehringer/componentcatalog/client/bitbucket/v89"

[ "$1" != "run" ] && echo "Usage: $(basename $0) run - Update Bitbucket REST client sources" && exit 1

# Change to the directory of the script, so that relative paths work
cd "$(dirname $0)"

echo "[INFO] Updating Bitbucket REST client sources..."

rm -irf $(printf "$BB_CLIENT_SRC_TO" "main") && echo "[INFO] Deleted $(printf "$BB_CLIENT_SRC_TO" "main")"
cp -r  $(printf "$BB_CLIENT_SRC_FROM" "main") $(dirname $(printf "$BB_CLIENT_SRC_TO" "main")) && echo "[INFO] Updated $(printf "$BB_CLIENT_SRC_TO" "main")"
