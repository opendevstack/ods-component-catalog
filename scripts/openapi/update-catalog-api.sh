#!/bin/bash
# This script updates the Component Catalog REST API sources
CC_API_SRC_FROM="./generated-sources/openapi-server-componentcatalog/src/%s/java/com/boehringer/componentcatalog/server"
CC_API_SRC_TO="../../src/%s/java/com/boehringer/componentcatalog/server"

# Change to the directory of the script, so that relative paths work
cd "$(dirname $0)"

[ "$1" != "run" ] && echo "Usage: $(basename $0) run - Update Component Catalog backend REST API sources" && exit 1

echo "[INFO] Updating Component Catalog backend REST API sources..."

rm -irf $(printf "$CC_API_SRC_TO/api" "main") && echo "[INFO] Deleted $(printf "$CC_API_SRC_TO/api" "main")"
rm -irf $(printf "$CC_API_SRC_TO/model" "main") && echo "[INFO] Deleted $(printf "$CC_API_SRC_TO/model" "main")"

cp -r  $(printf "$CC_API_SRC_FROM/api" "main") $(printf "$CC_API_SRC_TO" "main") && echo "[INFO] Updated $(printf "$CC_API_SRC_TO/api" "main")"
cp -r  $(printf "$CC_API_SRC_FROM/model" "main") $(printf "$CC_API_SRC_TO" "main") && echo "[INFO] Updated $(printf "$CC_API_SRC_TO/model" "main")"
