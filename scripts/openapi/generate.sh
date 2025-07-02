#!/bin/bash

# Usage: ./generate.sh [run]

function usage() {
    echo "Usage: $(basename $0) run"
    echo "This script generates the sources for the following:
        - a Bitbucket Spring REST client
        - a Component Catalog Spring Boot REST server

        The generated sources are placed under the directory: ./generated-sources
    "
}

[ "$1" != "run" ] && usage && exit 1

# Change to the directory of the script, so that relative paths work
cd "$(dirname $0)"

which npm && which npx || { echo "ERROR: some required software packages are missing, take a look at the README.md for installation instructions"; exit 1; }

# Clean up previously generated sources
echo "[INFO] Cleaning up previously generated sources..."
rm -rf generated-sources

## Backend - Component Catalog
# Bitbucket REST client
echo "[INFO] Generating Bitbucket REST client from configuration: generator-configs/generator-bitbucket-client.yaml ..."
npx openapi-generator-cli generate -c generator-configs/generator-bitbucket-client.yaml > /dev/null

# Component Catalog REST API
echo "[INFO] Generating Component Catalog REST server from configuration: generator-configs/generator-componentcatalog-server.yaml ..."
npx openapi-generator-cli generate -c generator-configs/generator-componentcatalog-server.yaml > /dev/null
